package com.arquitecturajava.data1;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

//De forma automatica generara sobre esta interface una clase que lo implemente
//e incluya todas las operaciones basicas
public interface LibroRepository extends CrudRepository<Libro, String>{

	public List<Libro> findByTitulo(String titulo);
	public List<Libro> findByAutor(String autor);
	public List<Libro> findByTituloAndAutor(String titulo,String autor);
	public List<Libro> findByTituloOrAutor(String titulo,String autor);
	public List<Libro> findByPrecio(double precio);
	public List<Libro> findByPrecioGreaterThan(double precio);
	public List<Libro> findByPrecioLessThan(double precio);
	public List<Libro> findByPrecioBetween(double precio1,double precio2);
	public List<Libro> findByTituloLike(String titulo); 
	public List<Libro> findByTituloContaining(String titulo); 
	public List<Libro> findByTituloStartingWith(String titulo); 
	public List<Libro> findByTituloEndingWith(String titulo);
	public List<Libro> findByAutorIsNull();
	public List<Libro> findByTituloNot(String titulo);//Excluye el titulo
	public List<Libro> findByTituloIn(Collection<String> coleccion);
}
