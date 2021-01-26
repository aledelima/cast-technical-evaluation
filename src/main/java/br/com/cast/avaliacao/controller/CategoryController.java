package br.com.cast.avaliacao.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cast.avaliacao.dto.CategoryDto;
import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@GetMapping
	ResponseEntity<List<CategoryDto>> findAll() {
		List<Category> categories = service.findAll();
		return ResponseEntity.ok().body(categories.stream().map(c -> new CategoryDto(c)).collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	ResponseEntity<CategoryDto> findById(@PathVariable Integer id) {
		Category category = service.findById(id);
		CategoryDto categoryDto = new CategoryDto(category);
		return ResponseEntity.ok().body(categoryDto);
	}
	
	@GetMapping("/search/{description}")
	ResponseEntity<List<CategoryDto>> findByDescription(@PathVariable String description) {
		List<Category> categories = service.findByDescription(description);
		return ResponseEntity.ok().body(categories.stream().map(c -> new CategoryDto(c)).collect(Collectors.toList()));
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	ResponseEntity<Void> insert(@Valid @RequestBody CategoryDto categoryDto) {
		Category category = service.fromDto(categoryDto);
		category = service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	ResponseEntity<Void> update(@Valid @RequestBody CategoryDto categoryDto) {
		Category category = service.fromDto(categoryDto);
		category = service.update(category);
		return ResponseEntity.noContent().build();
	}
	
}
