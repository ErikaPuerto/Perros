package edu.avanzada.perros.controlador;

import edu.avanzada.perros.modelo.Conexion;
import edu.avanzada.perros.modelo.PerroDAO;
import edu.avanzada.perros.modelo.PerroVO;
import edu.avanzada.perros.vista.ControladorVentana;  // Importa la nueva clase
import edu.avanzada.perros.vista.ControladorVentana;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorPerro {
    private List<PerroVO> listaPerros;
    private PerroDAO miPerroDAO;
    private ControladorVentana controladorVentana;  // Declarar la clase para ventanas emergentes

    public ControladorPerro() {
        controladorVentana = new ControladorVentana();  // Instanciar la clase
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
            controladorVentana.mostrarError("Error al obtener la lista de perros: " + ex.getMessage());
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
            controladorVentana.mostrarError("Error en la verificación de existencia: " + ex.getMessage());
        }
        return false; // Retorna false si no encuentra el perro o hay algún error
    }

    public void obtenerRegistros() {
        listaPerros = listaDePerros();
        if (listaPerros.isEmpty()) {
            controladorVentana.mostrarMensaje("No hay registros de perros.");
        } else {
            int numeroPerro = 0;
            for (PerroVO perro : listaPerros) {
                numeroPerro++;
                controladorVentana.mostrarMensaje("*Perro No. " + numeroPerro + "*");
                imprimirDetallesPerro(perro);
            }
        }
    }

    public void imprimirDetallesPerro(PerroVO perro) {
        // Utiliza ventana emergente para mostrar detalles
        controladorVentana.mostrarMensaje("Id: " + perro.getId() +
                                        "\nRaza: " + perro.getNombre() +
                                        "\nPais de Origen: " + perro.getPaisOrigen() +
                                        "\nGrupo: " + perro.getGrupo() +
                                        "\nSeccion: " + perro.getSeccion() +
                                        "\n*****************\n");
    }
}
