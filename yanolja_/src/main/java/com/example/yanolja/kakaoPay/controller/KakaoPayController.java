package com.example.yanolja.kakaoPay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.yanolja.kakaoPay.model.KakaoPayService;
import com.example.yanolja.kakaoPay.vo.KakaoApproveResponse;
import com.example.yanolja.kakaoPay.vo.KakaoCancelResponse;
import com.example.yanolja.kakaoPay.vo.KakaoResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {

	@Autowired
	KakaoPayService kakaoPayService;

	/**
	 * 결제요청
	 */
	@PostMapping("/ready")
	public KakaoResponse readyToKakaoPay() {

		return kakaoPayService.kakaoPayReady();
	}

	/**
	 * 결제 성공
	 */
	@GetMapping("/success")
	public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {

		KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);

		return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
	}

	/**
	 * 결제 진행 중 취소
	 */
	@GetMapping("/cancel")
	public void cancel() {

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