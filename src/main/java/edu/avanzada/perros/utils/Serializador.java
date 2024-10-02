package edu.avanzada.perros.utils;

import edu.avanzada.perros.modelo.PerroVO;
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

    public static void serializarDatos(List<PerroVO> listaPerros) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listaPerros);
            System.out.println("Datos serializados correctamente.");
        } catch (IOException ex) {
            System.out.println("Error al serializar los datos: " + ex.getMessage());
        }
    }

    public static List<PerroVO> deserializarDatos() {
        List<PerroVO> listaPerros = new ArrayList<>();
        
        
        File archivo = new File("razas_serializadas.dat");
        if (!archivo.exists()) {
        System.out.println("Archivo de serializacion no encontrado. Cargando lista vacia.");
        return listaPerros;  // Devuelve lista vacía si el archivo no existe
        }
        
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            listaPerros = (List<PerroVO>) ois.readObject();
            System.out.println("Datos deserializados correctamente.");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al deserializar los datos: " + ex.getMessage());
        }
        return listaPerros;
    }
}
