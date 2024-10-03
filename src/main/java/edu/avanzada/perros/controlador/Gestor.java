package edu.avanzada.perros.controlador;

import edu.avanzada.perros.modelo.PerroVO;
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

    public Gestor() {
    this.miControladorPerro = new ControladorPerro();
    this.miPerroDAO = new PerroDAO();  // Asegúrate de que también se inicialice el DAO
    this.listaPerros = new ArrayList<>();  // Asegúrate de inicializar la lista
}

    public void registrarPerro(PerroVO miPerro) {
        if (!miControladorPerro.existePerro(miPerro.getNombre())) {
            miPerroDAO.insertarDatos(miPerro);
            listaPerros.add(miPerro);
            System.out.println("Perro registrado con exito.");
        } else {
            System.out.println("La raza ya esta registrada.");
        }
    }

    public void eliminarPerro(String id) {
        if (miPerroDAO.eliminarPerro(id)) {
            listaPerros.removeIf(perro -> perro.getId().equals(id));
            System.out.println("Perro Eliminado.");
        } else {
            System.out.println("No se pudo eliminar el registro del perro.");
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
            System.out.println("Perro modificado exitosamente.");
            miControladorPerro.imprimirDetallesPerro(perroModificado);
        } else {
            System.out.println("No se pudo modificar el registro del perro.");
        }
    } else {
        System.out.println("No existe un registro de perro con ese ID.");
    }
}

    
    //Consultar perro por id (llave primaria)
    public void buscarPerro(String id) {
        PerroVO perroEncontrado = (PerroVO) miPerroDAO.consultarPorId(id);
        if (perroEncontrado != null) {
            miControladorPerro.imprimirDetallesPerro(perroEncontrado);
        } else {
            System.out.println("No existe un registro de perro con esa raza.");
        }
    }
    
  
}