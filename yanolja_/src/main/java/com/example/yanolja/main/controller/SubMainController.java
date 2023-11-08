package com.example.yanolja.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.yanolja.grobal.Response.MainResponse;
import com.example.yanolja.main.model.MainService;

@Controller
@RequestMapping(value = "/sub_main")
public class SubMainController {

	@Autowired
	MainService mainService;

	@GetMapping("/{kind}")
	public String List(@PathVariable String kind, Model model) {
		// kind 변수는 요청 경로에서 추출된 호텔 종류입니다.
		// 이를 사용하여 서비스 메서드를 호출하고 모델에 데이터를 추가합니다.
		List<MainResponse> post = mainService.TofindByKind(kind);
		model.addAttribute("post", post);
		model.addAttribute("kind", kind);

		return "SubMain/sub_main";
	}

	@GetMapping("/list")
	public String list(@RequestParam("regionid") int regionid, @RequestParam("kind") String kind, Model model) {

		// 지역과 호텔 종류에 따라 호텔 목록을 조회하고 "post" 모델 속성에 추가
		List<MainResponse> post = mainService.findAllFrom(regionid, kind);
		model.addAttribute("post", post);
		return "SubMain/list/list"; // Main/Hotellist 템플릿을 렌더링
	}

	@GetMapping("/Tophotellist{number}")
	public String TopHotellist(@PathVariable("number") String number, @RequestParam("kind") String kind, Model model) {
		List<MainResponse> post = mainService.TofindByKindDesc(kind);
		model.addAttribute("post", post);
		return "SubMain/list/Tophotellist" + number; // Main/Hotellist 템플릿을 렌더링
	}

	// 위치 기반 선택 (수정필요)
	@GetMapping("/selectlocation")
	public String selectlocation(@RequestParam("selectedText") String selectedText, @RequestParam("kind") String kind,
			Model model) {

		List<String> RDList = mainService.findRegionDetail(selectedText);

		model.addAttribute("RDList", RDList);

		model.addAttribute("selectedText", selectedText);

		model.addAttribute("kind", kind);

		return "SubMain/locationlist/seoul";
	}

}
