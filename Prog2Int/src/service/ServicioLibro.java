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
import java.util.List;

public class ServicioLibro implements ServicioGenerico<Libro> {

    private final LibroDaoImp libroDao = new LibroDaoImp();
    private final FichaBibliograficaDaoImp fichaDao = new FichaBibliograficaDaoImp();

    @Override
    public void insertar(Libro libro) throws Exception {
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            validarLibro(libro);

            libroDao.crear(libro, conn);

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

    @Override
    public List<Libro> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return libroDao.leerTodos(conn);
        }
    }

    @Override
    public void actualizar(Libro libro) throws Exception {
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            validarLibro(libro);

            libroDao.actualizar(libro, conn);

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

    private void validarLibro(Libro libro) throws Exception {
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            throw new Exception("Título obligatorio");
        }
        if (libro.getAutor() == null || libro.getAutor().isBlank()) {
            throw new Exception("Autor obligatorio");
        }
    }
}
