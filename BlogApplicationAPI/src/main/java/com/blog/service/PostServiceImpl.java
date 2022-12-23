package com.blog.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDTO;
import com.blog.dto.PostResponse;
import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id", userId));
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(cat);
		post.setUser(user);
		
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDto, Integer postId) {
		
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		this.postRepo.save(post);
		
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));		
		postRepo.delete(post);
	}	
	

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
		
		//with pagination
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost=postRepo.findAll(p);
		List<Post> allPosts=pagePost.getContent();
		List<PostDTO> postDtos=allPosts.stream().map((Post)->modelMapper.map(Post, PostDTO.class)).collect(Collectors.toList());

		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
		
		
		//List<Post> allPosts=this.postRepo.findAll();
		//List<PostDTO> postDtos=allPosts.stream().map((Post)->modelMapper.map(Post, PostDTO.class)).collect(Collectors.toList());
		//return postDtos;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		 
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
				
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts=postRepo.findByCategory(cat);
		
		List<PostDTO> postDtos=posts.stream().map((post)->modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id", userId));
		List<Post> posts=postRepo.findByUser(user);
		
		List<PostDTO> postDtos=posts.stream().map((post)->modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
