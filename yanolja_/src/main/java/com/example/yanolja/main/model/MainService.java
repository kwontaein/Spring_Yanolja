package com.example.yanolja.main.model;

import java.awt.Image;
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

	public ReserveResponse selectForReview(int roomid) {
		return mainMapper.selectForReview(roomid);
	}

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

	public int findHotelId(int roomid) {
		return mainMapper.findHotelId(roomid);
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

	public List<ReviewResponse> review(final int hotelid, String roomname, String orderby, boolean onlyPhoto) {
		return mainMapper.review(hotelid, roomname, orderby, onlyPhoto);
	}

	public List<String> roomnameList(final int hotelid) {
		return mainMapper.roomnameList(hotelid);
	}

	public List<ReviewResponse> reviewroom(int roomid, String orderby, boolean onlyPhoto) {
		return mainMapper.reviewroom(roomid, orderby, onlyPhoto);
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

	public void insertBook(int userid, int roomid, String bookdate) {
		mainMapper.insertBook(userid, roomid, bookdate);
	}

	public void insertBookList(int userid, List<Map<String, Object>> parameterMap, String order_number) {
		mainMapper.insertBookList(userid, parameterMap, order_number);
	}

	public List<ReserveResponse> selectReserve_order(String ordernumber) {
		return mainMapper.selectReserve_order(ordernumber);
	}

	public List<BookResponse> selectBook(int userid, int period) {
		return mainMapper.selectBook(userid, period);
	}

	public List<ReserveResponse> select_p_Reserve(String name, String phone, String order_number) {
		return mainMapper.select_p_Reserve(name, phone, order_number);
	}

	public void insertReview(int hotelid, int roomid, int userid, double rating1, double rating2, double rating3,
			double rating4, String textData) {
		mainMapper.insertReview(hotelid, roomid, userid, rating1, rating2, rating3, rating4, textData);
	}

	public void updateReivewYn(int bookid) {
		mainMapper.updateReivewYn(bookid);
	}

	public void saveImage(int hotelid, int currentReviewid, String originalFileName, byte[] imageBytes) {
		// TODO Auto-generated method stub
		mainMapper.saveImage(hotelid,currentReviewid,originalFileName,imageBytes);
	}

	public int lastReviewid() {
		return mainMapper.lastReviewid();
	}
}