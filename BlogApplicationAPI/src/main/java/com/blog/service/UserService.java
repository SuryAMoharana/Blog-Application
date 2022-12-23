package com.blog.service;

import java.util.List;

import com.blog.dto.UserDTO;



public interface UserService {
	
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer userId);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getAllUser();
	void deleteUserById(Integer userId);

}
