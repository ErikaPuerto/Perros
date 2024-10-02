
package edu.avanzada.perros.control;

import edu.avanzada.perros.vista.VistaStart1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author anaro
 */
public final class ControlPrincipal implements ActionListener {

    private final ControlRegistrar controlR;
    private final ControlConsultar controlC;
    private VistaStart1 vistaStart;

    public ControlPrincipal() {
        controlR = new ControlRegistrar(this);
        controlC = new ControlConsultar(this);
        CrearVistaStart();
    }

    public void CrearVistaStart() {
        vistaStart = new VistaStart1();
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
