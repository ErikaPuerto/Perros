package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PerroDAO {

    private Connection con;
    private ResultSet rs;

    public PerroDAO() {
        con = null;
        rs = null;
    }

    public PerroVO consultarPerro(String nombre) {
        PerroVO perro = null;
        String consulta = "SELECT * FROM mascotas WHERE nombre = ?";
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                perro = new PerroVO();
                perro.setNombre(rs.getString("nombre"));
                perro.setPaisOrigen(rs.getString("paisOrigen"));
                perro.setApariencia(rs.getString("apariencia"));
                perro.setPelo(rs.getString("pelo"));
                perro.setColor(rs.getString("color"));
                perro.setEspalda(rs.getString("espalda"));
                perro.setLomo(rs.getString("lomo"));
                perro.setCola(rs.getString("cola"));
                perro.setPecho(rs.getString("pecho"));
            }
            ps.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en la consulta: " + ex.getMessage());
        }
        return perro;
    }

    public ArrayList<PerroVO> listaDePerros() {
        ArrayList<PerroVO> misPerros = new ArrayList<>();
        String consulta = "SELECT * FROM mascotas";
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                PerroVO perro = new PerroVO();
                perro.setNombre(rs.getString("nombre"));
                perro.setPaisOrigen(rs.getString("paisOrigen"));
                perro.setApariencia(rs.getString("apariencia"));
                perro.setPelo(rs.getString("pelo"));
                perro.setColor(rs.getString("color"));
                perro.setEspalda(rs.getString("espalda"));
                perro.setLomo(rs.getString("lomo"));
                perro.setCola(rs.getString("cola"));
                perro.setPecho(rs.getString("pecho"));
                misPerros.add(perro);
            }
            ps.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al obtener la lista de perros: " + ex.getMessage());
        }
        return misPerros;
    }

    public boolean insertarDatos(PerroVO perro) {
        String insercion = "INSERT INTO mascotas (nombre, paisOrigen, clasificacion) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insercion);
            ps.setString(1, perro.getNombre());
            ps.setString(2, perro.getPaisOrigen());
            ps.setString(3, perro.getClasificacion());
            ps.executeUpdate();
            ps.close();
            System.out.println("Datos del perro insertados correctamente.");
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al insertar los datos: " + ex.getMessage());
            return false;
        }
    }

    public boolean eliminarPerro(String nombre) {
        String consulta = "DELETE FROM mascotas WHERE nombre = ?";
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, nombre);
            ps.executeUpdate();
            ps.close();
            Conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el perro: " + ex.getMessage());
        }
        return false;
    }

    public boolean modificarPerro(PerroVO perro) {
        String consulta = "UPDATE mascotas SET pelo = ?, color = ?, apariencia = ?, espalda = ?, lomo = ?, cola = ?, pecho = ? WHERE nombre = ?";
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, perro.getPelo());
            ps.setString(2, perro.getColor());
            ps.setString(3, perro.getApariencia());
            ps.setString(4, perro.getEspalda());
            ps.setString(5, perro.getLomo());
            ps.setString(6, perro.getCola());
            ps.setString(7, perro.getPecho());
            ps.setString(8, perro.getNombre());
            ps.executeUpdate();
            ps.close();
            Conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al modificar el perro: " + ex.getMessage());
            return false;
        }
    }

    public boolean existePerro(String nombre) {
        String consulta = "SELECT 1 FROM mascotas WHERE nombre = ?";
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            boolean existe = rs.next();
            ps.close();
            Conexion.desconectar();
            return existe;
        } catch (SQLException ex) {
            System.out.println("Error en la verificación de existencia: " + ex.getMessage());
        }
        return false;
    }
}
