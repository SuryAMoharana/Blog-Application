package com.blog.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	Integer fieldValue;	
	
	public ResourceNotFoundException(String resourceName, String fieldName, Integer userId) {
		super(String.format("%s not founf with %s : %1",resourceName, fieldName, userId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = userId;
	}
	
	

}
