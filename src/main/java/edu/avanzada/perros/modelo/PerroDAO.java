package edu.avanzada.perros.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PerroDAO {

    //Consultar perro por id
    public PerroVO consultarPerro(String id) {
        PerroVO perro = null;
        String consulta = "SELECT * FROM mascotas WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    perro = new PerroVO();
                    perro.setId(rs.getString("id"));
                    perro.setNombre(rs.getString("nombre"));
                    perro.setPaisOrigen(rs.getString("paisOrigen"));
                    perro.setGrupo(rs.getString("grupo"));
                    perro.setSeccion(rs.getString("seccion"));
                    perro.setApariencia(rs.getString("apariencia"));
                    perro.setPelo(rs.getString("pelo"));
                    perro.setColor(rs.getString("color"));
                    perro.setEspalda(rs.getString("espalda"));
                    perro.setLomo(rs.getString("lomo"));
                    perro.setCola(rs.getString("cola"));
                    perro.setPecho(rs.getString("pecho"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la consulta: " + ex.getMessage());
        }
        return perro;
    }

    //Consulta por nombre de la raza
    public PerroVO consultarPerroNombre(String nombre) {
        PerroVO perro = null;
        String consulta = "SELECT * FROM mascotas WHERE nombre = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    perro = new PerroVO();
                    perro.setId(rs.getString("id"));
                    perro.setNombre(rs.getString("nombre"));
                    perro.setPaisOrigen(rs.getString("paisOrigen"));
                    perro.setGrupo(rs.getString("grupo"));
                    perro.setSeccion(rs.getString("seccion"));
                    perro.setApariencia(rs.getString("apariencia"));
                    perro.setPelo(rs.getString("pelo"));
                    perro.setColor(rs.getString("color"));
                    perro.setEspalda(rs.getString("espalda"));
                    perro.setLomo(rs.getString("lomo"));
                    perro.setCola(rs.getString("cola"));
                    perro.setPecho(rs.getString("pecho"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la consulta: " + ex.getMessage());
        }
        return perro;
    }
    
    //Consulta todas las razas por pais de Origen
    public PerroVO consultarPerroPais(String paisOrigen) {
        PerroVO perro = null;
        String consulta = "SELECT * FROM mascotas WHERE paisOrigen = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, paisOrigen);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    perro = new PerroVO();
                    perro.setId(rs.getString("id"));
                    perro.setNombre(rs.getString("nombre"));
                    perro.setPaisOrigen(rs.getString("paisOrigen"));
                    perro.setGrupo(rs.getString("grupo"));
                    perro.setSeccion(rs.getString("seccion"));
                    perro.setApariencia(rs.getString("apariencia"));
                    perro.setPelo(rs.getString("pelo"));
                    perro.setColor(rs.getString("color"));
                    perro.setEspalda(rs.getString("espalda"));
                    perro.setLomo(rs.getString("lomo"));
                    perro.setCola(rs.getString("cola"));
                    perro.setPecho(rs.getString("pecho"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la consulta: " + ex.getMessage());
        }
        return perro;
    }
    
    //Consultar todas las razas por color de manto
    public PerroVO consultarPerroManto(String color) {
        PerroVO perro = null;
        String consulta = "SELECT * FROM mascotas WHERE color = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, color);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    perro = new PerroVO();
                    perro.setId(rs.getString("id"));
                    perro.setNombre(rs.getString("nombre"));
                    perro.setPaisOrigen(rs.getString("paisOrigen"));
                    perro.setGrupo(rs.getString("grupo"));
                    perro.setSeccion(rs.getString("seccion"));
                    perro.setApariencia(rs.getString("apariencia"));
                    perro.setPelo(rs.getString("pelo"));
                    perro.setColor(rs.getString("color"));
                    perro.setEspalda(rs.getString("espalda"));
                    perro.setLomo(rs.getString("lomo"));
                    perro.setCola(rs.getString("cola"));
                    perro.setPecho(rs.getString("pecho"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la consulta: " + ex.getMessage());
        }
        return perro;
    }    
    
    public ArrayList<PerroVO> listaDePerros() {
        ArrayList<PerroVO> misPerros = new ArrayList<>();
        String consulta = "SELECT * FROM mascotas";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PerroVO perro = new PerroVO();
                perro.setId(rs.getString("id"));
                perro.setNombre(rs.getString("nombre"));
                perro.setPaisOrigen(rs.getString("paisOrigen"));
                perro.setGrupo(rs.getString("grupo"));
                perro.setSeccion(rs.getString("seccion"));
                perro.setApariencia(rs.getString("apariencia"));
                perro.setPelo(rs.getString("pelo"));
                perro.setColor(rs.getString("color"));
                perro.setEspalda(rs.getString("espalda"));
                perro.setLomo(rs.getString("lomo"));
                perro.setCola(rs.getString("cola"));
                perro.setPecho(rs.getString("pecho"));
                misPerros.add(perro);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener la lista de perros: " + ex.getMessage());
        }
        return misPerros;
    }

    public boolean insertarDatos(PerroVO perro) {
        String insercion = "INSERT INTO mascotas (id, nombre, paisOrigen, grupo, seccion, apariencia, pelo, color, espalda, lomo, cola, pecho) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(insercion)) {
            ps.setString(1, perro.getId());
            ps.setString(2, perro.getNombre());
            ps.setString(3, perro.getPaisOrigen());
            ps.setString(4, perro.getGrupo());
            ps.setString(5, perro.getSeccion());
            ps.setString(6, perro.getApariencia());
            ps.setString(7, perro.getPelo());
            ps.setString(8, perro.getColor());
            ps.setString(9, perro.getEspalda());
            ps.setString(10, perro.getLomo());
            ps.setString(11, perro.getCola());
            ps.setString(12, perro.getPecho());
            ps.executeUpdate();
            System.out.println("Datos del perro insertados correctamente.");
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al insertar los datos: " + ex.getMessage());
            return false;
        }
    }

    public boolean eliminarPerro(String id) {
        String consulta = "DELETE FROM mascotas WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el perro: " + ex.getMessage());
            return false;
        }
    }
    

    public boolean modificarPerro(PerroVO perro) {
        String consulta = "UPDATE mascotas SET nombre = ?, paisOrigen = ?, grupo = ?, seccion = ?, apariencia = ?, pelo = ?, color = ?, espalda = ?, lomo = ?, cola = ?, pecho = ? WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, perro.getNombre());
            ps.setString(2, perro.getPaisOrigen());
            ps.setString(3, perro.getGrupo());
            ps.setString(4, perro.getSeccion());
            ps.setString(5, perro.getApariencia());
            ps.setString(6, perro.getPelo());
            ps.setString(7, perro.getColor());
            ps.setString(8, perro.getEspalda());
            ps.setString(9, perro.getLomo());
            ps.setString(10, perro.getCola());
            ps.setString(11, perro.getPecho());
            ps.setString(12, perro.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error al modificar el perro: " + ex.getMessage());
            return false;
        }
    }


    public boolean existePerro(String id) {
        String consulta = "SELECT nombre, paisOrigen, grupo, seccion FROM mascotas WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String paisOrigen = rs.getString("paisOrigen");
                    String grupo = rs.getString("grupo");
                    String seccion = rs.getString("seccion");

                    // Valida si los campos no son nulos o vacíos
                    return nombre != null && !nombre.isEmpty() &&
                           paisOrigen != null && !paisOrigen.isEmpty() &&
                           grupo != null && !grupo.isEmpty() &&
                           seccion != null && !seccion.isEmpty();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la verificación de existencia: " + ex.getMessage());
        }
        return false; // Retorna false si no encuentra el perro o hay algún error
    }
}