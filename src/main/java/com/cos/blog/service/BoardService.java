package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDTO;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional
	public void save(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> selectBoardList(Pageable pageable) {
		Page<Board>  pageBoard = boardRepository.findAll(pageable);
		return  pageBoard;
	}
	
	@Transactional(readOnly = true)
	public Board selectBoardDetail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}

	@Transactional
	public void deleteBoard(int id) {
		// TODO Auto-generated method stub
		boardRepository.deleteById(id);
	}

	@Transactional
	public void updateBoard(int id, Board requestBoard) {
		// TODO Auto-generated method stub
		Board  board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
				});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		
	}

	@Transactional
	public void replySave(int boardId, Reply reply, User user) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글작성에 실패하였습니다.");
				});
		reply.setUser(user);
		reply.setBoard(board);
		replyRepository.save(reply);
	}
	
	@Transactional
	public void replySave(ReplySaveRequestDTO replySaveRequestDTO) {
		replyRepository.mSave(replySaveRequestDTO.getContent(), replySaveRequestDTO.getUserId(), replySaveRequestDTO.getBoardId());
		
	}
	
	@Transactional
	public void replyDelete(int replyId) {
		// TODO Auto-generated method stub
		replyRepository.deleteById(replyId);
	}
	
	

}
