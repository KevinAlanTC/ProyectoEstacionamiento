package com.estacionamiento;

import javax.swing.*;
import java.awt.*;

public class VentanasEstacionamiento {
    private GestionLugaresEstacionamiento claseGLE;
    
    private JPanel escogerLugarEstacionamiento, detallesEstacionamiento;

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
    
    public void mostrarConfirmacionReserva(JPanel pantallaAnterior, int tipoVehiculo, int lugar) {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel principal de esta función
        JPanel confirmacionReserva = new JPanel();
        confirmacionReserva.setLayout(null);

        // Mensaje de confirmación
        JLabel mensajeConfirmacion = new JLabel("Reserva confirmada!");
        mensajeConfirmacion.setBounds(10, 10, 200, 50);
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
        confirmacionReserva.add(botonConfirmar);

        VentanaPrincipal.mostrarPantallaActual(confirmacionReserva);
    }
}
