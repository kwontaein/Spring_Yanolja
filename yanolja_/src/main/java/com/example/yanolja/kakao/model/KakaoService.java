package com.example.yanolja.kakao.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.yanolja.kakao.mapper.KakaoMapper;
import com.example.yanolja.kakao.oauth2.User;
import com.example.yanolja.kakao.vo.KakaoApproveResponse;
import com.example.yanolja.kakao.vo.KakaoCancelResponse;
import com.example.yanolja.kakao.vo.KakaoResponse;
import com.example.yanolja.kakao.vo.KakaoVo;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoService {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	KakaoMapper kakaoMapper;
	// application.properties 또는 application.yml에서 설정된 카카오 API 정보를 주입받습니다.
	@Value("${kakao.client.id}")
	private String KAKAO_CLIENT_ID;

	@Value("${kakao.client.secret}")
	private String KAKAO_CLIENT_SECRET;

	@Value("${kakao.redirect.url}")
	private String KAKAO_REDIRECT_URL;

	static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
	static final String admin_Key = "admin_code"; // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
	private KakaoResponse kakaoReady;
	private KakaoApproveResponse kakaoApproveResponse;

	@Autowired
	HttpSession httpSession;

	// 카카오 API의 인증 및 사용할 URL
	private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
	private final static String KAKAO_API_URI = "https://kapi.kakao.com";

	// 카카오 로그인 URL 생성 메서드
	public String getKakaoLogin() {
		// 카카오 인증 URL을 생성하여 반환합니다.
		return KAKAO_AUTH_URI + "/oauth/authorize" + "?client_id=" + KAKAO_CLIENT_ID + "&redirect_uri="
				+ KAKAO_REDIRECT_URL + "&response_type=code";
	}

	// 카카오로부터 받은 코드로부터 사용자 정보를 가져오는 메서드
	public KakaoVo getKakaoInfo(String code) throws Exception {
		// 코드가 없는 경우 예외 처리
		if (code == null)
			throw new Exception("Failed to get authorization code");
		String accessToken = "";
		String refreshToken = "";

		try {
			// HTTP 요청 헤더 설정
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded");

			// HTTP 요청 파라미터 설정
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", KAKAO_CLIENT_ID);
			params.add("client_secret", KAKAO_CLIENT_SECRET);
			params.add("code", code);
			params.add("redirect_uri", KAKAO_REDIRECT_URL);

			// REST 요청을 보내는데 사용할 RestTemplate 생성
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

			// 카카오에 토큰 요청을 보내고 응답을 받습니다.
			ResponseEntity<String> response = restTemplate.exchange(KAKAO_AUTH_URI + "/oauth/token", HttpMethod.POST,
					httpEntity, String.class);

			// 응답 데이터 파싱
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

			accessToken = (String) jsonObj.get("access_token");
			refreshToken = (String) jsonObj.get("refresh_token");
			System.out.println("토큰 받아서 전달");
		} catch (Exception e) {
			throw new Exception("API call failed");
		}

		// Access Token을 사용하여 사용자 정보를 가져옵니다.
		return getUserInfoWithToken(accessToken);
	}

	// Access Token을 사용하여 사용자 정보를 가져오는 메서드
	private KakaoVo getUserInfoWithToken(String accessToken) throws Exception {
		// HTTP 요청 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// REST 요청을 보내는데 사용할 RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

		// 카카오 API를 통해 사용자 정보 요청을 보내고 응답을 받습니다.
		ResponseEntity<String> response = rt.exchange(KAKAO_API_URI + "/v2/user/me", HttpMethod.POST, httpEntity,
				String.class);

		// 응답 데이터 파싱
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());
		JSONObject account = (JSONObject) jsonObj.get("kakao_account");
		JSONObject profile = (JSONObject) account.get("profile");

		// 필요한 사용자 정보 추출
		long id = (long) jsonObj.get("id");
		String email = String.valueOf(account.get("email"));
		String nickname = String.valueOf(profile.get("nickname"));
		System.out.println(nickname);
		httpSession.setAttribute("nickname", nickname);
		// KakaoDTO 객체를 빌드하여 반환
		return KakaoVo.builder().id(id).email(email).nickname(nickname).build();
	}

	// 카카오 회원가입
	public void joinUser(User user) {
		kakaoMapper.insertUser(user);
	}
	
	//이메일 중복체크
	public int emaildupcheck(String email) {
		// MyBatis를 사용하여 이메일 중복 확인
		User user = kakaoMapper.findByEmail(email);
		if (user != null) {
			return 1; // 이미 존재하는 이메일
		} else {
			return 0; // 존재하지 않는 이메일
		}
	}

	// 사용자 이메일로 조회하여 User 반환
	public User findByEmail(String email) {
		return kakaoMapper.findByEmail(email);
	}
	
	// 사용자 이메일로 조회하여 Userid 반환
	public int findUserid(String email) {
		return kakaoMapper.findUserid(email);
	}
	
