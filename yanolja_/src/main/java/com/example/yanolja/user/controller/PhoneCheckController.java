package com.example.yanolja.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@RestController
public class PhoneCheckController {

	final DefaultMessageService messageService;

	// 생성자에서 메시지 서비스 초기화
	public PhoneCheckController() {
		// 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
		String api_key = "NCSHLHWZ1EZKOIUE";
		String api_secret = "WMZYZ9VEVVQUOKAJV0BKTDIB1XLZKUPA";
		// 메시지 서비스를 초기화합니다.
		this.messageService = NurigoApp.INSTANCE.initialize(api_key, api_secret, "https://api.coolsms.co.kr");
	}

	@RequestMapping("/phoneCheck")
	public Map<String, Object> sendOne(@RequestParam("phone") String phone) {
		Map<String, Object> responseMap = new HashMap<>(); // 응답 데이터를 저장할 Map 객체

		 // 랜덤한 인증번호 생성
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);

        // 발신번호 및 수신번호 설정
        Message message = new Message();
        message.setFrom("01025023964");
        message.setTo(phone);
        message.setText("인증번호 : " + checkNum + " 입니다.");

        // 인증번호를 포함한 메시지를 보내고 응답을 받습니다.
        SingleMessageSentResponse messageResponse = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(messageResponse);
        
        // 응답 데이터에 인증번호 추가
        responseMap.put("checkNum", checkNum);

        return responseMap;
	}

}
