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
class Data1ApplicationTests {
	
	@Autowired
	LibroRepository repositorioLibro;
	@Test
	void buscarTodosTest() {
		
		//Devuelve un iterable, no una colección, porque
		//Spring data esta configurado para ser lo más flexible posible
		Iterable<Libro> it = repositorioLibro.findAll();
		
		//Crea una lista y la rellena con los datos del iterable
		List<Libro> miLista = new ArrayList<Libro>();
		it.forEach(System.out::println);
		it.forEach(miLista::add);
		
		assertThat(miLista.size(),greaterThan(6));
	}
	
	@Test
	void buscarUnoTest() {
		
	
		Optional<Libro> optional = repositorioLibro.findById("1A");
		if(optional.isPresent()) {
			assertThat(optional.get().getTitulo(),equalTo("Java"));
			System.out.println("Titulo: " + optional.get().getTitulo());
			System.out.println("Precio: " + optional.get().getPrecio());
		}
		
	}
	
	@Test
	void insertarUnoTest() {
		
		Libro lib1 = new  Libro("2F","swing","Jose de Jesus",new Date(),25.0);
		repositorioLibro.save(lib1);
		
		
		Optional<Libro> optional = repositorioLibro.findById("1F");
		if(optional.isPresent()) {
			assertThat(optional.get().getTitulo(),equalTo("Python"));
			System.out.println("Titulo: " + optional.get().getTitulo());
			System.out.println("Precio: " + optional.get().getPrecio());
		}
		
	}

	@Test
	void borrarUnoTest() {
		
		Libro lib1 = new  Libro("1F");
		repositorioLibro.delete(lib1);
		Optional<Libro> optional = repositorioLibro.findById("1F");
		assertFalse(optional.isPresent());
		
		
		
	}
	
	@Test
	void buscarVariosTest() {
		
	
		Iterable<Libro> it = repositorioLibro.findAllById(List.of("1A","2A"));
		List<Libro>		listLibro = new ArrayList<Libro>();
		
		it.forEach(listLibro::add);
		
		for(Libro list : listLibro) {
			System.out.println("Encontro" + list);
		}
		for(int i = 0;i<=0;i++) {
//			assertThat(listLibro.get(i).getTitulo() , equalTo("Java"));
//			assertThat(listLibro.get(i).getTitulo() , equalTo("Net"));
			
		}
		
		
	}
	
	@Test
	void buscarTituloTest() {
		
	
		List<Libro> lista = repositorioLibro.findByTitulo("Java EE");
		for(Libro lib : lista){
			System.out.println(lib.getTitulo());
		}
		//assertThat(lista,hasItem(new Libro("3B")));
		
		
	}
	
	@Test
	void buscarAutorTest() {
		
	
		List<Libro> lista = repositorioLibro.findByAutor("Cecilio");
		for(Libro lib : lista){
			System.out.println("Autor: " + lib.getAutor());
		}
		assertThat(lista,hasItem(new Libro("3B")));
		
		
	}
	
	@Test
	void buscarPorTituloAutorTest() {
		
	
		List<Libro> lista = repositorioLibro.findByTituloAndAutor("Java","Cecilio");
		for(Libro lib : lista){
			System.out.println("Titulo y Autor: " + lib.getTitulo() + " " + lib.getAutor());
		}
		assertThat(lista,hasItem(new Libro("1A")));
		
		
	}
	
	@Test
	void buscarPorTituloOrAutorTest() {
		
	
		List<Libro> lista = repositorioLibro.findByTituloOrAutor("Net","Cecilio");
		for(Libro lib : lista){
			System.out.println("Titulo or Autor: " + lib.getTitulo() + " " + lib.getAutor());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
		
		
	}
	
	@Test
	void buscarPorPrecioTest() {
		
	
		List<Libro> lista = repositorioLibro.findByPrecio(50);
		for(Libro lib : lista){
			System.out.println("Se encontro el precio: " + lib.getPrecio());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	
	@Test
	void buscarPorPrecioMayorTest() {
		
	
		List<Libro> lista = repositorioLibro.findByPrecioGreaterThan(20);
		for(Libro lib : lista){
			System.out.println("Se encontro el precio Mayor: " + lib.getPrecio());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	
	@Test
	void buscarPorPrecioMenorTest() {
		
	
		List<Libro> lista = repositorioLibro.findByPrecioLessThan(20);
		for(Libro lib : lista){
			System.out.println("Se encontro el precio Menor: " + lib.getPrecio());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	
	@Test
	void buscarPorPrecioEntreTest() {
		
	
		List<Libro> lista = repositorioLibro.findByPrecioBetween(10,20);
		for(Libro lib : lista){
			System.out.println("Se encontro entre: " + lib.getPrecio());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	
	@Test
	void buscarPorTituloLikeTest() {
		
	
		List<Libro> lista = repositorioLibro.findByTituloLike("%Java%");
		for(Libro lib : lista){
			System.out.println("Se encontro el titulo por like: " + lib.getTitulo());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	
	@Test
	void buscarPorTituloContainingTest() {
		
	
		List<Libro> lista = repositorioLibro.findByTituloContaining("Java");
		for(Libro lib : lista){
			System.out.println("Se encontro el titulo por findByTituloContaining: " + lib.getTitulo());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	
	@Test
	void buscarPorTituloComienzaTest() {
		
	
		List<Libro> lista = repositorioLibro.findByTituloStartingWith("Java");
		for(Libro lib : lista){
			System.out.println("Se encontro el titulo por findByTituloStartWithing: " + lib.getTitulo());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	
	@Test
	void buscarPorTituloFinalizaTest() {
		
	
		List<Libro> lista = repositorioLibro.findByTituloEndingWith("Script");
		for(Libro lib : lista){
			System.out.println("Se encontro el titulo por findByTituloEndingWith: " + lib.getTitulo());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	@Test
	void buscarPorTituloIsNullTest() {
		
	
		List<Libro> lista = repositorioLibro.findByAutorIsNull();
		for(Libro lib : lista){
			System.out.println("Se encontro el titulo por findByAutorisNull: " + lib.getTitulo());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	@Test
	void buscarPorTituloNotTest() {
		
	
		List<Libro> lista = repositorioLibro.findByTituloNot("Net");
		for(Libro lib : lista){
			System.out.println("Se encontro el titulo por findByTituloNot: " + lib.getTitulo());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	
	@Test
	void buscarPorTituloEnConjuntoTest() {
		
		List<String> listaTitulos = new ArrayList<String>();
		listaTitulos.add("Java");
		listaTitulos.add("PHP");
		
		List<Libro> lista = repositorioLibro.findByTituloIn(listaTitulos);
		for(Libro lib : lista){
			System.out.println("Se encontro el titulo por findByTituloIn: " + lib.getTitulo());
		}
		//assertThat(lista,hasItem(new Libro("1A")));
			
	}
	
}
