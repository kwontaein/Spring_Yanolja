package com.example.yanolja.hotel.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.yanolja.grobal.Response.ImageResponse;
import com.example.yanolja.grobal.Response.MainResponse;
import com.example.yanolja.grobal.Response.ReserveResponse;
import com.example.yanolja.grobal.Response.ReviewResponse;
import com.example.yanolja.grobal.Response.RoomResponse;
import com.example.yanolja.grobal.model.grobalService;
import com.example.yanolja.hotel.model.HotelService;
import com.example.yanolja.hotel.post.FacilityResponse;
import com.example.yanolja.hotel.post.InfoResponse;
import com.example.yanolja.hotel.post.PolicyResponse;
import com.example.yanolja.hotel.post.TrafficResponse;
import com.example.yanolja.reserve.model.ReserveService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "http://localhost:8080") // 허용할 도메인을 지정
public class HotelController {

	@Autowired
	private final HotelService hotelService = null;

	@Autowired
	ReserveService reserveService;

	@Autowired
	grobalService grobalService;

	@Autowired
	HttpSession session;

	// 호텔컨트롤러 호텔 하위 세부 정보
	// --------------------------------------------------------------------------------
	// 내용 보기 (호텔 상세정보)
	@GetMapping("/places/View.do")
	public String openPlaceView(@RequestParam final Long hotelid, Model model) {
		MainResponse post = hotelService.findById(hotelid);
		String kindhotel = post.getKindhotel();
		String kind = "hotel";
		if (kindhotel.equals("호텔")) {
			kind = "hotel";
		} else if (kindhotel.equals("모텔")) {
			kind = "motel";
		} else if (kindhotel.equals("펜션")) {
			kind = "pension";
		} else if (kindhotel.equals("게스트하우스")) {
			kind = "guest";
		}
		session.setAttribute("resentViewHotelid", hotelid);
		session.setAttribute("resentViewKindHotel", kindhotel);// 최근 본 호텔아이디 세션에 저장해서 이 값이 있을 경우 보여줄 자료 출력
		session.setAttribute("rskindbykor", kind);

		model.addAttribute("post", post);
		return "places/Viewplace";
	}

	// 객실 목록 불러오기
	@GetMapping("/roomlist")
	public String roomlist(@RequestParam final int hotelid, Model model, HttpSession session) {

		List<String> sessionDates = grobalService.SetSessionDate(session);
		// MainResponse에 찾아오려는 값이 null이면 오류남
		String sessionDate1 = sessionDates.get(0);
		String sessionDate2 = sessionDates.get(1);

		String formatDate = grobalService.formatDates(sessionDate1);
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
			List<RoomResponse> post = hotelService.findRoomByDate(hotelid, formatDate);
			model.addAttribute("post", post);
		} else if (sessionDate1 == null && sessionDate2 == null) {
			System.out.println("날짜 값이 없습니다!");
		}

