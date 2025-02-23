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
    
    // Método para verificar el número de boleto ingresado por el usuario
    public void verificarBoleto(JPanel pantallaAnterior, String numeroBoletoStr) 
    {
        try 
        {
            int numeroBoleto = Integer.parseInt(numeroBoletoStr);
            int tipoVehiculo = (numeroBoleto >= 2000) ? 1 : 0; // Determina el tipo de vehículo basado en el número de boleto
            int lugar = numeroBoleto % 1000; // Obtiene el número de lugar del boleto

            // Verifica si el lugar está ocupado y el boleto es válido
            if (revisionLugaresEstacionamiento[tipoVehiculo][lugar]) 
            {
                JOptionPane.showMessageDialog(null, "Boleto verificado. Puede salir del estacionamiento.");
                revisionLugaresEstacionamiento[tipoVehiculo][lugar] = false; // Marca el lugar como desocupado
                claseVE.mostrarPantallaPago(pantallaAnterior, tipoVehiculo, lugar); // Muestra la pantalla de pago
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Boleto no válido o lugar ya está libre.");
            }
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(null, "Número de boleto no válido.");
        }
    }
    
}
