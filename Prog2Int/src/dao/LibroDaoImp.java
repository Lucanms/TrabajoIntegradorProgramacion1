package dao;

import entities.Libro;
import entities.FichaBibliografica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDaoImp implements LibroDao {

    private final FichaBibliograficaDaoImp fichaDao = new FichaBibliograficaDaoImp();

    @Override
    public void crear(Libro libro, Connection conn) throws SQLException {
        String sql = "INSERT INTO libro(eliminado, titulo, autor, editorial, anio_edicion) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBoolean(1, libro.isEliminado());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutor());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAnioEdicion());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                long idLibro = rs.getLong(1);
                libro.setIdLibro(idLibro);
            }
        }
    }

    @Override
    public Libro leer(long id, Connection conn) throws SQLException {
        Libro libro = null;

        String sql = "SELECT * FROM libro WHERE id_libro = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                libro = new Libro(
                        rs.getLong("id_libro"),
                        rs.getBoolean("eliminado"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial"),
                        rs.getInt("anio_edicion")
                );

                libro.setFicha(
                        fichaDao.leerPorLibroId(id, conn)
                );
            }
        }

        return libro;
    }

    @Override
    public List<Libro> leerTodos(Connection conn) throws SQLException {
        List<Libro> lista = new ArrayList<>();

        String sql = "SELECT * FROM libro";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Libro libro = new Libro(
                        rs.getLong("id_libro"),
                        rs.getBoolean("eliminado"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial"),
                        rs.getInt("anio_edicion")
                );

                libro.setFicha(
                        fichaDao.leerPorLibroId(libro.getIdLibro(), conn)
                );

                lista.add(libro);
            }
        }

        return lista;
    }

    @Override
    public void actualizar(Libro libro, Connection conn) throws SQLException {
        String sql = "UPDATE libro SET eliminado=?, titulo=?, autor=?, editorial=?, anio_edicion=? WHERE id_libro=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, libro.isEliminado());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutor());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAnioEdicion());
            stmt.setLong(6, libro.getIdLibro());
            stmt.executeUpdate();
        }

        if (libro.getFicha() != null) {
            fichaDao.actualizar(libro.getFicha(), conn);
        }
    }

    @Override
    public void eliminar(long id, Connection conn) throws SQLException {
        String sql = "UPDATE libro SET eliminado=true WHERE id_libro=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

        fichaDao.eliminarPorLibroId(id, conn);
    }
}
