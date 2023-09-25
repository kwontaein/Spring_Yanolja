package com.example.yanolja.board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yanolja.board.mapper.BoardMapper;
import com.example.yanolja.board.post.PostRequest;
import com.example.yanolja.board.post.PostResponse;

import jakarta.transaction.Transactional;

@Service
public class BoardService {

	@Autowired
	private final BoardMapper boardMapper = null;

	/**
	 * 게시글 저장
	 * 
	 * @param params - 게시글 정보
	 * @return Generated PK
	 */
	@Transactional
	public Long savePost(final PostRequest params) {
		boardMapper.save(params);
		return params.getId();
	}

	/**
	 * 게시글 상세정보 조회
	 * 
	 * @param id - PK
	 * @return 게시글 상세정보
	 */
	public PostResponse findPostById(final Long id) {
		return boardMapper.findById(id);
	}

	/**
	 * 게시글 수정
	 * 
	 * @param params - 게시글 정보
	 * @return PK
	 */
	@Transactional
	public Long updatePost(final PostRequest params) {
		boardMapper.update(params);
		return params.getId();
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param id - PK
	 * @return PK
	 */
	public Long deletePost(final Long id) {
		boardMapper.deleteById(id);
		return id;
	}

	/**
	 * 게시글 리스트 조회
	 * 
	 * @return 게시글 리스트
	 */
	public List<PostResponse> findAllPost() {
		return boardMapper.findAll();
	}

}
