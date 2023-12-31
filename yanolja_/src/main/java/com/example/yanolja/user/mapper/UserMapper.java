package com.example.yanolja.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.yanolja.grobal.Response.ImageResponse;
import com.example.yanolja.grobal.Response.MainResponse;
import com.example.yanolja.grobal.Response.ReviewResponse;
import com.example.yanolja.user.CustomUserDetails;
import com.example.yanolja.user.vo.UserVo;

@Mapper
public interface UserMapper {
	// 로그인
	public CustomUserDetails getUserAccount(String email);

	// 회원가입
	void userSave(UserVo vo);

	// 중복체크
	int emaildupcheck(String email) throws Exception;

	public List<ReviewResponse> UserByreview(int userid);

	public int UserByCnt(int userid);

	public List<ImageResponse> reviewUserPhotos(int userid);
	
	void insertLike(int hotelid, int userid);

	int selectLike(int hotelid, int userid);

	void deleteLike(int hotelid, int userid);

	List<MainResponse> Likehotels(int userid);
}
