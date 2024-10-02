
package edu.avanzada.perros.control;

import edu.avanzada.perros.vista.VistaConsultar1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author anaro
 */
public class ControlConsultar implements ActionListener {
    
    private final ControlPrincipal controlPrin;
    private VistaConsultar1 vistaConsultar;
    
    public ControlConsultar(ControlPrincipal controlPrin) {
        this.controlPrin = controlPrin;
    }
    
    public void CrearVistaConsultar() {
        vistaConsultar = new VistaConsultar1();
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
