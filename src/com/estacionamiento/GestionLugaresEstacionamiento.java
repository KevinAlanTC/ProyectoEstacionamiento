package com.estacionamiento;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GestionLugaresEstacionamiento 
{
	
    private boolean[][] revisionLugaresEstacionamiento;
    private char[][] boletoLugaresEstacionamiento;
    private Map<Integer, LocalDateTime> tiempoEntrada; // Para registrar la hora de entrada

    private VentanasEstacionamiento claseVE;
    
    public GestionLugaresEstacionamiento() 
    {
        inicializarLugaresEstacionamiento();
        tiempoEntrada = new HashMap<>();
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
                int uniqueKey = generarLlaveUnica(tipoVehiculo, lugar);
                tiempoEntrada.put(uniqueKey, LocalDateTime.now()); // Registrar la hora de entrada
                claseVE.mostrarConfirmacionReserva(pantallaActual, tipoVehiculo, lugar);
            } 
            else 
            {
            	JOptionPane.showMessageDialog(null, "Has seleccionado No. Elige otro lugar disponible.");
            }
        }
    }
    
    public double calcularCosto(int tipoVehiculo, int lugar) 
    {
        int uniqueKey = generarLlaveUnica(tipoVehiculo, lugar);
        LocalDateTime entrada = tiempoEntrada.get(uniqueKey);
        LocalDateTime ahora = LocalDateTime.now();

        // Calcula la diferencia en horas
        long horas = java.time.Duration.between(entrada, ahora).toHours();

        // Supongamos que el costo es de 10 unidades monetarias por hora
        return horas * 10;
    }

    public int generarLlaveUnica(int tipoVehiculo, int lugar) 
    {
        return tipoVehiculo * 1000 + lugar;
    }
    
    public LocalDateTime getTiempoEntrada(int uniqueKey) 
    {
        return tiempoEntrada.get(uniqueKey);
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
                double costoTotal = calcularCosto(tipoVehiculo, lugar);
                claseVE.mostrarPantallaPago(pantallaAnterior, tipoVehiculo, lugar, costoTotal); // Muestra la pantalla de pago
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
