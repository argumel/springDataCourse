package com.arquitecturajava.data1;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CapituloRepository extends CrudRepository<Capitulo, String>{
	
	
	List<Capitulo> findByTitulo(String titulo);
	//Buscamos por el titulo del Libro
	List<Capitulo> findByLibroTitulo(String titulo);
	List<Capitulo> findByPaginas(int paginas);

}
