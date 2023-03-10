package com.blog.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDTO> content;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Long totalElement;
	
	private Integer totalPages;
	
	private boolean lastPage;

}
