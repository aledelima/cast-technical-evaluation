package br.com.cast.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.dto.CourseNewDto;
import br.com.cast.avaliacao.model.Course;
import br.com.cast.avaliacao.repository.CourseRepository;
import br.com.cast.avaliacao.service.exception.ObjectNotFoundException;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repository;
	
	@Autowired
	private CategoryService categoryService;
	
	public List<Course> findAll() {
		return repository.findAll();
	}
	
	public Course findById(Integer id) {
		Optional<Course> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Course.class.getName()));
	}

	public List<Course> findByDescription(String description) {
		return repository.findByDescription(description);
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Course insert(Course obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Course update(Course obj) {
		findById(obj.getId());
		return repository.save(obj);
	}
	
	public Course fromDto(CourseNewDto dto) {	
		return new Course(
				dto.getId(),
				dto.getDescription(),
				dto.getBegin(),
				dto.getEnd(),
				dto.getStudentsQtd(), 
				categoryService.findById(dto.getCategoryId())
			);
	}
	
}
