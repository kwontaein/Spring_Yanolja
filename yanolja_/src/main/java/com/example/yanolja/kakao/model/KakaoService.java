package com.example.yanolja.kakao.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import org.springframework.web.client.RestTemplate;

import com.example.yanolja.kakao.mapper.KakaoMapper;
import com.example.yanolja.kakao.oauth2.User;
import com.example.yanolja.kakao.vo.KakaoVo;

import jakarta.servlet.http.HttpSession;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Service
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

}