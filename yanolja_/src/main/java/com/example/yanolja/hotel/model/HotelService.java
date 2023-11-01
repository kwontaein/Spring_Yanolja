package com.example.yanolja.hotel.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yanolja.grobal.ImageResponse;
import com.example.yanolja.grobal.MainResponse;
import com.example.yanolja.grobal.ReserveResponse;
import com.example.yanolja.grobal.ReviewResponse;
import com.example.yanolja.grobal.RoomResponse;
import com.example.yanolja.hotel.mapper.HotelMapper;
import com.example.yanolja.hotel.post.FacilityResponse;
import com.example.yanolja.hotel.post.InfoResponse;
import com.example.yanolja.hotel.post.PolicyResponse;
import com.example.yanolja.hotel.post.TrafficResponse;

@Service
public class HotelService {
	
	@Autowired
	private final HotelMapper hotelMapper = null;

	
	// hotelid 값을 통해 MainResponse 반환
	public MainResponse findById(final Long hotelid) {
		return hotelMapper.findById(hotelid);
	}

	// 날짜별 예약 가능한 방 보여주기
	public List<RoomResponse> findRoomByDate(final int hotelid, String StratDate) {
		return hotelMapper.findRoomByDate(hotelid, StratDate);
	}

	// 호텔 안내 정보 가져오기 (안내/정책 페이지)
	public List<InfoResponse> hotelinfo(final int hotelid) {
		return hotelMapper.hotelinfo(hotelid);
	}

	// 호텔 정책 정보 가져오기 (안내/정책 페이지)
	public List<PolicyResponse> hotelpolicy(final int hotelid) {
		return hotelMapper.hotelpolicy(hotelid);
	}
	// 호텔 소개 정보 (안내/정책 페이지)
	public String hotelintro(final int hotelid) {
		return hotelMapper.hotelintro(hotelid);
	}

	// 호텔의 총 방 갯수
	public int roomcnt(int hotelid) {
		return hotelMapper.roomcnt(hotelid);
	}

	// 특정 방의 리뷰 갯수
	public int reviewroomcnt(Integer roomid) {
		return hotelMapper.reviewroomcnt(roomid);
	}

	// 후기 목록 조회
	public List<ReviewResponse> review(final int hotelid, String roomname, String orderby, boolean onlyPhoto) {
		return hotelMapper.review(hotelid, roomname, orderby, onlyPhoto);
	}

	// 방 이름 목록 조회 (특정 방의 후기만 보기)
	public List<String> roomnameList(final int hotelid) {
		return hotelMapper.roomnameList(hotelid);
	}

	// 특정 방의 후기만 보기
	public List<ReviewResponse> reviewroom(int roomid, String orderby, boolean onlyPhoto) {
		return hotelMapper.reviewroom(roomid, orderby, onlyPhoto);
	}

	// 후기 평점
	public ReviewResponse rating_detail(int hotelid) {
		return hotelMapper.rating_detail(hotelid);
	}

	// 위치/교통 정보 조회
	public TrafficResponse hotelLocation(int hotelid) {
		return hotelMapper.hotelLocation(hotelid);
	}

	// 방 세부정보
	public RoomResponse findRoomDetail(final int roomid, String StratDate) {
		return hotelMapper.findRoomDetail(roomid, StratDate);
	}

	// 시설/서비스 조회
	public FacilityResponse facility(int roomid) {
		return hotelMapper.facility(roomid);
	}

	// 예약 가능여부 찾기
	public List<ReserveResponse> reserve_possible(int hotelid) {
		return hotelMapper.reserve_possible(hotelid);
	};

	// 리뷰 이미지 목록 조회
	public List<ImageResponse> reviewAllPhotos(Integer hotelid) {

		return hotelMapper.reviewAllPhotos(hotelid);
	}

}
