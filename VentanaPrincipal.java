package com.estacionamiento;

// Clase principal, relacionada con pantallas entrada y salida

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime; // Biblioteca para obtener la hora
import java.time.format.DateTimeFormatter; // Biblioteca para formatear la hora (darle un formato)

public class VentanaPrincipal extends JFrame 
{
	private static JFrame ventana;
	private static JPanel primeraPantalla, elegirServicio, elegirSalida, elegirCreditos;
	private static JLabel horaHMS;
	
	private static GestionLugaresEstacionamiento claseGLE;
    private static GestionLugaresPension claseGLP;
    private static VentanasEstacionamiento claseVE;
    private static VentanasPension claseVP;
	
	public static void main(String[] args)
	{
		TiempoSimulado.iniciarReloj();

		ventana = new JFrame();
		ventana.setTitle("Estacionamiento");
        ventana.setSize(900, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        claseGLE = new GestionLugaresEstacionamiento();
        claseVE = new VentanasEstacionamiento(claseGLE);
        claseGLE.setVentanasEstacionamiento(claseVE);
        
        claseGLP = new GestionLugaresPension();
        claseVP = new VentanasPension(claseGLP);
        claseGLP.setVentanasPension(claseVP);
        
        mostrarPrimeraPantalla(null);
        ventana.setVisible(true); // Se muestra la ventana
	}
	
	/*
	 * A cada función se le pasa el panel de la pantalla anterior que estaba en el frame Ventana (usualmente el panel principal 
	 *  de donde el llamado a la otra función) para poder ocultarlo y que sea visible el siguiente Panel. Si no hay panel de 
	 *  procediencia se envía null como argumento
	 */
	
	//Las líneas de dentro de esta función deberían ir al inicio de cada función, pero al ser varias funciones es mejor usarlas así
	public static void ocultarPantallaAnterior(JPanel pantallaAnterior)
	{
		if (pantallaAnterior != null) 
            ventana.remove(pantallaAnterior);
	}
	
	// Las líneas dentro de esta función deberían ir al final de cada función, pero al ser varias funciones es mejor usarlas así
	public static void mostrarPantallaActual(JPanel pantallaActual)
	{
		ventana.add(pantallaActual); // Se añade el panel principal de la función ya con todos los elementos a la ventana
        ventana.revalidate(); // Actualiza el contenedor de la ventana
        ventana.repaint(); // Redibuja la ventana
	}

	public static void mostrarPrimeraPantalla(JPanel pantallaAnterior)
	{
		ocultarPantallaAnterior(pantallaAnterior);
		
		// Se instancia el panel principal de esta función
		primeraPantalla = new JPanel();
		primeraPantalla.setLayout(null);
		
		// Se obtiene la hora y se le da formato
		LocalTime horaActual = LocalTime.now();
        	DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        	String horaFormateada = horaActual.format(formato);
        	Font fuenteArial = new Font("Arial", Font.PLAIN, 25);
        	// Se instancia una etiqueta para mostrar dicha hora 
        	horaHMS = new JLabel(""+ horaFormateada);
        	horaHMS.setBounds(775, -28, 100, 80);
        	horaHMS.setFont(fuenteArial);
        	primeraPantalla.add(horaHMS);        
        
        	// Se intancia un botón para que el usuario registre su entrada
        	JButton botonEntrada = new JButton("Entrada");
        	botonEntrada.addActionListener(e -> mostrarPantallaEntrada(primeraPantalla));
        	botonEntrada.setBounds(145, 211, 210, 60);
		botonEntrada.setBackground(Color.decode("#4C5C68"));
		botonEntrada.setForeground(Color.decode("#FFFFFF"));
        	primeraPantalla.add(botonEntrada);
        
        	// Se instancia un botón para que el usuario registre la salida
        	JButton botonSalida = new JButton("Salida");
        	botonSalida.addActionListener(e -> mostrarPantallaSalida(primeraPantalla));
        	botonSalida.setBounds(515, 211, 210, 60);
		botonSalida.setBackground(Color.decode("#4C5C68"));
		botonSalida.setForeground(Color.decode("#FFFFFF"));
        	primeraPantalla.add(botonSalida);
        
        	JButton botonCreditos = new JButton("Créditos");
        	botonCreditos.addActionListener(e -> mostrarPantallaCreditos(primeraPantalla));
        	botonCreditos.setBounds(45,482, 150, 48);
		botonCreditos.setBackground(Color.decode("#4C5C68"));
		botonCreditos.setForeground(Color.decode("#FFFFFF"));
        	primeraPantalla.add(botonCreditos);
        
        	JButton cerrar = new JButton("Cerrar programa");
		cerrar.addActionListener(e -> System.exit(0));
		cerrar.setBounds(678,507,190,44);
		cerrar.setBackground(Color.decode("#4C5C68"));
		cerrar.setForeground(Color.decode("#FFFFFF"));
		primeraPantalla.add(cerrar);
		
		ImageIcon PrimeraPantalla = new ImageIcon (new ImageIcon("PrimeraPantalla.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
	 	Image img1 = PrimeraPantalla.getImage();     	
	 	JLabel etiPro1 = new JLabel();    
	 	etiPro1.setIcon(PrimeraPantalla);
	 	etiPro1.setBounds( -15,-30, 900, 600);
	 	primeraPantalla.add(etiPro1);
        
        mostrarPantallaActual(primeraPantalla);
	}
	
	public static void mostrarPantallaEntrada(JPanel pantallaAnterior)
	{
		ocultarPantallaAnterior(pantallaAnterior);
		
		// Se instancia el panel principal de esta función
		elegirServicio = new JPanel();
		elegirServicio.setLayout(null);
		
		// Se instancia un botón para que el usuario elija un lugar en el estacionamiento
		JButton lugarEstacionamiento = new JButton("Quisiera un lugar en el estacionamiento");
		lugarEstacionamiento.addActionListener(e -> claseVE.mostrarLugaresEstacionamiento(elegirServicio));
		lugarEstacionamiento.setBounds(55, 232, 345, 76);
		lugarEstacionamiento.setBackground(Color.decode("#4C5C68"));
		lugarEstacionamiento.setForeground(Color.decode("#FFFFFF"));
		elegirServicio.add(lugarEstacionamiento);
		
		// Se instancia un botón para que el usario elija un lugar de pensión
		JButton lugarPension = new JButton("Quisera un lugar de pensión");
		lugarPension.addActionListener(e -> claseVP.mostrarLugaresPension(elegirServicio));
		lugarPension.setBounds(496, 232, 345, 76);
		lugarPension.setBackground(Color.decode("#4C5C68"));
		lugarPension.setForeground(Color.decode("#FFFFFF"));
		elegirServicio.add(lugarPension);
		
		JButton regresar = new JButton("Regresar al Menú Principal");
		regresar.addActionListener(e -> mostrarPrimeraPantalla(elegirServicio));
		regresar.setBounds(677,455,190,44);
		regresar.setBackground(Color.decode("#4C5C68"));
		regresar.setForeground(Color.decode("#FFFFFF"));
		elegirServicio.add(regresar);

		JButton cerrar = new JButton("Cerrar programa");
		cerrar.addActionListener(e -> System.exit(0));
		cerrar.setBounds(677,507,190,44);
		cerrar.setBackground(Color.decode("#4C5C68"));
		cerrar.setForeground(Color.decode("#FFFFFF"));
		elegirServicio.add(cerrar);
		
		LocalTime horaActual = LocalTime.now();
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
    	String horaFormateada = horaActual.format(formato);
    	Font fuenteArial = new Font("Arial", Font.PLAIN, 25);
    	// Se instancia una etiqueta para mostrar dicha hora 
    	horaHMS = new JLabel(""+ horaFormateada);
    	horaHMS.setBounds(730, -24, 100, 80);
    	horaHMS.setFont(fuenteArial);
    	elegirServicio.add(horaHMS);

		ImageIcon PantallaEntrada = new ImageIcon (new ImageIcon("PantallaEntrada.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
	 	Image img1 = PantallaEntrada.getImage();     	
	 	JLabel etiPro1 = new JLabel();    
	 	etiPro1.setIcon(PantallaEntrada);
	 	etiPro1.setBounds( -15,-30, 900, 600);
	 	elegirServicio.add(etiPro1);
		
		mostrarPantallaActual(elegirServicio);
	}
	
	
	public static void mostrarPantallaSalida(JPanel pantallaAnterior)
	{
		ocultarPantallaAnterior(pantallaAnterior);
		
		// Se instancia el panel principal de esta función
		elegirSalida = new JPanel();
		elegirSalida.setLayout(null);
		
		LocalTime horaActual = LocalTime.now();
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
    	String horaFormateada = horaActual.format(formato);
    	Font fuenteArial = new Font("Arial", Font.PLAIN, 25);
    	// Se instancia una etiqueta para mostrar dicha hora 
    	horaHMS = new JLabel(""+ horaFormateada);
    	horaHMS.setBounds(730, -24, 100, 80);
    	horaHMS.setFont(fuenteArial);
    	elegirSalida.add(horaHMS);
		
		// Se instancia un botón para que el usuario elija salir del estacionamiento
		JButton salidaEstacionamiento = new JButton("Voy a salir del estacionamiento");
		salidaEstacionamiento.addActionListener(e -> claseVE.mostrarSalidaEstacionamiento(elegirSalida));
		salidaEstacionamiento.setBounds(55, 232, 345, 76);
		salidaEstacionamiento.setBackground(Color.decode("#4C5C68"));
		salidaEstacionamiento.setForeground(Color.decode("#FFFFFF"));
		elegirSalida.add(salidaEstacionamiento);
		
		// Se instancia un botón para que el usario elija recoger su auto de pensión
		JButton salidaPension = new JButton("Voy a recoger mi coche de pensión");
		salidaPension.addActionListener(e -> claseVP.mostrarSalidaPension(elegirSalida));
		salidaPension.setBounds(496, 232, 345, 76);
		salidaPension.setBackground(Color.decode("#4C5C68"));
		salidaPension.setForeground(Color.decode("#FFFFFF"));
		elegirSalida.add(salidaPension);
		
		JButton regresar = new JButton("Regresar al Menú Principal");
		regresar.addActionListener(e -> mostrarPrimeraPantalla(elegirSalida));
		regresar.setBounds(677,455,190,44);
		regresar.setBackground(Color.decode("#4C5C68"));
		regresar.setForeground(Color.decode("#FFFFFF"));
		elegirSalida.add(regresar);

		JButton cerrar = new JButton("Cerrar programa");
		cerrar.addActionListener(e -> System.exit(0));
		cerrar.setBounds(677,507,190,44);
		cerrar.setBackground(Color.decode("#4C5C68"));
		cerrar.setForeground(Color.decode("#FFFFFF"));
		elegirSalida.add(cerrar);

		ImageIcon PantallaSalida = new ImageIcon (new ImageIcon("PantallaSalida.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
	 	Image img1 = PantallaSalida.getImage();     	
	 	JLabel etiPro1 = new JLabel();    
	 	etiPro1.setIcon(PantallaSalida);
	 	etiPro1.setBounds( -15,-30, 900, 600);
	 	elegirSalida.add(etiPro1);
		
		mostrarPantallaActual(elegirSalida);
	}
	
	private static void mostrarPantallaCreditos(JPanel pantallaAnterior)
	{
		ocultarPantallaAnterior(pantallaAnterior);
		elegirCreditos = new JPanel();
		elegirCreditos.setLayout(null);
	 	
		JButton regresar = new JButton("Regresar al Menú Principal");
		regresar.addActionListener(e -> mostrarPrimeraPantalla(elegirCreditos));
		regresar.setBounds(677,455,190,44);
		regresar.setBackground(Color.decode("#4C5C68"));
		regresar.setForeground(Color.decode("#FFFFFF"));
		elegirCreditos.add(regresar);

		JButton cerrar = new JButton("Cerrar programa");
		cerrar.addActionListener(e -> System.exit(0));
		cerrar.setBounds(677,507,190,44);
		cerrar.setBackground(Color.decode("#4C5C68"));
		cerrar.setForeground(Color.decode("#FFFFFF"));
		elegirCreditos.add(cerrar);
		
		ImageIcon creditos = new ImageIcon (new ImageIcon("Creditos.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
	 	Image img1 = creditos.getImage();     	
	 	JLabel etiPro1 = new JLabel();    
	 	etiPro1.setIcon(creditos);
	 	etiPro1.setBounds( -15,-30, 900, 600);
	 	elegirCreditos.add(etiPro1);
	 	
	 	ventana.add(elegirCreditos);
	
	 	mostrarPantallaActual(elegirCreditos);
		
	    }
	
}