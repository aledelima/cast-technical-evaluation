package br.com.cast.avaliacao.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.repository.CategoryRepository;

@Configuration
public class DatabaseSeeding implements CommandLineRunner {

	@Autowired
	CategoryRepository repository;

	@Override
	public void run(String... args) throws Exception {
		
		Category behavioral = new Category(null, "Comportamental");
		Category programming = new Category(null, "Programação");
		Category quality = new Category(null, "Qualidade");
		Category process = new Category(null, "Processos");
	
		repository.saveAll(Arrays.asList(behavioral, programming, quality, process));
	}
	
}
