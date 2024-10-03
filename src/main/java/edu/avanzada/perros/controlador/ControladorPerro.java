package edu.avanzada.perros.controlador;

import edu.avanzada.perros.modelo.Conexion;
import edu.avanzada.perros.modelo.PerroDAO;
import edu.avanzada.perros.modelo.PerroVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nedic
 */
public class ControladorPerro {
    private List<PerroVO> listaPerros;
    private PerroDAO miPerroDAO;
    
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
    
    public boolean existePerro(String id) {
        String consulta = "SELECT 1 FROM mascotas WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();  // Si devuelve algún resultado, el perro existe
            }
        } catch (SQLException ex) {
            System.out.println("Error en la verificación de existencia: " + ex.getMessage());
        }
        return false; // Retorna false si no encuentra el perro o hay algún error
    }
    
    public void obtenerRegistros() {
    listaPerros = listaDePerros();
    if (listaPerros.isEmpty()) {
        System.out.println("No hay registros de perros.");
    } else {
        int numeroPerro = 0;
        for (PerroVO perro : listaPerros) {
            numeroPerro++;
            System.out.println("*Perro No. " + numeroPerro + "*");
            imprimirDetallesPerro(perro);
        }
    }
}

    public void imprimirDetallesPerro(PerroVO perro) {
        System.out.println("Id: " + perro.getId());
        System.out.println("Raza: " + perro.getNombre());
        System.out.println("Pais de Origen: " + perro.getPaisOrigen());
        System.out.println("Grupo: " + perro.getGrupo());
        System.out.println("Seccion: " + perro.getSeccion());
        System.out.println("*****************\n");
    }
}