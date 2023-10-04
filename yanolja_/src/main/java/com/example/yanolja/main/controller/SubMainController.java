package com.example.yanolja.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.yanolja.main.model.MainService;
import com.example.yanolja.main.post.MainResponse;

@Controller
@RequestMapping(value = "/sub_main")
public class SubMainController {

	@Autowired
	MainService mainService;

	String kindhotel;

	@GetMapping("/motel")
	public String motellist(Model model) {

		// MainResponse에 찾아오려는 값이 null이면 오류남
		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		kindhotel = "모텔";
		List<MainResponse> post = mainService.TofindByKind(kindhotel);
		model.addAttribute("post", post);
		model.addAttribute("kind", "motel");
		return "SubMain/sub_main"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/hotel")
	public String Hotellist(Model model) {

		// MainResponse에 찾아오려는 값이 null이면 오류남
		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		// 호텔 인기순으로 바꿀 예정
		kindhotel = "호텔";
		List<MainResponse> post = mainService.TofindByKind(kindhotel);
		model.addAttribute("post", post);
		model.addAttribute("kind", "hotel");
		return "SubMain/sub_main"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/pension")
	public String pensionlist(Model model) {

		// MainResponse에 찾아오려는 값이 null이면 오류남
		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		kindhotel = "펜션";
		List<MainResponse> post = mainService.TofindByKind(kindhotel);
		model.addAttribute("post", post);
		model.addAttribute("kind", "pension");
		return "SubMain/sub_main"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/familly")
	public String famillylist(Model model) {
		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		kindhotel = "게스트하우스";
		List<MainResponse> post = mainService.TofindByKind(kindhotel);
		model.addAttribute("post", post);
		model.addAttribute("kind", "guest");
		return "SubMain/sub_main"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/list")
	public String list(@RequestParam("regionid") int regionid, Model model) {

		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		List<MainResponse> post = mainService.findAllFrom(regionid, kindhotel);
		model.addAttribute("post", post);
		return "SubMain/list/list"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/Tophotellist{number}")
	public String TopHotellist(@PathVariable("number") String number, Model model) {
		// MainResponse에 찾아오려는 값이 null이면 오류남
		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		// 호텔 인기순으로 바꿀 예정
		List<MainResponse> post = mainService.TofindByKindDesc(kindhotel);
		model.addAttribute("post", post);
		return "SubMain/list/Tophotellist" + number; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/selectlocation")
	public String selectlocation(@RequestParam("selectedText") String selectedText) {
		if (selectedText.equals("서울")) {
			return "SubMain/locationlist/seoul";
		} else if (selectedText.equals("부산")) {
			return "SubMain/locationlist/busan";
		} else if (selectedText.equals("제주")) {
			return "SubMain/locationlist/jeju";
		} else if (selectedText.equals("경기")) {
			return "SubMain/locationlist/geonggi";
		} else if (selectedText.equals("인천")) {
			return "SubMain/locationlist/incheon";
		} else if (selectedText.equals("강원")) {
			return "SubMain/locationlist/kangwon";
		} else if (selectedText.equals("경상")) {
			return "SubMain/locationlist/kungsang";
		} else if (selectedText.equals("전라")) {
			return "SubMain/locationlist/jeonra";
		} else if (selectedText.equals("충청")) {
			return "SubMain/locationlist/chungchong";
		} else {
			return "SubMain/locationlist/seoul";
		}
	}

}
