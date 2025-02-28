package com.estacionamiento;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GestionLugaresPension 
{
    private boolean[] revisionLugaresPension;
    private char[] boletoLugaresPension;
    private VentanasPension ventanasPension;
    
    private Map<Integer, Integer> duracionDias; // Para registrar la duración en días

    public GestionLugaresPension() 
    {
        inicializarLugares();
    }

    public void setVentanasPension(VentanasPension ventanasPension) 
    {
        this.ventanasPension = ventanasPension;
        duracionDias = new HashMap<>();

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
            	JOptionPane.showMessageDialog(null, "Has seleccionado Sí.");
                revisionLugaresPension[lugar] = true;
                
                // Mostrar el mensaje con un cuadro de entrada (input dialog)
                String duracion = JOptionPane.showInputDialog(null, 
                        "¿De cuántos días quisieras que fuera la pensión?", 
                        "Duración de la pensión", 
                        JOptionPane.QUESTION_MESSAGE);

                if (duracion != null && !duracion.isEmpty()) {
                    try {
                        int duracionEnDias = Integer.parseInt(duracion);
                        duracionDias.put(lugar, duracionEnDias); // Guardar los días en el mapa
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor ingresa un número válido para los días.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ingresó una duración válida.");
                }

                
                ventanasPension.mostrarConfirmacionReserva(pantallaAnterior, lugar);
            } 
            else 
            {
            	JOptionPane.showMessageDialog(null, "Has seleccionado No. Elige otro lugar disponible.");
            }
        }
    }
    
    public double calcularCosto(int lugar) 
    {
        int dias = duracionDias.get(lugar);
        // Supongamos que el costo es de 100 unidades por día
        return dias * 100;
    }

    public int getDuracionDias(int lugar) 
    {
        // Devuelve la duración almacenada en el mapa `duracionDias`
        return duracionDias.getOrDefault(lugar, 0); // Retorna 0 si no hay valor para ese lugar
    }

    public void verificarBoleto(JPanel pantallaAnterior, String numeroBoletoStr) 
    {
        try 
        {
            int numeroBoleto = Integer.parseInt(numeroBoletoStr); // Convertir el boleto a número
            int lugar = numeroBoleto % 1000; // Extraer el lugar del boleto

            if (revisionLugaresPension[lugar]) 
            {
                JOptionPane.showMessageDialog(null, "Boleto verificado. Puede salir de la pensión.");
                revisionLugaresPension[lugar] = false; // Marcar el lugar como libre

                // Obtener la duración y calcular el costo
                int duracion = getDuracionDias(lugar);
                double costoTotal = calcularCosto(lugar);

                // Pasar el flujo a VentanasPension para mostrar la pantalla de pago
                ventanasPension.mostrarPantallaPago(pantallaAnterior, lugar, costoTotal);
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
