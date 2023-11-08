package com.example.yanolja.reserve.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yanolja.grobal.Response.ReserveResponse;
import com.example.yanolja.grobal.Response.RoomResponse;
import com.example.yanolja.reserve.mapper.ReserveMapper;
import com.example.yanolja.reserve.post.BookResponse;
import com.example.yanolja.reserve.post.CouponResponse;
import com.example.yanolja.reserve.post.ReservedInfo;

import jakarta.servlet.http.HttpSession;

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

	public ReservedInfo SelectReserveByBookid(String bookid) {
		return reserveMapper.SelectReserveByBookid(bookid);
	}

	public ReservedInfo SelectReserveByOrder_number(String order_number) {
		return reserveMapper.SelectReserveByOrder_number(order_number);
	}

	public void insertDatebyReservation(Map<String, Object> parameterList, String date) {
		reserveMapper.insertinsertDatebyReservation(parameterList,date);
	}

	public void insertDatebyReservationOne(int roomid, String sessionDate1) {
		reserveMapper.insertDatebyReservationOne(roomid, sessionDate1);
	}


	//두 날짜 사이 값들 리스트를 생성하기 위한 메소드
	public List<String> DateList(String sessionDate1, String sessionDate2) {
		// 날짜 문자열을 파싱할 형식 지정
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy. MM. dd. (E)");
		// 목록에 저장할 날짜 형식을 지정
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 생성된 날짜를 저장할 리스트
		List<String> dateList = new ArrayList<>();
		try {
			Date startDate = inputFormat.parse(sessionDate1);
			Date endDate = inputFormat.parse(sessionDate2);
			generateDateList(startDate, endDate, outputFormat, dateList);
		} catch (ParseException e) {
			// 형식에 맞지 않는 경우 처리
			try {
				Date startDate = outputFormat.parse(sessionDate1);
				Date endDate = outputFormat.parse(sessionDate2);
				generateDateList(startDate, endDate, outputFormat, dateList);
			} catch (ParseException er) {
				// 두 가지 형식 모두 형식에 맞지 않는 경우 처리
				er.printStackTrace();
			}
		}
		System.out.println(dateList);
		return dateList;
	}
	
	//날짜 목록 생성
	private void generateDateList(Date startDate, Date endDate, SimpleDateFormat outputFormat, List<String> dateList) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		while (calendar.getTime().before(endDate)) {
			String formattedDate = outputFormat.format(calendar.getTime());
			dateList.add(formattedDate);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
	
	//결제 후 카카오 정보 세션 삭제 메소드
	public void DeleteSession(HttpSession session) {
		session.removeAttribute("partner_user_id");
		session.removeAttribute("userPhone");
		session.removeAttribute("price");
		session.removeAttribute("partner_order_id");
		session.removeAttribute("vat");
	}


}
