package edu.avanzada.perros.controlador;

import edu.avanzada.perros.modelo.PerroDAO;
import edu.avanzada.perros.modelo.PerroVO;
import edu.avanzada.perros.utils.ArchivoAleatorioPerros;
import edu.avanzada.perros.utils.Propiedades;
import edu.avanzada.perros.utils.Serializador;
import edu.avanzada.perros.vista.ControladorVentana;
import java.util.List;
import java.util.Map;

public class CargarPerro {
    private PerroDAO miPerroDAO;
    private ControladorPerro miControladorPerro;
    private List<PerroVO> listaPerros;
    private ArchivoAleatorioPerros archivoAleatorio;
    private ControladorVentana controladorVentana; 
    private Propiedades propiedades; // Nueva instancia
    private Serializador serializador; // Nueva instancia

    public CargarPerro() {
        miPerroDAO = new PerroDAO();
        miControladorPerro = new ControladorPerro();
        controladorVentana = new ControladorVentana();
        
        serializador = new Serializador(controladorVentana); // Crear instancia de Serializador
        listaPerros = serializador.deserializarDatos(); // Cambiar a instancia

        if (listaPerros.isEmpty()) {
            listaPerros = miControladorPerro.listaDePerros();
        }

        cargarDatosDesdePropiedades();

        miControladorPerro.obtenerRegistros();
        
        archivoAleatorio = new ArchivoAleatorioPerros(controladorVentana);  
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            archivoAleatorio.escribirPerros(listaPerros);
            archivoAleatorio.cerrar();
            serializador.serializarDatos(listaPerros); // Cambiar a instancia
        }));
    }
    
    private void cargarDatosDesdePropiedades() {
        propiedades = new Propiedades(controladorVentana); // Crear instancia de Propiedades
        Map<String, String> razasIniciales = propiedades.cargarPropiedades(); // Cambiar a instancia

        for (String key : razasIniciales.keySet()) {
            String[] valores = razasIniciales.get(key).split(",");
            String nombre = valores[0].trim();
            String paisOrigen = valores[1].trim();
            String id = key.trim();

            controladorVentana.mostrarMensaje("Cargando perro: ID=" + id + ", Nombre=" + nombre + ", País de Origen=" + paisOrigen);

            if (!miControladorPerro.existePerro(id)) {
                PerroVO nuevoPerro = new PerroVO();
                nuevoPerro.setId(id);
                nuevoPerro.setNombre(nombre);
                nuevoPerro.setPaisOrigen(paisOrigen);
                nuevoPerro.setGrupo("");
                nuevoPerro.setSeccion("");

                if (miPerroDAO.insertarDatos(nuevoPerro)) {
                    listaPerros.add(nuevoPerro);
                    controladorVentana.mostrarMensaje("Raza '" + nombre + "' registrada en la base de datos."); 
                } else {
                    controladorVentana.mostrarError("Error al registrar la raza '" + nombre + "' en la base de datos.");
                }
            } else {
                List<PerroVO> perrosExistentes = miPerroDAO.consultarPorId(id);
                if (!perrosExistentes.isEmpty()) {
                    listaPerros.add(perrosExistentes.get(0));
                    controladorVentana.mostrarMensaje("La raza '" + nombre + "' ya está registrada en la base de datos.");
                }
            }
        }
    }

    public void leerArchivoAleatorio() {
        archivoAleatorio.leerTodo();
    }
}
