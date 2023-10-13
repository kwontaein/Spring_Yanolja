package com.example.yanolja.main.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yanolja.main.mapper.MainMapper;
import com.example.yanolja.main.post.BookResponse;
import com.example.yanolja.main.post.FacilityResponse;
import com.example.yanolja.main.post.InfoResponse;
import com.example.yanolja.main.post.MainRequest;
import com.example.yanolja.main.post.MainResponse;
import com.example.yanolja.main.post.PolicyResponse;
import com.example.yanolja.main.post.ReserveResponse;
import com.example.yanolja.main.post.ReviewResponse;
import com.example.yanolja.main.post.RoomResponse;
import com.example.yanolja.main.post.TrafficResponse;

import jakarta.transaction.Transactional;

@Service
public class MainService {

	@Autowired
	private final MainMapper mainMapper = null;

	/**
	 * 호텔 저장
	 * 
	 * @param params - 호텔 정보
	 * @return Generated PK
	 */
	@Transactional
	public Long savePost(final MainRequest params) {
		mainMapper.save(params);
		return params.getHotelid();
	}

	/**
	 * 호텔 상세정보 조회
	 * 
	 * @param id - PK
	 * @return 호텔 상세정보
	 */
	public MainResponse findPostById(final Long hotelid) {
		return mainMapper.findById(hotelid);
	}

	/**
	 * 호텔 수정
	 * 
	 * @param params - 호텔 정보
	 * @return PK
	 */
	@Transactional
	public Long updatePost(final MainRequest params) {
		mainMapper.update(params);
		return params.getHotelid();
	}

	/**
	 * 호텔 삭제
	 * 
	 * @param id - PK
	 * @return PK
	 */
	public Long deletePost(final Long hotelid) {
		mainMapper.deleteById(hotelid);
		return hotelid;
	}

	/**
	 * 호텔 리스트 조회
	 * 
	 * @return 호텔 리스트
	 */
	public List<MainResponse> findAllFrom(int regionid, String kindhotel) {
		return mainMapper.findAllFrom(regionid, kindhotel);
	}

	/**
	 * 호텔 리스트 조회
	 * 
	 * @return 호텔 리스트
	 */
	public List<MainResponse> TofindByKind(String kindhotel) {
		return mainMapper.TofindByKind(kindhotel);
	}

	/**
	 * 호텔 리스트 조회
	 * 
	 * @return 호텔 리스트
	 */
	public List<MainResponse> TofindByKindDesc(String kindhotel) {
		return mainMapper.TofindByKindDesc(kindhotel);
	}

	/**
	 * 호텔 리스트 조회
	 * 
	 * @return 호텔 리스트
	 */
	public List<MainResponse> findAllHotel() {
		return mainMapper.findAll();
	}

	/**
	 * 풀빌라 리스트 조회
	 * 
	 * @return 풀빌라 리스트
	 */
	public List<MainResponse> findPool() {
		return mainMapper.findPool();
	}

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	public List<RoomResponse> findRoom(final int hotelid) {
		return mainMapper.findRoom(hotelid);
	}

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	public List<RoomResponse> findRoomByDate(final int hotelid, String StratDate) {
		return mainMapper.findRoomByDate(hotelid, StratDate);
	}

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	public RoomResponse findRoomDetail(final int roomid, String StratDate) {
		return mainMapper.findRoomDetail(roomid, StratDate);
	}

	public String findUPhone(String uname) {
		return mainMapper.findUPhone(uname);
	}

	public List<ReserveResponse> reserve_possible(int hotelid) {
		return mainMapper.reserve_possible(hotelid);
	};

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	public List<InfoResponse> hotelinfo(final int hotelid) {
		return mainMapper.hotelinfo(hotelid);
	}

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	public List<PolicyResponse> hotelpolicy(final int hotelid) {
		return mainMapper.hotelpolicy(hotelid);
	}

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	public String hotelintro(final int hotelid) {
		return mainMapper.hotelintro(hotelid);
	}

	/**
	 * 도로명주소 조회
	 * 
	 * @return 도로명주소
	 */
	public String hotelLocationex(final int hotelid) {
		return mainMapper.hotelLocationex(hotelid);
	}

	public int roomcnt(int hotelid) {
		return mainMapper.roomcnt(hotelid);
	}

	public int reviewroomcnt(Integer roomid) {
		return mainMapper.reviewroomcnt(roomid);
	}

	public List<ReviewResponse> review(final int hotelid) {
		return mainMapper.review(hotelid);
	}

	public List<ReviewResponse> reviewroom(final int roomid) {
		return mainMapper.review(roomid);
	}

	public ReviewResponse rating_detail(int hotelid) {
		return mainMapper.rating_detail(hotelid);
	}

	/**
	 * 도로명주소, 주차여부, 오시는길 조회
	 * 
	 * @return 방 리스트
	 */
	public TrafficResponse hotelLocation(int hotelid) {
		return mainMapper.hotelLocation(hotelid);
	}

	/**
	 * 풀빌라 리스트 조회
	 * 
	 * @return 풀빌라 리스트
	 */
	public List<MainResponse> findLocateBy(final int regionid) {
		return mainMapper.findlocateby(regionid);
	}

	public List<RoomResponse> cartlist(List<Integer> roomIds) {
		return mainMapper.cartlist(roomIds);
	}

	public RoomResponse cartlist2(Integer roomId) {
		return mainMapper.cartlist2(roomId);
	}

	public FacilityResponse facility(int roomid) {
		return mainMapper.facility(roomid);
	}

	public Map<String, Integer> findHRids(String roomname, String hotelname) {
		return mainMapper.findHRids(roomname, hotelname);
	}

	public void insertReserve(List<Map<String, Object>> parameterMap, String user_name, String user_phone,
			String order_number) {
		mainMapper.insertReserve(parameterMap, user_name, user_phone, order_number);
	}

	public void insertReserveOne(int hotelid, int roomid, String date1List, String date2List, String user_name,
			String user_phone, String order_number) {
		mainMapper.insertReserveOne(hotelid, roomid, date1List, date2List, user_name, user_phone, order_number);
	}

	public void insertBook(int userid ,int roomid ,String bookdate) {
		mainMapper.insertBook(userid, roomid, bookdate);
	}
	
	public void insertBookList(int userid ,List<Map<String, Object>> parameterMap) {
		mainMapper.insertBookList(userid, parameterMap);
	}
	
	public ReserveResponse selectReserve_order(String ordernumber) {
		return mainMapper.selectReserve_order(ordernumber);
	}
	public List<BookResponse> selectBook(int userid, int period) {
		return mainMapper.selectBook(userid ,period);
	}
}