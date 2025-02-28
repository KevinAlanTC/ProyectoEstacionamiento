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
        Font fuenteArial = new Font("Arial", Font.PLAIN, 15);
        
        // Se instancia en una etiqueta la instrucción que debe seguir el usuario
        JLabel instruccion = new JLabel("Selecciona el lugar de tu preferencia:");
        instruccion.setBounds(20, 55, 250, 50);
    	instruccion.setFont(fuenteArial);
        escogerLugarPension.add(instruccion);
        
        // En el siguiente panel se manejarán únicamente los lugares de pensión de los coches
        JPanel cochesLugaresPension = new JPanel();
        cochesLugaresPension.setLayout(new GridLayout(2, 4, 20, 21));
        cochesLugaresPension.setOpaque(false);
        
        // Con el siguiente arreglo de botones se detectará cuando el usuario quiera elegir un lugar de coches en la pensión
        JButton[] botonescochesLugaresPension = new JButton[8];
        for (int i = 0; i < 8; i++) 
        {
            final int index = i; // Crear una variable final
            botonescochesLugaresPension[i] = new JButton("Lugar " + (i + 1));
            botonescochesLugaresPension[i].addActionListener(e -> claseGLP.comprobarDisponibilidadLugar(index, escogerLugarPension));
            botonescochesLugaresPension[i].setBackground(Color.decode("#4C5C68"));
            botonescochesLugaresPension[i].setForeground(Color.decode("#FFFFFF"));
            cochesLugaresPension.add(botonescochesLugaresPension[i]);
        }

        cochesLugaresPension.setBounds(132, 104, 442, 402);
        escogerLugarPension.add(cochesLugaresPension);
        
        JButton regresar2 = new JButton("Regresar a Salida");
        regresar2.addActionListener(e -> VentanaPrincipal.mostrarPantallaEntrada(escogerLugarPension));
        regresar2.setBounds(677,403,190,44);
    	regresar2.setBackground(Color.decode("#4C5C68"));
    	regresar2.setForeground(Color.decode("#FFFFFF"));
        escogerLugarPension.add(regresar2);
        
        JButton regresar = new JButton("Regresar al Menú Principal");
		regresar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(escogerLugarPension));
		regresar.setBounds(677,455,190,44);
    	regresar.setBackground(Color.decode("#4C5C68"));
    	regresar.setForeground(Color.decode("#FFFFFF"));
		escogerLugarPension.add(regresar);

		JButton cerrar = new JButton("Cerrar programa");
		cerrar.addActionListener(e -> System.exit(0));
    	cerrar.setBounds(677,507,190,44);
    	cerrar.setBackground(Color.decode("#4C5C68"));
    	cerrar.setForeground(Color.decode("#FFFFFF"));
		escogerLugarPension.add(cerrar);
		
		ImageIcon LugaresPension = new ImageIcon (new ImageIcon("LugaresPension.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
	 	Image img1 = LugaresPension.getImage();     	
	 	JLabel etiPro1 = new JLabel();    
	 	etiPro1.setIcon(LugaresPension);
	 	etiPro1.setBounds( -15,-30, 900, 600);
	 	escogerLugarPension.add(etiPro1);


        VentanaPrincipal.mostrarPantallaActual(escogerLugarPension);
    }

    public void mostrarConfirmacionReserva(JPanel pantallaAnterior, int lugar) 
    {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel principal de esta función
        JPanel confirmacionReserva = new JPanel();
        confirmacionReserva.setLayout(null);
        Font fuenteArial = new Font("Arial", Font.PLAIN, 25);

        JLabel lugarLabel = new JLabel("" + (lugar + 1));
        lugarLabel.setBounds(445, 170, 200, 50);
        lugarLabel.setFont(fuenteArial);
        confirmacionReserva.add(lugarLabel);

        // Botón de confirmación
        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.setBounds(335, 411, 200, 60);
        botonConfirmar.setBackground(Color.decode("#4C5C68"));
        botonConfirmar.setForeground(Color.decode("#FFFFFF"));
        confirmacionReserva.add(botonConfirmar);
        
        ImageIcon ConfirmacionPension = new ImageIcon (new ImageIcon("ConfirmacionPension.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
	 	Image img1 = ConfirmacionPension.getImage();     	
	 	JLabel etiPro1 = new JLabel();    
	 	etiPro1.setIcon(ConfirmacionPension);
	 	etiPro1.setBounds( -15,-30, 900, 600);
	 	confirmacionReserva.add(etiPro1);

        VentanaPrincipal.mostrarPantallaActual(confirmacionReserva);
    }
    
    public void mostrarSalidaPension(JPanel pantallaAnterior)
	{
		VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);
		
		// Se instancia el panel principal de esta función
		panelSalidaPension = new JPanel();
		panelSalidaPension.setLayout(null);
		
		// Codigo de la funcion
		
		VentanaPrincipal.mostrarPantallaActual(panelSalidaPension);
	}
}