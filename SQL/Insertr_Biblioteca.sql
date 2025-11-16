USE biblioteca;


INSERT INTO libro (eliminado, titulo, autor, editorial, anio_edicion)
VALUES
 (FALSE, 'Cien años de soledad', 'Gabriel García Márquez', 'Sudamericana', 1967),
 (FALSE, 'Rayuela', 'Julio Cortázar', 'Alfaguara', 1963),
 (FALSE, 'Ficciones', 'Jorge Luis Borges', 'Losada', 1944);


INSERT INTO ficha_bibliografica (eliminado, isbn, clasificacion_dewey, estanteria, idioma, id_libro)
VALUES
 (FALSE, '978-84-376-0494-7', '863.7', 'A1', 'Español', 1),
 (FALSE, '978-84-376-0000-0', '863.7', 'B2', 'Español', 2),
 (FALSE, '978-950-03-0175-0', '862',   'C3', 'Español', 3);
