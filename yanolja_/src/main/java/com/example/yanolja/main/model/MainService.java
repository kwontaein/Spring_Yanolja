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

//--------------------------------------------------------------------
	// hotelid 값을 통해 MainResponse 반환
	public MainResponse findById(final Long hotelid) {
		return mainMapper.findById(hotelid);
	}

	// 최근 본 숙소 관련 숙소 검색
	public List<MainResponse> findPostById(final Long hotelid, String kindhotel) {
		return mainMapper.findPostById(hotelid, kindhotel);
	}

	// 지역, 호텔종류에 따라 목록 반환
	public List<MainResponse> findAllFrom(int regionid, String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.findAllFrom(regionid, kind);
	}

	// 큰 지역 명으로 찾기
	public List<MainResponse> findAllFromRegion(String regionname, String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.findAllFromRegion(regionname, kindhotel);
	}

	// 세부 지역 명으로 찾기
	public List<MainResponse> findAllFromRd(String regionname, String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.findAllFromRd(regionname, kindhotel);
	}

	// Sub_main 페이지 들어갈 때 숙소 종류 구분
	public List<MainResponse> TofindByKind(String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.TofindByKind(kind);
	}

	// 인기순 출력
	public List<MainResponse> TofindByKindDesc(String kindhotel) {
		String kind = kind_name(kindhotel);
		return mainMapper.TofindByKindDesc(kind);
	}

	// 호텔 이름 검색을 위해 영어로 바꾸는 메소드
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

	// 날짜별 예약 가능한 방 보여주기
	public List<RoomResponse> findRoomByDate(final int hotelid, String StratDate) {
		return mainMapper.findRoomByDate(hotelid, StratDate);
	}

	// 방 세부정보
	public RoomResponse findRoomDetail(final int roomid, String StratDate) {
		return mainMapper.findRoomDetail(roomid, StratDate);
	}

	// 휴대폰 번호 찾기(예약 페이지)
	public String findUPhone(String uname) {
		return mainMapper.findUPhone(uname);
	}

	// 예약 가능여부 찾기
	public List<ReserveResponse> reserve_possible(int hotelid) {
		return mainMapper.reserve_possible(hotelid);
	};

	// 후기 작성시 불러오는 정보
	public ReserveResponse selectForReview(int roomid) {
		return mainMapper.selectForReview(roomid);
	}

	// 호텔 안내 정보 가져오기 (안내/정책 페이지)
	public List<InfoResponse> hotelinfo(final int hotelid) {
		return mainMapper.hotelinfo(hotelid);
	}

	// 호텔 정책 정보 가져오기 (안내/정책 페이지)
	public List<PolicyResponse> hotelpolicy(final int hotelid) {
		return mainMapper.hotelpolicy(hotelid);
	}

	// roomid를 통해 hotelid 값 가져오기 (리뷰작성 쪽)
	public int findHotelId(int roomid) {
		return mainMapper.findHotelId(roomid);
	}

	// 호텔 소개 정보 (안내/정책 페이지)
	public String hotelintro(final int hotelid) {
		return mainMapper.hotelintro(hotelid);
	}

	// 호텔의 총 방 갯수
	public int roomcnt(int hotelid) {
		return mainMapper.roomcnt(hotelid);
	}

	// 특정 방의 리뷰 갯수
	public int reviewroomcnt(Integer roomid) {
		return mainMapper.reviewroomcnt(roomid);
	}

	// 후기 목록 조회
	public List<ReviewResponse> review(final int hotelid, String roomname, String orderby, boolean onlyPhoto) {
		return mainMapper.review(hotelid, roomname, orderby, onlyPhoto);
	}

	// 방 이름 목록 조회 (특정 방의 후기만 보기)
	public List<String> roomnameList(final int hotelid) {
		return mainMapper.roomnameList(hotelid);
	}

	// 특정 방의 후기만 보기
	public List<ReviewResponse> reviewroom(int roomid, String orderby, boolean onlyPhoto) {
		return mainMapper.reviewroom(roomid, orderby, onlyPhoto);
	}

	// 후기 평점
	public ReviewResponse rating_detail(int hotelid) {
		return mainMapper.rating_detail(hotelid);
	}

	// 위치/교통 정보 조회
	public TrafficResponse hotelLocation(int hotelid) {
		return mainMapper.hotelLocation(hotelid);
	}

	// 카트에 담긴 방 리스트 조회
	public List<RoomResponse> cartlist(List<Integer> roomIds) {
		return mainMapper.cartlist(roomIds);
	}

	// 카트에 담지 않고 방 예약을 할 때
	public RoomResponse cartlist2(Integer roomId) {
		return mainMapper.cartlist2(roomId);
	}

	// 시설/서비스 조회
	public FacilityResponse facility(int roomid) {
		return mainMapper.facility(roomid);
	}

	// 예약 정보 삽입을 위한 호텔, 룸 아이디 조회
	public Map<String, Integer> findHRids(String roomname, String hotelname) {
		return mainMapper.findHRids(roomname, hotelname);
	}

	// 예약 정보 삽입
	public void insertReserve(List<Map<String, Object>> parameterMap, String user_name, String user_phone,
			String order_number) {
		mainMapper.insertReserve(parameterMap, user_name, user_phone, order_number);
	}

	// 예약 정보 하나만 삽입
	public void insertReserveOne(int hotelid, int roomid, String date1List, String date2List, String user_name,
			String user_phone, String order_number) {
		mainMapper.insertReserveOne(hotelid, roomid, date1List, date2List, user_name, user_phone, order_number);
	}

	// 로그인한 경우, 예약 내역 삽입
	public void insertBook(int userid, int roomid, String bookdate) {
		mainMapper.insertBook(userid, roomid, bookdate);
	}

	// 로그인한 경우, 예약 내역 여러개 삽입
	public void insertBookList(int userid, List<Map<String, Object>> parameterMap, String order_number) {
		mainMapper.insertBookList(userid, parameterMap, order_number);
	}

	// 예약 직후 예약 정보 출력
	public List<ReserveResponse> selectReserve_order(String ordernumber) {
		return mainMapper.selectReserve_order(ordernumber);
	}

	// 예약 내역 조회
	public List<BookResponse> selectBook(int userid, int period) {
		return mainMapper.selectBook(userid, period);
	}

	// 비회원 예약 내역 조회
	public List<ReserveResponse> select_p_Reserve(String name, String phone, String order_number) {
		return mainMapper.select_p_Reserve(name, phone, order_number);
	}

	// 후기 삽입
	public void insertReview(int hotelid, int roomid, int userid, double rating1, double rating2, double rating3,
			double rating4, String textData) {
		mainMapper.insertReview(hotelid, roomid, userid, rating1, rating2, rating3, rating4, textData);
	}

	// 후기 업데이트
	public void updateReivewYn(int bookid) {
		mainMapper.updateReivewYn(bookid);
	}

	// 후기 이미지 저장
	public void saveImage(int hotelid, int currentReviewid, String originalFileName, byte[] imageBytes, int userid) {
		mainMapper.saveImage(hotelid, currentReviewid, originalFileName, imageBytes, userid);
	}

	// 호텔 이미지 저장
	public void saveImageTest(String originalFileName, byte[] imageBytes,int roomid) {
		mainMapper.saveImageTest(originalFileName, imageBytes,roomid);
	}

	// 이미지 삭제
	public void DelPhotoByimgid(int imgid) {
		mainMapper.DelPhotoByimgid(imgid);
	}

	// 이미지 업데이트
	public void updateImage(String originalFileName, byte[] imageBytes, int reviewid, int userid) {
		mainMapper.updateImage(originalFileName, imageBytes, reviewid, userid);
	}

	// 마지막 리뷰 아이디 조회
	public int lastReviewid() {
		return mainMapper.lastReviewid();
	}

	// 리뷰 이미지 목록 조회
	public List<ImageResponse> reviewAllPhotos(Integer hotelid) {

		return mainMapper.reviewAllPhotos(hotelid);
	}

	// 리뷰 삭제
	public void DeleteReviewById(int reviewid) {
		mainMapper.DeleteReviewById(reviewid);
	}

	// 리뷰 삭제 시 이미지 같이 삭제
	public void DeletePhotoById(int reviewid) {
		mainMapper.DeletePhotoById(reviewid);
	}

	// 리뷰 수정 시 가져오는 정보
	public int selectRsByreview(int reviewid) {

		return mainMapper.selectRsByreview(reviewid);
	}

	// 리뷰 수정 시 가져오는 정보 2
	public ReviewResponse selectForReviewUpdate(int roomIdbyReview) {

		return mainMapper.selectForReviewUpdate(roomIdbyReview);
	}

	// 리뷰 수정 시 가져올 이미지 목록
	public List<ImageResponse> ReviewInseredPhoto(Integer reviewid) {

		return mainMapper.ReviewInseredPhoto(reviewid);
	}

	// 리뷰 수정
	public void updateReview(double rating1, double rating2, double rating3, double rating4, String textData,
			int reviewid) {
		mainMapper.updateReview(rating1, rating2, rating3, rating4, textData, reviewid);

	}

	//// region 테이블에서 regionName 목록 반환
	public List<String> findRegionName() {

		return mainMapper.findRegionName();
	}

	// 좋아요 여부 확인
	public int selectLike(int hotelid, int userid) {

		return mainMapper.selectLike(hotelid, userid);
	}

	// 좋아요 삭제
	public void deleteLike(int hotelid, int userid) {

		mainMapper.deleteLike(hotelid, userid);
	}

	// 좋아요 추가
	public void insertLike(int hotelid, int userid) {

		mainMapper.insertLike(hotelid, userid);
	}

	// 좋아요 한 호텔 목록 조회
	public List<MainResponse> Likehotels(int userid) {

		return mainMapper.Likehotels(userid);
	}

	// 쿠폰 정보 조회
	public List<CouponResponse> selectcoupon(int userid) {

		return mainMapper.selectcoupon(userid);
	}
}
