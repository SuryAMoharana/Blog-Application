package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstants;
import com.blog.dto.ImageResponse;
import com.blog.dto.PostDTO;
import com.blog.dto.PostResponse;
import com.blog.entities.Post;
import com.blog.repositories.ApiResponse;
import com.blog.service.FileService;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto,
												@PathVariable Integer userId,
												@PathVariable Integer categoryId){
		
		
		PostDTO createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
						
		
	}
	
	@GetMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userid){
		
		List<PostDTO> posts=this.postService.getPostByUser(userid);
		
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryid){
		
		List<PostDTO> posts=this.postService.getPostByCategory(categoryid);
		
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
		
	}
	
	@GetMapping("/allPosts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE, required=false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.SORT_BY, required=false) String sortBY,
			@RequestParam(value="sortDir", defaultValue = AppConstants.SORT_DIR, required=false) String sortDir
			){
		PostResponse postResponse=this.postService.getAllPost(pageNumber, pageSize, sortBY, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		PostDTO postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDto, HttpStatus.OK);		
		
	}
	
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable Integer postId){
		
		PostDTO updatedPost=postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDTO>(updatedPost, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		
		postService.deletePost(postId);
		
		return new ApiResponse("Post is deleted successfully",true);
		
	}
	
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keywords){
		
		List<PostDTO> result=postService.searchPost(keywords);
		
		return new ResponseEntity<List<PostDTO>>(result, HttpStatus.OK);
		
	}
	
	//POST IMAGE UPLOAD
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException{
		
		String fileName=this.fileService.uploadImage(path, image);
		
		PostDTO postDto=postService.getPostById(postId);
		
		postDto.setImageName(fileName);
		
		PostDTO updatedPost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatedPost, HttpStatus.OK);	
		
	}
	
	@GetMapping(value="/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		
		
		InputStream resource=fileService.getResource(path, imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
		
	}

}
