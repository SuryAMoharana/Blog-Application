package com.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.UserDTO;
import com.blog.repositories.ApiResponse;
import com.blog.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> createuser(@Valid @RequestBody UserDTO userDto ){
		
		UserDTO createdUserDto=userService.createUser(userDto);
		
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable Integer userId){
		
		UserDTO updatedUser=this.userService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updatedUser);		
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){
		
		this.userService.deleteUserById(uid);
		//return new ResponseEntity(Map.of("message","User deleted successfullly"),HttpStatus.OK);
		return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/alluser")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		
		return ResponseEntity.ok(this.userService.getAllUser());
		
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userid){
		
		return ResponseEntity.ok(this.userService.getUserById(userid));
		
	}

}
