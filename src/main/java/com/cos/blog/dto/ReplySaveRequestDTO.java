package com.cos.blog.dto;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReplySaveRequestDTO {
	
	private int userId;
	
	private int boardId;
	
	private String content;
	
}
