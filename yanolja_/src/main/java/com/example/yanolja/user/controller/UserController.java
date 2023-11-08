package com.example.yanolja.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.yanolja.grobal.Response.ImageResponse;
import com.example.yanolja.grobal.Response.MainResponse;
import com.example.yanolja.grobal.Response.ReviewResponse;
import com.example.yanolja.kakao.model.KakaoService;
import com.example.yanolja.user.CustomUserDetails;
import com.example.yanolja.user.model.EmailService;
import com.example.yanolja.user.model.UserService;
import com.example.yanolja.user.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Value("${kakao.client.id}")
	private String KAKAO_CLIENT_ID;

	@Value("${kakao.logout_redirect.url}")
	private String KAKAO_LOGOUT_REDIRECT_URL;

	@Autowired
	UserService userService;
	@Autowired
	KakaoService kakaoService;

	@Autowired
	private EmailService mailService;

	private String emailForVerification; // email 값을 저장할 변수
	private String receivedPassword; // 비밀번호를 저장할 변수

//마이페이지 관련 --------------------------------------------------------------------------	
	@RequestMapping("/mypage")
	public String myPage() {
		return "User/Mypage";
	}

	// 내 리뷰목록
	@GetMapping("/myreview")
	public String Myreview(HttpSession session, Model model) {
		// username 세션에서 가져와서 검색
		// 내가 쓴 리뷰 목록 가져오는 코드 추가
		int userid = (int) session.getAttribute("userid");
		if (session.getAttribute("userid") != null) {
			List<ReviewResponse> review = userService.UserByreview(userid);
			List<ImageResponse> img = userService.reviewUserPhotos(userid);
			int room_cnt = userService.UserByCnt(userid);

			model.addAttribute("room_cnt", room_cnt);
			model.addAttribute("review", review);
			model.addAttribute("img", img);
		}
		return "User/UserOption/MyReview";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/";
	}

	@GetMapping("/Mybenefit")
	public String Mybenefit(HttpSession session) {
		session.invalidate();
		return "User/UserOption/Mybenefit";
	}

	// 로그인 방법 선택
	@GetMapping("/tologin")
	public String tologin(Model model) {
		model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
		return "User/tologin";
	}

//로그인----------------------------------------------------------------------------------------------------------
	// 로그인 폼
	@GetMapping("/login")
	public String login(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception) {
		System.out.println("로그인 페이지 실행");
		model.addAttribute("error", error);
		System.out.println(error);
		model.addAttribute("exception", exception);
		System.out.println(exception);
		return "User/login";
	}

	// 로그인 성공시 이동
	@GetMapping("/user_access")
	public String userAccess(@AuthenticationPrincipal CustomUserDetails userDetails, Model model, HttpSession session) {
		// 현재 로그인한 사용자의 세션 정보를 가져올 수 있음
		if (userDetails != null) {
			String username = userDetails.getUName();
			int userid = userDetails.getUserid();
			// 여기에서 필요한 세션 정보를 HttpSession에 추가합니다.
			session.setAttribute("username", username);
			session.setAttribute("userid", userid);
			System.out.println("username =" + username);
			System.out.println("userid =" + userid);
		}
		return "redirect:/";
	}

//회원가입 ----------------------------------------------------------------------------

	// 회원가입 폼
	@GetMapping("/register")
	public String registerForm() {
		return "User/register";
	}

	// 회원가입 진행
	// BindingResult는 검증 오류가 발생할 경우 오류 내용을 보관
	@PostMapping("/register.do")
	public String register(UserVo vo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		vo.setEmail(this.emailForVerification);
		vo.setPassword(receivedPassword);
		// 여기에서 다른 필요한 작업 수행
		userService.joinUser(vo);
		System.out.println(vo);
		return "redirect:/login";
	}

	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(@RequestParam String email) {
		System.out.println("이메일 인증 요청이 들어옴!");
		System.out.println("이메일 인증 이메일 : " + email);
		// email 값을 클래스 레벨 변수에 저장
		this.emailForVerification = email;

		return mailService.joinEmail(email);
	}

	// 아이디 중복 확인
	@RequestMapping("/emailCheck")
	// value값을 주지 않으면 하위 메소드에 getmapping,postmapping등의 어노테이션을 써서 표현 가능
	@ResponseBody // ajax 값을 jsp로 바로 보내기위해 사용
	public String emaildupcheck(@RequestParam("email") String email) throws Exception {
		String result = "N";
		// 기본값 n
		int flag = userService.emaildupcheck(email);
		System.out.println(email);
		// 이메일 검사
		if (flag == 1)
			result = "Y";
		// 아이디가 있을시 Y 없을시 N 으로 jsp view 에 값을 보냄
		return result;
	}

	// 회원가입 폼2
	@GetMapping("/register2")
	public String registerForm2() {
		return "User/register2";
	}

	// 회원가입 폼3
	@GetMapping("/register3")
	public String registerForm3(@RequestParam("password") String password) {
		this.receivedPassword = password;
		return "User/register3";
	}

	@GetMapping("/UserOption/Setting")
	public String setting(Model model) {
		model.addAttribute("KAKAO_CLIENT_ID", KAKAO_CLIENT_ID);
		model.addAttribute("KAKAO_LOGOUT_REDIRECT_URL", KAKAO_LOGOUT_REDIRECT_URL);
		return "User/UserOption/Setting";
	}

	// user--------------------------------------------------------------------------------
	// 찜
	@PostMapping("/Like_hotel.do")
	@ResponseBody
	public String likehotel(@RequestParam int hotelid, @RequestParam final int userid) {
		int cnt = userService.selectLike(hotelid, userid);

		if (cnt == 1) {
			// 있으면 삭제
			userService.deleteLike(hotelid, userid);
			return "찜 목록에서 삭제되었습니다.";
		} else {
			// DB에 없으면 추가
			userService.insertLike(hotelid, userid);
			return "찜 목록에 추가되었습니다.";
		}
	}

	// 찜목록
	@GetMapping("/Like_hotel")
	public String Getlikehotel(Model model, HttpSession session) {
		int userid = (int) session.getAttribute("userid");
		List<MainResponse> post = userService.Likehotels(userid);
		model.addAttribute("post", post);
		return "User/UserOption/Like_hotel";
	}

}
