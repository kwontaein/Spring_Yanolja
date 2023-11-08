package com.example.yanolja.user.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.yanolja.grobal.Response.ImageResponse;
import com.example.yanolja.grobal.Response.MainResponse;
import com.example.yanolja.grobal.Response.ReviewResponse;
import com.example.yanolja.user.mapper.UserMapper;
import com.example.yanolja.user.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	@Autowired
	private final UserMapper userMapper = null;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	// 회원가입
	@Transactional
	public void joinUser(UserVo vo) {
		vo.setPassword(passwordEncoder.encode(vo.getPassword())); // 비밀번호 암호화
		vo.setCreatedate(Timestamp.valueOf(LocalDateTime.now())); // 생성일자 넣기
		vo.setMasteryn("0");// 유저 권한 기본이 0 , 관리자는 1

		if (vo.getName() == null || vo.getName().isEmpty()) {
			// 이름 부분 배열 정의
			String[] firstNameArray = { "경기도", "서울", "강원도", "경상도", "전라도" };
			String[] lastNameArray = { "불주먹", "물주먹", "흙주먹", "깍두기", "통" };

			// 이름 부분 랜덤 선택
			String randomFirstName = getRandomElement(firstNameArray);
			String randomLastName = getRandomElement(lastNameArray);

			// 1부터 20까지의 랜덤한 숫자 생성
			int randomNum = new Random().nextInt(20) + 1;

			// 최종 이름 생성
			String randomName = randomFirstName + " " + randomLastName + " " + randomNum;
			vo.setName(randomName);
		}
		userMapper.userSave(vo);
	}

	// 배열에서 랜덤 요소 선택하는 메서드
	private String getRandomElement(String[] array) {
		Random random = new Random();
		int randomIndex = random.nextInt(array.length);
		return array[randomIndex];
	}

	// 중복체크
	public int emaildupcheck(String email) throws Exception {
		int result = 0;

		result = userMapper.emaildupcheck(email);
		return result;
	}

	// 로그인
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// 여기서 받은 유저 패스워드와 비교하여 로그인 인증
		UserDetails userdetails = (UserDetails) userMapper.getUserAccount(email); // email로 쿼리문 실행
		System.out.println(email);
		System.out.println(userdetails);
		if (userdetails == null) {// 받아온 값이 있다면
			System.out.println("값이 없습니다");
			throw new UsernameNotFoundException("User not authorized.");
		}
		return userdetails;
	}

	public List<ReviewResponse> UserByreview(int userid) {
		// TODO Auto-generated method stub
		return userMapper.UserByreview(userid);
	}

	public int UserByCnt(int userid) {
		// TODO Auto-generated method stub
		return userMapper.UserByCnt(userid);
	}

	public List<ImageResponse> reviewUserPhotos(int userid) {
		// TODO Auto-generated method stub
		return userMapper.reviewUserPhotos(userid);
	}

	// 좋아요 여부 확인
	public int selectLike(int hotelid, int userid) {

		return userMapper.selectLike(hotelid, userid);
	}

	// 좋아요 삭제
	public void deleteLike(int hotelid, int userid) {

		userMapper.deleteLike(hotelid, userid);
	}

	// 좋아요 추가
	public void insertLike(int hotelid, int userid) {

		userMapper.insertLike(hotelid, userid);
	}

	// 좋아요 한 호텔 목록 조회
	public List<MainResponse> Likehotels(int userid) {

		return userMapper.Likehotels(userid);
	}

}
