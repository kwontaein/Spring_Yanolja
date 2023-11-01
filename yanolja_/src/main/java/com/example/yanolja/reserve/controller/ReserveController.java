package com.example.yanolja.reserve.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.yanolja.grobal.ReserveResponse;
import com.example.yanolja.grobal.RoomResponse;
import com.example.yanolja.reserve.model.ReserveService;
import com.example.yanolja.reserve.post.BookResponse;
import com.example.yanolja.reserve.post.Cartinfo;
import com.example.yanolja.reserve.post.CouponResponse;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080") // 허용할 도메인을 지정
public class ReserveController {
	@Autowired
	ReserveService reserveService;
	
	//reserveController예약 기능 --------------------------------------------------------------------------------------	
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
				RoomResponse roomdetail = reserveService.cartlist2(roomid);
				// 카카오 결제에서 사용하기 위한 세션
				session.setAttribute("roomdetail", roomdetail);
				model.addAttribute("room", roomdetail);
			}

			Object uname = session.getAttribute("username");
			if (uname != null) {
				String phone = reserveService.findUPhone(uname.toString());
				int userid = (int) session.getAttribute("userid");
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
		public String ReserveAgree(HttpSession session) {
			Integer userid = (Integer) session.getAttribute("userid");
			String user_name = (String) session.getAttribute("partner_user_id");
			String user_phone = (String) session.getAttribute("userPhone");
			String order_number = (String) session.getAttribute("partner_order_id");
			String sessionDate1 = (String)session.getAttribute("sessionDate1");
			String sessionDate2 = (String)session.getAttribute("sessionDate2");
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

				reserveService.insertReserve(parameterList, user_name, user_phone, order_number);

				if (userid != null) {
					reserveService.insertBookList(userid, parameterList, order_number);
				}
			} else {
				RoomResponse roomdetail = (RoomResponse) session.getAttribute("roomdetail");
				String hotelName = roomdetail.getHotelname();
				String roomName = roomdetail.getRoomname();

				Map<String, Integer> Ids = reserveService.findHRids(roomName, hotelName);
				int hotelid = Ids.get("hotelid");
				int roomid = Ids.get("roomid");

				String formattedDate1 = formatDates(sessionDate1);
				String formattedDate2 = formatDates(sessionDate2);
				String bookdate = formattedDate1;

				reserveService.insertReserveOne(hotelid, roomid, formattedDate1, formattedDate2, user_name, user_phone,
						order_number);

				if (userid != null) {
					reserveService.insertBook(userid, roomid, bookdate);
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
			System.out.println(name + "|" + phone + "|" + order_number);
			if (pesonal_reserve == null) {
				System.out.println("값이 없습니다");
			} else {
				model.addAttribute("pesonal_reserve", pesonal_reserve);
			}
			System.out.println(pesonal_reserve);
			// 주문번호 이름 전화번호로 일치하는 정보 찾아서 반환
			return "Search/Reserve/Reserve_history_NotUser";
		}

	//reserveController장바구니 기능 --------------------------------------------------------------------------------------	
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

		// 메소드 ---------------------------------------------------------------------------------------------------	
			// 날짜 형식 지정 메소드 ReserveAgree
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

			// 카트 룸 정보를 검색하는 공통 메소드 cart
			private List<Cartinfo> getCartRoomInfoList(HttpSession session) {
				return (List<Cartinfo>) session.getAttribute("cartRoomInfoList");
			}

			// Cartinfo를 RoomResponse와 결합하는 공통 메소드 cart
			private List<RoomResponse> combineCartInfoWithRoomResponse(List<Cartinfo> cartRoomInfoList) {
				List<Integer> roomIds = cartRoomInfoList.stream().map(Cartinfo::getRoomid).collect(Collectors.toList());
				List<RoomResponse> Cartroom = reserveService.cartlist(roomIds);
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

}
