package com.example.yanolja.kakaoPay.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

	@Autowired
	HttpSession session;

	public String kakaoPayReady(String hotelname, String roomname, String roomid, String price, String username) {

		int price2 = Integer.parseInt(price);
		System.out.println(price2);
		int p1 = (price2 * 10) / 110;
		int p2 = price2 - ((price2 * 10) / 110);
		String vat = Integer.toString(p1);
		String novat = Integer.toString(p2);

		String StartDate = (String) session.getAttribute("selectedStartDate");
		String dateString = StartDate.substring(4, 15); // "Oct 06 2023" 부분 추출

		String EndDate = (String) session.getAttribute("selectedStartDate");
		String dateString2 = EndDate.substring(4, 15); // "Oct 06 2023" 부분 추출

		String formattedDate = null;
		String formattedDate2 = null;

		try {
			SimpleDateFormat inputDateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
			Date date = inputDateFormat.parse(dateString);
			Date date2 = inputDateFormat.parse(dateString2);
			SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyMMdd");
			formattedDate = outputDateFormat.format(date);
			formattedDate2 = outputDateFormat.format(date2);
		} catch (Exception e) {
			e.printStackTrace();
			// 예외 처리 필요
		}

		// 카카오페이 요청 양식
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid); // 가맹점 코드
		parameters.add("partner_order_id", formattedDate + roomid); // 주문번호 : 체크인날짜 + 룸아이디
		parameters.add("partner_user_id", username); // 유저네임
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
		parameters.add("partner_order_id", "1111"); // 주문번호
		parameters.add("partner_user_id", "croffle");// 유저네임
		parameters.add("pg_token", pgToken);// 성공토큰

		System.out.println("KakaoApproveResponse 실행");
		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

		System.out.println("KakaoApproveResponse 실행2");
		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();
		try {
			KakaoApproveResponse approveResponse = restTemplate.postForObject(
					"https://kapi.kakao.com/v1/payment/approve", requestEntity, KakaoApproveResponse.class);
			System.out.println("KakaoApproveResponse 실행3");
			return approveResponse;
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 결제 환불
	 */
	public KakaoCancelResponse kakaoCancel() {
		// 카카오페이 요청
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", "환불할 결제 고유 번호");
		parameters.add("cancel_amount", "환불 금액");
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