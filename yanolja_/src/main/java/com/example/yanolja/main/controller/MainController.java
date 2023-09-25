package com.example.yanolja.main.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.yanolja.main.model.MainService;
import com.example.yanolja.main.post.FacilityResponse;
import com.example.yanolja.main.post.InfoResponse;
import com.example.yanolja.main.post.MainResponse;
import com.example.yanolja.main.post.PolicyResponse;
import com.example.yanolja.main.post.ReserveResponse;
import com.example.yanolja.main.post.ReviewResponse;
import com.example.yanolja.main.post.RoomResponse;
import com.example.yanolja.main.post.TrafficResponse;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080") // 허용할 도메인을 지정
public class MainController {

	@Autowired
	MainService mainService;

	String sessionDate1;

	String sessionDate2;

	// 테스트 호출
	@GetMapping("/test")
	public String test() {
		// 모든 호텔 목록을 조회하고 "post" 모델 속성에 추가
		return "Main/test"; // User/test 템플릿을 렌더링
	}

	// 테스트 호출
	@GetMapping("/test2")
	public String test2() {
		// 모든 호텔 목록을 조회하고 "post" 모델 속성에 추가
		return "User/test"; // User/test 템플릿을 렌더링
	}

	@GetMapping("/calendar")
	public String showCalendar(Model model, HttpSession session,
			@RequestParam(value = "selectedStartDate", required = false) String selectedStartDate,
			@RequestParam(value = "selectedEndDate", required = false) String selectedEndDate,
			@RequestParam(value = "hotelid", required = false) Integer hotelid,
			@RequestParam(value = "roomid", required = false) Integer roomid) {

		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrowDate = currentDate.plusDays(1);
		LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
		LocalDate lastDayOfOneYearLater = currentDate.plusYears(1).withDayOfMonth(currentDate.lengthOfMonth());

		if (hotelid != null) {
			List<ReserveResponse> rspossible = mainService.reserve_possible(hotelid);

			int roomcnt = mainService.roomcnt(hotelid);

			for (ReserveResponse response : rspossible) {
				int reserveCnt = response.getReserveCnt(); // ReserveResponse 객체에서 reserveCnt 값을 가져옵니다.
				LocalDateTime reserveDate = response.getReserveDate(); // ReserveResponse 객체에서 reserveDate 값을 가져옵니다.

				// ReserveResponse 객체를 생성하고 리스트에 추가
				ReserveResponse comparison = new ReserveResponse(reserveDate, reserveCnt, roomcnt);
				// 날짜별 예약가능 여부를 저장하는 리스트
				List<ReserveResponse> comparisonList = new ArrayList<>();
				comparisonList.add(comparison);
				model.addAttribute("comparisonList", comparisonList);
			}

		} else if (roomid != null) {
			// roomid가 전달된 경우

			// roomcnt 랑 reserve room 비교
		} else {
			// hotelid와 roomid 모두 전달되지 않은 경우
			// 처리할 코드 작성
		}

		// hotelid로 예약 날짜와 예약된 방 정보를 가져옴

		List<LocalDate> datesInRange = new ArrayList<>();

		LocalDate currentDatePointer = firstDayOfMonth;
		while (!currentDatePointer.isAfter(lastDayOfOneYearLater)) {
			datesInRange.add(currentDatePointer);
			currentDatePointer = currentDatePointer.plusDays(1);
		}

		sessionDate1 = updateSessionAttribute(session, "selectedStartDate", sessionDate1, selectedStartDate,
				currentDate);
		sessionDate2 = updateSessionAttribute(session, "selectedEndDate", sessionDate2, selectedEndDate, tomorrowDate);

		model.addAttribute("datesInRange", datesInRange);
		model.addAttribute("currentDate", currentDate);
		model.addAttribute("tomorrowDate", tomorrowDate);
		return "calendar/calendar";
	}

	private String updateSessionAttribute(HttpSession session, String attributeName, String sessionValue,
			String requestValue, LocalDate Date) {

		if (sessionValue != null && Objects.equals(sessionValue, requestValue) && requestValue != null) {
			session.setAttribute(attributeName, sessionValue);
		} else if (sessionValue != null && !Objects.equals(sessionValue, requestValue) && requestValue != null) {
			sessionValue = requestValue;
			session.setAttribute(attributeName, sessionValue);
		} else if (sessionValue != null && requestValue == null) {
			session.setAttribute(attributeName, sessionValue);
		} else if (sessionValue == null && requestValue == null) {
			requestValue = Date.toString(); // currentDate를 문자열로 변환해서 sessionDate1에 할당
			sessionValue = Date.toString(); // tomorrowDate를 문자열로 변환해서 sessionDate2에 할당
			session.setAttribute(attributeName, sessionValue);
		} else {
			sessionValue = requestValue;
			session.setAttribute(attributeName, sessionValue);
		}
		return sessionValue;
	}

