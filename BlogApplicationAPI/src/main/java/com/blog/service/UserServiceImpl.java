package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dto.UserDTO;
import com.blog.repositories.UserRepo;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo urepo;

	@Override
	public UserDTO createUser(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateUser(UserDTO dto, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub

	}

}
