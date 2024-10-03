package edu.avanzada.perros.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Propiedades {

    private static final String PROPERTIES_FILE = "Razas.properties"; // Asegúrate de que este archivo esté en resources

    public static Map<String, String> cargarPropiedades() {
        Map<String, String> razas = new HashMap<>();
        try (InputStream input = Propiedades.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar el archivo " + PROPERTIES_FILE);
                return razas;
            }
            Properties propiedades = new Properties();
            propiedades.load(input);

            for (String key : propiedades.stringPropertyNames()) {
                String value = propiedades.getProperty(key);
                razas.put(key, value);  // Guardamos la clave (Raza1, Raza2) y el valor (Labrador,Canadá)
            }
            System.out.println("Propiedades cargadas correctamente.");
        } catch (Exception ex) {
            System.out.println("Error al cargar las propiedades: " + ex.getMessage());
        }
        return razas;
    }
}