package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection cn = null;
    private static final String URLBD = "jdbc:mysql://localhost:3306/avanzada";
    private static final String usuario = "juli";
    private static final String contrasena = "avanzada";

    private Conexion() { }

    public static Connection getConexion() {
        if (cn == null) {
            try {
                cn = DriverManager.getConnection(URLBD, usuario, contrasena);
            } catch (SQLException ex) {
                System.out.println("No se puede conectar a la base de datos: " + ex.getMessage());
            }
        }
        return cn;
    }

    public static void desconectar() {
        if (cn != null) {
            try {
                cn.close();
                cn = null;
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}
