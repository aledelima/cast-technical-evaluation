package br.com.cast.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cast.avaliacao.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
