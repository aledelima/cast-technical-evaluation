package br.com.cast.avaliacao.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.model.Course;
import br.com.cast.avaliacao.repository.CategoryRepository;
import br.com.cast.avaliacao.repository.CourseRepository;

@Configuration
public class DatabaseSeeding implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CourseRepository courseRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category behavioral = new Category(null, "Comportamental");
		Category programming = new Category(null, "Programação");
		Category quality = new Category(null, "Qualidade");
		Category process = new Category(null, "Processos");
	
		categoryRepository.saveAll(Arrays.asList(behavioral, programming, quality, process));
		
		
		Course java = new Course(null, "Java", LocalDate.now(), LocalDate.now().plusDays(5), 3, programming);
		Course angular = new Course(null, "Angular", LocalDate.now().plusDays(7), LocalDate.now().plusDays(12), 7, programming);
		Course qa = new Course(null, "QA", LocalDate.of(2021, 1, 05), LocalDate.of(2021, 1, 12), 12, quality);
		
		courseRepository.saveAll(Arrays.asList(java, angular, qa));
		
	}
	
}
