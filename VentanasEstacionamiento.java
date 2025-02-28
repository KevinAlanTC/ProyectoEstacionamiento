package com.estacionamiento;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        	Font fuenteArial = new Font("Arial", Font.PLAIN, 15);
        
        	// Se instancia en una etiqueta la instrucción que debe seguir el usuario
        	JLabel instruccion = new JLabel("Selecciona el lugar de tu preferencia:");
        	instruccion.setBounds(20, 60, 250, 50);
        	instruccion.setFont(fuenteArial);
        	escogerLugarEstacionamiento.add(instruccion);
        
        	// En el siguiente panel se manejarán únicamente los lugares de estacionamiento de los coches
        	JPanel cochesLugaresEstacionamiento = new JPanel();
        	cochesLugaresEstacionamiento.setLayout(new GridLayout(2, 4, 25, 25));
        	cochesLugaresEstacionamiento.setOpaque(false);
        
        	// En el siguiente panel se manejarán únicamente los lugares de estacionamiento de las motos
        	JPanel motosLugaresEstacionamiento = new JPanel();
        	motosLugaresEstacionamiento.setLayout(new GridLayout(8, 1, 9, 9));
        	motosLugaresEstacionamiento.setOpaque(false);
        
        	// Con el siguiente arreglo de botones se detectará cuando el usuario quiera elegir un lugar de coches en el estacionamiento
        	JButton[] botonescochesLugaresEstacionamiento = new JButton[8];
        
        	// Con el siguiente arreglo de botones se detectará cuando el usuario quiera elegir un lugar de motos en el estacionamiento
        	JButton[] botonesmotosLugaresEstacionamiento = new JButton[8];
        
        	for (int i = 0; i < 8; i++) 
        	{
            		final int index = i; // Crear una variable final
            		botonescochesLugaresEstacionamiento[i] = new JButton(String.valueOf(index + 1000));
            		botonescochesLugaresEstacionamiento[i].addActionListener(e -> claseGLE.comprobarDisponibilidadLugar(0, index, escogerLugarEstacionamiento));
            		botonescochesLugaresEstacionamiento[i].setBackground(Color.decode("#4C5C68"));
            		botonescochesLugaresEstacionamiento[i].setForeground(Color.decode("#FFFFFF"));
            		cochesLugaresEstacionamiento.add(botonescochesLugaresEstacionamiento[i]);

            		botonesmotosLugaresEstacionamiento[i] = new JButton(String.valueOf(index + 1000));
            		botonesmotosLugaresEstacionamiento[i].addActionListener(e -> claseGLE.comprobarDisponibilidadLugar(1, index, escogerLugarEstacionamiento));
            		botonesmotosLugaresEstacionamiento[i].setBackground(Color.decode("#4C5C68"));
            		botonesmotosLugaresEstacionamiento[i].setForeground(Color.decode("#FFFFFF"));
            		motosLugaresEstacionamiento.add(botonesmotosLugaresEstacionamiento[i]);
        	}

        	cochesLugaresEstacionamiento.setBounds(18, 107, 431, 391);
        	escogerLugarEstacionamiento.add(cochesLugaresEstacionamiento);
        	motosLugaresEstacionamiento.setBounds(519, 60, 111, 460);
        	escogerLugarEstacionamiento.add(motosLugaresEstacionamiento);
        
        	JButton regresar2 = new JButton("Regresar a Entrada");
       		regresar2.addActionListener(e -> VentanaPrincipal.mostrarPantallaEntrada(escogerLugarEstacionamiento));
        	regresar2.setBounds(677,403,190,44);
        	regresar2.setBackground(Color.decode("#4C5C68"));
        	regresar2.setForeground(Color.decode("#FFFFFF"));
        	escogerLugarEstacionamiento.add(regresar2);
        
        	JButton regresar = new JButton("Regresar al Menú Principal");
        	regresar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(escogerLugarEstacionamiento));
        	regresar.setBounds(677,455,190,44);
        	regresar.setBackground(Color.decode("#4C5C68"));
        	regresar.setForeground(Color.decode("#FFFFFF"));
        	escogerLugarEstacionamiento.add(regresar);

        	JButton cerrar = new JButton("Cerrar programa");
        	cerrar.addActionListener(e -> System.exit(0));
        	cerrar.setBounds(677,507,190,44);
        	cerrar.setBackground(Color.decode("#4C5C68"));
        	cerrar.setForeground(Color.decode("#FFFFFF"));
        	escogerLugarEstacionamiento.add(cerrar);

        	ImageIcon LugaresEstacionamiento = new ImageIcon (new ImageIcon("LugaresEstacionamiento.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
        	Image img1 = LugaresEstacionamiento.getImage();     	
        	JLabel etiPro1 = new JLabel();    
        	etiPro1.setIcon(LugaresEstacionamiento);
        	etiPro1.setBounds( -15,-30, 900, 600);
        	escogerLugarEstacionamiento.add(etiPro1);

        	VentanaPrincipal.mostrarPantallaActual(escogerLugarEstacionamiento);
    	}
    
    	public void mostrarConfirmacionReserva(JPanel pantallaAnterior, int tipoVehiculo, int lugar) 
    	{
        	VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        	// Se instancia el panel principal de esta función
        	JPanel confirmacionReserva = new JPanel();
        	confirmacionReserva.setLayout(null);
        	Font fuenteArial = new Font("Arial", Font.PLAIN, 25);
        
        	// Asignar un número de boleto único
        	int numeroBoleto = (tipoVehiculo == 0 ? 1000 : 2000) + lugar;
        	
        	JLabel detallesReserva = new JLabel("Estacionamiento");
        	detallesReserva.setBounds(450, 170, 200, 50);
        	detallesReserva.setFont(fuenteArial);
        	confirmacionReserva.add(detallesReserva);

	        JLabel tipoVehiculoLabel = new JLabel("" + (tipoVehiculo == 0 ? "Coche" : "Moto"));
	        tipoVehiculoLabel.setBounds(450, 200, 200, 50);
	        tipoVehiculoLabel.setFont(fuenteArial);
        	confirmacionReserva.add(tipoVehiculoLabel);

	        JLabel lugarLabel = new JLabel("" + (lugar + 1));
	        lugarLabel.setBounds(450, 230, 200, 50);
	        lugarLabel.setFont(fuenteArial);
        	confirmacionReserva.add(lugarLabel);

	        // Obtener la hora de entrada
        	int uniqueKey = claseGLE.generarLlaveUnica(tipoVehiculo, lugar);
        	LocalDateTime horaEntrada = claseGLE.getTiempoEntrada(uniqueKey);
        	DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

	        JLabel horaEntradaLabel = new JLabel("" + horaEntrada.format(formatoHora));
	        horaEntradaLabel.setBounds(450, 260, 200, 50);
	        horaEntradaLabel.setFont(fuenteArial);
        	confirmacionReserva.add(horaEntradaLabel);
        
        	// Botón de confirmación
        	JButton botonConfirmar = new JButton("Confirmar");
        	botonConfirmar.setBounds(335, 411, 200, 60);
            botonConfirmar.setBackground(Color.decode("#4C5C68"));
            botonConfirmar.setForeground(Color.decode("#FFFFFF"));
        	botonConfirmar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(confirmacionReserva));
        	confirmacionReserva.add(botonConfirmar);
        	
        	ImageIcon ConfirmacionE = new ImageIcon (new ImageIcon("ConfirmacionEstacionamiento.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
    	 	Image img1 = ConfirmacionE.getImage();     	
    	 	JLabel etiPro1 = new JLabel();    
    	 	etiPro1.setIcon(ConfirmacionE);
    	 	etiPro1.setBounds( -15,-30, 900, 600);
    	 	confirmacionReserva.add(etiPro1);

	        VentanaPrincipal.mostrarPantallaActual(confirmacionReserva);
    	}
    
    	public void mostrarSalidaEstacionamiento(JPanel pantallaAnterior)
    	{
        	VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);
	
        	// Se instancia el panel principal de esta función
        	panelSalidaEstacionamiento = new JPanel();
        	panelSalidaEstacionamiento.setLayout(null);

 	       JTextField campoBoleto = new JTextField();
        	campoBoleto.setBounds(175, 215, 325, 35);
        	panelSalidaEstacionamiento.add(campoBoleto);

	        // Botón para verificar el número de boleto ingresado
        	JButton botonVerificar = new JButton("Verificar Boleto");
        	botonVerificar.setBounds(513, 208, 225, 47);
        	botonVerificar.addActionListener(e -> claseGLE.verificarBoleto(panelSalidaEstacionamiento, campoBoleto.getText()));
        	botonVerificar.setBackground(Color.decode("#4C5C68"));
        	botonVerificar.setForeground(Color.decode("#FFFFFF"));
        	panelSalidaEstacionamiento.add(botonVerificar);
        	
        	JButton regresar3 = new JButton("Regresar a Salida");
       		regresar3.addActionListener(e -> VentanaPrincipal.mostrarPantallaSalida(panelSalidaEstacionamiento));
        	regresar3.setBounds(677,403,190,44);
        	regresar3.setBackground(Color.decode("#4C5C68"));
        	regresar3.setForeground(Color.decode("#FFFFFF"));
        	panelSalidaEstacionamiento.add(regresar3);
        	
    
        
        	JButton regresar = new JButton("Regresar al Menú Principal");
        	regresar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(panelSalidaEstacionamiento));
        	regresar.setBounds(677,455,190,44);
        	regresar.setBackground(Color.decode("#4C5C68"));
        	regresar.setForeground(Color.decode("#FFFFFF"));
        	panelSalidaEstacionamiento.add(regresar);
        	
        	JButton cerrar = new JButton("Cerrar programa");
        	cerrar.addActionListener(e -> System.exit(0));
        	cerrar.setBounds(677,507,190,44);
        	cerrar.setBackground(Color.decode("#4C5C68"));
        	cerrar.setForeground(Color.decode("#FFFFFF"));
        	panelSalidaEstacionamiento.add(cerrar);
        
		
		ImageIcon SalidaE = new ImageIcon (new ImageIcon("SalidaEstacionamiento.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
	 	Image img1 = SalidaE.getImage();     	
	 	JLabel etiPro1 = new JLabel();    
	 	etiPro1.setIcon(SalidaE);
	 	etiPro1.setBounds( -15,-30, 900, 600);
	 	panelSalidaEstacionamiento.add(etiPro1);

        	VentanaPrincipal.mostrarPantallaActual(panelSalidaEstacionamiento);
    	}
	
    	public void mostrarPantallaPago(JPanel pantallaAnterior, int tipoVehiculo, int lugar, double costoTotal) 
    	{
        	VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

	        // Se instancia el panel principal de esta función
	        JPanel pantallaPago = new JPanel();
        	pantallaPago.setLayout(null);

	        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
	        Font fuenteArial = new Font("Arial", Font.PLAIN, 25);
	        // Mostrar la hora de entrada
        	int uniqueKey = claseGLE.generarLlaveUnica(tipoVehiculo, lugar);
        	LocalDateTime horaEntrada = claseGLE.getTiempoEntrada(uniqueKey);
        	
        	JLabel horaEntradaLabel = new JLabel( horaEntrada.format(formatoHora));
        	horaEntradaLabel.setBounds(370, 180, 300, 50);
        	horaEntradaLabel.setFont(fuenteArial);
        	pantallaPago.add(horaEntradaLabel);

	        // Capturar y mostrar la hora de salida (tiempo simulado)
        	LocalDateTime horaSalida = TiempoSimulado.ahora();
        	JLabel horaSalidaLabel = new JLabel(horaSalida.format(formatoHora));
        	horaSalidaLabel.setBounds(370, 210, 300, 50);
        	horaSalidaLabel.setFont(fuenteArial);
        	pantallaPago.add(horaSalidaLabel);

	        // Mostrar costo total
        	JLabel costoLabel = new JLabel("$" + costoTotal );
        	costoLabel.setBounds(600, 240, 300, 50);
        	costoLabel.setFont(fuenteArial);
        	pantallaPago.add(costoLabel);
        
        	// Opciones de Pago (solo un ejemplo básico)
        	JButton botonPagoTarjeta = new JButton("Pagar con Tarjeta");
        	botonPagoTarjeta.setBounds(480, 357, 194, 50);
        	botonPagoTarjeta.addActionListener(e -> mostrarPantallaConfirmacion(pantallaPago));
        	botonPagoTarjeta.setBackground(Color.decode("#4C5C68"));
        	botonPagoTarjeta.setForeground(Color.decode("#FFFFFF"));
        	pantallaPago.add(botonPagoTarjeta);

	        JButton botonPagoEfectivo = new JButton("Pagar en Efectivo");
        	botonPagoEfectivo.setBounds(198, 357, 194, 50);
        	botonPagoEfectivo.addActionListener(e -> mostrarPantallaConfirmacion(pantallaPago));
        	botonPagoEfectivo.setBackground(Color.decode("#4C5C68"));
        	botonPagoEfectivo.setForeground(Color.decode("#FFFFFF"));
        	pantallaPago.add(botonPagoEfectivo);
        	
        	ImageIcon PagoE = new ImageIcon (new ImageIcon("PagoEstacionamieto.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
    	 	Image img1 = PagoE.getImage();     	
    	 	JLabel etiPro1 = new JLabel();    
    	 	etiPro1.setIcon(PagoE);
    	 	etiPro1.setBounds( -15,-30, 900, 600);
    	 	pantallaPago.add(etiPro1);

	        VentanaPrincipal.mostrarPantallaActual(pantallaPago);
	}


    	public void mostrarPantallaConfirmacion(JPanel pantallaAnterior)
    	{
        	VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

	        // Se instancia el panel principal de esta función
	        JPanel pantallaConfirmacion = new JPanel();
	        pantallaConfirmacion.setLayout(null);

	        // Botón para finalizar
	        JButton botonFinalizar = new JButton("Finalizar");
        	botonFinalizar.setBounds(267, 302, 340, 80);
        	botonFinalizar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(pantallaConfirmacion));
        	botonFinalizar.setBackground(Color.decode("#4C5C68"));
        	botonFinalizar.setForeground(Color.decode("#FFFFFF"));
        	pantallaConfirmacion.add(botonFinalizar);
        	
        	ImageIcon ConfirmacionPago = new ImageIcon (new ImageIcon("ConfirmacionPago.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
    	 	Image img1 = ConfirmacionPago.getImage();   
    	 	JLabel etiPro1 = new JLabel();    
    	 	etiPro1.setIcon(ConfirmacionPago);
    	 	etiPro1.setBounds( -15,-30, 900, 600);
    	 	pantallaConfirmacion.add(etiPro1);


	        VentanaPrincipal.mostrarPantallaActual(pantallaConfirmacion);
    	}
    
}