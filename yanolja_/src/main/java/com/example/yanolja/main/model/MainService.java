package com.example.yanolja.main.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yanolja.main.mapper.MainMapper;
import com.example.yanolja.main.post.BookResponse;
import com.example.yanolja.main.post.CouponResponse;
import com.example.yanolja.main.post.FacilityResponse;
import com.example.yanolja.main.post.ImageResponse;
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

	@Transactional
	public Long savePost(final MainRequest params) {
		mainMapper.save(params);
		return params.getHotelid();
	}

	public MainResponse findById(final Long hotelid) {
		return mainMapper.findById(hotelid);
	}

	public List<MainResponse> findPostById(final Long hotelid, String kindhotel) {
		return mainMapper.findPostById(hotelid, kindhotel);
	}

	@Transactional
	public Long updatePost(final MainRequest params) {
		mainMapper.update(params);
		return params.getHotelid();
	}

	public Long deletePost(final Long hotelid) {
		mainMapper.deleteById(hotelid);
		return hotelid;
	}

	public List<MainResponse> findAllFrom(int regionid, String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.findAllFrom(regionid, kind);
	}

	public List<MainResponse> findAllFromRegion(String regionname, String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.findAllFromRegion(regionname, kindhotel);
	}

	public List<MainResponse> findAllFromRd(String regionname, String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.findAllFromRd(regionname, kindhotel);
	}

	public List<MainResponse> TofindByKind(String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.TofindByKind(kind);
	}

	public List<MainResponse> TofindByKindDesc(String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.TofindByKindDesc(kind);
	}

	// 이름 반환 메소드
	public String kind_name(String kindhotel) {
		String kind = "호텔";
		if (kindhotel.equals("hotel")) {
			kind = "호텔";
		} else if (kindhotel.equals("motel")) {
			kind = "모텔";
		} else if (kindhotel.equals("pension")) {
			kind = "펜션";
		} else if (kindhotel.equals("familly")) {
			kind = "게스트하우스";
		}
		return kind;
	}

	public List<MainResponse> findAllHotel() {
		return mainMapper.findAll();
	}

	public List<MainResponse> findPool() {
		return mainMapper.findPool();
	}

	public List<RoomResponse> findRoom(final int hotelid) {
		return mainMapper.findRoom(hotelid);
	}

	public List<RoomResponse> findRoomByDate(final int hotelid, String StratDate) {
		return mainMapper.findRoomByDate(hotelid, StratDate);
	}

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

	public List<InfoResponse> hotelinfo(final int hotelid) {
		return mainMapper.hotelinfo(hotelid);
	}

	public List<PolicyResponse> hotelpolicy(final int hotelid) {
		return mainMapper.hotelpolicy(hotelid);
	}

	public int findHotelId(int roomid) {
		return mainMapper.findHotelId(roomid);
	}

	public String hotelintro(final int hotelid) {
		return mainMapper.hotelintro(hotelid);
	}

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

	public void saveImage(int hotelid, int currentReviewid, String originalFileName, byte[] imageBytes, int userid) {
		// TODO Auto-generated method stub
		mainMapper.saveImage(hotelid, currentReviewid, originalFileName, imageBytes, userid);
	}

	public void DelPhotoByimgid(int imgid) {
		// TODO Auto-generated method stub
		mainMapper.DelPhotoByimgid(imgid);
	}

	public void updateImage(String originalFileName, byte[] imageBytes, int reviewid, int userid) {
		// TODO Auto-generated method stub
		mainMapper.updateImage(originalFileName, imageBytes, reviewid, userid);
	}

	public int lastReviewid() {
		return mainMapper.lastReviewid();
	}

	public List<ImageResponse> reviewAllPhotos(Integer hotelid) {
		// TODO Auto-generated method stub
		return mainMapper.reviewAllPhotos(hotelid);
	}

	public void DeleteReviewById(int reviewid) {
		mainMapper.DeleteReviewById(reviewid);
	}

	public void DeletePhotoById(int reviewid) {
		mainMapper.DeletePhotoById(reviewid);
	}

	public int selectRsByreview(int reviewid) {
		// TODO Auto-generated method stub
		return mainMapper.selectRsByreview(reviewid);
	}

	public ReviewResponse selectForReviewUpdate(int roomIdbyReview) {
		// TODO Auto-generated method stub
		return mainMapper.selectForReviewUpdate(roomIdbyReview);
	}

	public List<ImageResponse> ReviewInseredPhoto(Integer reviewid) {
		// TODO Auto-generated method stub
		return mainMapper.ReviewInseredPhoto(reviewid);
	}

	public void updateReview(double rating1, double rating2, double rating3, double rating4, String textData,
			int reviewid) {
		mainMapper.updateReview(rating1, rating2, rating3, rating4, textData, reviewid);

	}

	public List<String> findRegionName() {
		// TODO Auto-generated method stub
		return mainMapper.findRegionName();
	}

	public int selectLike(int hotelid, int userid) {
		// TODO Auto-generated method stub
		return mainMapper.selectLike(hotelid, userid);
	}

	public void deleteLike(int hotelid, int userid) {
		// TODO Auto-generated method stub
		mainMapper.deleteLike(hotelid, userid);
	}

	public void insertLike(int hotelid, int userid) {
		// TODO Auto-generated method stub
		mainMapper.insertLike(hotelid, userid);
	}

	public List<MainResponse> Likehotels(int userid) {
		// TODO Auto-generated method stub
		return mainMapper.Likehotels(userid);
	}

	public List<CouponResponse> selectcoupon(int userid) {
		// TODO Auto-generated method stub
		return mainMapper.selectcoupon(userid);
	}

}