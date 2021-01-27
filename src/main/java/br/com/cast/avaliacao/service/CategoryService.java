package br.com.cast.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.dto.CategoryNewDto;
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
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}

	public List<Category> findByDescription(String description) {
		return repository.findByDescription(description);
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Category update(Category obj) {
		findById(obj.getId());
		return repository.save(obj);
	}
	
	public Category fromDto(CategoryNewDto dto) {
		return new Category(dto.getId(), dto.getDescription());
	}
	
}
