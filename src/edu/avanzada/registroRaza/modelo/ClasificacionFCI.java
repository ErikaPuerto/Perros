/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.registroRaza.modelo;

/**
 *
 * @author anaro
 */
public class ClasificacionFCI extends Raza  {
    private String grupo;
    private String seccion;
    
    
    public ClasificacionFCI(String grupo,String seccion,String nombre, int codigo, String paisOrigen, String apariencia, String pelo, String Color, String espalda, String lomo, String cola, String pecho) {
        super(nombre, codigo, paisOrigen, apariencia, pelo, Color, espalda, lomo, cola);
        this.grupo = grupo;
        this.seccion = seccion;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getSeccion() {
        return seccion;
    }
    
    
}