	// ----------------------------------------------------
	// 메인페이지 호출
	@GetMapping("/")
	public String openMain() {
		if (sessionDate1 == null && sessionDate2 == null) {

			LocalDate nowDate = LocalDate.now();
			LocalDate tomorrowDate = nowDate.plusDays(1);

			sessionDate1 = nowDate.toString();
			sessionDate2 = tomorrowDate.toString();

			System.out.println(sessionDate1);
			System.out.println(sessionDate2);
		}
		return "Main/Main"; // Main/Main 템플릿을 렌더링
	}

	// 슬라이드 호출
	@GetMapping("/slide")
	public String slide() {
		return "Main/hotelslide"; // Main/Main 템플릿을 렌더링
	}

	// 메인페이지 호출
	@GetMapping("/ViewAll")
	public String ViewAll() {
		return "Main/SeeAllView"; // Main/Main 템플릿을 렌더링
	}

//--------------------------------------------------------------------------------
	@GetMapping("/Hotellist")
	public String Hotellist(@RequestParam("regionid") int regionid, @RequestParam("kindhotel") String kindhotel,
			Model model) {
		// MainResponse에 찾아오려는 값이 null이면 오류남
		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		List<MainResponse> post = mainService.findAllFrom(regionid, kindhotel);
		model.addAttribute("post", post);
		return "lists/Hotellist"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/Nonslidelist")
	public String Nonslidelist(@RequestParam("regionid") int regionid, @RequestParam("kindhotel") String kindhotel,
			Model model) {
		// MainResponse에 찾아오려는 값이 null이면 오류남
		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		List<MainResponse> post = mainService.findAllFrom(regionid, kindhotel);
		model.addAttribute("post", post);
		return "lists/Nonslidelist"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/ViewHotels")
	public String ViewHotels(@RequestParam("regionid") int regionid, @RequestParam("kindhotel") String kindhotel,
			Model model) {

		// MainResponse에 찾아오려는 값이 null이면 오류남
		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		List<MainResponse> post = mainService.findAllFrom(regionid, kindhotel);
		model.addAttribute("post", post);
		return "lists/ViewHotels"; // Main/Hotellist 템플릿을 렌더링
	}

// --------------------------------------------------------------------------------
	@GetMapping("/eventrolling")
	public String eventrolling() {
		return "Main/eventrolling"; // Main/ex 템플릿을렌더링
	}

//--------------------------------------------------------------------------------
	// 내용 보기 (호텔 상세정보)
	@GetMapping("/places/View.do")
	public String openPlaceView(@RequestParam final Long hotelid, Model model) {
		MainResponse post = mainService.findPostById(hotelid);
		model.addAttribute("post", post);
		return "places/Viewplace";
	}

	// 객실 목록 불러오기
	@GetMapping("/roomlist")
	public String roomlist(@RequestParam final int hotelid, Model model, HttpSession session) {
		// MainResponse에 찾아오려는 값이 null이면 오류남

		if (sessionDate1 == null && sessionDate2 == null) {

			LocalDate nowDate = LocalDate.now();
			LocalDate tomorrowDate = nowDate.plusDays(1);

			sessionDate1 = nowDate.toString();
			sessionDate2 = tomorrowDate.toString();
			System.out.println(sessionDate1);
			System.out.println(sessionDate2);
		}

		// 현재 날짜를 가져옵니다
		LocalDate currentDate = LocalDate.now();
		// 원하는 형식의 날짜로 포맷
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.(E)", Locale.KOREAN);
		String formattedDate = currentDate.format(formatter);
		// 내일 날짜 계산
		LocalDate tomorrowDate = currentDate.plusDays(1);
		String formattedTomorrowDate = tomorrowDate.format(formatter);

		model.addAttribute("currentDate", formattedDate);
		model.addAttribute("tomorrowDate", formattedTomorrowDate);

		if (sessionDate1 != null && sessionDate2 != null) { // 선택된 날짜 값이 있다면
			// 호텔아이디를 통해 해당 호텔이 가진 객실을 전부 불러옴 -> 가격순으로 보여주기 + 예약 가능 여부 따져서 스타일 변경
			List<RoomResponse> post = mainService.findRoomByDate(hotelid, sessionDate1);
			model.addAttribute("post", post);
		} else if (sessionDate1 == null && sessionDate2 == null) {
			System.out.println("날짜 값이 없습니다!");
		}

		return "places/infodetail/roomlist"; // Main/Hotellist 템플릿을 렌더링
	}

	// 안내/정책
	@GetMapping("/InfoPolicy")
	public String InfoPolicy(@RequestParam int hotelid, Model model) {
		List<InfoResponse> info = mainService.hotelinfo(hotelid);
		List<PolicyResponse> policy = mainService.hotelpolicy(hotelid);

		String intro = mainService.hotelintro(hotelid);
		intro = intro.replaceAll("\\n", "</div><div>");

		model.addAttribute("info", info);
		model.addAttribute("intro", intro);
		model.addAttribute("policy", policy);

		return "places/infodetail/policy";
	}

	@GetMapping("/facility")
	public String facility(@RequestParam int hotelid, Model model) {
		int cnt = mainService.roomcnt(hotelid);
		model.addAttribute("cnt", cnt);
		return "places/infodetail/facility";
	}

	@GetMapping("/Review")
	public String Review(@RequestParam(required = false) Integer hotelid,
			@RequestParam(required = false) Integer roomid, Model model) {

		if (hotelid != null) {
			List<ReviewResponse> review = mainService.review(hotelid);
			ReviewResponse review_detail = mainService.rating_detail(hotelid);
			model.addAttribute("review", review);
			model.addAttribute("review_detail", review_detail);
		} else if (roomid != null) {
			List<ReviewResponse> review = mainService.reviewroom(roomid);
			int cnt = mainService.reviewroomcnt(roomid);
			model.addAttribute("cnt", cnt);
			model.addAttribute("review", review);
		}
		return "places/infodetail/review";
	}

	// 위치/교통 불러오기
	@GetMapping("/places/roomView")
	public String roomView(@RequestParam int roomid, Model model) {

		if (sessionDate1 == null && sessionDate2 == null) {

			LocalDate nowDate = LocalDate.now();
			LocalDate tomorrowDate = nowDate.plusDays(1);

			sessionDate1 = nowDate.toString();
			sessionDate2 = tomorrowDate.toString();
		}

		RoomResponse roomdetail = mainService.findRoomDetail(roomid);
		FacilityResponse Fc = mainService.facility(roomid);
		LocalDate currentDate = LocalDate.now();
		// 원하는 형식의 날짜로 포맷
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREAN);

		String formattedDate = currentDate.format(formatter);
		String compare = formattedDate.toString();

		if (sessionDate1.equals(compare)) {
			model.addAttribute("compare", compare);
		}
		model.addAttribute("room", roomdetail);
		model.addAttribute("Fc", Fc);
		return "places/infodetail/Roomdetail";
	}

	// 위치/교통 불러오기
	@GetMapping("/locationtraffic")
	public String map(@RequestParam int hotelid, Model model) {
		// 호텔아이디를 통해 해당 호텔이 가진 객실을 전부 불러옴 -> 가격순으로 보여주기
		TrafficResponse post = mainService.hotelLocation(hotelid);
		// model.addAttribute("location", location);
		model.addAttribute("post", post);
		return "places/infodetail/locationtraffic";
	}

	@GetMapping("/Search")
	public String search() {
		return "Search/search";
	}

	@GetMapping("/Searchdetail")
	public String koreahotel(@RequestParam String category) {
		if(category.equals("국내숙소")) {
			return "Search/koreahotel";
		}else if(category.equals("레저/티켓")) {
			return "Search/ticket";
		}else if(category.equals("교통/항공")) {
			return "Search/traffic";
		}else if(category.equals("해외숙소")) {
			return "Search/outland";
		}else {
			return "Search/koreahotel";
		}
	}

	// 게시글 작성 페이지
	/*
	 * @GetMapping("/Main/write") public String openPostWrite(@RequestParam(value =
	 * "id", required = false) final Long hotelid, Model model) { // 수정 시 실행, 호텔의 상세
	 * 정보를 조회하고 "post" 모델 속성에 추가 if (hotelid != null) { MainResponse post =
	 * mainService.findPostById(hotelid); model.addAttribute("post", post); } return
	 * "Main/write"; // Main/write 템플릿을 렌더링 }
	 * 
	 * // 게시글 생성 (숙소 등록)
	 * 
	 * @PostMapping("/Main/write.do") public String savePost(final MainRequest
	 * params) { System.out.println("write실행"); // 숙소 정보를 저장하고 숙소 목록 페이지로 리다이렉션
	 * mainService.savePost(params); System.out.println(params); return
	 * "redirect:/Main/list.do"; }
	 * 
	 * // 수정하기 (등록글 수정)
	 * 
	 * @PostMapping("/Main/update.do") public String updatePost(final MainRequest
	 * params) { System.out.println("update실행"); // 숙소 정보를 수정하고 숙소 목록 페이지로 리다이렉션
	 * mainService.updatePost(params); return "redirect:/Main/list.do"; }
	 */

}
