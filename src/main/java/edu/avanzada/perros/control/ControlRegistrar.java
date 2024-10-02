/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.perros.control;

import edu.avanzada.perros.vista.VistaRegistrar1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author anaro
 */
public class ControlRegistrar implements ActionListener {

    private final ControlPrincipal controlPrin;
    private VistaRegistrar1 vistaRegistrar;

    public ControlRegistrar(ControlPrincipal controlPrin) {
        this.controlPrin = controlPrin;

    }

    public void CrearVistaRegistrar() {
        vistaRegistrar = new VistaRegistrar1();
        vistaRegistrar.registrar.addActionListener(this);
        vistaRegistrar.limpiar.addActionListener(this);
        vistaRegistrar.volver.addActionListener(this);
    }

    
    
    public void limpiarCajas(){
        vistaRegistrar.Limpiar();
    }
            
    @Override
    public void actionPerformed(ActionEvent e
    ) {
        if ("Volver".equals(e.getActionCommand())) {
            vistaRegistrar.dispose();
            controlPrin.CrearVistaStart();
        }
        if ("Limpiar".equals(e.getActionCommand())) {
            limpiarCajas();
        }
        if ("Registrar".equals(e.getActionCommand())) {
            
        }
    }
}

