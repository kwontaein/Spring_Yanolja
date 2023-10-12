package com.example.yanolja.main.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.yanolja.main.model.MainService;
import com.example.yanolja.main.post.Cartinfo;
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

	@GetMapping("/KakaoPayPage")
	public String kakaopay() {
		return "KakaoPay/KaKaoPay";
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

	// 날짜 세션 설정
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

		RoomResponse roomdetail = mainService.findRoomDetail(roomid, sessionDate1);
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

//---------------------------------------------------------------------------------------
	@GetMapping("/Search")
	public String search() {
		return "Search/search";
	}

	@GetMapping("/Searchdetail")
	public String koreahotel(@RequestParam String category) {
		if (category.equals("국내숙소")) {
			return "Search/koreahotel";
		} else if (category.equals("레저/티켓")) {
			return "Search/ticket";
		} else if (category.equals("교통/항공")) {
			return "Search/traffic";
		} else if (category.equals("해외숙소")) {
			return "Search/outland";
		} else {
			return "Search/koreahotel";
		}
	}

	@GetMapping("/Reserve")
	public String reserve(@RequestParam(value = "roomid", required = false) Integer roomid,
			@RequestParam(value = "roomids", required = false) List<Integer> roomids, Model model,
			HttpSession session) {
		List<Cartinfo> cartRoomInfoList = getCartRoomInfoList(session);

		if (cartRoomInfoList != null && cartRoomInfoList.size() != 0 && roomid == null) {
			// 받아온 roomids 이외의 값들을 삭제합니다
			List<Cartinfo> updatedCartRoomInfos = cartRoomInfoList.stream()
					.filter(cartInfo -> roomids.contains(cartInfo.getRoomid())).collect(Collectors.toList());

			List<RoomResponse> combinedList = combineCartInfoWithRoomResponse(updatedCartRoomInfos);
			// 카카오 결제에서 사용하기 위한 세션
			session.setAttribute("combinedList", combinedList);
			// combinedList를 hotelname으로 정렬
			combinedList.sort(Comparator.comparing(RoomResponse::getHotelname));
			model.addAttribute("room2", combinedList);
		} else if (roomid != null) {
			RoomResponse roomdetail = mainService.cartlist2(roomid);
			// 카카오 결제에서 사용하기 위한 세션
			session.setAttribute("roomdetail", roomdetail);
			model.addAttribute("room", roomdetail);
		}

		Object uname = session.getAttribute("username");
		if (uname != null) {
			String phone = mainService.findUPhone(uname.toString());
			if (phone != null) {
				model.addAttribute("phone", phone);
			}
		}
		return "Search/Reserve/Reserve";
	}

	@GetMapping("/cart")
	public String cart(HttpSession session, Model model) {
		List<Cartinfo> cartRoomInfoList = getCartRoomInfoList(session);

		if (cartRoomInfoList != null && cartRoomInfoList.size() != 0) {
			List<RoomResponse> combinedList = combineCartInfoWithRoomResponse(cartRoomInfoList);

			// combinedList를 hotelname으로 정렬
			combinedList.sort(Comparator.comparing(RoomResponse::getHotelname));
			model.addAttribute("Cartroom", combinedList);
		}

		return "User/cart";
	}

	// 카트 룸 정보를 검색하는 공통 메소드
	private List<Cartinfo> getCartRoomInfoList(HttpSession session) {
		return (List<Cartinfo>) session.getAttribute("cartRoomInfoList");
	}

	// Cartinfo를 RoomResponse와 결합하는 공통 메소드
	private List<RoomResponse> combineCartInfoWithRoomResponse(List<Cartinfo> cartRoomInfoList) {
		List<Integer> roomIds = cartRoomInfoList.stream().map(Cartinfo::getRoomid).collect(Collectors.toList());
		List<RoomResponse> Cartroom = mainService.cartlist(roomIds);
		List<RoomResponse> combinedList = new ArrayList<>();

		for (RoomResponse roomResponse : Cartroom) {
			for (Cartinfo cartInfo : cartRoomInfoList) {
				if (roomResponse.getRoomid() == cartInfo.getRoomid()) {
					RoomResponse combinedInfo = new RoomResponse();
					combinedInfo.setHotelname(roomResponse.getHotelname());
					combinedInfo.setHotelid(roomResponse.getHotelid());
					combinedInfo.setLoc(roomResponse.getLoc());
					combinedInfo.setRoomid(roomResponse.getRoomid());
					combinedInfo.setMaxManCnt(roomResponse.getMaxManCnt());
					combinedInfo.setRoomname(roomResponse.getRoomname());
					combinedInfo.setPrice(roomResponse.getPrice());
					combinedInfo.setDefaultmancnt(roomResponse.getDefaultmancnt());
					combinedInfo.setCheckIn(roomResponse.getCheckIn());
					combinedInfo.setCheckout(roomResponse.getCheckout());
					combinedInfo.setDate1(cartInfo.getDate1());
					combinedInfo.setDate2(cartInfo.getDate2());
					combinedInfo.setRentalType(roomResponse.getRentalType());
					combinedList.add(combinedInfo);
					break;
				}
			}
		}
		return combinedList;
	}

	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("roomid") int roomid, @RequestParam("StartDate") String Date1,
			@RequestParam("EndDate") String Date2, HttpSession session) {

		// 세션에서 기존에 저장된 RoomInfo 리스트를 가져옴.
		List<Cartinfo> cartRoomInfoList = (List<Cartinfo>) session.getAttribute("cartRoomInfoList");

		// 만약 세션에 저장된 리스트가 없다면 새로운 리스트를 생성.
		if (cartRoomInfoList == null) {
			cartRoomInfoList = new ArrayList<>();
		}

		// 중복 체크를 수행.
		boolean isDuplicate = false;
		for (Cartinfo roomInfo : cartRoomInfoList) {
			if (roomInfo.getRoomid() == (roomid)) {
				isDuplicate = true;
				break;
			}
		}

		// 클라이언트로부터 전달받은 roomid가 리스트에 없는 경우에만 추가.
		if (!isDuplicate) {
			Cartinfo roomInfo = new Cartinfo(roomid, Date1, Date2);
			roomInfo.setRoomid(roomid);
			roomInfo.setDate1(Date1);
			roomInfo.setDate2(Date2);
			cartRoomInfoList.add(roomInfo);
		}

		// 리스트를 다시 세션에 저장.
		session.setAttribute("cartRoomInfoList", cartRoomInfoList);

		return "redirect:/places/roomView?roomid=" + roomid;
	}

	@GetMapping("/removeFromCart")
	public String removeFromCart(@RequestParam("roomid[]") List<Integer> roomids, HttpSession session) {
		// 세션에서 기존에 저장된 roomid 리스트를 가져옵니다.

		List<Cartinfo> cartRoomids = (List<Cartinfo>) session.getAttribute("cartRoomInfoList");

		// 만약 세션에 저장된 리스트가 없다면 아무 작업을 하지 않고 반환합니다.
		if (cartRoomids == null) {
			return "redirect:cart"; // 또는 다른 페이지나 뷰로 리다이렉트
		}

		// roomids 리스트에 있는 각 roomid를 cartRoomids 리스트에서 검색하여 삭제합니다
		List<Cartinfo> updatedCartRoomInfos = new ArrayList<>(cartRoomids);
		for (int roomid : roomids) {
			updatedCartRoomInfos.removeIf(cartInfo -> cartInfo.getRoomid() == roomid);
		}
		// 리스트를 다시 세션에 저장합니다.
		session.setAttribute("cartRoomInfoList", updatedCartRoomInfos);

		// 원하는 처리를 수행한 후, 다른 페이지로 리다이렉트하거나 뷰를 반환할 수 있습니다.
		return "redirect:cart";
	}

	@GetMapping("/Reserve_Agree")
	public String ReserveAgree(HttpSession session) {
		String user_name = (String) session.getAttribute("partner_user_id");
		String user_phone = (String) session.getAttribute("userPhone");
		String order_number = (String) session.getAttribute("partner_order_id");

		List<RoomResponse> combinedList = (List<RoomResponse>) session.getAttribute("combinedList");
		if (combinedList != null) {
			// 필요한 값을 추출
			List<Integer> hotelIds = combinedList.stream().map(RoomResponse::getHotelid).collect(Collectors.toList());
			List<Integer> roomIds = combinedList.stream().map(RoomResponse::getRoomid).collect(Collectors.toList());
			List<String> date1List = combinedList.stream().map(RoomResponse::getDate1).collect(Collectors.toList());
			List<String> date2List = combinedList.stream().map(RoomResponse::getDate2).collect(Collectors.toList());

			List<Map<String, Object>> parameterList = new ArrayList<>();
			// combinedList를 기반으로 데이터를 추가
			for (RoomResponse roomResponse : combinedList) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("hotelId", roomResponse.getHotelid());
				dataMap.put("roomid", roomResponse.getRoomid());

				// 날짜 문자열 파싱
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy. MM. dd. (E)");
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

				try {
					Date date1 = inputFormat.parse(roomResponse.getDate1());
					Date date2 = inputFormat.parse(roomResponse.getDate2());

					String formattedDate1 = outputFormat.format(date1);
					String formattedDate2 = outputFormat.format(date2);

					dataMap.put("date1", formattedDate1);
					dataMap.put("date2", formattedDate2);
					parameterList.add(dataMap);
				} catch (ParseException e) {
					// 파싱 오류 처리
					e.printStackTrace();
				}
			}

			System.out.println(parameterList + "/" + user_name + "/" + user_phone + "/" + order_number);
			mainService.insertReserve(parameterList, user_name, user_phone, order_number);
		} else {
			RoomResponse roomdetail = (RoomResponse) session.getAttribute("roomdetail");
			System.out.println(roomdetail);
			String hotelName = roomdetail.getHotelname();
			String roomName = roomdetail.getRoomname();
			session.getAttribute(sessionDate1);
			session.getAttribute(sessionDate2);
			System.out.println(sessionDate1 + "/" + sessionDate2);

			Map<String, Integer> Ids = mainService.findHRids(roomName, hotelName);
			System.out.println(Ids);
			int hotelid = Ids.get("hotelid");
			int roomid = Ids.get("roomid");

			System.out.println(hotelName + "/" + roomName + "/" + sessionDate1 + "/" + sessionDate2 + "/" + user_name
					+ "/" + user_phone + "/" + order_number);
			mainService.insertReserveOne(hotelid, roomid, sessionDate1, sessionDate2, user_name, user_phone,
					order_number);

		}
		return "redirect:/";
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
