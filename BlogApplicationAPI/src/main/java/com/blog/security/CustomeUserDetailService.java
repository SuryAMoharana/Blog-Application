package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repositories.UserRepo;

@Service
public class CustomeUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//LOADING USER FROM DATABASE BY USERNAME
		
		User user=userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email:"+username,0));
		
		
		return user;
	}
	
	
	

}
