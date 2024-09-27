package edu.avanzada.registroRaza.modelo;

public class PerroVO {
    
    private String nombre;
    private String paisOrigen;
    private String clasificacion;
    private String apariencia;
    private String pelo;
    private String color;
    private String espalda;
    private String lomo;
    private String cola;
    private String pecho;

    public PerroVO() {   }
    public PerroVO(String nombre, String paisOrigen, String clasificacion, String apariencia,
            String pelo, String color, String esplada, String lomo, String cola, String pecho) {
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.clasificacion = clasificacion;
        this.apariencia = apariencia;
        this.pelo = pelo;
        this.color = color;
        this.espalda = espalda;
        this.lomo = lomo;
        this.cola = cola;
        this.pecho = pecho;
    }

    public String getNombre() {        return nombre;    }
    public void setNombre(String nombre) {        this.nombre = nombre;    }
    
    public String getPaisOrigen() {       return paisOrigen;    }
    public void setPaisOrigen(String paisOrigen) {        this.paisOrigen = paisOrigen;    }
    
    public String getClasificacion() {        return clasificacion;    }
    public void setClasificacion(String clasificacion) {        this.clasificacion = clasificacion;    } 
    
    public String getApariencia() {        return apariencia;    }
    public void setApariencia(String apariencia) {        this.apariencia = apariencia;    } 
    
    public String getPelo() {        return pelo;    }
    public void setPelo(String pelo) {        this.pelo = pelo;    } 
    
    public String getColor() {        return color;    }
    public void setColor(String color) {        this.color = color;    } 
    
    public String getEspalda() {        return espalda;    }
    public void setEspalda(String espalda) {        this.espalda = espalda;    } 
    
    public String getLomo() {        return lomo;    }
    public void setLomo(String lomo) {        this.lomo = lomo;    } 
    
    public String getCola() {        return cola;    }
    public void setCola(String cola) {        this.cola = cola;    } 
    
    public String getPecho() {        return pecho;    }
    public void setPecho(String pecho) {        this.pecho = pecho;    } 
    
}

