package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.blog.dto.CategoryDTO;
import com.blog.repositories.ApiResponse;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO caegoryDto){
		CategoryDTO createCategory=this.categoryService.createCategory(caegoryDto);		
		return new ResponseEntity<CategoryDTO>(createCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{catId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO caegoryDto, @PathVariable Integer catId){
		CategoryDTO updatedCategory=this.categoryService.updateCategory(caegoryDto,catId);		
		return new ResponseEntity<CategoryDTO>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId);	
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/getbyid/{catId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer catId){
		CategoryDTO categoryDto=this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDTO>(categoryDto, HttpStatus.OK);
	}
	
	@GetMapping("/allcategories")
	public ResponseEntity<List<CategoryDTO>> getCategory(){
		List<CategoryDTO> categories=this.categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}

}
