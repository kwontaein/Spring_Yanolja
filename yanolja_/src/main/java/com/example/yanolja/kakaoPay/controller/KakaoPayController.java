package com.example.yanolja.kakaoPay.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.yanolja.kakaoPay.model.KakaoPayService;
import com.example.yanolja.kakaoPay.vo.KakaoApproveResponse;
import com.example.yanolja.kakaoPay.vo.KakaoCancelResponse;
import com.example.yanolja.main.post.RoomResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class KakaoPayController {

	@Autowired
	KakaoPayService kakaoPayService;

	@GetMapping("/kakaoPay")
	public void readyToKakaoPayGet() {
	}

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

			// 여기에 kakaoPayService.kakaoPayReady 호출 코드 추가
			return "redirect:" + kakaoPayService.kakaoPayReady(hotelname, roomname, roomid, price, username ,userPhone);
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
	public void afterPayRequest(@RequestParam("pg_token") String pgToken, HttpServletResponse response, HttpSession session) {

		KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);
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
		KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel();
		return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
	}
}