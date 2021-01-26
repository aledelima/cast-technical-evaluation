package br.com.cast.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.dto.CategoryDto;
import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.repository.CategoryRepository;
import br.com.cast.avaliacao.service.exception.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Integer id) {
		Optional<Category> category = repository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	}

	public List<Category> findByDescription(String description) {
		return repository.findByDescription(description);
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return repository.save(category);
	}
	
	public Category update(Category category) {
		findById(category.getId());
		return repository.save(category);
	}
	
	public Category fromDto(CategoryDto categoryDto) {
		return new Category(categoryDto.getId(), categoryDto.getDescription());
	}
	
}
