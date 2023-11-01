package com.example.yanolja.main.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yanolja.grobal.ImageResponse;
import com.example.yanolja.grobal.MainResponse;
import com.example.yanolja.grobal.ReserveResponse;
import com.example.yanolja.grobal.ReviewResponse;
import com.example.yanolja.grobal.RoomResponse;
import com.example.yanolja.hotel.post.FacilityResponse;
import com.example.yanolja.hotel.post.InfoResponse;
import com.example.yanolja.hotel.post.PolicyResponse;
import com.example.yanolja.hotel.post.TrafficResponse;
import com.example.yanolja.main.mapper.MainMapper;
import com.example.yanolja.main.post.MainRequest;
import com.example.yanolja.reserve.post.BookResponse;
import com.example.yanolja.reserve.post.CouponResponse;

import jakarta.transaction.Transactional;

@Service
public class MainService {

	@Autowired
	private final MainMapper mainMapper = null;

//--------------------------------------------------------------------

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

	// 후기 작성시 불러오는 정보
	public ReserveResponse selectForReview(int roomid) {
		return mainMapper.selectForReview(roomid);
	}

	// roomid를 통해 hotelid 값 가져오기 (리뷰작성 쪽)
	public int findHotelId(int roomid) {
		return mainMapper.findHotelId(roomid);
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


}
