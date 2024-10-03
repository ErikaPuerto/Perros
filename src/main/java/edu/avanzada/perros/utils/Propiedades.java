package edu.avanzada.perros.utils;

import edu.avanzada.perros.vista.ControladorVentana;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Propiedades {
    private static final String PROPERTIES_FILE = "Razas.properties"; // Asegúrate de que este archivo esté en resources
    private ControladorVentana controladorVentana;

    // Constructor que recibe ControladorVentana
    public Propiedades(ControladorVentana controladorVentana) {
        this.controladorVentana = controladorVentana;
    }

    public Map<String, String> cargarPropiedades() {
        Map<String, String> razas = new HashMap<>();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                controladorVentana.mostrarError("Lo siento, no se pudo encontrar el archivo " + PROPERTIES_FILE);
                return razas;
            }
            Properties propiedades = new Properties();
            propiedades.load(input);

            for (String key : propiedades.stringPropertyNames()) {
                String value = propiedades.getProperty(key);
                razas.put(key, value);  // Guardamos la clave (Raza1, Raza2) y el valor (Labrador,Canadá)
            }
            controladorVentana.mostrarMensaje("Propiedades cargadas correctamente.");
        } catch (Exception ex) {
            controladorVentana.mostrarError("Error al cargar las propiedades: " + ex.getMessage());
        }
        return razas;
    }
}
