package com.estacionamiento;

import javax.swing.*;
import java.awt.*;

public class VentanasPension 
{
    private GestionLugaresPension claseGLP;
    private JPanel escogerLugarPension;

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

        JLabel lugarLabel = new JLabel("Número de Lugar: " + (lugar + 1));
        lugarLabel.setBounds(10, 130, 200, 50);
        confirmacionReserva.add(lugarLabel);

        // Botón de confirmación
        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.setBounds(10, 190, 100, 50);
        confirmacionReserva.add(botonConfirmar);

        VentanaPrincipal.mostrarPantallaActual(confirmacionReserva);
    }
}
