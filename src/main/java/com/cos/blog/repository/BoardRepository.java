package com.cos.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;


// DAO
// 자동으로 빈으로 등록됨
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	
}
