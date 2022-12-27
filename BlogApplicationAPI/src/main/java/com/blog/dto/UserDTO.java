package com.blog.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.blog.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Integer id;
	
	@NotEmpty
	@Size(min=4)
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10)	
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<CommentDTO> comments=new HashSet<>();

}
