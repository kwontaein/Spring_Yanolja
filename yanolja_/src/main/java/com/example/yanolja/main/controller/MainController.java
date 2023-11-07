package com.example.yanolja.main.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.yanolja.grobal.ImageResponse;
import com.example.yanolja.grobal.MainResponse;
import com.example.yanolja.grobal.ReserveResponse;
import com.example.yanolja.grobal.ReviewResponse;
import com.example.yanolja.main.model.MainService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080") // 허용할 도메인을 지정
public class MainController {

	@Autowired
	MainService mainService;

// 테스트 호출--------------------------------------------------------------------------------
	@PostMapping("/test")
	@ResponseBody
	public boolean test() {
		Integer a = 0;
		return a != null;
	}

	@GetMapping("/test2")
	public String test2() {

		return "test/test";
	}

	// 이미지 업로드 테스트
	@ResponseBody
	@PostMapping("/file-upload")
	public String testfileUpload(@RequestParam("article_file") MultipartFile[] multipartFiles) {
		String strResult = "{ \"result\":\"FAIL\" }";

		for (MultipartFile file : multipartFiles) {
			try {
				byte[] imageBytes = file.getBytes();
				String originalFileName = file.getOriginalFilename();
				// 이제 이미지 데이터를 데이터베이스에 저장하는 서비스 메서드를 호출
				System.out.println(originalFileName + " " + imageBytes);
				for (int i = 2; i < 97; i += 2) {
					mainService.saveImageTest(originalFileName, imageBytes, i);
					System.out.println(i + "번째 실행");
				}
				// mainService.saveImage(originalFileName, imageBytes);
				strResult = "{ \"result\":\"OK\" }";
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		return strResult;
	}

//kakao카카오 페이 결제 페이지--------------------------------------------------------------------------------
	@GetMapping("/KakaoPayPage")
	public String kakaopay() {
		return "KakaoPay/KaKaoPay";
	}

//Main메인페이지 호출--------------------------------------------------------------------------------
	@GetMapping("/")
	public String openMain(HttpSession session) {
		String sessionDate1 = (String) session.getAttribute("sessionDate1");
		String sessionDate2 = (String) session.getAttribute("sessionDate2");
		if (sessionDate1 == null && sessionDate2 == null) {

			LocalDate nowDate = LocalDate.now();
			LocalDate tomorrowDate = nowDate.plusDays(1);

			sessionDate1 = nowDate.toString();
			sessionDate2 = tomorrowDate.toString();
			session.setAttribute("sessionDate1", sessionDate1);
			session.setAttribute("sessionDate2", sessionDate2);
		}
		return "Main/Main"; // Main/Main 템플릿을 렌더링
	}

	// 전체보기 호출
	@GetMapping("/ViewAll")
	public String ViewAll(@RequestParam("data") String data, Model model) {
		model.addAttribute("data", data);
		return "Main/SeeAllView";
	}

//Main리스트 --------------------------------------------------------------------------------
	@GetMapping("/ResentRelated")
	public String ResentRelated(HttpSession session, Model model) {
		Long hotelid = (Long) session.getAttribute("resentViewHotelid");
		String kindhotel = (String) session.getAttribute("resentViewKindHotel");
		List<MainResponse> post = mainService.findPostById(hotelid, kindhotel);
		model.addAttribute("post", post);
		return "lists/ResentRelated";
	}

	@GetMapping("/Hotellist")
	public String Hotellist(@RequestParam("regionname") String regionname, @RequestParam("kindhotel") String kindhotel,
			Model model) {
		// MainResponse에 찾아오려는 값이 null이면 오류남
		List<MainResponse> post = posts(regionname, kindhotel);
		model.addAttribute("post", post);
		return "lists/Hotellist"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/Nonslidelist")
	public String Nonslidelist(@RequestParam("regionname") String regionname,
			@RequestParam("kindhotel") String kindhotel, Model model) {
		// MainResponse에 찾아오려는 값이 null이면 오류남
		List<MainResponse> post = posts(regionname, kindhotel);
		model.addAttribute("post", post);
		return "lists/Nonslidelist"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/ViewHotels")
	public String ViewHotels(@RequestParam("regionname") String regionname, @RequestParam("kindhotel") String kindhotel,
			Model model) {
		// MainResponse에 찾아오려는 값이 null이면 오류남
		List<MainResponse> post = posts(regionname, kindhotel);
		model.addAttribute("post", post);
		return "lists/ViewHotels"; // Main/Hotellist 템플릿을 렌더링
	}

	public List<MainResponse> posts(String regionname, String kindhotel) {
		// region 테이블에서 지역 명 가져오기
		List<MainResponse> post = mainService.findAllFromRegion(regionname, kindhotel);
		if (post.isEmpty()) {
			List<MainResponse> post2 = mainService.findAllFromRd(regionname, kindhotel);
			return post2;
		} else {
			return post;
		}
	}

// 스와이퍼 --------------------------------------------------------------------------------

	@GetMapping("/eventrolling")
	public String eventrolling() {
		return "Main/eventrolling"; // Main/ex 템플릿을렌더링
	}

	// 슬라이드 호출
	@GetMapping("/slide")
	public String slide() {
		return "Main/hotelslide"; // Main/Main 템플릿을 렌더링
	}

//Main검색 기능 --------------------------------------------------------------------------------------	
	@GetMapping("/Search")
	public String search() {
		return "Search/search";
	}

	// ajax 페이지 구분
	@GetMapping("/Searchdetail")
	public String Searchdetail(@RequestParam String category) {
		if (category.equals("국내숙소")) {
			return "Search/koreahotel";
		} else if (category.equals("레저/티켓")) {
			return "Search/ticket";
		} else if (category.equals("교통/항공")) {
			return "Search/traffic";
		} else if (category.equals("해외숙소")) {
			return "Search/outland";
		} else {
			return "Search/koreahotel";
		}
	}

//후기 관련---------------------------------------------------------------------------------------------------------
	// 후기작성
	@GetMapping("/writeReview")
	public String writeReview(@RequestParam(value = "roomid", required = false) Integer roomid,
			@RequestParam(value = "bookid", required = false) Integer bookid,
			@RequestParam(value = "reviewid", required = false) Integer reviewid, Model model, HttpSession session) {

		// 유저아이디, 해당 아이디와 룸아이디로 이루어진 예약정보가 없거나, 후기 갯수 < 해당 정보 라면 잘못된 접근입니다. 라는 문구 작성

		// 유저정보 확인
		Integer userid = (Integer) session.getAttribute("userid");

		if (userid != null) {
			// 호텔, 객실이름 및 사용자 이름 가져오기
			if (reviewid != null && roomid == null && bookid == null) {
				// rating 정보랑 이미지 정보 불러와서 출력.
				int roomIdbyReview = mainService.selectRsByreview(reviewid);
				ReviewResponse loadRs = mainService.selectForReviewUpdate(reviewid);
				List<ImageResponse> imgs = mainService.ReviewInseredPhoto(reviewid);
				model.addAttribute("loadRs", loadRs);
				model.addAttribute("roomid", roomIdbyReview);
				model.addAttribute("imgs", imgs);
				return "User/UpdateReview";
			} else {
				// 에약정보가 이미 있는지 확인
				Integer isBooked = mainService.isBooked(roomid, bookid);
				if (isBooked != null && isBooked == 0) {
					// 여긴 그냥 기본 정보만
					ReserveResponse loadRs = mainService.selectForReview(roomid);
					model.addAttribute("loadRs", loadRs);
					model.addAttribute("roomid", roomid);
					model.addAttribute("bookid", bookid);
					return "User/writeReview";
				} else {
					return "Main/Invalid/Invalid_approach";
				}
			}
		} else {
			return "Main/Invalid/Invalid_approach";
		}

	}

	// 후기 저장
	@PostMapping("/writeReview.do")
	@ResponseBody
	public String insertReview(@RequestParam(value = "rating1") double rating1,
			@RequestParam(value = "rating2") double rating2, @RequestParam(value = "rating3") double rating3,
			@RequestParam(value = "rating4") double rating4, @RequestParam(value = "textData") String textData,
			@RequestParam(value = "roomid") int roomid,
			@RequestParam(value = "bookid", required = false) Integer bookid,
			@RequestParam(value = "article_file", required = false) MultipartFile[] multipartFiles,
			HttpSession session) {

		// 호텔아이디 받아서 insert

		int userid = (int) session.getAttribute("userid");
		int hotelid = mainService.findHotelId(roomid);

		mainService.insertReview(hotelid, roomid, userid, rating1, rating2, rating3, rating4, textData);// 후기 정보 넣기

		int CurrentReviewid = mainService.lastReviewid();
		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {
				try {
					byte[] imageBytes = file.getBytes();
					String originalFileName = file.getOriginalFilename();
					// 이제 이미지 데이터를 데이터베이스에 저장하는 서비스 메서드를 호출
					mainService.saveImage(hotelid, CurrentReviewid, originalFileName, imageBytes, userid);// 이미지 파일 넣기
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
		if (bookid != null)
			mainService.updateReivewYn(bookid);

		return "success";
	}

	// 후기 수정
	@PostMapping("/UpdateReview.do")
	@ResponseBody
	public String UpdateReview(@RequestParam(value = "rating1") double rating1,
			@RequestParam(value = "rating2") double rating2, @RequestParam(value = "rating3") double rating3,
			@RequestParam(value = "rating4") double rating4, @RequestParam(value = "textData") String textData,
			@RequestParam(value = "roomid") int roomid, @RequestParam(value = "reviewid") int reviewid,
			@RequestParam(value = "article_file", required = false) MultipartFile[] multipartFiles,
			HttpSession session) {
		// 바뀐 별점 / 삭제할 이미지 목록 + 추가한 이미지 목록 등
		// 바뀐 이미지들 목록 받아와서 이전 정보와 비교 / 삭제 할 거 지우고 없던 거 추가 등 실행
		// 배열 두개 생성 -> 반복문 돌면서 두 배열 비교 -> 바뀌지 않은 게 있으면 그대로 배열에 저장 후 나머지는 두번째 목록의 배열 값을
		// 저장
		int userid = (int) session.getAttribute("userid");
		int hotelid = mainService.findHotelId(roomid);

		List<ImageResponse> imgs = mainService.ReviewInseredPhoto(reviewid);

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
				mainService.DelPhotoByimgid(imgId); // 이미지 삭제
			}
		}
		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {
				try {
					byte[] imageBytes = file.getBytes();
					String originalFileName = file.getOriginalFilename();
					if (loadedImgIds.contains(originalFileName)) { // 이미 불러온 이미지의 처리
					} else { // 새이미지의 처리
						mainService.saveImage(hotelid, reviewid, originalFileName, imageBytes, userid); // 이미지 파일 넣기
					}
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
		mainService.updateReview(rating1, rating2, rating3, rating4, textData, reviewid);// 후기 정보 넣기
		return "success";
	}

	// 후기 삭제
	@PostMapping("/DeleteReview.Do")
	@ResponseBody
	public String DeleteReview(@RequestParam(value = "reviewid") int reviewid) {
		mainService.DeleteReviewById(reviewid);
		mainService.DeletePhotoById(reviewid);
		return "success";
	}

//--------------------------------------------------------------------------------------------------------
}
