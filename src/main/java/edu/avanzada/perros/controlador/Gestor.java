package edu.avanzada.perros.controlador;

import edu.avanzada.perros.modelo.PerroVO;
import java.util.ArrayList;
import edu.avanzada.perros.modelo.PerroDAO;
import edu.avanzada.perros.utils.ArchivoAleatorioPerros;
import edu.avanzada.perros.vista.ControladorVentana;
import java.util.List;

public class Gestor {

    private PerroDAO miPerroDAO;
    private ControladorPerro miControladorPerro;
    private List<PerroVO> listaPerros;
    private ArchivoAleatorioPerros archivoAleatorio;
    private ControladorVentana controladorVentana;  // Declarar la clase para controlar ventanas

    public Gestor() {
        this.miControladorPerro = new ControladorPerro();
        this.miPerroDAO = new PerroDAO();  // Asegúrate de que también se inicialice el DAO
        this.listaPerros = new ArrayList<>();  // Asegúrate de inicializar la lista
        this.controladorVentana = new ControladorVentana();  // Instanciar la clase
    }

    public void registrarPerro(PerroVO miPerro) {
        if (!miControladorPerro.existePerro(miPerro.getNombre())) {
            miPerroDAO.insertarDatos(miPerro);
            listaPerros.add(miPerro);
            controladorVentana.mostrarMensaje("Perro registrado con éxito.");
        } else {
            controladorVentana.mostrarMensaje("La raza ya está registrada.");
        }
    }

    public void eliminarPerro(String id) {
        if (miPerroDAO.eliminarPerro(id)) {
            listaPerros.removeIf(perro -> perro.getId().equals(id));
            controladorVentana.mostrarMensaje("Perro eliminado.");
        } else {
            controladorVentana.mostrarMensaje("No se pudo eliminar el registro del perro.");
        }
    }

    public void modificarPerro(PerroVO perroModificado) {
        // Consultar el perro existente por ID
        List<PerroVO> perrosExistentes = miPerroDAO.consultarPorId(perroModificado.getId());

        if (!perrosExistentes.isEmpty()) {
            PerroVO perroExistente = perrosExistentes.get(0); // Obtener el primer resultado

            // Actualizar los campos del perro modificado
            perroModificado.setNombre(perroExistente.getNombre());
            perroModificado.setPaisOrigen(perroExistente.getPaisOrigen());
            perroModificado.setGrupo(perroExistente.getGrupo());
            perroModificado.setSeccion(perroExistente.getSeccion());
            perroModificado.setApariencia(perroExistente.getApariencia());
            perroModificado.setPelo(perroExistente.getPelo());
            perroModificado.setColor(perroExistente.getColor());
            perroModificado.setEspalda(perroExistente.getEspalda());
            perroModificado.setLomo(perroExistente.getLomo());
            perroModificado.setCola(perroExistente.getCola());
            perroModificado.setPecho(perroExistente.getPecho());

            // Intentar modificar el perro en la base de datos
            if (miPerroDAO.modificarPerro(perroModificado)) {
                // Actualizar la lista de perros en memoria
                listaPerros.replaceAll(perro -> perro.getId().equals(perroModificado.getId()) ? perroModificado : perro);
                controladorVentana.mostrarMensaje("Perro modificado exitosamente.");
                miControladorPerro.imprimirDetallesPerro(perroModificado);
            } else {
                controladorVentana.mostrarMensaje("No se pudo modificar el registro del perro.");
            }
        } else {
            controladorVentana.mostrarMensaje("No existe un registro de perro con ese ID.");
        }
    }

    // Método general para buscar perro por criterio
    public void buscarPerroPorCriterio(String campo, String valor, String grupo, String seccion) {
        List<PerroVO> perrosEncontrados = new ArrayList<>();

        switch (campo) {
            case "id":
                perrosEncontrados = miPerroDAO.consultarPorId(valor);
                break;
            case "nombre":
                perrosEncontrados = miPerroDAO.consultarPorNombre(valor);
                break;
            case "paisOrigen":
                perrosEncontrados = miPerroDAO.consultarPorPaisOrigen(valor);
                break;
            case "grupo":
                perrosEncontrados = miPerroDAO.consultarPorGrupoYSeccion(grupo, seccion);
                break;
            case "color":
                perrosEncontrados = miPerroDAO.consultarPorColor(valor);
                break;
            default:
                controladorVentana.mostrarMensaje("Criterio de búsqueda no válido.");
                return;
        }

        if (!perrosEncontrados.isEmpty()) {
            for (PerroVO perro : perrosEncontrados) {
                miControladorPerro.imprimirDetallesPerro(perro);
            }
        } else {
            controladorVentana.mostrarMensaje("No existe un registro de perro con ese " + campo + ".");
        }
    }

    // Métodos específicos de búsqueda
    public void buscarPerroPorId(String id) {
        buscarPerroPorCriterio("id", id, null, null);
    }

    public void buscarPerroPorNombre(String nombre) {
        buscarPerroPorCriterio("nombre", nombre, null, null);
    }

    public void buscarPerroPorPaisOrigen(String paisOrigen) {
        buscarPerroPorCriterio("paisOrigen", paisOrigen, null, null);
    }

    public void buscarPerroPorGrupoYSeccion(String grupo, String seccion) {
        buscarPerroPorCriterio("grupo", grupo, grupo, seccion);
    }

    public void buscarPerroPorColor(String color) {
        buscarPerroPorCriterio("color", color, null, null);
    }
}
