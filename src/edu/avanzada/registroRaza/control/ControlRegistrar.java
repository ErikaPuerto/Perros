/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.registroRaza.control;

import edu.avanzada.registroRaza.vista.VistaRegistrar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author anaro
 */
public class ControlRegistrar implements ActionListener {

    private final ControlPrincipal controlPrin;
    private VistaRegistrar vistaRegistrar;

    public ControlRegistrar(ControlPrincipal controlPrin) {
        this.controlPrin = controlPrin;

    }

    public void CrearVistaRegistrar() {
        vistaRegistrar = new VistaRegistrar();
        vistaRegistrar.registrar.addActionListener(this);
        vistaRegistrar.limpiar.addActionListener(this);
        vistaRegistrar.volver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Volver".equals(e.getActionCommand())) {
            vistaRegistrar.dispose();
            controlPrin.CrearVistaStart();
        }
    }

}
