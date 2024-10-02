package edu.avanzada.perros.control;

import edu.avanzada.perros.modelo.PerroDAO;
import java.util.ArrayList;
import edu.avanzada.perros.modelo.PerroVO;
import edu.avanzada.perros.utils.Propiedades;
import edu.avanzada.perros.utils.Serializador;
import edu.avanzada.perros.utils.ArchivoAleatorioPerros;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

public class Gestor {

    private PerroDAO miPerroDAO;
    private List<PerroVO> listaPerros;
    private ArchivoAleatorioPerros archivoAleatorio;

    public Gestor() {
        miPerroDAO = new PerroDAO();

        // Deserializamos los datos al iniciar
        listaPerros = Serializador.deserializarDatos();

        if (listaPerros.isEmpty()) {
            // Si no hay datos deserializados, cargamos desde la base de datos
            listaPerros = miPerroDAO.listaDePerros();
        }

        // Cargamos los datos iniciales desde el archivo de propiedades
        cargarDatosDesdePropiedades();

        // Procesamos los datos como sea necesario
        obtenerRegistros();
        
        archivoAleatorio = new ArchivoAleatorioPerros();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            archivoAleatorio.escribirPerros(listaPerros);
            archivoAleatorio.cerrar();
            Serializador.serializarDatos(listaPerros);
        }));
    }


    private void cargarDatosDesdePropiedades() {
        Map<String, String> razasIniciales = Propiedades.cargarPropiedades();

        for (String key : razasIniciales.keySet()) {
            String[] valores = razasIniciales.get(key).split(",");
            String nombre = valores[0].trim();
            String paisOrigen = valores[1].trim();
            String id = key.trim(); // Obtener el ID del key

            // Imprimir información de depuración
            System.out.println("Cargando perro: ID=" + id + ", Nombre=" + nombre + ", País de Origen=" + paisOrigen);

            // Verificamos si la raza ya existe en la base de datos usando el id (key)
            if (!miPerroDAO.existePerro(id)) {
                PerroVO nuevoPerro = new PerroVO();
                nuevoPerro.setId(id); // Asegúrate de setear el id
                nuevoPerro.setNombre(nombre);
                nuevoPerro.setPaisOrigen(paisOrigen);
                nuevoPerro.setGrupo("");  // Inicialmente vacío, podría completarse más tarde
                nuevoPerro.setSeccion("");
                

                if (miPerroDAO.insertarDatos(nuevoPerro)) {
                    listaPerros.add(nuevoPerro);
                    System.out.println("Raza '" + nombre + "' registrada en la base de datos.");
                } else {
                    System.out.println("Error al registrar la raza '" + nombre + "' en la base de datos.");
                }
            } else {
                System.out.println("La raza '" + nombre + "' ya está registrada en la base de datos.");
            }
        }
    }




    public void obtenerRegistros() {
        if (listaPerros.isEmpty()) {
            System.out.println("No hay registros de perros.");
        } else {
            int numeroPerro = 0;
            for (PerroVO perro : listaPerros) {
                numeroPerro++;
                System.out.println("****************Perro No. " + numeroPerro + "**********");
                imprimirDetallesPerro(perro);
            }
        }
    }

    public void buscarPerro(String id) {
        PerroVO perroEncontrado = miPerroDAO.consultarPerro(id);
        if (perroEncontrado != null) {
            imprimirDetallesPerro(perroEncontrado);
        } else {
            System.out.println("No existe un registro de perro con esa raza.");
        }
    }

    public void registrarPerro(PerroVO miPerro) {
        if (!miPerroDAO.existePerro(miPerro.getNombre())) {
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
                imprimirDetallesPerro(perroModificado);
            } else {
                System.out.println("No se pudo modificar el registro del perro.");
            }
        } else {
            System.out.println("No existe un registro de perro con ese nombre.");
        }
    }

    private void imprimirDetallesPerro(PerroVO perro) {
        System.out.println("Id" + perro.getId());
        System.out.println("Raza: " + perro.getNombre());
        System.out.println("Pais de Origen: " + perro.getPaisOrigen());
        System.out.println("Grupo: " + perro.getGrupo());
        System.out.println("Seccion: " + perro.getSeccion());
        System.out.println("*************************************************\n");
    }
    
    // Método para leer el archivo aleatorio (puede ser útil para verificar)
    public void leerArchivoAleatorio() {
        archivoAleatorio.leerTodo();
    }
}
