package com.example.yanolja.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.yanolja.main.post.FacilityResponse;
import com.example.yanolja.main.post.InfoResponse;
import com.example.yanolja.main.post.MainRequest;
import com.example.yanolja.main.post.MainResponse;
import com.example.yanolja.main.post.PolicyResponse;
import com.example.yanolja.main.post.ReserveResponse;
import com.example.yanolja.main.post.ReviewResponse;
import com.example.yanolja.main.post.RoomResponse;
import com.example.yanolja.main.post.TrafficResponse;

@Mapper
public interface MainMapper {

	/**
	 * 게시글 저장
	 * 
	 * @param params - 게시글 정보
	 */
	void save(MainRequest params);

	/**
	 * 게시글 상세정보 조회
	 * 
	 * @param id - PK
	 * @return 게시글 상세정보
	 */
	MainResponse findById(Long hotelid);

	/**
	 * 게시글 수정
	 * 
	 * @param params - 게시글 정보
	 */
	void update(MainRequest params);

	/**
	 * 게시글 삭제
	 * 
	 * @param id - PK
	 */
	void deleteById(Long hotelid);

	/**
	 * 호텔 리스트 조회
	 * 
	 * @return 호텔 리스트
	 */
	List<MainResponse> findAll();

	/**
	 * 풀빌라 리스트 조회
	 * 
	 * @return 풀빌라 리스트
	 */
	List<MainResponse> findPool();

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	List<RoomResponse> findRoom(int hotelid);

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	List<RoomResponse> findRoomByDate(int hotelid, String StartDate);

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	RoomResponse findRoomDetail(int hotelid);

	String findUPhone(String uname);

	List<ReserveResponse> reserve_possible(int hotelid);

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	String hotelLocationex(int hotelid);

	/**
	 * 방 리스트 조회
	 * 
	 * @return 방 리스트
	 */
	TrafficResponse hotelLocation(int hotelid);

	/**
	 * 안내 조회
	 * 
	 * @return 안내
	 */
	List<InfoResponse> hotelinfo(int hotelid);

	/**
	 * 정책 조회
	 * 
	 * @return 정책
	 */
	List<PolicyResponse> hotelpolicy(int hotelid);

	/**
	 * 후기 조회
	 * 
	 * @return 후기
	 */
	List<ReviewResponse> review(int hotelid);

	/**
	 * 방 후기 조회
	 * 
	 * @return 후기
	 */
	List<ReviewResponse> reviewroom(int roomid);

	/**
	 * 평점 세부 조회
	 * 
	 * @return 평점
	 */
	ReviewResponse rating_detail(int hotelid);

	/**
	 * 정책 조회
	 * 
	 * @return 정책
	 */
	String hotelintro(int hotelid);

	/**
	 * 방 갯수 조회
	 * 
	 * @return 갯수
	 */
	int roomcnt(int hotelid);

	/**
	 * 방 갯수 조회
	 * 
	 * @return 갯수
	 */
	int reviewroomcnt(int roomid);

	/**
	 * 위치, 종류기반 리스트 조회
	 * 
	 * @return 위치, 종류기반 리스트
	 */

	List<MainResponse> findAllFrom(int regionid, String kindhotel);

	/**
	 * 위치, 종류기반 리스트 조회
	 * 
	 * @return 위치, 종류기반 리스트
	 */

	List<MainResponse> TofindByKind(String kindhotel);

	/**
	 * 위치, 종류기반 리스트 조회
	 * 
	 * @return 위치, 종류기반 리스트
	 */

	List<MainResponse> TofindByKindDesc(String kindhotel);

	/**
	 * 위치기반 리스트 조회
	 * 
	 * @return 위치기반 리스트
	 */

	List<MainResponse> findlocateby(int regionid);

	FacilityResponse facility(int roomid);

	/**
	 * 게시글 수 카운팅
	 * 
	 * @return 게시글 수
	 */
	int count();

}
