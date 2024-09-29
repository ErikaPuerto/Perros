package Control;

import Modelo.PerroDAO;
import Modelo.PerroVO;
import Utils.Propiedades;
import Utils.Serializador;

import java.util.List;
import java.util.Map;

public class Gestor {

    private PerroDAO miPerroDAO;
    private List<PerroVO> listaPerros;

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

        // Serializamos los datos al cerrar
        Serializador.serializarDatos(listaPerros);
    }

    private void cargarDatosDesdePropiedades() {
        Map<String, String> razasIniciales = Propiedades.cargarPropiedades();

        for (String key : razasIniciales.keySet()) {
            String[] valores = razasIniciales.get(key).split(",");
            String nombre = valores[0].trim();
            String paisOrigen = valores[1].trim();

            // Verificamos si la raza ya existe en la base de datos
            if (!miPerroDAO.existePerro(nombre)) {
                PerroVO nuevoPerro = new PerroVO();
                nuevoPerro.setNombre(nombre);
                nuevoPerro.setPaisOrigen(paisOrigen);
                nuevoPerro.setClasificacion("");  // Inicialmente vacío, podría completarse más tarde

                miPerroDAO.insertarDatos(nuevoPerro);  // Insertamos en la base de datos
                listaPerros.add(nuevoPerro);  // También lo agregamos a la lista para serializar
                System.out.println("Raza '" + nombre + "' registrada desde el archivo de propiedades.");
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

    public void buscarPerro(String nombre) {
        PerroVO perroEncontrado = miPerroDAO.consultarPerro(nombre);
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

    public void eliminarPerro(String nombre) {
        if (miPerroDAO.eliminarPerro(nombre)) {
            listaPerros.removeIf(perro -> perro.getNombre().equals(nombre));
            System.out.println("Perro Eliminado.");
        } else {
            System.out.println("No se pudo eliminar el registro del perro.");
        }
    }

    public void modificarPerro(PerroVO perroModificado) {
        PerroVO perroExistente = miPerroDAO.consultarPerro(perroModificado.getNombre());

        if (perroExistente != null) {
            perroModificado.setNombre(perroExistente.getNombre());
            perroModificado.setPaisOrigen(perroExistente.getPaisOrigen());
            perroModificado.setClasificacion(perroExistente.getClasificacion());

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
        System.out.println("Raza: " + perro.getNombre());
        System.out.println("Pais de Origen: " + perro.getPaisOrigen());
        System.out.println("Clasificación: " + perro.getClasificacion());
        System.out.println("*************************************************\n");
    }
}