		return "places/infodetail/roomlist"; // Main/Hotellist 템플릿을 렌더링
	}

	// 안내/정책
	@GetMapping("/InfoPolicy")
	public String InfoPolicy(@RequestParam int hotelid, Model model) {
		List<InfoResponse> info = hotelService.hotelinfo(hotelid);
		List<PolicyResponse> policy = hotelService.hotelpolicy(hotelid);

		String intro = hotelService.hotelintro(hotelid);
		intro = intro.replaceAll("\\n", "</div><div>");

		model.addAttribute("info", info);
		model.addAttribute("intro", intro);
		model.addAttribute("policy", policy);

		return "places/infodetail/policy";
	}

	// 시설
	@GetMapping("/facility")
	public String facility(@RequestParam int hotelid, Model model) {
		int cnt = hotelService.roomcnt(hotelid);
		model.addAttribute("cnt", cnt);
		return "places/infodetail/facility";
	}

	// 후기 정보
	@GetMapping("/Review")
	public String Review(@RequestParam(required = false) Integer hotelid,
			@RequestParam(required = false) Integer roomid, @RequestParam(required = false) String roomname,
			@RequestParam(required = false) String orderby, @RequestParam(required = false) boolean onlyPhoto,
			Model model) {
		String rn;
		String ob;
		if (roomname == null)
			rn = "객실 전체";
		else
			rn = roomname;

		if (orderby == null)
			ob = "ratingdate desc";
		else
			ob = orderby;

		if (hotelid != null && roomid == null) {
			List<ReviewResponse> review = hotelService.review(hotelid, rn, ob, onlyPhoto); // 리뷰목록

			List<ImageResponse> images = hotelService.reviewAllPhotos(hotelid); // 사진

			ReviewResponse review_detail = hotelService.rating_detail(hotelid); // 평점

			List<String> roomnameList = hotelService.roomnameList(hotelid); // 새로운 리스트에 저장

			model.addAttribute("selectedroomname", rn).addAttribute("selectedorderby", ob)
					.addAttribute("review", review).addAttribute("review_detail", review_detail)
					.addAttribute("roomnameList", roomnameList).addAttribute("images", images);
		} else if (roomid != null) {
			List<ReviewResponse> reviewroom = hotelService.reviewroom(roomid, orderby, onlyPhoto); // 방 리뷰목록
			int cnt = hotelService.reviewroomcnt(roomid); // 방갯수
			model.addAttribute("cnt", cnt).addAttribute("review", reviewroom);
		}
		return "places/infodetail/review";
	}

	// 후기 무한스크롤 추가(아직 안함)
	@PostMapping("/Review.Do")
	@ResponseBody
	public Object ReviewDo(@RequestParam(required = false) String roomname, Model model) {
		Map<String, Object> reviews = new HashMap<String, Object>();
		return "success";
	}

	// 위치/교통
	@GetMapping("/locationtraffic")
	public String map(@RequestParam int hotelid, Model model) {
		// 호텔아이디를 통해 해당 호텔이 가진 객실을 전부 불러옴 -> 가격순으로 보여주기
		TrafficResponse post = hotelService.hotelLocation(hotelid);
		// model.addAttribute("location", location);
		model.addAttribute("post", post);
		return "places/infodetail/locationtraffic";
	}

	// 호텔컨트롤러 방 세부정보 불러오기
	// --------------------------------------------------------------------------------------
	@GetMapping("/places/roomView")
	public String roomView(@RequestParam int roomid, Model model) {

		List<String> sessionDates = grobalService.SetSessionDate(session);
		// MainResponse에 찾아오려는 값이 null이면 오류남
		String sessionDate1 = sessionDates.get(0);

		String formatDate1 = grobalService.formatDates(sessionDate1);

		RoomResponse roomdetail = hotelService.findRoomDetail(roomid, formatDate1);
		FacilityResponse Fc = hotelService.facility(roomid);
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

	// hotel 달력 호출
	// --------------------------------------------------------------------------------
	@GetMapping("/calendar")
	public String showCalendar(Model model,
			@RequestParam(value = "selectedStartDate", required = false) String selectedStartDate,
			@RequestParam(value = "selectedEndDate", required = false) String selectedEndDate,
			@RequestParam(value = "hotelid", required = false) Integer hotelid,
			@RequestParam(value = "roomid", required = false) Integer roomid) {

		String sessionDate1 = (String) session.getAttribute("sessionDate1");
		String sessionDate2 = (String) session.getAttribute("sessionDate2");
		LocalDate currentDate = LocalDate.now();
		LocalDate tomorrowDate = currentDate.plusDays(1);
		LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
		LocalDate lastDayOfOneYearLater = currentDate.plusYears(1).withDayOfMonth(currentDate.lengthOfMonth());

		// 달력에 날짜별 예약 가능 여부를 추가하는 부분
		if (hotelid != null) {
			List<ReserveResponse> rspossible = hotelService.reserve_possible(hotelid);

			int roomcnt = hotelService.roomcnt(hotelid);

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

		}
		// hotelid로 예약 날짜와 예약된 방 정보를 가져옴

		List<LocalDate> datesInRange = new ArrayList<>();

		LocalDate currentDatePointer = firstDayOfMonth;

		while (!currentDatePointer.isAfter(lastDayOfOneYearLater)) {
			datesInRange.add(currentDatePointer);
			currentDatePointer = currentDatePointer.plusDays(1);
		}

		sessionDate1 = hotelService.updateSessionAttribute("sessionDate1", sessionDate1, selectedStartDate,
				currentDate);
		sessionDate2 = hotelService.updateSessionAttribute("sessionDate2", sessionDate2, selectedEndDate, tomorrowDate);

		model.addAttribute("datesInRange", datesInRange);
		model.addAttribute("currentDate", currentDate);
		model.addAttribute("tomorrowDate", tomorrowDate);

		return "calendar/calendar";
	}
}
