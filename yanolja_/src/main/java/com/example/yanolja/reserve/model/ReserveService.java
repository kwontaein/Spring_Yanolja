package com.example.yanolja.reserve.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yanolja.grobal.ReserveResponse;
import com.example.yanolja.grobal.RoomResponse;
import com.example.yanolja.reserve.mapper.ReserveMapper;
import com.example.yanolja.reserve.post.BookResponse;
import com.example.yanolja.reserve.post.CouponResponse;

@Service
public class ReserveService {

	@Autowired
	private final ReserveMapper reserveMapper = null;

// 휴대폰 번호 찾기(예약 페이지)
	public String findUPhone(String uname) {
		return reserveMapper.findUPhone(uname);
	}

	// 카트에 담긴 방 리스트 조회
	public List<RoomResponse> cartlist(List<Integer> roomIds) {
		return reserveMapper.cartlist(roomIds);
	}

	// 카트에 담지 않고 방 예약을 할 때
	public RoomResponse cartlist2(Integer roomId) {
		return reserveMapper.cartlist2(roomId);
	}

	// 예약 정보 삽입을 위한 호텔, 룸 아이디 조회
	public Map<String, Integer> findHRids(String roomname, String hotelname) {
		return reserveMapper.findHRids(roomname, hotelname);
	}

	// 예약 정보 삽입
	public void insertReserve(List<Map<String, Object>> parameterMap, String user_name, String user_phone,
			String kakaoTid) {
		reserveMapper.insertReserve(parameterMap, user_name, user_phone, kakaoTid);
	}

	// 예약 정보 하나만 삽입
	public void insertReserveOne(int hotelid, int roomid, String date1List, String date2List, String user_name,
			String user_phone, String order_number, String price, String kakaoTid) {
		reserveMapper.insertReserveOne(hotelid, roomid, date1List, date2List, user_name, user_phone, order_number,
				price, kakaoTid);
	}

	// 로그인한 경우, 예약 내역 삽입
	public void insertBook(int userid, int roomid, String bookdate) {
		reserveMapper.insertBook(userid, roomid, bookdate);
	}

	// 로그인한 경우, 예약 내역 여러개 삽입
	public void insertBookList(int userid, List<Map<String, Object>> parameterMap, String order_number) {
		reserveMapper.insertBookList(userid, parameterMap, order_number);
	}

	// 예약 직후 예약 정보 출력
	public List<ReserveResponse> selectReserve_order(List<String> ordernumber) {
		return reserveMapper.selectReserve_order(ordernumber);
	}

	// 예약 내역 조회
	public List<BookResponse> selectBook(int userid, int period) {
		return reserveMapper.selectBook(userid, period);
	}

	// 비회원 예약 내역 조회
	public List<ReserveResponse> select_p_Reserve(String name, String phone, String order_number) {
		return reserveMapper.select_p_Reserve(name, phone, order_number);
	}

	// 쿠폰 정보 조회
	public List<CouponResponse> selectcoupon(int userid) {

		return reserveMapper.selectcoupon(userid);
	}

	public int SelectReserveByBookid(String bookid) {
		return reserveMapper.SelectReserveByBookid(bookid);
	}

	public int SelectReserveByOrder_number(String order_number) {
		return reserveMapper.SelectReserveByOrder_number(order_number);
	}
}
