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
        // Inicia el reloj simulado
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
     * Cada función recibe el panel anterior (si existe) para ocultarlo y mostrar el nuevo.
     */
    
    public static void ocultarPantallaAnterior(JPanel pantallaAnterior)
    {
        if (pantallaAnterior != null)
            ventana.remove(pantallaAnterior);
    }
    
    public static void mostrarPantallaActual(JPanel pantallaActual)
    {
        ventana.add(pantallaActual);
        ventana.revalidate();
        ventana.repaint();
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
        // Se instancia una etiqueta para mostrar la hora
        horaHMS = new JLabel("" + horaFormateada);
        horaHMS.setBounds(775, -28, 100, 80);
        horaHMS.setFont(fuenteArial);
        primeraPantalla.add(horaHMS);
        // Timer para actualizar el reloj simulado cada segundo
	    Timer actualizarReloj = new Timer(1000, e -> 
	    {
	        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
	        horaHMS.setText(" " + TiempoSimulado.ahora().format(formatoHora));
	    });
	    actualizarReloj.start();
        
        // Botón para registrar entrada
        JButton botonEntrada = new JButton("Entrada");
        botonEntrada.addActionListener(e -> mostrarPantallaEntrada(primeraPantalla));
        botonEntrada.setBounds(145, 211, 210, 60);
        botonEntrada.setBackground(Color.decode("#4C5C68"));
        botonEntrada.setForeground(Color.decode("#FFFFFF"));
        primeraPantalla.add(botonEntrada);
        
        // Botón para registrar salida
        JButton botonSalida = new JButton("Salida");
        botonSalida.addActionListener(e -> mostrarPantallaSalida(primeraPantalla));
        botonSalida.setBounds(515, 211, 210, 60);
        botonSalida.setBackground(Color.decode("#4C5C68"));
        botonSalida.setForeground(Color.decode("#FFFFFF"));
        primeraPantalla.add(botonSalida);
        
        // Botón para mostrar créditos
        JButton botonCreditos = new JButton("Créditos");
        botonCreditos.addActionListener(e -> mostrarPantallaCreditos(primeraPantalla));
        botonCreditos.setBounds(45, 482, 150, 48);
        botonCreditos.setBackground(Color.decode("#4C5C68"));
        botonCreditos.setForeground(Color.decode("#FFFFFF"));
        primeraPantalla.add(botonCreditos);
        
        // Botón para cerrar el programa
        JButton cerrar = new JButton("Cerrar programa");
        cerrar.addActionListener(e -> System.exit(0));
        cerrar.setBounds(678, 507, 190, 44);
        cerrar.setBackground(Color.decode("#4C5C68"));
        cerrar.setForeground(Color.decode("#FFFFFF"));
        primeraPantalla.add(cerrar);
        
        // Imagen de fondo
        ImageIcon PrimeraPantallaImg = new ImageIcon(new ImageIcon("PrimeraPantalla.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
        JLabel etiPro1 = new JLabel();
        etiPro1.setIcon(PrimeraPantallaImg);
        etiPro1.setBounds(-15, -30, 900, 600);
        primeraPantalla.add(etiPro1);
        
        mostrarPantallaActual(primeraPantalla);
    }
    
    public static void mostrarPantallaEntrada(JPanel pantallaAnterior)
    {
        ocultarPantallaAnterior(pantallaAnterior);
        
        // Se instancia el panel principal para la entrada
        elegirServicio = new JPanel();
        elegirServicio.setLayout(null);
        
        // Botón para solicitar un lugar en el estacionamiento
        JButton lugarEstacionamiento = new JButton("Quisiera un lugar en el estacionamiento");
        lugarEstacionamiento.addActionListener(e -> claseVE.mostrarLugaresEstacionamiento(elegirServicio));
        lugarEstacionamiento.setBounds(55, 232, 345, 76);
        lugarEstacionamiento.setBackground(Color.decode("#4C5C68"));
        lugarEstacionamiento.setForeground(Color.decode("#FFFFFF"));
        elegirServicio.add(lugarEstacionamiento);
        
        // Botón para solicitar un lugar de pensión
        JButton lugarPension = new JButton("Quisiera un lugar de pensión");
        lugarPension.addActionListener(e -> claseVP.mostrarLugaresPension(elegirServicio));
        lugarPension.setBounds(496, 232, 345, 76);
        lugarPension.setBackground(Color.decode("#4C5C68"));
        lugarPension.setForeground(Color.decode("#FFFFFF"));
        elegirServicio.add(lugarPension);
        
        // Botón para regresar al menú principal
        JButton regresar = new JButton("Regresar al Menú Principal");
        regresar.addActionListener(e -> mostrarPrimeraPantalla(elegirServicio));
        regresar.setBounds(677, 455, 190, 44);
        regresar.setBackground(Color.decode("#4C5C68"));
        regresar.setForeground(Color.decode("#FFFFFF"));
        elegirServicio.add(regresar);
        
        // Botón para cerrar el programa
        JButton cerrar = new JButton("Cerrar programa");
        cerrar.addActionListener(e -> System.exit(0));
        cerrar.setBounds(677, 507, 190, 44);
        cerrar.setBackground(Color.decode("#4C5C68"));
        cerrar.setForeground(Color.decode("#FFFFFF"));
        elegirServicio.add(cerrar);
        
        // Se muestra la hora actual en la pantalla de entrada
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormateada = horaActual.format(formato);
        Font fuenteArial = new Font("Arial", Font.PLAIN, 25);
        horaHMS = new JLabel("" + horaFormateada);
        horaHMS.setBounds(730, -24, 100, 80);
        horaHMS.setFont(fuenteArial);
        elegirServicio.add(horaHMS);
        
        // Imagen de fondo para la pantalla de entrada
        ImageIcon PantallaEntradaImg = new ImageIcon(new ImageIcon("PantallaEntrada.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
        JLabel etiPro1 = new JLabel();
        etiPro1.setIcon(PantallaEntradaImg);
        etiPro1.setBounds(-15, -30, 900, 600);
        elegirServicio.add(etiPro1);
        
        mostrarPantallaActual(elegirServicio);
    }
    
    public static void mostrarPantallaSalida(JPanel pantallaAnterior)
    {
        ocultarPantallaAnterior(pantallaAnterior);
        
        // Se instancia el panel principal para la salida
        elegirSalida = new JPanel();
        elegirSalida.setLayout(null);
        
        // Se muestra la hora actual en la pantalla de salida
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormateada = horaActual.format(formato);
        Font fuenteArial = new Font("Arial", Font.PLAIN, 25);
        horaHMS = new JLabel("" + horaFormateada);
        horaHMS.setBounds(730, -24, 100, 80);
        horaHMS.setFont(fuenteArial);
        elegirSalida.add(horaHMS);
        
        // Botón para salir del estacionamiento
        JButton salidaEstacionamiento = new JButton("Voy a salir del estacionamiento");
        salidaEstacionamiento.addActionListener(e -> claseVE.mostrarSalidaEstacionamiento(elegirSalida));
        salidaEstacionamiento.setBounds(55, 232, 345, 76);
        salidaEstacionamiento.setBackground(Color.decode("#4C5C68"));
        salidaEstacionamiento.setForeground(Color.decode("#FFFFFF"));
        elegirSalida.add(salidaEstacionamiento);
        
        // Botón para recoger el coche de pensión
        JButton salidaPension = new JButton("Voy a recoger mi coche de pensión");
        salidaPension.addActionListener(e -> claseVP.mostrarSalidaPension(elegirSalida));
        salidaPension.setBounds(496, 232, 345, 76);
        salidaPension.setBackground(Color.decode("#4C5C68"));
        salidaPension.setForeground(Color.decode("#FFFFFF"));
        elegirSalida.add(salidaPension);
        
        // Botón para regresar al menú principal
        JButton regresar = new JButton("Regresar al Menú Principal");
        regresar.addActionListener(e -> mostrarPrimeraPantalla(elegirSalida));
        regresar.setBounds(677, 455, 190, 44);
        regresar.setBackground(Color.decode("#4C5C68"));
        regresar.setForeground(Color.decode("#FFFFFF"));
        elegirSalida.add(regresar);
        
        // Botón para cerrar el programa
        JButton cerrar = new JButton("Cerrar programa");
        cerrar.addActionListener(e -> System.exit(0));
        cerrar.setBounds(677, 507, 190, 44);
        cerrar.setBackground(Color.decode("#4C5C68"));
        cerrar.setForeground(Color.decode("#FFFFFF"));
        elegirSalida.add(cerrar);
        
        // Imagen de fondo para la pantalla de salida
        ImageIcon PantallaSalidaImg = new ImageIcon(new ImageIcon("PantallaSalida.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
        JLabel etiPro1 = new JLabel();
        etiPro1.setIcon(PantallaSalidaImg);
        etiPro1.setBounds(-15, -30, 900, 600);
        elegirSalida.add(etiPro1);
        
        mostrarPantallaActual(elegirSalida);
    }
    
    private static void mostrarPantallaCreditos(JPanel pantallaAnterior)
    {
        ocultarPantallaAnterior(pantallaAnterior);
        
        // Se instancia el panel para los créditos
        elegirCreditos = new JPanel();
        elegirCreditos.setLayout(null);
        
        // Botón para regresar al menú principal
        JButton regresar = new JButton("Regresar al Menú Principal");
        regresar.addActionListener(e -> mostrarPrimeraPantalla(elegirCreditos));
        regresar.setBounds(677, 455, 190, 44);
        regresar.setBackground(Color.decode("#4C5C68"));
        regresar.setForeground(Color.decode("#FFFFFF"));
        elegirCreditos.add(regresar);
        
        // Botón para cerrar el programa
        JButton cerrar = new JButton("Cerrar programa");
        cerrar.addActionListener(e -> System.exit(0));
        cerrar.setBounds(677, 507, 190, 44);
        cerrar.setBackground(Color.decode("#4C5C68"));
        cerrar.setForeground(Color.decode("#FFFFFF"));
        elegirCreditos.add(cerrar);
        
        // Imagen de fondo para la pantalla de créditos
        ImageIcon creditosImg = new ImageIcon(new ImageIcon("Creditos.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
        JLabel etiPro1 = new JLabel();
        etiPro1.setIcon(creditosImg);
        etiPro1.setBounds(-15, -30, 900, 600);
        elegirCreditos.add(etiPro1);
        
        mostrarPantallaActual(elegirCreditos);
    }
}

