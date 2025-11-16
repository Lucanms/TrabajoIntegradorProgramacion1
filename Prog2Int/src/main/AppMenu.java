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
                    actualizarLibro();
                    break;
                case "5":
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
        System.out.println("4) Actualizar Libro");
        System.out.println("5) Eliminar Libro");
        System.out.println("X) Salir");
        System.out.print("Opción: ");
    }

    private void crearLibro() {
        try {
            System.out.print("Título: ");
            String titulo = sc.nextLine();

            System.out.print("Autor: ");
            String autor = sc.nextLine();

            System.out.print("Editorial: ");
            String editorial = sc.nextLine();

            System.out.print("Año edición: ");
            int anio = Integer.parseInt(sc.nextLine());
            
            FichaBibliografica ficha = new FichaBibliografica();
            System.out.print("ISBN: ");
            ficha.setIsbn(sc.nextLine());

            System.out.print("Clasificación Dewey: ");
            String dewey = sc.nextLine();

            System.out.print("Estantería: ");
            String estanteria = sc.nextLine();

            System.out.print("Idioma: ");
            String idioma = sc.nextLine();

            ficha.setClasificacionDewey(dewey);
            ficha.setEstanteria(estanteria);
            ficha.setIdioma(idioma);
            ficha.setEliminado(false);

            Libro libro = new Libro();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setAnioEdicion(anio);
            libro.setEliminado(false);
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
