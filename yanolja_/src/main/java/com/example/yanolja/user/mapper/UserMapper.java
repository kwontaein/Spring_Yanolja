package com.example.yanolja.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

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

}
