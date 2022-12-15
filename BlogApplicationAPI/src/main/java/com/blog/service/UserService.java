package com.blog.service;

import com.blog.dto.UserDTO;

public interface UserService {
	
	UserDTO createUser(UserDTO dto);
	UserDTO updateUser(UserDTO dto, Integer id);
	UserDTO getUserById(Integer id);
	void deleteUserById(Integer id);

}
