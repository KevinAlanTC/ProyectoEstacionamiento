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

    
    public class Pension 
    {
        int lugar;
        String duracion;
        LocalDateTime tiempoInicio;
        LocalDateTime tiempoFin;
        double costo;

        // Constructor y métodos necesarios
    }
    
    private Map<Integer, Pension> pensionesActivas = new HashMap<>();

    public void registrarPension(int lugar, String duracionSeleccionada) {
        LocalDateTime tiempoInicio = TiempoSimulado.ahora();
        LocalDateTime tiempoFin = calcularTiempoFin(tiempoInicio, duracionSeleccionada);
        double costo = calcularCostoPension(duracionSeleccionada);

        Pension nuevaPension = new Pension(lugar, duracionSeleccionada, tiempoInicio, tiempoFin, costo);
        pensionesActivas.put(lugar, nuevaPension);
        revisionLugaresPension[lugar] = true;
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
                ventanasPension.mostrarConfirmacionReserva(pantallaAnterior, lugar);
            } 
            else 
            {
            	JOptionPane.showMessageDialog(null, "Has seleccionado No. Elige otro lugar disponible.");
            }
        }
    }

    private LocalDateTime calcularTiempoFin(LocalDateTime inicio, String duracion) {
        switch (duracion) {
            case "1 día":
                return inicio.plusDays(1);
            case "1 semana":
                return inicio.plusWeeks(1);
            case "1 mes":
                return inicio.plusMonths(1);
            default:
                return inicio;
        }
    }

    public double calcularCostoPension(String duracion) 
    {
        switch (duracion) 
        {
            case "1 día":
                return 100.0;
            case "1 semana":
                return 600.0;
            case "1 mes":
                return 2000.0;
            default:
                return 0.0;
        }
    }

    public boolean verificarBoleto(int numeroBoleto) 
    {
        if (pensionesActivas.containsKey(numeroBoleto)) 
        {
            Pension pension = pensionesActivas.get(numeroBoleto);
            LocalDateTime tiempoActual = TiempoSimulado.ahora();
            if (!tiempoActual.isBefore(pension.tiempoFin)) 
            {
                // Pensión finalizada, permitir salida
                revisionLugaresPension[pension.lugar] = false;
                pensionesActivas.remove(numeroBoleto);
                return true;
            } 
            else 
            {
                // Pensión aún vigente
                return false;
            }
        } 
        else 
        {
            // Boleto no válido
            return false;
        }
    }

}
