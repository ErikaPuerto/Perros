package edu.avanzada.perros.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URLBD = "jdbc:mysql://localhost:3306/avanzada";
    private static final String usuario = "root";
    private static final String contrasena = "";

    private Conexion() { }

    public static Connection getConexion() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (ClassNotFoundException | SQLException ex) {
            // Aquí puedes manejar la excepción o lanzar una excepción personalizada
            throw new RuntimeException("Error al conectar a la base de datos: " + ex.getMessage(), ex);
        }
        return cn;
    }

    public static void desconectar(Connection cn) {
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException ex) {
                // Aquí también puedes manejar la excepción o lanzar una excepción personalizada
                throw new RuntimeException("Error al cerrar la conexión: " + ex.getMessage(), ex);
            }
        }
    }
}
