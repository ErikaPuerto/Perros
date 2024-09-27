/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.registroRaza.control;

import edu.avanzada.registroRaza.vista.VistaStart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.AncestorListener;

/**
 *
 * @author anaro
 */
public final class ControlPrincipal implements ActionListener{
    
    private final ControlRegistrar controlR;
    private final ControlConsultar controlC;
    private VistaStart vistaStart;
    
    public ControlPrincipal(){ 
        controlR = new ControlRegistrar(this);
        controlC = new ControlConsultar(this);
        CrearVistaStart();
    }
    
    public void CrearVistaStart(){
        vistaStart = new VistaStart();
        vistaStart.salir.addActionListener(this);
        vistaStart.registrar.addActionListener(this);
        vistaStart.consultar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Registrar".equals(e.getActionCommand())) {
            vistaStart.dispose();
            controlR.CrearVistaRegistrar();
        }
        if ("Consultar".equals(e.getActionCommand())) {
            vistaStart.dispose();
            controlC.CrearVistaConsultar();
        }
        if ("Salir".equals(e.getActionCommand())) {
            System.exit(0);
        }
    }
    
    
    
}
