package happyfeet.util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/happy_feet";
    private static final String USER = "root";
    private static final String PASSWORD = "campus2023";

    public static Connection getConnection() {
        try {
            // Cargar el driver (opcional en JDBC 4+ pero recomendado)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Retornar la conexión
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: No se encontró el Driver de MySQL.");
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
            return null;
        }
    }
}