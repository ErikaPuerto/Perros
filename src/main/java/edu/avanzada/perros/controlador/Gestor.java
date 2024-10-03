package edu.avanzada.perros.controlador;

import java.util.ArrayList;
import edu.avanzada.perros.modelo.PerroDAO;
import edu.avanzada.perros.controlador.ControladorPerro;
import edu.avanzada.perros.modelo.PerroVO;
import edu.avanzada.perros.utils.Propiedades;
import edu.avanzada.perros.utils.Serializador;
import edu.avanzada.perros.utils.ArchivoAleatorioPerros;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

public class Gestor {

    private PerroDAO miPerroDAO;
    private ControladorPerro miControladorPerro;
    private List<PerroVO> listaPerros;
    private ArchivoAleatorioPerros archivoAleatorio;


    public void registrarPerro(PerroVO miPerro) {
        if (!miControladorPerro.existePerro(miPerro.getNombre())) {
            miPerroDAO.insertarDatos(miPerro);
            listaPerros.add(miPerro);
            System.out.println("Perro registrado con éxito.");
        } else {
            System.out.println("La raza ya está registrada.");
        }
    }

    public void eliminarPerro(String id) {
        if (miPerroDAO.eliminarPerro(id)) {
            listaPerros.removeIf(perro -> perro.getNombre().equals(id));
            System.out.println("Perro Eliminado.");
        } else {
            System.out.println("No se pudo eliminar el registro del perro.");
        }
    }

    public void modificarPerro(PerroVO perroModificado) {
        PerroVO perroExistente = miPerroDAO.consultarPerro(perroModificado.getId());


        if (perroExistente != null) {
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


            if (miPerroDAO.modificarPerro(perroModificado)) {
                listaPerros.replaceAll(perro -> perro.getNombre().equals(perroModificado.getNombre()) ? perroModificado : perro);
                System.out.println("Perro modificado exitosamente.");
                miControladorPerro.imprimirDetallesPerro(perroModificado);
            } else {
                System.out.println("No se pudo modificar el registro del perro.");
            }
        } else {
            System.out.println("No existe un registro de perro con ese nombre.");
        }
    }
    public void buscarPerro(String id) {
        PerroVO perroEncontrado = miPerroDAO.consultarPerro(id);
        if (perroEncontrado != null) {
            miControladorPerro.imprimirDetallesPerro(perroEncontrado);
        } else {
            System.out.println("No existe un registro de perro con esa raza.");
        }
    }

}
