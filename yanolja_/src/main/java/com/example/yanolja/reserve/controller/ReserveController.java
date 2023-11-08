package com.example.yanolja.reserve.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.yanolja.grobal.Response.ReserveResponse;
import com.example.yanolja.grobal.Response.RoomResponse;
import com.example.yanolja.grobal.model.grobalService;
import com.example.yanolja.reserve.model.ReserveService;
import com.example.yanolja.reserve.post.BookResponse;
import com.example.yanolja.reserve.post.Cartinfo;
import com.example.yanolja.reserve.post.CouponResponse;
import com.example.yanolja.reserve.post.ReservedInfo;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReserveController {
	@Autowired
	ReserveService reserveService;

	@Autowired
	grobalService grobalService;

	// reserveController예약 기능
	// --------------------------------------------------------------------------------------
	// 예약 페이지
	@GetMapping("/Reserve")
	public String reserve(@RequestParam(value = "roomid", required = false) Integer roomid,
			@RequestParam(value = "roomids", required = false) List<Integer> roomids, Model model,
			HttpSession session) {

		// 카트 룸 정보를 검색하는 공통 메소드
		List<Cartinfo> cartRoomInfoList = getCartRoomInfoList(session);

		if (roomids != null && cartRoomInfoList != null) {
			deleteFromCart(roomids, cartRoomInfoList, session); // 카트에서 삭제
		}

		if (cartRoomInfoList != null && cartRoomInfoList.size() != 0 && roomid == null) {
			// RequestParam으로 받아온 roomids 이외의 값들을 삭제합니다
			List<Cartinfo> updatedCartRoomInfos = cartRoomInfoList.stream()
					.filter(cartInfo -> roomids.contains(cartInfo.getRoomid())).collect(Collectors.toList());

			List<RoomResponse> combinedList = combineCartInfoWithRoomResponse(updatedCartRoomInfos);

			// 카카오 결제에서 사용하기 위한 세션
			session.setAttribute("combinedList", combinedList);

			// combinedList를 hotelname으로 정렬
			combinedList.sort(Comparator.comparing(RoomResponse::getHotelname));

			model.addAttribute("room2", combinedList);

		} else if (roomid != null) {
			// 단일 예약
			RoomResponse roomdetail = reserveService.cartlist2(roomid);

			// 카카오 결제에서 사용하기 위한 세션
			session.setAttribute("roomdetail", roomdetail);

			model.addAttribute("room", roomdetail);
		}
		// 이름 정보 가져와서
		Object uname = session.getAttribute("username");

		if (uname != null) {
			// 있다면 휴대폰 번호 찾기
			String phone = reserveService.findUPhone(uname.toString());
			// 유저아이디 값 세션에서 가져오기
			int userid = (int) session.getAttribute("userid");
			// 쿠폰 정보도 가져오기
			List<CouponResponse> coupon = reserveService.selectcoupon(userid);

			model.addAttribute("coupon", coupon);

			if (phone != null) {

				model.addAttribute("phone", phone);
			}
		}

		return "Search/Reserve/Reserve";
	}

	// 결제 동의
	@PostMapping("/Reserve_Agree")
	@ResponseBody
	public List<String> ReserveAgree(HttpSession session,
			@RequestParam(value = "priceArray", required = false) String[] priceArray,
			@RequestParam(value = "StartDateArray", required = false) String[] StartDateArray,
			@RequestParam(value = "EndDateArray", required = false) String[] EndDateArray) {

		// 결제 정보 가져오기
		Integer userid = (Integer) session.getAttribute("userid");
		String user_name = (String) session.getAttribute("partner_user_id");
		String user_phone = (String) session.getAttribute("userPhone");
		String price = (String) session.getAttribute("price");
		String order_number = (String) session.getAttribute("partner_order_id");
		String sessionDate1 = (String) session.getAttribute("sessionDate1");
		String sessionDate2 = (String) session.getAttribute("sessionDate2");
		String kakaoTid = (String) session.getAttribute("kakaoTid");

		String formattedDate1 = grobalService.formatDates(sessionDate1);
		String formattedDate2 = grobalService.formatDates(sessionDate2);

		@SuppressWarnings("unchecked")
		List<RoomResponse> combinedList = (List<RoomResponse>) session.getAttribute("combinedList");

		List<String> on_list = new ArrayList<>();

		if (combinedList != null) {
			List<Map<String, Object>> parameterList = new ArrayList<>();

			int i = 1;
			int priceIndex = 0; // priceArray 요소의 인덱스

			for (RoomResponse roomResponse : combinedList) {

				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("hotelId", roomResponse.getHotelid());
				dataMap.put("roomid", roomResponse.getRoomid());

				// 주문번호 넣기
				dataMap.put("order_number", order_number);
				on_list.add(order_number);

				order_number = order_number + Integer.toString(i);
				i++;

				// 가격 할당
				if (priceIndex < priceArray.length) {
					dataMap.put("price", priceArray[priceIndex]);
					dataMap.put("date1", StartDateArray[priceIndex].replaceAll(" ", ""));
					dataMap.put("date2", EndDateArray[priceIndex].replaceAll(" ", ""));
					priceIndex++;
				}

				parameterList.add(dataMap);
			}

			System.out.println(parameterList);

			reserveService.insertReserve(parameterList, user_name, user_phone, kakaoTid);

			if (userid != null) {
				reserveService.insertBookList(userid, parameterList, order_number);
			}

			for (int a = 0; a < StartDateArray.length; a++) {
				List<String> DList = reserveService.DateList(StartDateArray[a], EndDateArray[a]);
				Map<String, Object> parameters = parameterList.get(a); // 현재 맵을 가져옴
				int roomid = (int) parameters.get("roomid"); // "roomid" 키에 해당하는 값을 가져옴

				for (String date : DList) {
					System.out.println("roomid: " + roomid); // roomid 값 출력
					System.out.println(date);
					reserveService.insertDatebyReservationOne(roomid, date);
				}

			}

			reserveService.DeleteSession(session);

			return on_list;

		} else {
			List<String> DList = reserveService.DateList(sessionDate1, sessionDate2);
			RoomResponse roomdetail = (RoomResponse) session.getAttribute("roomdetail");
			String hotelName = roomdetail.getHotelname();
			String roomName = roomdetail.getRoomname();

			Map<String, Integer> Ids = reserveService.findHRids(roomName, hotelName);
			int hotelid = Ids.get("hotelid");
			int roomid = Ids.get("roomid");

			reserveService.insertReserveOne(hotelid, roomid, formattedDate1, formattedDate2, user_name, user_phone,
					order_number, price, kakaoTid);

			for (String date : DList) {
				reserveService.insertDatebyReservationOne(roomid, date);
			}

			if (userid != null) {
				reserveService.insertBook(userid, roomid, formattedDate1);
			}
			on_list.add(order_number);
			reserveService.DeleteSession(session);
			return on_list;
		}

	}

	// 회원 예약 내역 조회 페이지
	@GetMapping("Reserve_history")
	public String ReserveHistory(Model model,
			@RequestParam(value = "ordernumber", required = false) List<String> ordernumber) {
		// book 테이블에서 userid = #{userid} 인 정보 조회
		if (ordernumber != null) {
			List<ReserveResponse> reserve = reserveService.selectReserve_order(ordernumber);
			model.addAttribute("reserve", reserve);
			model.addAttribute("ordernumber", ordernumber);
			return "Search/Reserve/Reserve_By_OrderNumber";
		} else {
			// 모델에 목록 담아서 보여주는 코드 추가
			return "Search/Reserve/Reserve_history";
		}
	}

	// 예약 내역 보는 페이지
	@GetMapping("Reserve_List")
	public String postReserveHistory(HttpSession session, Model model,
			@RequestParam(value = "period", required = false) int period) {

		// book 테이블에서 userid = #{userid} 인 정보 조회 3 /6 /12 /24 개월 까지 가능 개월수 받아오기
		Integer userid = (Integer) session.getAttribute("userid");
		if (userid != null) {
			List<BookResponse> Book = reserveService.selectBook(userid, period);
			model.addAttribute("Book", Book);
		}
		return "Search/Reserve/Reserve_List";
	}

	// 비회원 예약 내역 조회 반환
	@GetMapping("Reserve_history_NotUser")
	public String ReserveHistoryN(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "order_number", required = true) String order_number, Model model) {
		List<ReserveResponse> pesonal_reserve = reserveService.select_p_Reserve(name, phone, order_number);
		if (pesonal_reserve == null) {
			System.out.println("값이 없습니다");
		} else {
			model.addAttribute("pesonal_reserve", pesonal_reserve);
		}
		// 주문번호 이름 전화번호로 일치하는 정보 찾아서 반환
		return "Search/Reserve/Reserve_history_NotUser";
	}

	// 예약 취소 기능
	// 환불 및 /book 테이블에서 삭제(user)인 경우에만/ ordernumber = 0 으로 변경
	@PostMapping("/Reserve_Info")
	@ResponseBody
	public ReservedInfo ReserveCancel(@RequestParam(value = "bookid", required = false) String bookid,
			@RequestParam(value = "order_number", required = false) String order_number) {

		ReservedInfo reserve_info;

		if (bookid != null) {
			reserve_info = reserveService.SelectReserveByBookid(bookid);
			System.out.println(reserve_info);
		} else {
			reserve_info = reserveService.SelectReserveByOrder_number(order_number);
			System.out.println(reserve_info);
		}

		System.out.println(reserve_info);
		// 해당 정보에 대한 reserve 정보들 가져와서 반환 -> modal창으로 띄움? 아니면 데이터 전달 //받아온 정보와 일치하는 정보
		// modal에 넘겨주기
		return reserve_info;
	}

	// reserveController장바구니 기능
	// --------------------------------------------------------------------------------------
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
		List<Cartinfo> cartRoomInfoList = getCartRoomInfoList(session);

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
	@PostMapping("/removeFromCart")
	@ResponseBody
	public String removeFromCart(@RequestParam("roomid[]") List<Integer> roomids, HttpSession session) {
		// 세션에서 기존에 저장된 roomid 리스트를 가져옵니다.

		List<Cartinfo> cartRoomids = getCartRoomInfoList(session);

		// 만약 세션에 저장된 리스트가 없다면 아무 작업을 하지 않고 반환합니다.
		if (cartRoomids == null) {
			return "카트에 담긴 상품이 없습니다!";
		}

		// 삭제를 실행하는 메소드
		deleteFromCart(roomids, cartRoomids, session);

		// 원하는 처리를 수행.
		return "삭제가 완료되었습니다";
	}

	// 메소드
	// ---------------------------------------------------------------------------------------------------

	// 카트 룸 정보를 검색하는 공통 메소드
	@SuppressWarnings("unchecked")
	private List<Cartinfo> getCartRoomInfoList(HttpSession session) {
		return (List<Cartinfo>) session.getAttribute("cartRoomInfoList");
	}

	// Cartinfo를 RoomResponse와 결합하는 공통 메소드
	private List<RoomResponse> combineCartInfoWithRoomResponse(List<Cartinfo> cartRoomInfoList) {
		List<Integer> roomIds = cartRoomInfoList.stream().map(Cartinfo::getRoomid).collect(Collectors.toList());
		List<RoomResponse> roomResponses = reserveService.cartlist(roomIds);
		Map<Integer, RoomResponse> roomResponseMap = roomResponses.stream()
				.collect(Collectors.toMap(RoomResponse::getRoomid, roomResponse -> roomResponse));

		List<RoomResponse> combinedList = new ArrayList<>();

		for (Cartinfo cartInfo : cartRoomInfoList) {
			RoomResponse roomResponse = roomResponseMap.get(cartInfo.getRoomid());
			if (roomResponse != null) {
				roomResponse.setDate1(cartInfo.getDate1());
				roomResponse.setDate2(cartInfo.getDate2());
				combinedList.add(roomResponse);
			}
		}
		return combinedList;
	}

	// 카트에서 특정 방 정보들을 삭제하는 메소드
	private void deleteFromCart(List<Integer> roomids, List<Cartinfo> cartRoomids, HttpSession session) {

		// roomids 리스트에 있는 각 roomid를 cartRoomids 리스트에서 검색하여 삭제합니다
		List<Cartinfo> updatedCartRoomInfos = new ArrayList<>(cartRoomids);
		// 아이디 값 검색해서 삭제
		for (int roomid : roomids) {
			updatedCartRoomInfos.removeIf(cartInfo -> cartInfo.getRoomid() == roomid);
		}
		// 리스트를 다시 세션에 저장합니다.
		session.setAttribute("cartRoomInfoList", updatedCartRoomInfos);
	}
}
