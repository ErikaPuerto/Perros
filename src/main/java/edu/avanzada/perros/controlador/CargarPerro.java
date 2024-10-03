package edu.avanzada.perros.controlador;

import edu.avanzada.perros.modelo.PerroDAO;
import edu.avanzada.perros.modelo.PerroVO;
import edu.avanzada.perros.utils.ArchivoAleatorioPerros;
import edu.avanzada.perros.utils.Propiedades;
import edu.avanzada.perros.utils.Serializador;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nedic
 */
public class CargarPerro {
    private PerroDAO miPerroDAO;
    private ControladorPerro miControladorPerro;
    private List<PerroVO> listaPerros;
    private ArchivoAleatorioPerros archivoAleatorio;
    
    public CargarPerro() {
        miPerroDAO = new PerroDAO();
        miControladorPerro = new ControladorPerro();
        
        // Deserializamos los datos al iniciar
        listaPerros = Serializador.deserializarDatos();
        
        if (listaPerros.isEmpty()) {
            // Si no hay datos deserializados, cargamos desde la base de datos
            listaPerros = miControladorPerro.listaDePerros();
        }

        // Cargamos los datos iniciales desde el archivo de propiedades
        cargarDatosDesdePropiedades();

        // Procesamos los datos como sea necesario
        miControladorPerro.obtenerRegistros();
        
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

        if (!miControladorPerro.existePerro(id)) {
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
            // Si el perro ya existe, lo cargamos en la lista de perros
            List<PerroVO> perrosExistentes = miPerroDAO.consultarPorId(id); // Consultamos el perro de la base de datos
            if (!perrosExistentes.isEmpty()) {
                listaPerros.add(perrosExistentes.get(0));  // Agregamos el primer perro existente a la lista
                System.out.println("La raza '" + nombre + "' ya está registrada en la base de datos.");
            }
        }
    }
}


    // Método para leer el archivo aleatorio (puede ser útil para verificar)
    public void leerArchivoAleatorio() {
        archivoAleatorio.leerTodo();
    }
}