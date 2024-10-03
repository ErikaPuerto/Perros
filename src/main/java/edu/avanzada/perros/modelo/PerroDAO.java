package edu.avanzada.perros.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerroDAO {
      private Connection cn;
      // Constructor de PerroDAO
    public PerroDAO() {
        // Inicializa la conexión usando un método estático de tu clase de conexión
        this.cn = Conexion.getConexion(); // Asegúrate de que esto no sea null
    }
    // Método general para buscar perros por varios criterios
    public List<PerroVO> consultarPerroPorCriterios(String id, String nombre, String paisOrigen, String grupo, String seccion, String color) {
        List<PerroVO> perrosEncontrados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM perros WHERE 1=1");

        // Construir la consulta
        if (id != null && !id.isEmpty()) {
            sql.append(" AND id = ?");
        } 
        if (nombre != null && !nombre.isEmpty()) {
            sql.append(" AND nombre = ?");
        }
        if (paisOrigen != null && !paisOrigen.isEmpty()) {
            sql.append(" AND pais_origen = ?");
        }
        if (grupo != null && !grupo.isEmpty() && seccion != null && !seccion.isEmpty()) {
            sql.append(" AND grupo = ? AND seccion = ?");
        }
        if (color != null && !color.isEmpty()) {
            sql.append(" AND color = ?");
        }

        try (PreparedStatement stmt = cn.prepareStatement(sql.toString())) {
            int index = 1;
            if (id != null && !id.isEmpty()) {
                stmt.setString(index++, id);
            }
            if (nombre != null && !nombre.isEmpty()) {
                stmt.setString(index++, nombre);
            }
            if (paisOrigen != null && !paisOrigen.isEmpty()) {
                stmt.setString(index++, paisOrigen);
            }
            if (grupo != null && !grupo.isEmpty() && seccion != null && !seccion.isEmpty()) {
                stmt.setString(index++, grupo);
                stmt.setString(index++, seccion);
            }
            if (color != null && !color.isEmpty()) {
                stmt.setString(index++, color);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PerroVO perro = new PerroVO();
                perro.setId(rs.getString("id"));
                perro.setNombre(rs.getString("nombre"));
                perro.setPaisOrigen(rs.getString("pais_origen"));
                perro.setGrupo(rs.getString("grupo"));
                perro.setSeccion(rs.getString("seccion"));
                perro.setColor(rs.getString("color"));
                perrosEncontrados.add(perro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perrosEncontrados;
    }

    // Métodos específicos para cada criterio
    public List<PerroVO> consultarPorId(String id) {
        return consultarPerroPorCriterios(id, null, null, null, null, null);
    }
    public List<PerroVO> consultarPorNombre(String nombre) {
        return consultarPerroPorCriterios(null, nombre, null, null, null, null);
    }

    public List<PerroVO> consultarPorPaisOrigen(String paisOrigen) {
        return consultarPerroPorCriterios(null, null, paisOrigen, null, null, null);
    }

    public List<PerroVO> consultarPorGrupoYSeccion(String grupo, String seccion) {
        return consultarPerroPorCriterios(null, null, null, grupo, seccion, null);
    }

    public List<PerroVO> consultarPorColor(String color) {
        return consultarPerroPorCriterios(null, null, null, null, null, color);
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
}