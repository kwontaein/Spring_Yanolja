package com.example.yanolja.main.model;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.yanolja.grobal.Response.ImageResponse;
import com.example.yanolja.grobal.Response.MainResponse;
import com.example.yanolja.grobal.Response.ReserveResponse;
import com.example.yanolja.grobal.Response.ReviewResponse;
import com.example.yanolja.main.mapper.MainMapper;

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
		return mainMapper.findAllFromRegion(regionname, kindhotel);
	}

	// 세부 지역 명으로 찾기
	public List<MainResponse> findAllFromRd(String regionname, String kindhotel) {
		return mainMapper.findAllFromRd(regionname, kindhotel);
	}
	
	// 세부 지역 명'들'로 찾기
	public List<MainResponse> findAllFromRds(List<String> regionname, String kindhotel) {
		return mainMapper.findAllFromRds(regionname, kindhotel);
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
			double rating4, String textData, MultipartFile[] multipartFiles) {
		int CurrentReviewid = lastReviewid();

		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {
				try {
					byte[] imageBytes = file.getBytes();
					String originalFileName = file.getOriginalFilename();
					// 이제 이미지 데이터를 데이터베이스에 저장하는 서비스 메서드를 호출
					saveImage(hotelid, CurrentReviewid, originalFileName, imageBytes, userid);// 이미지 파일 넣기
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}

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
	public void saveImageTest(String originalFileName, byte[] imageBytes, int roomid) {
		mainMapper.saveImageTest(originalFileName, imageBytes, roomid);
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

	public Integer isBooked(Integer roomid, Integer bookid) {
		return mainMapper.isBooked(roomid, bookid);
	}

	public List<MainResponse> posts(String regionname, String kindhotel) {
		// region 테이블에서 지역 명 가져오기
		List<MainResponse> post = findAllFromRegion(regionname, kindhotel);
		if (post.isEmpty()) {
			List<MainResponse> post2 = findAllFromRd(regionname, kindhotel);
			return post2;
		} else {
			return post;
		}
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

	// 리뷰 사진 업데이트 메소드
	public void UpdateReviewImg(int userid, int roomid, int reviewid, MultipartFile[] multipartFiles) {

		int hotelid = findHotelId(roomid);

		List<ImageResponse> imgs = ReviewInseredPhoto(reviewid);

		// 이미 불러온 이미지의 imgid 값을 저장하는 Set
		Set<String> loadedImgIds = new HashSet<>();
		// 이미지 아이디 목록 저장
		for (ImageResponse img : imgs) {
			loadedImgIds.add(img.getImgname());
		}

		// 받아온 파일의 이름 목록 저장
		Set<String> uploadedFileNames = new HashSet<>();

		for (MultipartFile file : multipartFiles) {
			uploadedFileNames.add(file.getOriginalFilename());
		}

		// loadedImgIds에 있는 id 값과 uploadedFileNames의 id 값 비교 후 없을 시 삭제
		for (ImageResponse img : imgs) {
			String imgName = img.getImgname();
			int imgId = img.getImgid();
			if (!uploadedFileNames.contains(imgName)) { // 이미 불러온 이미지이지만 현재 업로드한 파일과 관련이 없는이미지 처리
				DelPhotoByimgid(imgId); // 이미지 삭제
			}
		}

		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {
				try {
					byte[] imageBytes = file.getBytes();
					String originalFileName = file.getOriginalFilename();
					if (loadedImgIds.contains(originalFileName)) { // 이미 불러온 이미지의 처리
					} else { // 새이미지의 처리
						saveImage(hotelid, reviewid, originalFileName, imageBytes, userid); // 이미지 파일 넣기
					}
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}

	public List<String> findRegionDetail(String selectedText) {
		// TODO Auto-generated method stub
		return mainMapper.findRegionDetail(selectedText);
	}

	public List<MainResponse> LocatePost(List<String> region, String kind) {
		// region 테이블에서 지역 명 가져오기
		List<MainResponse> post = findAllFromRegion(region.get(0), kind);
		if (post.isEmpty()) {
			List<MainResponse> post2 = findAllFromRds(region, kind);
			return post2;
		} else {
			return post;
		}
	}
}
