/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.perros.controlador;

import edu.avanzada.perros.modelo.PerroVO;

public class Launcher {

    public static void main(String[] args) {
        CargarPerro miPrincipal = new CargarPerro();
        // Crea una instancia de Gestor
        Gestor gestor = new Gestor();

        // Crea una nueva instancia de PerroVO
        PerroVO miPerro = new PerroVO();
        miPerro.setId("1"); // Establece un ID único
        miPerro.setNombre("Rex");
        miPerro.setPaisOrigen("Alemania");
        miPerro.setGrupo("Pastor");
        miPerro.setSeccion("Trabajo");
        miPerro.setApariencia("Fuerte");
        miPerro.setPelo("Corto");
        miPerro.setColor("Negro y fuego");
        miPerro.setEspalda("Recta");
        miPerro.setLomo("Cae levemente hacia la espalda detrás de la punta de las escápulas (ésta es la parte más baja) desde donde la espina sube hasta el lomo (punto más alto que la cruz) curvándose nuevamente más repentinamente hacia la cola, formando un leve arco una característica distintiva de la raza");
        miPerro.setCola("Larga");
        miPerro.setPecho("Amplio");

        // Llama al método registrarPerro para insertar el perrito
        gestor.registrarPerro(miPerro);
    }
}

