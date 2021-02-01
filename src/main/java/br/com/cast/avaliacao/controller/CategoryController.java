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
import br.com.cast.avaliacao.dto.CategoryNewDto;
import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@GetMapping
	ResponseEntity<List<CategoryDto>> findAll() {
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list.stream().map(c -> new CategoryDto(c)).collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	ResponseEntity<CategoryDto> findById(@PathVariable Integer id) {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(new CategoryDto(obj));
	}
	
	@GetMapping("/search/{description}")
	ResponseEntity<List<CategoryDto>> findByDescription(@PathVariable String description) {
		List<Category> list = service.findByDescription(description);
		return ResponseEntity.ok().body(list.stream().map(c -> new CategoryDto(c)).collect(Collectors.toList()));
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	ResponseEntity<Void> insert(@Valid @RequestBody CategoryNewDto dto) {
		
		Category obj = service.fromDto(dto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Void> update(@Valid @RequestBody CategoryNewDto dto, @PathVariable Integer id) {
		dto.setId(id);
		Category obj = service.fromDto(dto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
