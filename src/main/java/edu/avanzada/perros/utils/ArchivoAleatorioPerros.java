package edu.avanzada.perros.utils;

import edu.avanzada.perros.vista.ControladorVentana;
import edu.avanzada.perros.modelo.PerroVO;
import java.io.*;
import java.util.*;

public class ArchivoAleatorioPerros {
    private File fl;
    private RandomAccessFile archivo;
    private long tamreg;
    private long canreg;
    private ControladorVentana controlador; // Referencia al controlador

    // Constructor que recibe ControladorVentana
    public ArchivoAleatorioPerros(ControladorVentana controlador) {
        this.controlador = controlador; // Inicializa la referencia al controlador
        this.tamreg = 2910; // 10 + 25 + 25 + 25 + 25 + (400 * 7) = 2910
        this.canreg = 0;
    }

    public void escribirPerros(List<PerroVO> perros) {
        try {
            String fileName = "perros.dat";
            fl = new File(fileName);
            
            // Eliminar archivo existente si existe
            if (fl.exists()) {
                fl.delete();
            }
            
            archivo = new RandomAccessFile(fl, "rw");
            
            // Usar un Set para eliminar duplicados basados en el ID
            Set<String> idSet = new HashSet<>();
            
            for (PerroVO perro : perros) {
                if (idSet.add(perro.getId())) {
                    escribirPerro(perro);
                    canreg++;
                }
            }
            
            // Llamar al método del controlador para mostrar un mensaje
            controlador.mostrarMensaje("Archivo aleatorio creado: " + fl.getAbsolutePath());
        } catch (IOException e) {
            // Llamar al método del controlador para mostrar un mensaje de error
            controlador.mostrarMensaje("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            cerrar();
        }
    }

    private void escribirPerro(PerroVO perro) throws IOException {
        escribirCampo(perro.getId(), 10);
        escribirCampo(perro.getNombre(), 25);
        escribirCampo(perro.getPaisOrigen(), 25);
        escribirCampo(perro.getGrupo(), 25);
        escribirCampo(perro.getSeccion(), 25);
        escribirCampo(perro.getApariencia(), 400);
        escribirCampo(perro.getPelo(), 400);
        escribirCampo(perro.getColor(), 400);
        escribirCampo(perro.getEspalda(), 400);
        escribirCampo(perro.getLomo(), 400);
        escribirCampo(perro.getCola(), 400);
        escribirCampo(perro.getPecho(), 400);
    }

    private void escribirCampo(String valor, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(valor == null ? "" : valor);
        sb.setLength(longitud);
        archivo.writeBytes(sb.toString());
    }

    public void leerTodo() {
        controlador.mostrarMensaje("\nMostrando todos los Registros");
        try {
            archivo = new RandomAccessFile(fl, "r");
            archivo.seek(0);
            canreg = archivo.length() / tamreg;
            for (int r = 0; r < canreg; r++) {
                archivo.seek(r * tamreg);
                String id = leerCampo(10);
                String nombre = leerCampo(25);
                String paisOrigen = leerCampo(25);
                String grupo = leerCampo(25);
                String seccion = leerCampo(25);
                String apariencia = leerCampo(400); // apariencia
                String pelo = leerCampo(400); // pelo
                String color = leerCampo(400); // color
                String espalda = leerCampo(400); // espalda
                String lomo = leerCampo(400); // lomo
                String cola = leerCampo(400); // cola
                String pecho = leerCampo(400); // pecho
                
                controlador.mostrarMensaje("Registro No: " + (r + 1) + 
                                   " ID: " + id + 
                                   " Nombre: " + nombre + 
                                   " País de Origen: " + paisOrigen +
                                   " Grupo: " + grupo +
                                   " Sección: " + seccion);
            }
        } catch (IOException ioe) {
            controlador.mostrarMensaje("Error al leer el archivo: " + ioe.getMessage());
        } finally {
            cerrar();
        }
    }

    private String leerCampo(int longitud) throws IOException {
        byte[] bytes = new byte[longitud];
        archivo.readFully(bytes);
        return new String(bytes).trim();
    }

    public void cerrar() {
        try {
            if (archivo != null) {
                archivo.close();
            }
        } catch (IOException e) {
            controlador.mostrarMensaje("Error al cerrar el archivo: " + e.getMessage());
        }
    }
}
