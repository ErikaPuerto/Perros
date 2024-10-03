package edu.avanzada.perros.modelo;

import java.io.Serializable;

public class PerroVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String paisOrigen;
    private String grupo;
    private String seccion;

    private transient String apariencia;
    private transient String pelo;
    private transient String color;
    private transient String espalda;
    private transient String lomo;
    private transient String cola;
    private transient String pecho;

    public PerroVO() { }

    public PerroVO(String id, String nombre, String paisOrigen, String grupo, String seccion, String apariencia,
                   String pelo, String color, String espalda, String lomo, String cola, String pecho) {
        
        this.id = id;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.grupo = grupo;
        this.seccion = seccion;
        this.apariencia = apariencia;
        this.pelo = pelo;
        this.color = color;
        this.espalda = espalda;
        this.lomo = lomo;
        this.cola = cola;
        this.pecho = pecho;
    }

    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    
    public String getSeccion() {
        return seccion;
    }
    
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    public String getApariencia() {
        return apariencia;
    }

    public void setApariencia(String apariencia) {
        this.apariencia = apariencia;
    }

    public String getPelo() {
        return pelo;
    }

    public void setPelo(String pelo) {
        this.pelo = pelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEspalda() {
        return espalda;
    }

    public void setEspalda(String espalda) {
        this.espalda = espalda;
    }

    public String getLomo() {
        return lomo;
    }

    public void setLomo(String lomo) {
        this.lomo = lomo;
    }

    public String getCola() {
        return cola;
    }

    public void setCola(String cola) {
        this.cola = cola;
    }

    public String getPecho() {
        return pecho;
    }

    public void setPecho(String pecho) {
        this.pecho = pecho;
    }
}
