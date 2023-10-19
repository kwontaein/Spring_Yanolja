package com.example.yanolja.main.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.yanolja.main.model.MainService;
import com.example.yanolja.main.post.BookResponse;
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

// 테스트 호출--------------------------------------------------------------------------------
	@GetMapping("/test")
	public String test() {
		// 모든 호텔 목록을 조회하고 "post" 모델 속성에 추가
		return "Main/test"; // User/test 템플릿을 렌더링
	}

	// 테스트 호출
	@GetMapping("/test2")
	public String test2() {
		// 모든 호텔 목록을 조회하고 "post" 모델 속성에 추가
		return "test/test2"; // User/test 템플릿을 렌더링
	}

	// 테스트 호출
	@GetMapping("/test3")
	public String test3() {
		// 모든 호텔 목록을 조회하고 "post" 모델 속성에 추가
		return "test/test3"; // User/test 템플릿을 렌더링
	}

	@ResponseBody
	@PostMapping("/file-upload")
	public String testfileUpload(@RequestParam("article_file") MultipartFile[] multipartFiles) {
		String strResult = "{ \"result\":\"FAIL\" }";

		for (MultipartFile file : multipartFiles) {
			try {
				byte[] imageBytes = file.getBytes();
				String originalFileName = file.getOriginalFilename();
				// 이제 이미지 데이터를 데이터베이스에 저장하는 서비스 메서드를 호출
				System.out.println(originalFileName + " " + imageBytes);
				// mainService.saveImage(originalFileName, imageBytes);
				strResult = "{ \"result\":\"OK\" }";
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		return strResult;
	}

//카카오 페이 결제 페이지--------------------------------------------------------------------------------
	@GetMapping("/KakaoPayPage")
	public String kakaopay() {
		return "KakaoPay/KaKaoPay";
	}

//달력 호출 --------------------------------------------------------------------------------
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

// 메인페이지 호출--------------------------------------------------------------------------------
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

	// 전체보기 호출
	@GetMapping("/ViewAll")
	public String ViewAll() {
		return "Main/SeeAllView";
	}

//리스트 --------------------------------------------------------------------------------
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

// 스와이퍼 --------------------------------------------------------------------------------
	@GetMapping("/eventrolling")
	public String eventrolling() {
		return "Main/eventrolling"; // Main/ex 템플릿을렌더링
	}

	// 슬라이드 호출
	@GetMapping("/slide")
	public String slide() {
		return "Main/hotelslide"; // Main/Main 템플릿을 렌더링
	}

//--------------------------------------------------------------------------------
	// 내용 보기 (호텔 상세정보)
	@GetMapping("/places/View.do")
	public String openPlaceView(@RequestParam final Long hotelid, Model model, HttpSession session) {
		MainResponse post = mainService.findPostById(hotelid);
		session.setAttribute("resentViewHotelid", hotelid);// 최근 본 호텔아이디 세션에 저장해서 이 값이 있을 경우 보여줄 자료 출력
		model.addAttribute("post", post);
		return "places/Viewplace";
	}

// 호텔 하위 세부 정보 --------------------------------------------------------------------------------
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

	// 시설
	@GetMapping("/facility")
	public String facility(@RequestParam int hotelid, Model model) {
		int cnt = mainService.roomcnt(hotelid);
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
			ob = "ratingdate asc";
		else
			ob = orderby;
		if (hotelid != null && roomid == null) {
			List<ReviewResponse> review = mainService.review(hotelid, rn, orderby, onlyPhoto);
			List<String> roomnameList = mainService.roomnameList(hotelid); // 새로운 리스트에 저장
			ReviewResponse review_detail = mainService.rating_detail(hotelid);

			model.addAttribute("selectedroomname", rn).addAttribute("selectedorderby", ob)
					.addAttribute("review", review).addAttribute("review_detail", review_detail)
					.addAttribute("roomnameList", roomnameList);
		} else if (roomid != null) {
			List<ReviewResponse> reviewroom = mainService.reviewroom(roomid, orderby, onlyPhoto);
			int cnt = mainService.reviewroomcnt(roomid);
			model.addAttribute("cnt", cnt).addAttribute("review", reviewroom);
		}
		return "places/infodetail/review";
	}

	// 후기 무한스크롤 추가
	@PostMapping("/Review.Do")
	@ResponseBody
	public Object ReviewDo(@RequestParam(required = false) String roomname, Model model) {

		Map<String, Object> reviews = new HashMap<String, Object>();
		// reviews.put("a", );
		return "success";
	}

	// 위치/교통
	@GetMapping("/locationtraffic")
	public String map(@RequestParam int hotelid, Model model) {
		// 호텔아이디를 통해 해당 호텔이 가진 객실을 전부 불러옴 -> 가격순으로 보여주기
		TrafficResponse post = mainService.hotelLocation(hotelid);
		// model.addAttribute("location", location);
		model.addAttribute("post", post);
		return "places/infodetail/locationtraffic";
	}

// 방 세부정보 불러오기 --------------------------------------------------------------------------------------	
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

//검색 기능 --------------------------------------------------------------------------------------	
	@GetMapping("/Search")
	public String search() {
		return "Search/search";
	}

	// ajax 페이지 구분
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

//예약 기능 --------------------------------------------------------------------------------------	
	// 예약 페이지
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

	// 결제 동의
	@PostMapping("/Reserve_Agree")
	@ResponseBody
	public String ReserveAgree(HttpSession session) {
		Integer userid = (Integer) session.getAttribute("userid");
		String user_name = (String) session.getAttribute("partner_user_id");
		String user_phone = (String) session.getAttribute("userPhone");
		String order_number = (String) session.getAttribute("partner_order_id");

		List<RoomResponse> combinedList = (List<RoomResponse>) session.getAttribute("combinedList");

		if (combinedList != null) {
			List<Map<String, Object>> parameterList = new ArrayList<>();

			for (RoomResponse roomResponse : combinedList) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("hotelId", roomResponse.getHotelid());
				dataMap.put("roomid", roomResponse.getRoomid());

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
					e.printStackTrace();
				}
			}

			mainService.insertReserve(parameterList, user_name, user_phone, order_number);

			if (userid != null) {
				mainService.insertBookList(userid, parameterList, order_number);
			}
		} else {
			RoomResponse roomdetail = (RoomResponse) session.getAttribute("roomdetail");
			String hotelName = roomdetail.getHotelname();
			String roomName = roomdetail.getRoomname();

			Map<String, Integer> Ids = mainService.findHRids(roomName, hotelName);
			int hotelid = Ids.get("hotelid");
			int roomid = Ids.get("roomid");

			String formattedDate1 = formatDates(sessionDate1);
			String formattedDate2 = formatDates(sessionDate2);
			String bookdate = formattedDate1;

			mainService.insertReserveOne(hotelid, roomid, formattedDate1, formattedDate2, user_name, user_phone,
					order_number);

			if (userid != null) {
				mainService.insertBook(userid, roomid, bookdate);
			}
		}
		return order_number;
	}

	// 회원 예약 내역 조회 페이지
	@GetMapping("Reserve_history")
	public String ReserveHistory(Model model,
			@RequestParam(value = "ordernumber", required = false) String ordernumber) {
		// book 테이블에서 userid = #{userid} 인 정보 조회
		// System.out.println(ordernumber);
		if (ordernumber != null) {
			List<ReserveResponse> reserve = mainService.selectReserve_order(ordernumber);
			System.out.println(reserve.toString());
			model.addAttribute("reserve", reserve);
			model.addAttribute("ordernumber", ordernumber);
			return "Search/Reserve/Reserve_By_OrderNumber";
		} else {
			// 모델에 목록 담아서 보여주는 코드 추가
			return "Search/Reserve/Reserve_history";
		}
	}

	@GetMapping("Reserve_List")
	public String postReserveHistory(HttpSession session, Model model,
			@RequestParam(value = "period", required = false) int period) {

		// book 테이블에서 userid = #{userid} 인 정보 조회 3 /6 /12 /24 개월 까지 가능 개월수 받아오기
		Integer userid = (Integer) session.getAttribute("userid");
		if (userid != null) {
			List<BookResponse> Book = mainService.selectBook(userid, period);
			model.addAttribute("Book", Book);
		}
		return "Search/Reserve/Reserve_List";
	}

	// 비회원 예약 내역 조회 반환
	@GetMapping("Reserve_history_NotUser")
	public String ReserveHistoryN(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "order_number", required = true) String order_number, Model model) {
		List<ReserveResponse> pesonal_reserve = mainService.select_p_Reserve(name, phone, order_number);
		if (pesonal_reserve == null) {
			System.out.println("값이 없습니다");
		}
		model.addAttribute("pesonal_reserve", pesonal_reserve);
		// 주문번호 이름 전화번호로 일치하는 정보 찾아서 반환
		return "Search/Reserve/Reserve_history_NotUser";
	}

