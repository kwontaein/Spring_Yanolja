package com.example.yanolja.main.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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

@Mapper
public interface MainMapper {

	MainResponse findById(Long hotelid);

	List<MainResponse> findPostById(Long hotelid, String kindhotel);

	List<MainResponse> findAll();

	List<RoomResponse> findRoom(int hotelid);

	List<RoomResponse> findRoomByDate(int hotelid, String StartDate);

	List<RoomResponse> cartlist(List<Integer> roomIds);

	RoomResponse cartlist2(Integer roomId);

	RoomResponse findRoomDetail(int roomid, String StartDate);

	String findUPhone(String uname);

	List<ReserveResponse> reserve_possible(int hotelid);

	ReserveResponse selectForReview(int roomid);

	String hotelLocationex(int hotelid);

	TrafficResponse hotelLocation(int hotelid);

	List<InfoResponse> hotelinfo(int hotelid);

	List<PolicyResponse> hotelpolicy(int hotelid);

	int findHotelId(int roomid);

	List<ReviewResponse> review(int hotelid, String roomname, String orderby, boolean onlyPhoto);

	List<String> roomnameList(int hotelid);

	List<ReviewResponse> reviewroom(int roomid, String orderby, boolean onlyPhoto);

	ReviewResponse rating_detail(int hotelid);

	String hotelintro(int hotelid);

	int roomcnt(int hotelid);

	int reviewroomcnt(int roomid);

	List<MainResponse> findAllFrom(int regionid, String kindhotel);

	List<MainResponse> TofindByKind(String kindhotel);

	List<MainResponse> TofindByKindDesc(String kindhotel);

	List<MainResponse> findlocateby(int regionid);

	FacilityResponse facility(int roomid);

	int count();

	Map<String, Integer> findHRids(String roomname, String hotelname);

	void insertReserve(List<Map<String, Object>> parameterMap, String user_name, String user_phone,
			String order_number);

	void insertReserveOne(int hotelid, int roomid, String date1List, String date2List, String user_name,
			String user_phone, String order_number);

	void insertBook(int userid, int roomid, String bookdate);

	void insertBookList(int userid, List<Map<String, Object>> parameterMap, String order_number);

	List<ReserveResponse> selectReserve_order(String ordernumber);

	List<BookResponse> selectBook(int userid, int period);

	List<ReserveResponse> select_p_Reserve(String name, String phone, String order_number);

	void insertReview(int hotelid, int roomid, int userid, double rating1, double rating2, double rating3,
			double rating4, String textData);

	void updateReivewYn(int bookid);

	void saveImage(int hotelid, int currentReviewid, String originalFileName, byte[] imageBytes, int userid);

	void saveImageTest(String originalFileName, byte[] imageBytes, int roomid);

	int lastReviewid();

	List<ImageResponse> reviewAllPhotos(Integer hotelid);

	void DeleteReviewById(int reviewid);

	void DeletePhotoById(int reviewid);

	int selectRsByreview(int reviewid);

	ReviewResponse selectForReviewUpdate(int roomIdbyReview);

	List<ImageResponse> ReviewInseredPhoto(Integer reviewid);

	void updateImage(String originalFileName, byte[] imageBytes, int reviewid, int userid);

	void DelPhotoByimgid(int imgid);

	void updateReview(double rating1, double rating2, double rating3, double rating4, String textData, int reviewid);

	List<String> findRegionName();

	List<MainResponse> findAllFromRegion(String regionname, String kindhotel);

	List<MainResponse> findAllFromRd(String regionname, String kindhotel);

	void insertLike(int hotelid, int userid);

	int selectLike(int hotelid, int userid);

	void deleteLike(int hotelid, int userid);

	List<MainResponse> Likehotels(int userid);

	List<CouponResponse> selectcoupon(int userid);
}
