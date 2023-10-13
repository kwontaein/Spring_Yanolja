package com.example.yanolja.kakao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yanolja.kakao.model.KakaoService;
import com.example.yanolja.kakao.oauth2.User;
import com.example.yanolja.kakao.vo.KakaoVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/kakao")
public class KakaoController {

	@Autowired
	KakaoService kakaoService;

	@GetMapping("/callback")
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

	@GetMapping("/kakaologin")
	public String kakaologin() {
		return "User/Kakao";
	}

}
