package dao;

import entities.FichaBibliografica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FichaBibliograficaDaoImp implements FichaBibliograficaDao {

    @Override
    public void crear(FichaBibliografica ficha, Connection conn) throws SQLException {
        String sql = "INSERT INTO ficha_bibliografica (eliminado, isbn, clasificacion_dewey, estanteria, idioma, id_libro) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBoolean(1, ficha.isEliminado());
            stmt.setString(2, ficha.getIsbn());
            stmt.setString(3, ficha.getClasificacionDewey());
            stmt.setString(4, ficha.getEstanteria());
            stmt.setString(5, ficha.getIdioma());
            stmt.setLong(6, ficha.getIdLibro());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                ficha.setIdFichaBibliografica(rs.getLong(1));
            }
        }
    }

    @Override
    public FichaBibliografica leer(long id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM ficha_bibliografica WHERE id_ficha_bibliografica = ?";
        FichaBibliografica ficha = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ficha = mapRow(rs);
            }
        }

        return ficha;
    }

    public FichaBibliografica leerPorLibroId(long idLibro, Connection conn) throws SQLException {
        String sql = "SELECT * FROM ficha_bibliografica WHERE id_libro = ?";
        FichaBibliografica ficha = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idLibro);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ficha = mapRow(rs);
            }
        }

        return ficha;
    }

    @Override
    public List<FichaBibliografica> leerTodos(Connection conn) throws SQLException {
        List<FichaBibliografica> lista = new ArrayList<>();

        String sql = "SELECT * FROM ficha_bibliografica";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }

        return lista;
    }

    @Override
    public void actualizar(FichaBibliografica ficha, Connection conn) throws SQLException {
        String sql = "UPDATE ficha_bibliografica SET eliminado=?, isbn=?, clasificacion_dewey=?, estanteria=?, idioma=?, id_libro=? WHERE id_ficha_bibliografica=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, ficha.isEliminado());
            stmt.setString(2, ficha.getIsbn());
            stmt.setString(3, ficha.getClasificacionDewey());
            stmt.setString(4, ficha.getEstanteria());
            stmt.setString(5, ficha.getIdioma());
            stmt.setLong(6, ficha.getIdLibro());
            stmt.setLong(7, ficha.getIdFichaBibliografica());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(long id, Connection conn) throws SQLException {
        String sql = "UPDATE ficha_bibliografica SET eliminado=true WHERE id_ficha_bibliografica=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void eliminarPorLibroId(long idLibro, Connection conn) throws SQLException {
        String sql = "UPDATE ficha_bibliografica SET eliminado=true WHERE id_libro=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idLibro);
            stmt.executeUpdate();
        }
    }

    private FichaBibliografica mapRow(ResultSet rs) throws SQLException {
        return new FichaBibliografica(
                rs.getLong("id_ficha_bibliografica"),
                rs.getBoolean("eliminado"),
                rs.getString("isbn"),
                rs.getString("clasificacion_dewey"),
                rs.getString("estanteria"),
                rs.getString("idioma"),
                rs.getLong("id_libro")
        );
    }
}
