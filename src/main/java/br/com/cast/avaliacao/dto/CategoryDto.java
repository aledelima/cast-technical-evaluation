package br.com.cast.avaliacao.dto;

import br.com.cast.avaliacao.model.Category;

public class CategoryDto {

	private Integer id;
	private String description;
	
	public CategoryDto() {
	}

	public CategoryDto(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public CategoryDto(Category category) {
		this.id = category.getId();
		this.description = category.getDescription();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}
