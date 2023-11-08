package com.example.yanolja.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.yanolja.grobal.Response.ImageResponse;
import com.example.yanolja.grobal.Response.MainResponse;
import com.example.yanolja.grobal.Response.ReserveResponse;
import com.example.yanolja.grobal.Response.ReviewResponse;
import com.example.yanolja.grobal.Response.RoomResponse;

@Mapper
public interface MainMapper {

	List<MainResponse> findPostById(Long hotelid, String kindhotel);

	List<MainResponse> findAll();

	List<RoomResponse> findRoom(int hotelid);

	ReserveResponse selectForReview(int roomid);

	String hotelLocationex(int hotelid);

	int findHotelId(int roomid);

	List<MainResponse> findAllFrom(int regionid, String kindhotel);

	List<MainResponse> TofindByKind(String kindhotel);

	List<MainResponse> TofindByKindDesc(String kindhotel);

	List<MainResponse> findlocateby(int regionid);

	int count();

	void insertReview(int hotelid, int roomid, int userid, double rating1, double rating2, double rating3,
			double rating4, String textData);

	void updateReivewYn(int bookid);

	void saveImage(int hotelid, int currentReviewid, String originalFileName, byte[] imageBytes, int userid);

	void saveImageTest(String originalFileName, byte[] imageBytes, int roomid);

	int lastReviewid();

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

	Integer isBooked(Integer roomid, Integer bookid);

}
