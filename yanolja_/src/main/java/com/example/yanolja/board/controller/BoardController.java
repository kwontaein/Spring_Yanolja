package com.example.yanolja.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.yanolja.board.model.BoardService;
import com.example.yanolja.board.post.PostRequest;
import com.example.yanolja.board.post.PostResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	@Autowired
	BoardService boardService;


	// 게시글 작성 페이지
	@GetMapping("board/write")
	public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
		// 수정시 실행
		if (id != null) {
			PostResponse post = boardService.findPostById(id);
			model.addAttribute("post", post);
		}
		return "Board/write";
	}

	// 게시글 생성
	@PostMapping("board/write.do")
	public String savePost(final PostRequest params) {
		System.out.println("write실행");
		boardService.savePost(params);
		System.out.println(params);
		return "redirect:/board/list.do";
	}

	// 수정하기
	@PostMapping("/board/update.do")
	public String updatePost(final PostRequest params) {
		System.out.println("update실행");
		boardService.updatePost(params);
		return "redirect:/board/list.do";
	}

	// 목록 조회
	@GetMapping("/board/list.do")
	public String openPostList(Model model) {
		List<PostResponse> post = boardService.findAllPost();
		model.addAttribute("post", post);
		return "Board/List";
	}

	// 내용 보기
	@GetMapping("board/view.do")
	public String openPostView(@RequestParam final Long id, Model model) {
		PostResponse post = boardService.findPostById(id);
		model.addAttribute("post", post);
		return "Board/view";
	}

}
