package com.estacionamiento;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class VentanasEstacionamiento 
{
    private GestionLugaresEstacionamiento claseGLE;
    
    private JPanel escogerLugarEstacionamiento, panelSalidaEstacionamiento;

    // Constructor que recibe una instancia de GestionLugaresEstacionamiento
    public VentanasEstacionamiento(GestionLugaresEstacionamiento claseGLE) 
    {
        this.claseGLE = claseGLE;
    }

    public void mostrarLugaresEstacionamiento(JPanel pantallaAnterior) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel principal de esta función
        escogerLugarEstacionamiento = new JPanel();
        escogerLugarEstacionamiento.setLayout(null);
        
        // Se instancia en una etiqueta la instrucción que debe seguir el usuario
        JLabel instruccion = new JLabel("Selecciona el lugar de tu preferencia:");
        instruccion.setBounds(10, 10, 250, 50);
        escogerLugarEstacionamiento.add(instruccion);
        
        // En el siguiente panel se manejarán únicamente los lugares de estacionamiento de los coches
        JPanel cochesLugaresEstacionamiento = new JPanel();
        cochesLugaresEstacionamiento.setLayout(new GridLayout(2, 4, 10, 10));
        
        // En el siguiente panel se manejarán únicamente los lugares de estacionamiento de las motos
        JPanel motosLugaresEstacionamiento = new JPanel();
        motosLugaresEstacionamiento.setLayout(new GridLayout(8, 1, 10, 10));
        
        // Con el siguiente arreglo de botones se detectará cuando el usuario quiera elegir un lugar de coches en el estacionamiento
        JButton[] botonescochesLugaresEstacionamiento = new JButton[8];
        
        // Con el siguiente arreglo de botones se detectará cuando el usuario quiera elegir un lugar de motos en el estacionamiento
        JButton[] botonesmotosLugaresEstacionamiento = new JButton[8];
        
        for (int i = 0; i < 8; i++) 
        {
            final int index = i; // Crear una variable final
            botonescochesLugaresEstacionamiento[i] = new JButton();
            botonescochesLugaresEstacionamiento[i].addActionListener(e -> claseGLE.comprobarDisponibilidadLugar(0, index, escogerLugarEstacionamiento));
            cochesLugaresEstacionamiento.add(botonescochesLugaresEstacionamiento[i]);

            botonesmotosLugaresEstacionamiento[i] = new JButton();
            botonesmotosLugaresEstacionamiento[i].addActionListener(e -> claseGLE.comprobarDisponibilidadLugar(1, index, escogerLugarEstacionamiento));
            motosLugaresEstacionamiento.add(botonesmotosLugaresEstacionamiento[i]);
        }

        cochesLugaresEstacionamiento.setBounds(50, 100, 430, 450);
        escogerLugarEstacionamiento.add(cochesLugaresEstacionamiento);
        motosLugaresEstacionamiento.setBounds(550, 100, 100, 450);
        escogerLugarEstacionamiento.add(motosLugaresEstacionamiento);

        VentanaPrincipal.mostrarPantallaActual(escogerLugarEstacionamiento);
    }
    
    public void mostrarConfirmacionReserva(JPanel pantallaAnterior, int tipoVehiculo, int lugar) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel principal de esta función
        JPanel confirmacionReserva = new JPanel();
        confirmacionReserva.setLayout(null);
        
        // Asignar un número de boleto único
        int numeroBoleto = (tipoVehiculo == 0 ? 1000 : 2000) + lugar;

        // Mensaje de confirmación
        JLabel mensajeConfirmacion = new JLabel("Reserva confirmada! Tu número de boleto es: " + numeroBoleto);
        mensajeConfirmacion.setBounds(10, 10, 300, 50);
        confirmacionReserva.add(mensajeConfirmacion);

        // Detalles de la reserva
        JLabel detallesReserva = new JLabel("Detalles de la reserva:");
        detallesReserva.setBounds(10, 70, 200, 50);
        confirmacionReserva.add(detallesReserva);

        JLabel tipoVehiculoLabel = new JLabel("Tipo de Vehículo: " + (tipoVehiculo == 0 ? "Coche" : "Moto"));
        tipoVehiculoLabel.setBounds(10, 130, 200, 50);
        confirmacionReserva.add(tipoVehiculoLabel);

        JLabel lugarLabel = new JLabel("Número de Lugar: " + (lugar + 1));
        lugarLabel.setBounds(10, 190, 200, 50);
        confirmacionReserva.add(lugarLabel);

        // Botón de confirmación
        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.setBounds(10, 250, 100, 50);
        botonConfirmar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(confirmacionReserva));
        confirmacionReserva.add(botonConfirmar);

        VentanaPrincipal.mostrarPantallaActual(confirmacionReserva);
    }
    
    public void mostrarSalidaEstacionamiento(JPanel pantallaAnterior)
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel principal de esta función
        panelSalidaEstacionamiento = new JPanel();
        panelSalidaEstacionamiento.setLayout(null);

        // Se instancia una etiqueta con una despedida para el usuario y se le solicita su no. de boleto
        JLabel despedida = new JLabel("Gracias por visitarnos. Por favor, ingresa tu número de boleto para salir.");
        despedida.setBounds(10, 10, 400, 50);
        panelSalidaEstacionamiento.add(despedida);

        JTextField campoBoleto = new JTextField();
        campoBoleto.setBounds(10, 70, 200, 30);
        panelSalidaEstacionamiento.add(campoBoleto);

        // Botón para verificar el número de boleto ingresado
        JButton botonVerificar = new JButton("Verificar Boleto");
        botonVerificar.setBounds(220, 70, 150, 30);
        botonVerificar.addActionListener(e -> claseGLE.verificarBoleto(panelSalidaEstacionamiento, campoBoleto.getText()));
        panelSalidaEstacionamiento.add(botonVerificar);

        VentanaPrincipal.mostrarPantallaActual(panelSalidaEstacionamiento);
    }

	
    public void mostrarPantallaPago(JPanel pantallaAnterior, int tipoVehiculo, int lugar, double costoTotal) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel principal de esta función
        JPanel pantallaPago = new JPanel();
        pantallaPago.setLayout(null);

        // Mensaje de confirmación
        JLabel mensajePago = new JLabel("Por favor, realice su pago para completar el proceso de salida.");
        mensajePago.setBounds(10, 10, 400, 50);
        pantallaPago.add(mensajePago);

        // Detalles del pago
        JLabel detallesPago = new JLabel("Detalles del Pago:");
        detallesPago.setBounds(10, 70, 200, 50);
        pantallaPago.add(detallesPago);

        // Mostrar la hora de entrada
        int uniqueKey = claseGLE.generarLlaveUnica(tipoVehiculo, lugar);
        LocalDateTime horaEntrada = claseGLE.getTiempoEntrada(uniqueKey);
        JLabel horaEntradaLabel = new JLabel("Hora de Entrada: " + horaEntrada);
        horaEntradaLabel.setBounds(10, 130, 300, 50);
        pantallaPago.add(horaEntradaLabel);
        
        // Capturar y mostrar la hora de salida
        LocalDateTime horaSalida = LocalDateTime.now();
        JLabel horaSalidaLabel = new JLabel("Hora de Salida: " + horaSalida);
        horaSalidaLabel.setBounds(10, 190, 300, 50);
        pantallaPago.add(horaSalidaLabel);
        
        JLabel costoLabel = new JLabel("Costo total: " + costoTotal + " unidades monetarias");
        costoLabel.setBounds(10, 250, 200, 50);
        pantallaPago.add(costoLabel);
        
        // Opciones de Pago (solo un ejemplo básico)
        JButton botonPagoTarjeta = new JButton("Pagar con Tarjeta");
        botonPagoTarjeta.setBounds(10, 130, 200, 50);
        botonPagoTarjeta.addActionListener(e -> mostrarPantallaConfirmacion(pantallaPago));
        pantallaPago.add(botonPagoTarjeta);

        JButton botonPagoEfectivo = new JButton("Pagar en Efectivo");
        botonPagoEfectivo.setBounds(220, 130, 200, 50);
        botonPagoEfectivo.addActionListener(e -> mostrarPantallaConfirmacion(pantallaPago));
        pantallaPago.add(botonPagoEfectivo);

        VentanaPrincipal.mostrarPantallaActual(pantallaPago);
    }

    public void mostrarPantallaConfirmacion(JPanel pantallaAnterior)
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel principal de esta función
        JPanel pantallaConfirmacion = new JPanel();
        pantallaConfirmacion.setLayout(null);

        // Mensaje de confirmación de pago
        JLabel mensajeConfirmacion = new JLabel("Pago realizado con éxito. Gracias por su visita.");
        mensajeConfirmacion.setBounds(10, 10, 300, 50);
        pantallaConfirmacion.add(mensajeConfirmacion);

        // Botón para finalizar
        JButton botonFinalizar = new JButton("Finalizar");
        botonFinalizar.setBounds(10, 70, 100, 50);
        botonFinalizar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(pantallaConfirmacion));
        pantallaConfirmacion.add(botonFinalizar);

        VentanaPrincipal.mostrarPantallaActual(pantallaConfirmacion);
    }
    
}
