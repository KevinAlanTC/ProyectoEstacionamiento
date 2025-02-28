package com.estacionamiento;

import javax.swing.*;
import java.awt.*;

public class VentanasPension 
{
    private GestionLugaresPension claseGLP;
    private JPanel escogerLugarPension, panelSalidaPension;

    public VentanasPension(GestionLugaresPension claseGLP) 
    {
        this.claseGLP = claseGLP;
    }

    public void mostrarLugaresPension(JPanel pantallaAnterior) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel principal de esta función
        escogerLugarPension = new JPanel();
        escogerLugarPension.setLayout(null);
        
        // Se instancia en una etiqueta la instrucción que debe seguir el usuario
        JLabel instruccion = new JLabel("Selecciona el lugar de tu preferencia:");
        instruccion.setBounds(10, 10, 250, 50);
        escogerLugarPension.add(instruccion);
        
        // En el siguiente panel se manejarán únicamente los lugares de pensión de los coches
        JPanel cochesLugaresPension = new JPanel();
        cochesLugaresPension.setLayout(new GridLayout(2, 4, 10, 10));
        
        // Con el siguiente arreglo de botones se detectará cuando el usuario quiera elegir un lugar de coches en la pensión
        JButton[] botonescochesLugaresPension = new JButton[8];
        for (int i = 0; i < 8; i++) 
        {
            final int index = i; // Crear una variable final
            botonescochesLugaresPension[i] = new JButton("Lugar " + (i + 1));
            botonescochesLugaresPension[i].addActionListener(e -> claseGLP.comprobarDisponibilidadLugar(index, escogerLugarPension));
            cochesLugaresPension.add(botonescochesLugaresPension[i]);
        }

        cochesLugaresPension.setBounds(50, 100, 430, 450);
        escogerLugarPension.add(cochesLugaresPension);

        VentanaPrincipal.mostrarPantallaActual(escogerLugarPension);
    }

    public void mostrarConfirmacionReserva(JPanel pantallaAnterior, int lugar) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        JPanel confirmacionReserva = new JPanel();
        confirmacionReserva.setLayout(null);

        JLabel mensajeConfirmacion = new JLabel("Reserva confirmada!");
        mensajeConfirmacion.setBounds(10, 10, 300, 50);
        confirmacionReserva.add(mensajeConfirmacion);

        JLabel detallesReserva = new JLabel("Detalles de la reserva:");
        detallesReserva.setBounds(10, 70, 200, 50);
        confirmacionReserva.add(detallesReserva);

        JLabel lugarLabel = new JLabel("Número de Lugar: " + (lugar + 1));
        lugarLabel.setBounds(10, 130, 300, 50);
        confirmacionReserva.add(lugarLabel);

        int duracion = claseGLP.getDuracionDias(lugar);
        JLabel duracionLabel = new JLabel("Duración: " + duracion + " días");
        duracionLabel.setBounds(10, 190, 300, 50);
        confirmacionReserva.add(duracionLabel);

        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.setBounds(10, 250, 150, 50);
        botonConfirmar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(confirmacionReserva));
        confirmacionReserva.add(botonConfirmar);

        VentanaPrincipal.mostrarPantallaActual(confirmacionReserva);
    }

    
    public void mostrarSalidaPension(JPanel pantallaAnterior) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        JPanel panelSalidaPension = new JPanel();
        panelSalidaPension.setLayout(null);

        JLabel despedida = new JLabel("Por favor, ingresa tu número de boleto para salir.");
        despedida.setBounds(10, 10, 400, 50);
        panelSalidaPension.add(despedida);

        JTextField campoBoleto = new JTextField();
        campoBoleto.setBounds(10, 70, 200, 30);
        panelSalidaPension.add(campoBoleto);

        JButton botonVerificar = new JButton("Verificar Boleto");
        botonVerificar.setBounds(220, 70, 150, 30);
        botonVerificar.addActionListener(e -> {
            String numeroBoletoStr = campoBoleto.getText();
            claseGLP.verificarBoleto(panelSalidaPension, numeroBoletoStr);
        });
        panelSalidaPension.add(botonVerificar);

        VentanaPrincipal.mostrarPantallaActual(panelSalidaPension);
    }

    public void mostrarPantallaPago(JPanel pantallaAnterior, int lugar, double costoTotal) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        JPanel pantallaPago = new JPanel();
        pantallaPago.setLayout(null);

        JLabel mensajePago = new JLabel("Por favor, realice su pago para completar el proceso de salida.");
        mensajePago.setBounds(10, 10, 400, 50);
        pantallaPago.add(mensajePago);

        JLabel detallesPago = new JLabel("Detalles del Pago:");
        detallesPago.setBounds(10, 70, 200, 50);
        pantallaPago.add(detallesPago);

        int duracion = claseGLP.getDuracionDias(lugar);
        JLabel duracionLabel = new JLabel("Duración: " + duracion + " días");
        duracionLabel.setBounds(10, 130, 300, 50);
        pantallaPago.add(duracionLabel);

        JLabel costoLabel = new JLabel("Costo total: " + costoTotal + " unidades monetarias");
        costoLabel.setBounds(10, 190, 300, 50);
        pantallaPago.add(costoLabel);

        JButton botonPagoTarjeta = new JButton("Pagar con Tarjeta");
        botonPagoTarjeta.setBounds(10, 250, 150, 50);
        botonPagoTarjeta.addActionListener(e -> mostrarPantallaConfirmacion(pantallaPago));
        pantallaPago.add(botonPagoTarjeta);

        JButton botonPagoEfectivo = new JButton("Pagar en Efectivo");
        botonPagoEfectivo.setBounds(170, 250, 150, 50);
        botonPagoEfectivo.addActionListener(e -> mostrarPantallaConfirmacion(pantallaPago));
        pantallaPago.add(botonPagoEfectivo);

        VentanaPrincipal.mostrarPantallaActual(pantallaPago);
    }

    public void mostrarPantallaConfirmacion(JPanel pantallaAnterior)
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        JPanel pantallaConfirmacion = new JPanel();
        pantallaConfirmacion.setLayout(null);

        JLabel mensajeConfirmacion = new JLabel("Pago realizado con éxito. Gracias por su visita.");
        mensajeConfirmacion.setBounds(10, 10, 300, 50);
        pantallaConfirmacion.add(mensajeConfirmacion);

        JButton botonFinalizar = new JButton("Finalizar");
        botonFinalizar.setBounds(10, 70, 100, 50);
        botonFinalizar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(pantallaConfirmacion));
        pantallaConfirmacion.add(botonFinalizar);

        VentanaPrincipal.mostrarPantallaActual(pantallaConfirmacion);
    }

}
