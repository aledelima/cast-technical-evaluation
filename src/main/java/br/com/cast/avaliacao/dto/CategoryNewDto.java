package br.com.cast.avaliacao.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.cast.avaliacao.model.Category;

public class CategoryNewDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotBlank(message="Preenchimento obrigatório")
	private String description;
	
	public CategoryNewDto() {
	}

	public CategoryNewDto(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public CategoryNewDto(Category category) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryNewDto other = (CategoryNewDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
