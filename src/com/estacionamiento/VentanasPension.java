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

    public void mostrarSeleccionDuracion(int lugarSeleccionado, JPanel pantallaAnterior) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        JPanel pantallaDuracion = new JPanel();
        pantallaDuracion.setLayout(null);

        JLabel instruccion = new JLabel("Selecciona la duración de tu pensión:");
        instruccion.setBounds(10, 10, 300, 30);
        pantallaDuracion.add(instruccion);

        String[] opcionesDuracion = {"1 día", "1 semana", "1 mes"};
        JComboBox<String> comboDuracion = new JComboBox<>(opcionesDuracion);
        comboDuracion.setBounds(10, 50, 200, 30);
        pantallaDuracion.add(comboDuracion);

        JButton botonContinuar = new JButton("Continuar");
        botonContinuar.setBounds(10, 100, 100, 30);
        botonContinuar.addActionListener(e -> 
        {
            String duracionSeleccionada = (String) comboDuracion.getSelectedItem();
            claseGLP.registrarPension(lugarSeleccionado, duracionSeleccionada);
            mostrarPantallaPagoPension(pantallaDuracion, lugarSeleccionado, duracionSeleccionada);
        });
        pantallaDuracion.add(botonContinuar);

        VentanaPrincipal.mostrarPantallaActual(pantallaDuracion);
    }

    public void mostrarPantallaPagoPension(JPanel pantallaAnterior, int lugar, String duracionSeleccionada) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        JPanel pantallaPago = new JPanel();
        pantallaPago.setLayout(null);

        JLabel mensajePago = new JLabel("Realiza el pago para completar tu reserva:");
        mensajePago.setBounds(10, 10, 400, 30);
        pantallaPago.add(mensajePago);

        double costo = claseGLP.calcularCostoPension(duracionSeleccionada);

        JLabel detalleCosto = new JLabel("Costo de la pensión (" + duracionSeleccionada + "): " + costo + " unidades monetarias");
        detalleCosto.setBounds(10, 50, 400, 30);
        pantallaPago.add(detalleCosto);

        // Opciones de pago (simplificado)
        JButton botonPagar = new JButton("Pagar");
        botonPagar.setBounds(10, 100, 100, 30);
        botonPagar.addActionListener(e -> 
        {
            mostrarConfirmacionReserva(pantallaPago, lugar, duracionSeleccionada, costo);
        });
        pantallaPago.add(botonPagar);

        VentanaPrincipal.mostrarPantallaActual(pantallaPago);
    }

    public void mostrarConfirmacionReserva(JPanel pantallaAnterior, int lugar, String duracion, double costo) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        JPanel confirmacionReserva = new JPanel();
        confirmacionReserva.setLayout(null);

        JLabel mensajeConfirmacion = new JLabel("Reserva confirmada. ¡Gracias por tu pago!");
        mensajeConfirmacion.setBounds(10, 10, 400, 30);
        confirmacionReserva.add(mensajeConfirmacion);

        JLabel lugarLabel = new JLabel("Lugar asignado: " + (lugar + 1));
        lugarLabel.setBounds(10, 50, 200, 30);
        confirmacionReserva.add(lugarLabel);

        JLabel duracionLabel = new JLabel("Duración de la pensión: " + duracion);
        duracionLabel.setBounds(10, 90, 300, 30);
        confirmacionReserva.add(duracionLabel);

        JLabel costoLabel = new JLabel("Costo total: " + costo + " unidades monetarias");
        costoLabel.setBounds(10, 130, 300, 30);
        confirmacionReserva.add(costoLabel);

        JButton botonFinalizar = new JButton("Finalizar");
        botonFinalizar.setBounds(10, 170, 100, 30);
        botonFinalizar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(confirmacionReserva));
        confirmacionReserva.add(botonFinalizar);

        VentanaPrincipal.mostrarPantallaActual(confirmacionReserva);
    }
    
    public void mostrarSalidaPension(JPanel pantallaAnterior) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        panelSalidaPension = new JPanel();
        panelSalidaPension.setLayout(null);

        JLabel instruccion = new JLabel("Ingrese su número de boleto para retirar su vehículo:");
        instruccion.setBounds(10, 10, 400, 30);
        panelSalidaPension.add(instruccion);

        JTextField campoBoleto = new JTextField();
        campoBoleto.setBounds(10, 50, 200, 30);
        panelSalidaPension.add(campoBoleto);

        JButton botonVerificar = new JButton("Verificar Boleto");
        botonVerificar.setBounds(220, 50, 150, 30);
        botonVerificar.addActionListener(e -> {
            int numeroBoleto = Integer.parseInt(campoBoleto.getText());
            if (claseGLP.verificarBoleto(numeroBoleto)) {
                mostrarConfirmacionSalida(panelSalidaPension);
            } else {
                JOptionPane.showMessageDialog(null, "Boleto no válido o pensión aún vigente.");
            }
        });
        panelSalidaPension.add(botonVerificar);

        VentanaPrincipal.mostrarPantallaActual(panelSalidaPension);
    }

    public void mostrarConfirmacionSalida(JPanel pantallaAnterior) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        JPanel confirmacionSalida = new JPanel();
        confirmacionSalida.setLayout(null);

        JLabel mensajeSalida = new JLabel("Retiro confirmado. Gracias por usar nuestros servicios.");
        mensajeSalida.setBounds(10, 10, 400, 30);
        confirmacionSalida.add(mensajeSalida);

        JButton botonFinalizar = new JButton("Finalizar");
        botonFinalizar.setBounds(10, 50, 100, 30);
        botonFinalizar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(confirmacionSalida));
        confirmacionSalida.add(botonFinalizar);

        VentanaPrincipal.mostrarPantallaActual(confirmacionSalida);
    }

    
}
