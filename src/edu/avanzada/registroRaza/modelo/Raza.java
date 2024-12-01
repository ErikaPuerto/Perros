/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.registroRaza.modelo;

/**
 *
 * @author anaro
 */
public class Raza {
    private String nombre;
    private int codigo;
    private String paisOrigen;
    private String apariencia;
    private String pelo;
    private String Color;
    private String espalda;
    String lomo;
    String cola;
    private String pecho;

    public Raza(String nombre, int codigo, String paisOrigen, String apariencia, String pelo, String Color, String espalda, String lomo, String cola) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.paisOrigen = paisOrigen;
        this.apariencia = apariencia;
        this.pelo = pelo;
        this.Color = Color;
        this.espalda = espalda;
        this.lomo = lomo;
        this.cola = cola;
        this.pecho = pecho;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public String getApariencia() {
        return apariencia;
    }

    public String getPelo() {
        return pelo;
    }

    public String getColor() {
        return Color;
    }

    public String getEspalda() {
        return espalda;
    }

    public String getLomo() {
        return lomo;
    }

    public String getCola() {
        return cola;
    }

    public String getPecho() {
        return pecho;
    }
    
    
    

}
