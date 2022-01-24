package com.cos.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDTO;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> board(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		boardService.save(board, principalDetail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.deleteBoard(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> updateBoard(@PathVariable int id, @RequestBody Board board) {
		boardService.updateBoard(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 데이터를 받을때 dto를 만들어주는게 좋다.
	// dto를 사용하지 않는 이유는!!
	@PostMapping("/api/board/{boardId}/reply1")
	public ResponseDto<Integer> replySave1(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
		
		boardService.replySave(boardId,reply,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 데이터를 받을때 dto를 만들어주는게 좋다.
	// dto를 사용하지 않는 이유는!!
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDTO replySaveRequestDTO) {
			
			boardService.replySave(replySaveRequestDTO);
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 데이터를 받을때 dto를 만들어주는게 좋다.
	// dto를 사용하지 않는 이유는!!
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
				
			boardService.replyDelete(replyId);
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
}