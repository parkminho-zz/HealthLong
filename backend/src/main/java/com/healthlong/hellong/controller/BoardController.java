package com.healthlong.hellong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthlong.hellong.model.dto.Board;
import com.healthlong.hellong.model.dto.SearchCondition;
import com.healthlong.hellong.model.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	// 응답을 편하게 하기 위해 상수로 지정
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final BoardService boardService;

	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 게시글 (검색) 조회
	@GetMapping("")
	public ResponseEntity<?> list(@ModelAttribute SearchCondition condition) {
		List<Board> list = boardService.searchBoard(condition); // 검색 조회
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}

	// 게시글 상세 보기
	@GetMapping("/{id}")
	public ResponseEntity<Board> detail(@PathVariable("id") int id) {
		Board board = boardService.readBoard(id);
		if (board != null)
			return new ResponseEntity<Board>(board, HttpStatus.OK);
		return new ResponseEntity<Board>(HttpStatus.NOT_FOUND);
	}

	// 게시글 등록 (JSON형태로 보낸다)
	@PostMapping("")
	public ResponseEntity<?> write(@RequestBody Board board) {
		boardService.writeBoard(board);
		return new ResponseEntity<Board>(board, HttpStatus.CREATED);
	}

	// 게시글 수정 (JSON형태로 보낸다) 경로 중요
//	@PutMapping("/board/{id}") --> PathVariable 처리와 ID 추가 작업 필요
	@PutMapping("")
	public ResponseEntity<String> update(@RequestBody Board board) {
		boardService.modifyBoard(board);
		if (boardService.modifyBoard(board))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	// 게시글 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		if (boardService.removeBoard(id))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
	}	

}	
