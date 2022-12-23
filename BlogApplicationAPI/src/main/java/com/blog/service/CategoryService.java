package com.blog.service;

import java.util.List;

import com.blog.dto.CategoryDTO;

public interface CategoryService {
	
	//create
	CategoryDTO createCategory(CategoryDTO categoryDto);
	
	//update
	CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//get
	CategoryDTO getCategory(Integer categoryId);
	
	//getall
	List<CategoryDTO> getAllCategories();
	

}
