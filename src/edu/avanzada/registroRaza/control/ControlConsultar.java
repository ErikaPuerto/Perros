
package edu.avanzada.registroRaza.control;

import edu.avanzada.registroRaza.vista.VistaConsultar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author anaro
 */
public class ControlConsultar implements ActionListener {
    
    private final ControlPrincipal controlPrin;
    private VistaConsultar vistaConsultar;
    
    public ControlConsultar(ControlPrincipal controlPrin) {
        this.controlPrin = controlPrin;
    }
    
    public void CrearVistaConsultar() {
        vistaConsultar = new VistaConsultar();
        vistaConsultar.volver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Volver".equals(e.getActionCommand())) {
            vistaConsultar.dispose();
            controlPrin.CrearVistaStart();
        }
    }
    
}
