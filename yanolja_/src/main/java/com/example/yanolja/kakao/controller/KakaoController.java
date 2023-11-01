package com.example.yanolja.kakao.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.yanolja.kakao.model.KakaoService;
import com.example.yanolja.kakao.oauth2.User;
import com.example.yanolja.kakao.vo.KakaoApproveResponse;
import com.example.yanolja.kakao.vo.KakaoCancelResponse;
import com.example.yanolja.kakao.vo.KakaoVo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@RestController 의 경우 @ResponseBody 태그를 포함하고 있다고 보면 된다 (따흑
@Controller
public class KakaoController {

	@Autowired
	KakaoService kakaoService;

	@GetMapping("/kakao/callback")
	@ResponseBody
	public ResponseEntity<String> callback(HttpServletRequest request, HttpSession session) throws Exception {

		try {
			// KakaoService를 사용하여 'code' 파라미터로부터 KakaoVo 정보를 가져옵니다.
			KakaoVo kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));

			// 추출한 정보를 기반으로 회원가입 또는 로그인 처리
			if (kakaoInfo != null) {
				User existingUser = kakaoService.findByEmail(kakaoInfo.getEmail());
				if (existingUser == null) {
					// 사용자가 등록되어 있지 않다면 회원가입 수행
					User newUser = new User();
					newUser.setEmail(kakaoInfo.getEmail());
					// 다른 필요한 정보도 설정
					kakaoService.insertUser(newUser);
				} else {
					// 이미 등록된 사용자라면 로그인 처리
					// Spring Security를 사용한다면, SecurityContext에 사용자 정보 저장

					int userid = kakaoService.findUserid(kakaoInfo.getEmail()); // userid 조회

					Authentication authentication = new UsernamePasswordAuthenticationToken(existingUser, null);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					session.setAttribute("username", existingUser.getName());
					session.setAttribute("userid", userid);
					// 예: SecurityContextHolder.getContext().setAuthentication(...);
				}
			}
			// 성공적인 응답을 반환
			return ResponseEntity.ok("Successfully processed the callback.");
		} catch (Exception e) {
			// 처리 중 오류가 발생한 경우 예외 처리
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the callback.");
		}
	}

	@GetMapping("/kakao/kakaologin")
	public String kakaologin() {
		return "User/Kakao";
	}
//카카오페이 ----------------------------------------------------------------------------------------------------
	/**
	 * 결제요청
	 */
	@PostMapping("/kakaoPay")
	public String readyToKakaoPay(@RequestParam("roomdata") String roomdata) {

		try {

			// JSON 문자열을 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(roomdata);
			System.out.println(jsonNode);
			// 필요한 값을 추출
			String hotelname = jsonNode.get("hotelname").asText();
			String roomname = jsonNode.get("roomname").asText();
			String roomid = jsonNode.get("roomid").asText();
			String price = jsonNode.get("price").asText();
			String username = jsonNode.get("username").asText();
			String userPhone = jsonNode.get("userPhone").asText();

			//DB 저장하는 코드 추가해야함
			int point = Integer.parseInt(price) / 200;

			System.out.println("point : " + point);
			// 여기에 kakaoPayService.kakaoPayReady 호출 코드 추가
			System.out.println();
			return "redirect:" + kakaoService.kakaoPayReady(hotelname, roomname, roomid, price, username, userPhone);
		} catch (Exception e) {
			e.printStackTrace();
			// 예외 처리 필요
		}
		return "/";
		// +kakaoPayService.kakaoPayReady(hotelname, roomname, roomid, price, username);
	}

	/**
	 * 결제 성공
	 */
	@GetMapping("/kakaoPaySuccess")
	public void afterPayRequest(@RequestParam("pg_token") String pgToken, HttpServletResponse response,
			HttpSession session) {

		KakaoApproveResponse kakaoApprove = kakaoService.ApproveResponse(pgToken);
		System.out.println("kakaoPaySuccess pg_token : " + pgToken);
		// 예약 테이블에 토큰값과 호텔아이디 룸아이디 유저아이디 체크인 체크아웃 날짜 저장하는 코드 추가
		String sc = "<script>" + "alert('결제가 완료되었습니다');\r\n" + "function Cwindow(){\r\n"
				+ "window.opener.displayPaymentSuccessMessage();\r\n" + "};\r\n" + "Cwindow() \r\n"
				+ "window.close();\r\n" + "</script>";
		response.setContentType("text/html");
		try {
			response.getWriter().write(sc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 결제 진행 중 취소
	 */
	@GetMapping("/cancel")
	public void cancel(HttpServletResponse response) { // 취소시 자동으로 창 닫기
		// JavaScript를 사용하여 현재 창을 닫음
		String script = "<script>  alert('결제가 취소되었습니다'); window.close();</script>";
		response.setContentType("text/html");
		try {
			response.getWriter().write(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 결제 실패
	 */
	@GetMapping("/fail")
	public void fail() {

	}

	/**
	 * 환불
	 */
	@PostMapping("/refund")
	public ResponseEntity refund() {
		KakaoCancelResponse kakaoCancelResponse = kakaoService.kakaoCancel();
		return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
	}

}
