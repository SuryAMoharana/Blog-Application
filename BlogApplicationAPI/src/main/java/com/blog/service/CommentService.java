package com.blog.service;

import com.blog.dto.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO commentDto, Integer postId, Integer userId);
	
	void deleteComment(Integer commentId);
	

}
