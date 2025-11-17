/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import config.DatabaseConnection;
import dao.LibroDaoImp;
import dao.FichaBibliograficaDaoImp;
import entities.Libro;
import entities.FichaBibliografica;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ServicioLibro implements ServicioGenerico<Libro> {

    private final LibroDaoImp libroDao = new LibroDaoImp();
    private final FichaBibliograficaDaoImp fichaDao = new FichaBibliograficaDaoImp();

    @Override
    public void insertar(Libro libro) throws Exception {
        Connection conn = null;
        validarIsbnUnico(libro.getFicha().getIsbn(), null);

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            libroDao.crear(libro, conn);

            Long idGenerado = libro.getIdLibro();

            FichaBibliografica ficha = libro.getFicha();
            ficha.setIdLibro(idGenerado);

            fichaDao.crear(ficha, conn);

            conn.commit();

        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;

        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public Libro getById(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return libroDao.leer(id, conn);
        }
    }

    public List<Libro> buscarPorTitulo(String titulo) throws Exception {

        if (titulo == null || titulo.isBlank()) {
            throw new Exception("Debe ingresar un título para buscar.");
        }

        List<Libro> todos = getAll();

        List<Libro> resultado = todos.stream()
                .filter(l -> l.getTitulo() != null
                && l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .toList();

        if (resultado.isEmpty()) {
            throw new Exception("No se encontraron libros con ese título.");
        }

        return resultado;
    }

    @Override
    public List<Libro> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return libroDao.leerTodos(conn);
        }
    }

    @Override
    public void actualizar(Libro libro) throws Exception {
        Connection conn = null;

        validarIsbnUnico(libro.getFicha().getIsbn(), libro.getIdLibro());

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            libroDao.actualizar(libro, conn);
            fichaDao.actualizar(libro.getFicha(), conn);

            conn.commit();

        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public void eliminar(long id) throws Exception {
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            libroDao.eliminar(id, conn);

            conn.commit();

        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;

        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    private void validarIsbnUnico(String isbn, Long idLibroActual) throws Exception {
        List<Libro> libros = getAll();

        boolean existe = libros.stream()
                .anyMatch(l
                        -> l.getFicha() != null
                && l.getFicha().getIsbn() != null
                && l.getFicha().getIsbn().equalsIgnoreCase(isbn)
                && (idLibroActual == null || l.getIdLibro() != idLibroActual)
                );

        if (existe) {
            throw new Exception("Ya existe un libro con ese ISBN.");
        }
    }
}