//회원가입
	public void insertUser(User user) {
		// MyBatis를 사용하여 사용자 정보를 데이터베이스에 저장
		System.out.println("insertUser 실행");

		int randomPasswd = new Random().nextInt(1000000) + 1;

		user.setPassword(String.valueOf(randomPasswd));
		user.setPassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 암호화
		user.setCreatedate(Timestamp.valueOf(LocalDateTime.now())); // 생성일자 넣기
		if (user.getName() == null || user.getName().isEmpty()) {
			// 이름 부분 배열 정의
			String[] firstNameArray = { "경기도", "서울", "강원도", "경상도", "전라도" };
			String[] lastNameArray = { "불주먹", "물주먹", "흙주먹", "깍두기", "통" };

			// 이름 부분 랜덤 선택
			String randomFirstName = getRandomElement(firstNameArray);
			String randomLastName = getRandomElement(lastNameArray);

			// 1부터 20까지의 랜덤한 숫자 생성
			int randomNum = new Random().nextInt(20) + 1;

			// 최종 이름 생성
			String randomName = randomFirstName + " " + randomLastName + " " + randomNum;
			user.setName(randomName);
		}

		System.out.println("email : " + user.getEmail() + " Name : " + user.getName() + " Password : "
				+ user.getPassword() + " Phone : " + user.getPhone() + " Master : " + user.getMasteryn() + " create : "
				+ user.getCreatedate());
		kakaoMapper.insertUser(user);
	}

	// 배열에서 랜덤 요소 선택하는 메서드
	private String getRandomElement(String[] array) {
		Random random = new Random();
		int randomIndex = random.nextInt(array.length);
		return array[randomIndex];
	}

//카카오 페이 관련 ---------------------------------------------------------------------------------------------------
	public String kakaoPayReady(String hotelname, String roomname, String roomid, String price, String username,
			String userPhone) {

		Random random = new Random();
		// 5자리 난수 생성 (범위: 00001 ~ 99999)
		int min = 1; // 5자리 최소값
		int max = 99999; // 5자리 최대값
		int randomNum = random.nextInt(max - min + 1) + min;

		System.out.println("kakaoPayReady 실행");

		int price2 = Integer.parseInt(price);
		int p1 = (price2 * 10) / 110;
		int p2 = price2 - ((price2 * 10) / 110);
		String vat = Integer.toString(p1);
		String novat = Integer.toString(p2);
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyMMddHHmm"));

		httpSession.setAttribute("partner_user_id", username);
		httpSession.setAttribute("partner_order_id", formatedNow + roomid + randomNum);
		httpSession.setAttribute("price", price);
		httpSession.setAttribute("vat", vat);
		httpSession.setAttribute("userPhone", userPhone);

		// 카카오페이 요청 양식
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid); // 가맹점 코드
		parameters.add("partner_order_id", formatedNow + roomid + randomNum); // 주문번호 : 결제날짜 + 룸아이디
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

	//결제 완료 승인
	public KakaoApproveResponse ApproveResponse(String pgToken) {

		httpSession.setAttribute("kakaoTid", kakaoReady.getTid());
		// 카카오 요청
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", kakaoReady.getTid()); // 결제 고유 번호
		parameters.add("partner_order_id", (String) httpSession.getAttribute("partner_order_id")); // 주문번호
		parameters.add("partner_user_id", (String) httpSession.getAttribute("partner_user_id"));// 유저네임
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

	//결제 환불
	public KakaoCancelResponse kakaoCancel() {

		// 결제 최종 단계에서 취소인지, 그냥 취소인지 구분

		// -> 세션값 여부로 구분?
		System.out.println("환불중");
		String priceStr = (String) httpSession.getAttribute("price");

		int price = Integer.parseInt(priceStr);
		int p1 = (price * 10) / 110;
		int p2 = price - ((price * 10) / 110);
		String vat = Integer.toString(p1);
		String novat = Integer.toString(p2);

		// 카카오페이 요청
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", kakaoReady.getTid());
		parameters.add("cancel_amount", (String) httpSession.getAttribute("price"));
		parameters.add("cancel_tax_free_amount", novat);
		parameters.add("cancel_vat_amount", vat);

		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
		System.out.println(parameters);
		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();

		KakaoCancelResponse cancelResponse = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/cancel",
				requestEntity, KakaoCancelResponse.class);

		System.out.println("환불완");
		return cancelResponse;
	}
	
	//최종 단계 결제 환불
	public KakaoCancelResponse kakaoCancel2(String price2, String kakaoTid) {

		int price = Integer.parseInt(price2);
		int p1 = (price * 10) / 110;
		int p2 = price - ((price * 10) / 110);
		String vat = Integer.toString(p1);
		String novat = Integer.toString(p2);

		// 카카오페이 요청
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", kakaoTid);
		parameters.add("cancel_amount", price2);
		parameters.add("cancel_tax_free_amount", novat);
		parameters.add("cancel_vat_amount", vat);

		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();

		KakaoCancelResponse cancelResponse = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/cancel",
				requestEntity, KakaoCancelResponse.class);

		return cancelResponse;
	}

	//카카오 헤더
	private HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		String auth = "KakaoAK " + admin_Key;
		httpHeaders.set("Authorization", auth);
		httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		return httpHeaders;
	}

	public void updateReserve(String price, String kakaoTid, String ordernumber) {
		kakaoMapper.updateReserve(price, kakaoTid, ordernumber);
	}

	public void UpdateToCancel(String ordernumber) {
		kakaoMapper.UpdateToCancel(ordernumber);
	}

}
