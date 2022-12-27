package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.CommentDTO;
import com.blog.entities.Comment;
import com.blog.repositories.ApiResponse;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentControlle {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create/user/{userId}/post/{postId}")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDto, @PathVariable Integer userId, @PathVariable Integer postId){
		
		CommentDTO createComment=commentService.createComment(commentDto, postId, userId);
		
		return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		
		commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);
		
	}

}
