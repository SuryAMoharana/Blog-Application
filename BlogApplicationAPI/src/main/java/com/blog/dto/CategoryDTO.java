package com.blog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min=5)
	private String categoryTitle;
	
	@NotEmpty
	@Size(min=10)
	private String categoryDescription;

}
