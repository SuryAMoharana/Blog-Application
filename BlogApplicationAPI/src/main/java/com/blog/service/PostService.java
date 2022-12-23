package com.blog.service;

import java.util.List;

import com.blog.dto.PostDTO;
import com.blog.dto.PostResponse;
import com.blog.entities.Post;

public interface PostService {
	
	PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId);
	
	PostDTO updatePost (PostDTO postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize);
	
	PostDTO getPostById(Integer postId);
	
	List<PostDTO> getPostByCategory(Integer categoryId);
	
	List<PostDTO> getPostByUser(Integer userId);
	
	List<PostDTO> searchPost(String keyword);
	
	

}
