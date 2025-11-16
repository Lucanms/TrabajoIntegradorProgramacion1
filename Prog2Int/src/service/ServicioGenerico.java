/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;

public interface ServicioGenerico<T> {

    void insertar(T entidad) throws Exception;

    T getById(long id) throws Exception;

    List<T> getAll() throws Exception;

    void actualizar(T entidad) throws Exception;

    void eliminar(long id) throws Exception;
}
