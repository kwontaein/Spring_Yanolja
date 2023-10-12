package com.example.yanolja.kakaoPay.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.yanolja.kakaoPay.vo.KakaoApproveResponse;
import com.example.yanolja.kakaoPay.vo.KakaoCancelResponse;
import com.example.yanolja.kakaoPay.vo.KakaoResponse;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

	static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
	static final String admin_Key = "f0dc4984df09287545cbdbc9478a53ee"; // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
	private KakaoResponse kakaoReady;
	private KakaoApproveResponse kakaoApproveResponse;
	@Autowired
	HttpSession session;

	public String kakaoPayReady(String hotelname, String roomname, String roomid, String price, String username, String userPhone) {

		System.out.println("kakaoPayReady 실행");
		int price2 = Integer.parseInt(price);
		int p1 = (price2 * 10) / 110;
		int p2 = price2 - ((price2 * 10) / 110);
		String vat = Integer.toString(p1);
		String novat = Integer.toString(p2);
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyMMddHHmm"));

		session.setAttribute("partner_user_id", username);
		session.setAttribute("partner_order_id", formatedNow + roomid);
		session.setAttribute("price", price);
		session.setAttribute("vat", vat);
		session.setAttribute("userPhone", userPhone);
		
		// 카카오페이 요청 양식
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid); // 가맹점 코드
		parameters.add("partner_order_id", formatedNow + roomid); // 주문번호 : 결제날짜 + 룸아이디
		parameters.add("partner_user_id", username); // 유저네임 username
		parameters.add("item_name", hotelname + ", " + roomname); // 호텔 이름 + (방번호)방이름
		parameters.add("quantity", "1"); // 상품 수량
		parameters.add("total_amount", price); // 상품 총액
		parameters.add("vat_amount", vat); // 상품 부가세 금액
		parameters.add("tax_free_amount", novat);// 상품 비과세 금액
		parameters.add("approval_url", "http://localhost:8080/kakaoPaySuccess"); // 성공 시 redirect url
		parameters.add("cancel_url", "http://localhost:8080/cancel"); // 취소 시 redirect url
		parameters.add("fail_url", "http://localhost:8080/fail"); // 실패 시 redirect url
		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(parameters, this.getHeaders());
		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();

		try {
			kakaoReady = restTemplate.postForObject(new URI("https://kapi.kakao.com/v1/payment/ready"), body,
					KakaoResponse.class);
			// 성공시 여기로 리다이렉트
			return kakaoReady.getNext_redirect_pc_url();
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/pay";
	}

	/**
	 * 결제 완료 승인
	 */
	public KakaoApproveResponse ApproveResponse(String pgToken) {

		// 카카오 요청
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", kakaoReady.getTid()); // 결제 고유 번호
		parameters.add("partner_order_id", (String) session.getAttribute("partner_order_id")); // 주문번호
		parameters.add("partner_user_id", (String) session.getAttribute("partner_user_id"));// 유저네임
		parameters.add("pg_token", pgToken);// 성공토큰
		System.out.println("KakaoApproveResponse 실행");
		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();
		try {
			KakaoApproveResponse approveResponse = restTemplate.postForObject(
					"https://kapi.kakao.com/v1/payment/approve", requestEntity, KakaoApproveResponse.class);
			return approveResponse;
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// 실패한 경우 kakaoCancel 메소드 호출
			KakaoCancelResponse cancelResponse = kakaoCancel();

			// 여기에서 필요한 실패 처리 로직을 추가할 수 있습니다.

			return null;
		}
	}

	/**
	 * 결제 환불
	 */
	public KakaoCancelResponse kakaoCancel() {
		// 카카오페이 요청
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", kakaoReady.getTid());
		parameters.add("cancel_amount", "");
		parameters.add("cancel_tax_free_amount", "환불 비과세 금액");
		parameters.add("cancel_vat_amount", "환불 부가세");

		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();

		KakaoCancelResponse cancelResponse = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/cancel",
				requestEntity, KakaoCancelResponse.class);

		return cancelResponse;
	}

	/**
	 * 카카오 요구 헤더값
	 */
	private HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		String auth = "KakaoAK " + admin_Key;
		httpHeaders.set("Authorization", auth);
		httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		return httpHeaders;
	}
}