package com.arquitecturajava.data1;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@SpringBootTest
@Sql({"/schema.sql","/data.sql"})
class Data2ApplicationTestsCapitulos {
	
	@Autowired
	LibroRepository 	repositorioLibro;
	
	@Autowired
	CapituloRepository 	capituloRepository;
	
	@Test
	void buscarTodosTest() {
		
		List<Capitulo> it = capituloRepository.findByTitulo("Bucles");
		
		for(Capitulo c: it) {
			System.out.println("Titulo: " + c.getTitulo());
		}
	}
	
	@Test
	void buscarLibroTituloTest() {
		
		List<Capitulo> it = capituloRepository.findByLibroTitulo("Java");
		
		for(Capitulo c: it) {
			System.out.println("LibroTitulo: " + c.getTitulo());
			//assertThat(it,hasItem(new Capitulo(c.getTitulo())));
		}
		
		assertThat(it,hasItem(new Capitulo("Introducción")));
//		assertThat(it,hasItem(new Capitulo("Sintaxis Basica")));
//		assertThat(it,hasItem(new Capitulo("Sentencias de control")));
//		assertThat(it,hasItem(new Capitulo("Bucles")));
	}
	
	
		
	
	
	
	
}
