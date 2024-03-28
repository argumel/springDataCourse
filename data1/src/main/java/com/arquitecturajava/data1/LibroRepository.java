package com.arquitecturajava.data1;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//De forma automatica generara sobre esta interface una clase que lo implemente
//e incluya todas las operaciones basicas
public interface LibroRepository extends CrudRepository<Libro, String>{

	public List<Libro> findByAutor(String titulo);
}
