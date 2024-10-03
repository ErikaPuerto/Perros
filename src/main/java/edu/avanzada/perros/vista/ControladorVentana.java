/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.perros.vista;

/**
 *
 * @author nedic
 */
import javax.swing.JOptionPane;

public class ControladorVentana {

    // Método para mostrar un mensaje de información
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para mostrar un mensaje de error
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Método para mostrar un mensaje de advertencia
    public void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    // Método para mostrar opciones (Ej. Confirmar acción)
    public boolean mostrarConfirmacion(String mensaje) {
        int resultado = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
        return resultado == JOptionPane.YES_OPTION;
    }
}

