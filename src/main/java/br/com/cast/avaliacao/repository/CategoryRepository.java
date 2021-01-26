package br.com.cast.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cast.avaliacao.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query("select c from Category c where c.description like %?1%")
	List<Category> findByDescription(String description);
	
}
