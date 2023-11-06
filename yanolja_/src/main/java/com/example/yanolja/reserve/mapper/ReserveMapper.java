package com.example.yanolja.reserve.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.yanolja.grobal.ReserveResponse;
import com.example.yanolja.grobal.RoomResponse;
import com.example.yanolja.reserve.post.BookResponse;
import com.example.yanolja.reserve.post.CouponResponse;
import com.example.yanolja.reserve.post.ReservedInfo;

@Mapper
public interface ReserveMapper {

	String findUPhone(String uname);

	List<RoomResponse> cartlist(List<Integer> roomIds);

	RoomResponse cartlist2(Integer roomId);

	Map<String, Integer> findHRids(String roomname, String hotelname);

	void insertReserve(List<Map<String, Object>> parameterMap, String user_name, String user_phone, String kakaoTid);

	void insertReserveOne(int hotelid, int roomid, String date1List, String date2List, String user_name,
			String user_phone, String order_number, String price, String kakaoTid);

	void insertBook(int userid, int roomid, String bookdate);

	void insertBookList(int userid, List<Map<String, Object>> parameterMap, String order_number);

	List<ReserveResponse> selectReserve_order(List<String> ordernumber);

	List<BookResponse> selectBook(int userid, int period);

	List<ReserveResponse> select_p_Reserve(String name, String phone, String order_number);

	List<CouponResponse> selectcoupon(int userid);

	ReservedInfo SelectReserveByBookid(String bookid);

	ReservedInfo SelectReserveByOrder_number(String order_number);
}
