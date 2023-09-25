package com.example.yanolja.kakao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.yanolja.kakao.oauth2.User;
@Mapper
public interface KakaoMapper {

	User findByUsername(String username);

	User findByEmail(String email);

	void insertUser(User user);
}
