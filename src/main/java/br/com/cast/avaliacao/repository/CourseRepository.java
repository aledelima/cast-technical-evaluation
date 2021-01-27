package br.com.cast.avaliacao.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cast.avaliacao.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

	@Query("SELECT c FROM Course c WHERE c.description like %?1%")
	List<Course> findByDescription(String description);
	
	
	@Query("SELECT c FROM Course c WHERE ?2 <> c.category.id AND ?1 BETWEEN c.begin AND c.end")
	List<Course> bookedWithBeginDate(LocalDate begin, Integer categoryId);
	
	
	@Query("SELECT c FROM Course c WHERE ?2 <> c.category.id AND ?1 BETWEEN c.begin AND c.end")
	List<Course> bookedWithEndDate(LocalDate end,  Integer categoryId);
	
	
	
}
