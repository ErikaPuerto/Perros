/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.perros.utils;

import edu.avanzada.perros.modelo.PerroVO;
import java.io.*;
import java.util.List;

public class ArchivoAleatorioPerros {
    private String id;
    private String nombre;
    private String paisOrigen;
    private String grupo;
    private String seccion;
    private String apariencia;
    private String pelo;
    private String color;
    private String espalda;
    private String lomo;
    private String cola;
    private String pecho;
    private long tamreg;
    private long canreg;
    private File fl;
    private RandomAccessFile archivo;

    public ArchivoAleatorioPerros() {
        this.id = "";
        this.nombre = "";
        this.paisOrigen = "";
        this.grupo = "";
        this.seccion = "";
        this.apariencia = "";
        this.pelo = "";
        this.color = "";
        this.espalda = "";
        this.lomo = "";
        this.cola = "";
        this.pecho = "";
        this.tamreg = 300; // Tamaño aproximado de cada registro en bytes
        this.canreg = 0;
        try {
            String fileName = "perros.dat";
            fl = new File(fileName);
            archivo = new RandomAccessFile(fl, "rw");
            if (archivo.length() > 0) {
                canreg = archivo.length() / tamreg;
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error al crear el archivo: " + fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Error al leer el archivo: " + ioe.getMessage());
        }
    }

    public void escribirPerros(List<PerroVO> perros) {
        try {
            for (PerroVO perro : perros) {
                // Buscar si el perro ya existe, si existe actualiza, sino escribe nuevo
                actualizarOEscribirPerro(perro);
            }
            System.out.println("Archivo aleatorio actualizado: " + fl.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    private void actualizarOEscribirPerro(PerroVO perro) throws IOException {
        archivo.seek(0); // Comenzamos desde el inicio del archivo
        boolean perroExistente = false;

        for (int i = 0; i < canreg; i++) {
            archivo.seek(i * tamreg); // Ubicar el puntero al inicio del registro
            String idLeido = archivo.readLine().substring(0, 10).trim(); // Leer ID

            if (idLeido.equals(perro.getId())) {
                // Si encontramos el perro, lo sobrescribimos
                archivo.seek(i * tamreg); // Volver al inicio del registro a sobrescribir
                escribirPerro(perro); // Sobreescribimos los datos del perro
                perroExistente = true;
                break;
            }
        }

        if (!perroExistente) {
            // Si no se encontró el perro en el archivo, agregamos un nuevo registro
            archivo.seek(archivo.length()); // Posicionamos el puntero al final
            escribirPerro(perro); // Escribimos el nuevo perro
            canreg++; // Aumentamos el conteo de registros
        }
    }

    private void escribirPerro(PerroVO perro) throws IOException {
        archivo.writeBytes(ajustarLongitud(perro.getId(), 10));         // ID de longitud fija
        archivo.writeBytes(ajustarLongitud(perro.getNombre(), 25));     // Nombre de longitud fija
        archivo.writeBytes(ajustarLongitud(perro.getPaisOrigen(), 25)); // País de origen
        archivo.writeBytes(ajustarLongitud(perro.getGrupo(), 25));      // Grupo
        archivo.writeBytes(ajustarLongitud(perro.getSeccion(), 25));    // Sección
        archivo.writeBytes(ajustarLongitud(perro.getApariencia(), 25)); // Apariencia
        archivo.writeBytes(ajustarLongitud(perro.getPelo(), 25));       // Pelo
        archivo.writeBytes(ajustarLongitud(perro.getColor(), 25));      // Color
        archivo.writeBytes(ajustarLongitud(perro.getEspalda(), 25));    // Espalda
        archivo.writeBytes(ajustarLongitud(perro.getLomo(), 25));       // Lomo
        archivo.writeBytes(ajustarLongitud(perro.getCola(), 25));       // Cola
        archivo.writeBytes(ajustarLongitud(perro.getPecho(), 25));      // Pecho
        archivo.writeBytes("\n");
    }

    private String ajustarLongitud(String str, int longitud) {
        if (str == null) {
            str = "";
        }
        if (str.length() > longitud) {
            return str.substring(0, longitud);
        } else {
            return String.format("%-" + longitud + "s", str);
        }
    }

    public void leerTodo() {
        System.out.println("\nMostrando todos los Registros");
        try {
            archivo.seek(0);
            canreg = archivo.length() / tamreg;
            for (int r = 0; r < canreg; r++) {
                archivo.seek(r * tamreg); // Ubicar puntero al inicio del registro
                id = archivo.readLine().substring(0, 10).trim();
                nombre = archivo.readLine().substring(10, 35).trim();
                paisOrigen = archivo.readLine().substring(35, 60).trim();
                grupo = archivo.readLine().substring(60, 85).trim();
                seccion = archivo.readLine().substring(85, 110).trim();
                apariencia = archivo.readLine().substring(110, 135).trim();
                pelo = archivo.readLine().substring(135, 160).trim();
                color = archivo.readLine().substring(160, 185).trim();
                espalda = archivo.readLine().substring(185, 210).trim();
                lomo = archivo.readLine().substring(210, 235).trim();
                cola = archivo.readLine().substring(235, 260).trim();
                pecho = archivo.readLine().substring(260, 285).trim();

                System.out.println("Registro No: " + (r + 1) + 
                                   " ID: " + id + 
                                   " Nombre: " + nombre + 
                                   " País de Origen: " + paisOrigen);
            }
        } catch (IOException ioe) {
            System.out.println("Error al leer el archivo: " + ioe.getMessage());
        }
    }

    public void cerrar() {
        try {
            if (archivo != null) {
                archivo.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo: " + e.getMessage());
        }
    }
}
