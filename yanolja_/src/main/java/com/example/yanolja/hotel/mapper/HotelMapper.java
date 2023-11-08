package com.example.yanolja.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.yanolja.grobal.Response.ImageResponse;
import com.example.yanolja.grobal.Response.MainResponse;
import com.example.yanolja.grobal.Response.ReserveResponse;
import com.example.yanolja.grobal.Response.ReviewResponse;
import com.example.yanolja.grobal.Response.RoomResponse;
import com.example.yanolja.hotel.post.FacilityResponse;
import com.example.yanolja.hotel.post.InfoResponse;
import com.example.yanolja.hotel.post.PolicyResponse;
import com.example.yanolja.hotel.post.TrafficResponse;
@Mapper
public interface HotelMapper {
	MainResponse findById(Long hotelid);

	List<RoomResponse> findRoomByDate(int hotelid, String StartDate);

	List<InfoResponse> hotelinfo(int hotelid);

	List<PolicyResponse> hotelpolicy(int hotelid);

	List<ReviewResponse> review(int hotelid, String roomname, String orderby, boolean onlyPhoto);

	List<String> roomnameList(int hotelid);

	List<ReviewResponse> reviewroom(int roomid, String orderby, boolean onlyPhoto);

	String hotelintro(int hotelid);

	int roomcnt(int hotelid);

	int reviewroomcnt(int roomid);

	ReviewResponse rating_detail(int hotelid);

	TrafficResponse hotelLocation(int hotelid);

	RoomResponse findRoomDetail(int roomid, String StartDate);

	FacilityResponse facility(int roomid);

	List<ReserveResponse> reserve_possible(int hotelid);

	List<ImageResponse> reviewAllPhotos(Integer hotelid);

}
