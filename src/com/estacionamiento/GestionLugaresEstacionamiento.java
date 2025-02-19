package com.estacionamiento;

import javax.swing.*;

public class GestionLugaresEstacionamiento 
{
	
    private boolean[][] revisionLugaresEstacionamiento;
    private char[][] boletoLugaresEstacionamiento;

    private VentanasEstacionamiento claseVE;
    
    public GestionLugaresEstacionamiento() 
    {
        inicializarLugaresEstacionamiento();
    }
    
    public void setVentanasEstacionamiento(VentanasEstacionamiento ventanasEstacionamiento) 
    {
        this.claseVE = ventanasEstacionamiento;
    }

    public void inicializarLugaresEstacionamiento() 
    {
        revisionLugaresEstacionamiento = new boolean[2][8]; // [0] para coches, [1] para motos
        boletoLugaresEstacionamiento = new char[2][8];

        char[] boletoLugaresEstacionamientoCoches = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        char[] boletoLugaresEstacionamientoMotos = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        // Asignar los boletos a los lugares correspondientes
        for (int i = 0; i < 8; i++) 
        {
            boletoLugaresEstacionamiento[0][i] = boletoLugaresEstacionamientoCoches[i];
            boletoLugaresEstacionamiento[1][i] = boletoLugaresEstacionamientoMotos[i];
        }

        // Asignar false a los lugares para indicar que están libres
        for (int i = 0; i < 8; i++) 
        {
            revisionLugaresEstacionamiento[0][i] = false;
            revisionLugaresEstacionamiento[1][i] = false;
        }
    }

    public void comprobarDisponibilidadLugar(int tipoVehiculo, int lugar, JPanel pantallaActual) 
    {
        if (revisionLugaresEstacionamiento[tipoVehiculo][lugar]) 
        {
            JOptionPane.showMessageDialog(null, "Este lugar está ocupado. Por favor elije otro.");
        } 
        else 
        {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Confirmas que deseas este lugar?", "Lugar libre. ¡Enhorabuena!", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) 
            {
            	JOptionPane.showMessageDialog(null, "Has seleccionado Sí.");
                revisionLugaresEstacionamiento[tipoVehiculo][lugar] = true;
                claseVE.mostrarConfirmacionReserva(pantallaActual, tipoVehiculo, lugar);
            } 
            else 
            {
            	JOptionPane.showMessageDialog(null, "Has seleccionado No. Elige otro lugar disponible.");
            }
        }
    }
    
    public void inicializarLugaresPension() // Pendiente a revisar si en pensión hay motos
    {
        revisionLugaresEstacionamiento = new boolean[2][8]; // [0] para coches, [1] para motos
        boletoLugaresEstacionamiento = new char[2][8];

        char[] boletoLugaresEstacionamientoCoches = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        char[] boletoLugaresEstacionamientoMotos = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        // Asignar los boletos a los lugares correspondientes
        for (int i = 0; i < 8; i++) 
        {
            boletoLugaresEstacionamiento[0][i] = boletoLugaresEstacionamientoCoches[i];
            boletoLugaresEstacionamiento[1][i] = boletoLugaresEstacionamientoMotos[i];
        }

        // Asignar false a los lugares para indicar que están libres
        for (int i = 0; i < 8; i++) 
        {
            revisionLugaresEstacionamiento[0][i] = false;
            revisionLugaresEstacionamiento[1][i] = false;
        }
    }
}
