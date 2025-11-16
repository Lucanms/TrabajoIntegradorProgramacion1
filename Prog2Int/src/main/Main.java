package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DatabaseConnection;

public class Main {
      public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()){
            if (conn != null) {
                System.out.println("Conexion establecida con exito");                
            }
        } catch (Exception e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        
        new AppMenu().iniciar();
    }
    
}