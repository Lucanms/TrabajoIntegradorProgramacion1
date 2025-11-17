/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.List;
import java.util.Scanner;

import entities.FichaBibliografica;
import entities.Libro;
import service.ServicioLibro;

public class AppMenu {

    private final Scanner sc = new Scanner(System.in);
    private final ServicioLibro ServicioLibro = new ServicioLibro();

    public void iniciar() {
        String op;

        do {
            mostrarOpciones();
            op = sc.nextLine().trim().toUpperCase();

            switch (op) {
                case "1":
                    crearLibro();
                    break;
                case "2":
                    listarLibros();
                    break;
                case "3":
                    buscarPorId();
                    break;
                case "4":
                    buscarPorTitulo();
                    break;
                case "5":
                    actualizarLibro();
                    break;
                case "6":
                    eliminarLibro();
                    break;
                case "X":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (!op.equals("X"));
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ BIBLIOTECA ---");
        System.out.println("1) Crear Libro");
        System.out.println("2) Listar Libros");
        System.out.println("3) Buscar por ID");
        System.out.println("4) Buscar por Titulo");
        System.out.println("5) Actualizar Libro");
        System.out.println("6) Eliminar Libro");
        System.out.println("X) Salir");
        System.out.print("Opción: ");
    }

    private void crearLibro() {
        try {
            System.out.print("Título: ");
            String titulo = sc.nextLine();
            if (titulo.isBlank()) {
                throw new Exception("El título no puede estar vacío.");
            }

            System.out.print("Autor: ");
            String autor = sc.nextLine();
            if (autor.isBlank()) {
                throw new Exception("El autor no puede estar vacío.");
            }

            System.out.print("Editorial: ");
            String editorial = sc.nextLine();
            if (editorial.isBlank()) {
                throw new Exception("La editorial no puede estar vacía.");
            }

            System.out.print("Año edición: ");
            String anioStr = sc.nextLine();
            if (anioStr.isBlank()) {
                throw new Exception("El año no puede estar vacío.");
            }

            int anio;
            try {
                anio = Integer.parseInt(anioStr);
            } catch (NumberFormatException e) {
                throw new Exception("El año debe ser un número entero válido.");
            }
            if (anio <= 0) {
                throw new Exception("El año debe ser mayor que 0.");
            }

            System.out.print("ISBN: ");
            String isbn = sc.nextLine();
            if (isbn.isBlank()) {
                throw new Exception("El ISBN no puede estar vacío.");
            }

            System.out.print("Clasificación Dewey: ");
            String dewey = sc.nextLine();
            if (dewey.isBlank()) {
                throw new Exception("La clasificación Dewey no puede estar vacía.");
            }

            System.out.print("Estantería: ");
            String estanteria = sc.nextLine();
            if (estanteria.isBlank()) {
                throw new Exception("La estantería no puede estar vacía.");
            }

            System.out.print("Idioma: ");
            String idioma = sc.nextLine();
            if (idioma.isBlank()) {
                throw new Exception("El idioma no puede estar vacío.");
            }

            Libro libro = new Libro();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setAnioEdicion(anio);
            libro.setEliminado(false);

            FichaBibliografica ficha = new FichaBibliografica();
            ficha.setIsbn(isbn);
            ficha.setClasificacionDewey(dewey);
            ficha.setEstanteria(estanteria);
            ficha.setIdioma(idioma);
            ficha.setEliminado(false);

            libro.setFicha(ficha);

            ServicioLibro.insertar(libro);

            System.out.println("Libro creado con éxito.");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void listarLibros() {
        try {

            List<Libro> lista = ServicioLibro.getAll();
            lista.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void buscarPorId() {
        try {
            System.out.print("ID: ");
            long id = Long.parseLong(sc.nextLine());

            Libro libro = ServicioLibro.getById(id);
            System.out.println(libro != null ? libro : "Ese libro no existe");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void buscarPorTitulo() {
        try {
            System.out.print("Ingrese parte del título: ");
            String t = sc.nextLine();

            List<Libro> encontrados = ServicioLibro.buscarPorTitulo(t);

            encontrados.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void actualizarLibro() {
        try {
            System.out.print("Ingrese ID del libro a actualizar: ");
            int id = Integer.parseInt(sc.nextLine());

            Libro libro = ServicioLibro.getById(id);

            if (libro == null) {
                System.out.println("No existe un libro con ese ID.");
                return;
            }

            System.out.println("--- Actualizar Libro ---");

            System.out.println("Título actual: " + libro.getTitulo());
            System.out.print("Nuevo título (ENTER para mantener): ");
            String nuevoTitulo = sc.nextLine();
            if (!nuevoTitulo.isEmpty()) {
                libro.setTitulo(nuevoTitulo);
            }

            System.out.println("Autor actual: " + libro.getAutor());
            System.out.print("Nuevo autor (ENTER para mantener): ");
            String nuevoAutor = sc.nextLine();
            if (!nuevoAutor.isEmpty()) {
                libro.setAutor(nuevoAutor);
            }

            System.out.println("Editorial actual: " + libro.getEditorial());
            System.out.print("Nueva editorial (ENTER para mantener): ");
            String nuevaEditorial = sc.nextLine();
            if (!nuevaEditorial.isEmpty()) {
                libro.setEditorial(nuevaEditorial);
            }

            System.out.println("Año edición actual: " + libro.getAnioEdicion());
            System.out.print("Nuevo año (ENTER para mantener): ");
            String nuevoAnio = sc.nextLine();
            if (!nuevoAnio.isEmpty()) {
                libro.setAnioEdicion(Integer.parseInt(nuevoAnio));
            }

            FichaBibliografica ficha = libro.getFicha();

            System.out.println("ISBN actual: " + ficha.getIsbn());
            System.out.print("Nuevo ISBN (ENTER para mantener): ");
            String isbn = sc.nextLine();
            if (!isbn.isEmpty()) {
                ficha.setIsbn(isbn);
            }

            System.out.println("Clasificación Dewey actual: " + ficha.getClasificacionDewey());
            System.out.print("Nueva clasificación (ENTER para mantener): ");
            String dewey = sc.nextLine();
            if (!dewey.isEmpty()) {
                ficha.setClasificacionDewey(dewey);
            }

            System.out.println("Estantería actual: " + ficha.getEstanteria());
            System.out.print("Nueva estantería (ENTER para mantener): ");
            String estanteria = sc.nextLine();
            if (!estanteria.isEmpty()) {
                ficha.setEstanteria(estanteria);
            }

            System.out.println("Idioma actual: " + ficha.getIdioma());
            System.out.print("Nuevo idioma (ENTER para mantener): ");
            String idioma = sc.nextLine();
            if (!idioma.isEmpty()) {
                ficha.setIdioma(idioma);
            }

            ServicioLibro.actualizar(libro);

            System.out.println("Libro actualizado correctamente.");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void eliminarLibro() {
        try {
            System.out.print("ID a eliminar: ");
            long id = Long.parseLong(sc.nextLine());

            ServicioLibro.eliminar(id);
            System.out.println("Libro eliminado.");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
