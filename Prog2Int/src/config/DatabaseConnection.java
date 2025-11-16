package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException ("Error: No se encontro el driver JDBC", e);
        
        }
    }
    
    public static Connection getConnection() throws SQLException {
        if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty() || PASSWORD == null){
            throw new SQLException ("Configuracion de la base de datos incompleta o inválida.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    
    
}
