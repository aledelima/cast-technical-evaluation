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

import br.com.cast.avaliacao.dto.CourseDto;
import br.com.cast.avaliacao.dto.CourseNewDto;
import br.com.cast.avaliacao.model.Course;
import br.com.cast.avaliacao.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	CourseService service;
	
	@GetMapping
	ResponseEntity<List<CourseDto>> findAll() {
		List<Course> list = service.findAll();
		return ResponseEntity.ok().body(list.stream().map(c -> new CourseDto(c)).collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Course> findById(@PathVariable Integer id) {
		Course obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/search/{description}")
	ResponseEntity<List<CourseDto>> findByDescription(@PathVariable String description) {
		List<Course> list = service.findByDescription(description);
		return ResponseEntity.ok().body(list.stream().map(c -> new CourseDto(c)).collect(Collectors.toList()));
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	ResponseEntity<Void> insert(@Valid @RequestBody CourseNewDto dto) {
		dto.setId(null);
		Course course = service.fromDto(dto);
		course = service.insert(course);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Void> update(@Valid @RequestBody CourseNewDto dto, @PathVariable Integer id) {
		dto.setId(id);
		Course obj = service.fromDto(dto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
