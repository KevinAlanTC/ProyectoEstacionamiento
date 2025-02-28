package com.estacionamiento;

import javax.swing.*;

public class GestionLugaresPension 
{
    private boolean[] revisionLugaresPension;
    private char[] boletoLugaresPension;
    private VentanasPension ventanasPension;

    public GestionLugaresPension() 
    {
        inicializarLugares();
    }

    public void setVentanasPension(VentanasPension ventanasPension) 
    {
        this.ventanasPension = ventanasPension;
    }

    public void inicializarLugares() 
    {
        revisionLugaresPension = new boolean[8]; // Para coches
        boletoLugaresPension = new char[8];

        char[] boletoLugaresPensionCoches = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        // Asignar los boletos a los lugares correspondientes
        for (int i = 0; i < 8; i++) 
        {
            boletoLugaresPension[i] = boletoLugaresPensionCoches[i];
        }

        // Asignar false a los lugares para indicar que están libres
        for (int i = 0; i < 8; i++) 
        {
            revisionLugaresPension[i] = false;
        }
    }

    public void comprobarDisponibilidadLugar(int lugar, JPanel pantallaAnterior) 
    {
	if (revisionLugaresPension[lugar]) 
        {
            JOptionPane.showMessageDialog(null, "Este lugar está ocupado. Por favor elije otro.");
        } 
        else 
        {
            int respuesta = JOptionPane.showConfirmDialog(null, "Lugar libre. ¡Enhorabuena!", "¿Confirmas que deseas este lugar?", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) 
            {
                System.out.println("Has seleccionado Sí.");
                revisionLugaresPension[lugar] = true;
                ventanasPension.mostrarConfirmacionReserva(pantallaAnterior, lugar);
            } 
            else 
            {
                System.out.println("Has seleccionado No. Elige otro lugar disponible.");
            }
        }
    }
}