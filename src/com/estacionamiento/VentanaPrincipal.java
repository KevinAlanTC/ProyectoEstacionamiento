package com.estacionamiento;

// Clase principal, relacionada con pantallas entrada y salida

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime; // Biblioteca para obtener la hora
import java.time.format.DateTimeFormatter; // Biblioteca para formatear la hora (darle un formato)

public class VentanaPrincipal extends JFrame 
{
	private static JFrame ventana;
	private static JPanel primeraPantalla, elegirServicio, elegirSalida;
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
		
		/*/ Se obtiene la hora y se le da formato
		LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = horaActual.format(formato);
        
        // Se instancia una etiqueta para mostrar dicha hora 
        horaHMS = new JLabel(""+ horaFormateada);
        horaHMS.setBounds(50, 50, 100, 50);
        primeraPantalla.add(horaHMS);*/
		
		// Instanciar el JLabel para el reloj simulado
	    JLabel relojSimuladoLabel = new JLabel();
	    relojSimuladoLabel.setBounds(700, 10, 200, 50); // Ajusta la posición y tamaño según sea necesario
	    primeraPantalla.add(relojSimuladoLabel);

	    // Timer para actualizar el reloj simulado cada segundo
	    Timer actualizarReloj = new Timer(1000, e -> 
	    {
	        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
	        relojSimuladoLabel.setText("Tiempo Simulado: " + TiempoSimulado.ahora().format(formatoHora));
	    });
	    actualizarReloj.start();
        
        // Se intancia un botón para que el usuario registre su entrada
        JButton botonEntrada = new JButton("Entrada");
        botonEntrada.addActionListener(e -> mostrarPantallaEntrada(primeraPantalla));
        botonEntrada.setBounds(50, 150, 200, 50);
        primeraPantalla.add(botonEntrada);
        
        // Se instancia un botón para que el usuario registre la salida
        JButton botonSalida = new JButton("Salida");
        botonSalida.addActionListener(e -> mostrarPantallaSalida(primeraPantalla));
        botonSalida.setBounds(251, 150, 200, 50);
        primeraPantalla.add(botonSalida);
        
        mostrarPantallaActual(primeraPantalla);
	}
	
	private static void mostrarPantallaEntrada(JPanel pantallaAnterior)
	{
		ocultarPantallaAnterior(pantallaAnterior);
		
		// Se instancia el panel principal de esta función
		elegirServicio = new JPanel();
		elegirServicio.setLayout(null);
		
		// Se intancia en una etiqueta la instrucción que debe seguir el usuario
		JLabel instruccion = new JLabel("Seleciona la opción de tu preferencia:");
		instruccion.setBounds(10, 10, 250, 50);
		elegirServicio.add(instruccion);
		
		// Se instancia un botón para que el usuario elija un lugar en el estacionamiento
		JButton lugarEstacionamiento = new JButton("Quisiera un lugar en el estacionamiento");
		lugarEstacionamiento.addActionListener(e -> claseVE.mostrarLugaresEstacionamiento(elegirServicio));
		lugarEstacionamiento.setBounds(50, 200, 400, 100);
		elegirServicio.add(lugarEstacionamiento);
		
		// Se instancia un botón para que el usario elija un lugar de pensión
		JButton lugarPension = new JButton("Quisera un lugar de pensión");
		lugarPension.addActionListener(e -> claseVP.mostrarLugaresPension(elegirServicio));
		lugarPension.setBounds(50, 400, 400, 100);
		elegirServicio.add(lugarPension);
		
		mostrarPantallaActual(elegirServicio);
	}
	
	
	private static void mostrarPantallaSalida(JPanel pantallaAnterior)
	{
		ocultarPantallaAnterior(pantallaAnterior);
		
		// Se instancia el panel principal de esta función
		elegirSalida = new JPanel();
		elegirSalida.setLayout(null);
		
		// Se intancia en una etiqueta la instrucción que debe seguir el usuario
		JLabel instruccion = new JLabel("Seleciona la opción que deseas:");
		instruccion.setBounds(10, 10, 250, 50);
		elegirSalida.add(instruccion);
		
		// Se instancia un botón para que el usuario elija salir del estacionamiento
		JButton salidaEstacionamiento = new JButton("Voy a salir del estacionamiento");
		salidaEstacionamiento.addActionListener(e -> claseVE.mostrarSalidaEstacionamiento(elegirSalida));
		salidaEstacionamiento.setBounds(50, 200, 400, 100);
		elegirSalida.add(salidaEstacionamiento);
		
		// Se instancia un botón para que el usario elija recoger su auto de pensión
		JButton salidaPension = new JButton("Voy a recoger mi coche de pensión");
		salidaPension.addActionListener(e -> claseVP.mostrarSalidaPension(elegirSalida));
		salidaPension.setBounds(50, 400, 400, 100);
		elegirSalida.add(salidaPension);
		
		mostrarPantallaActual(elegirSalida);
	}
	
}