//장바구니 기능 --------------------------------------------------------------------------------------	
	// 장바구니 페이지
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

	// 장바구니 추가
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

	// 장바구니 삭제
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

//---------------------------------------------------------------------------------------------------------
	// 후기작성
	@GetMapping("/writeReview")
	public String writeReview(@RequestParam(value = "roomid") int roomid, @RequestParam(value = "bookid") int bookid,
			Model model) {
		// 유저아이디, 해당 아이디와 룸아이디로 이루어진 예약정보가 없거나, 후기 갯수 < 해당 정보 라면 잘못된 접근입니다. 라는 문구 작성
		// 호텔, 객실이름 및 사용자 이름 가져오기
		ReserveResponse loadRs = mainService.selectForReview(roomid);
		model.addAttribute("loadRs", loadRs);
		model.addAttribute("bookid", bookid);
		return "User/writeReview";
	}

	@PostMapping("/writeReview.do")
	@ResponseBody
	public String insertReview(@RequestParam(value = "rating1") double rating1,
			@RequestParam(value = "rating2") double rating2, @RequestParam(value = "rating3") double rating3,
			@RequestParam(value = "rating4") double rating4, @RequestParam(value = "textData") String textData,
			@RequestParam(value = "roomid") int roomid, @RequestParam(value = "bookid") int bookid,
			@RequestParam(value = "article_file", required = false) MultipartFile[] multipartFiles,
			HttpSession session) {

		// 호텔아이디 받아서 insert

		int userid = (int) session.getAttribute("userid");
		int hotelid = mainService.findHotelId(roomid);

		int CurrentReviewid = mainService.lastReviewid() + 1;
		System.out.println("CurrentReviewid : " + CurrentReviewid);
		for (MultipartFile file : multipartFiles) {
			try {
				byte[] imageBytes = file.getBytes();
				String originalFileName = file.getOriginalFilename();
				// 이제 이미지 데이터를 데이터베이스에 저장하는 서비스 메서드를 호출
				System.out.println(originalFileName + " " + imageBytes);
				mainService.saveImage(hotelid, CurrentReviewid, originalFileName, imageBytes);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		mainService.updateReivewYn(bookid);
		mainService.insertReview(hotelid, roomid, userid, rating1, rating2, rating3, rating4, textData);

		return "success";
	}

// 메소드 ---------------------------------------------------------------------------------------------------	
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

	// 날짜 형식 지정 메소드
	private String formatDates(String sessionDate) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy. MM. dd. (E)");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = inputFormat.parse(sessionDate);
			String formattedDate = outputFormat.format(date1);
			return formattedDate;
		} catch (ParseException e) {
			return sessionDate;
		}
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

//--------------------------------------------------------------------------------------------------------
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
