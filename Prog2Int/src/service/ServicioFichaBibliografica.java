/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import config.DatabaseConnection;
import dao.FichaBibliograficaDaoImp;
import entities.FichaBibliografica;

import java.sql.Connection;
import java.util.List;

public class ServicioFichaBibliografica implements ServicioGenerico<FichaBibliografica> {

    private final FichaBibliograficaDaoImp fichaDao = new FichaBibliograficaDaoImp();

    @Override
    public void insertar(FichaBibliografica ficha) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            fichaDao.crear(ficha, conn);
        }
    }

    @Override
    public FichaBibliografica getById(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return fichaDao.leer(id, conn);
        }
    }

    @Override
    public List<FichaBibliografica> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return fichaDao.leerTodos(conn);
        }
    }

    @Override
    public void actualizar(FichaBibliografica ficha) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            fichaDao.actualizar(ficha, conn);
        }
    }

    @Override
    public void eliminar(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            fichaDao.eliminar(id, conn);
        }
    }
}
