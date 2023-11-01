package com.example.yanolja.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.yanolja.grobal.MainResponse;
import com.example.yanolja.main.model.MainService;

@Controller
@RequestMapping(value = "/sub_main")
public class SubMainController {

	@Autowired
	MainService mainService;

// 전역변수의 경우 javascript 경우에 선언해주는 게 제일 Best!
// Controller 의 경우 어노테이션 및 데이터 반환 처리,  service 넘겨주는 구조 -> mvc구조 선언 해주는 역할이기 때문
	String kindhotel;

	@GetMapping("/{kind}")
	public String List(@PathVariable String kind, Model model) {
		// kind 변수는 요청 경로에서 추출된 호텔 종류입니다.
		// 이를 사용하여 서비스 메서드를 호출하고 모델에 데이터를 추가합니다.
		kindhotel = kind;
		List<MainResponse> post = mainService.TofindByKind(kind);
		model.addAttribute("post", post);
		model.addAttribute("kind", kind);

		return "SubMain/sub_main";
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
	//위치 기반 선택 (수정필요)
	@GetMapping("/selectlocation")
	public String selectlocation(@RequestParam("selectedText") String selectedText, Model model) {
		model.addAttribute("kind", kindhotel);
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
