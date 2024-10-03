package edu.avanzada.perros.utils;

import edu.avanzada.perros.modelo.PerroVO;
import edu.avanzada.perros.vista.ControladorVentana;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

public class Serializador {
    private static final String FILE_NAME = "razas_serializadas.dat";
    private ControladorVentana controladorVentana;

    // Constructor que recibe ControladorVentana
    public Serializador(ControladorVentana controladorVentana) {
        this.controladorVentana = controladorVentana;
    }

    public void serializarDatos(List<PerroVO> listaPerros) {
        File archivo = new File(FILE_NAME);

        // Verifica si el archivo ya existe
        if (!archivo.exists()) {
            try (FileOutputStream fos = new FileOutputStream(archivo);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(listaPerros);
                controladorVentana.mostrarMensaje("Datos serializados correctamente.");
            } catch (IOException ex) {
                controladorVentana.mostrarError("Error al serializar los datos: " + ex.getMessage());
            }
        } else {
            controladorVentana.mostrarError("El archivo ya existe. No se han serializado los datos.");
        }
    }

    public List<PerroVO> deserializarDatos() {
        List<PerroVO> listaPerros = new ArrayList<>();
        File archivo = new File(FILE_NAME);
        if (!archivo.exists()) {
            controladorVentana.mostrarError("Archivo de serialización no encontrado. Cargando lista vacía.");
            return listaPerros;  // Devuelve lista vacía si el archivo no existe
        }

        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            listaPerros = (List<PerroVO>) ois.readObject();
            controladorVentana.mostrarMensaje("Datos deserializados correctamente.");
        } catch (IOException | ClassNotFoundException ex) {
            controladorVentana.mostrarError("Error al deserializar los datos: " + ex.getMessage());
        }
        return listaPerros;
    }
}
