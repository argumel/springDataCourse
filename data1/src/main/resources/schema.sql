DROP TABLE IF EXISTS Capitulos;
create table Capitulos (titulo varchar(25), paginas integer,libros_isbn varchar(10));
DROP TABLE IF EXISTS Libros;
create table Libros (isbn varchar(10), titulo varchar(25),autor varchar(25), fecha date,precio decimal, PRIMARY KEY (isbn));