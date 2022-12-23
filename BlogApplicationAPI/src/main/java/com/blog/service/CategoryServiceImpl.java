package com.blog.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CategoryDTO;
import com.blog.entities.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDto) {
		// TODO Auto-generated method stub
		Category cat=this.modelMapper.map(categoryDto, Category.class );
		Category addedCat=this.categoryRepo.save(cat);
				
		return this.modelMapper.map(addedCat, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=	this.categoryRepo.findById(categoryId)
						.orElseThrow(()-> new ResourceNotFoundException("Category",  "Category Id", categoryId));
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category updatedCat=this.categoryRepo.save(cat);
		
		
		return this.modelMapper.map(updatedCat, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category cat=	this.categoryRepo.findById(categoryId)
						.orElseThrow(()-> new ResourceNotFoundException("Category", "Category ID", categoryId));
		
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=	this.categoryRepo.findById(categoryId)
						.orElseThrow(()-> new ResourceNotFoundException("Category", "Category ID", categoryId));
		return this.modelMapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> categories=	this.categoryRepo.findAll();
		List<CategoryDTO> categoryDtos=categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());	
		
		return categoryDtos;
	}

}
