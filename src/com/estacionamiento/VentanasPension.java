package com.estacionamiento;

import javax.swing.*;
import java.awt.*;

public class VentanasPension {
    private GestionLugaresPension claseGLP;
    private JPanel escogerLugarPension, panelSalidaPension;

    public VentanasPension(GestionLugaresPension claseGLP) {
        this.claseGLP = claseGLP;
    }

    public void mostrarLugaresPension(JPanel pantallaAnterior) {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel principal y se configura la fuente
        escogerLugarPension = new JPanel();
        escogerLugarPension.setLayout(null);
        Font fuenteArial = new Font("Arial", Font.PLAIN, 15);

        // Instrucción para el usuario
        JLabel instruccion = new JLabel("Selecciona el lugar de tu preferencia:");
        instruccion.setBounds(20, 55, 250, 50);
        instruccion.setFont(fuenteArial);
        escogerLugarPension.add(instruccion);

        // Panel para los lugares de pensión (coches)
        JPanel cochesLugaresPension = new JPanel();
        cochesLugaresPension.setLayout(new GridLayout(2, 4, 20, 21));
        cochesLugaresPension.setOpaque(false);

        // Se crean los botones para cada lugar
        JButton[] botonescochesLugaresPension = new JButton[8];
        for (int i = 0; i < 8; i++) {
            final int index = i;
            botonescochesLugaresPension[i] = new JButton("Lugar " + (i + 1));
            botonescochesLugaresPension[i].addActionListener(e -> claseGLP.comprobarDisponibilidadLugar(index, escogerLugarPension));
            botonescochesLugaresPension[i].setBackground(Color.decode("#4C5C68"));
            botonescochesLugaresPension[i].setForeground(Color.decode("#FFFFFF"));
            cochesLugaresPension.add(botonescochesLugaresPension[i]);
        }
        cochesLugaresPension.setBounds(132, 104, 442, 402);
        escogerLugarPension.add(cochesLugaresPension);

        // Botón para regresar a la salida
        JButton regresar2 = new JButton("Regresar a Salida");
        regresar2.addActionListener(e -> VentanaPrincipal.mostrarPantallaEntrada(escogerLugarPension));
        regresar2.setBounds(677, 403, 190, 44);
        regresar2.setBackground(Color.decode("#4C5C68"));
        regresar2.setForeground(Color.decode("#FFFFFF"));
        escogerLugarPension.add(regresar2);

        // Botón para regresar al menú principal
        JButton regresar = new JButton("Regresar al Menú Principal");
        regresar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(escogerLugarPension));
        regresar.setBounds(677, 455, 190, 44);
        regresar.setBackground(Color.decode("#4C5C68"));
        regresar.setForeground(Color.decode("#FFFFFF"));
        escogerLugarPension.add(regresar);

        // Botón para cerrar el programa
        JButton cerrar = new JButton("Cerrar programa");
        cerrar.addActionListener(e -> System.exit(0));
        cerrar.setBounds(677, 507, 190, 44);
        cerrar.setBackground(Color.decode("#4C5C68"));
        cerrar.setForeground(Color.decode("#FFFFFF"));
        escogerLugarPension.add(cerrar);

        // Fondo de la pantalla con imagen
        ImageIcon LugaresPension = new ImageIcon(new ImageIcon("LugaresPension.png")
                .getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
        JLabel etiPro1 = new JLabel();
        etiPro1.setIcon(LugaresPension);
        etiPro1.setBounds(-15, -30, 900, 600);
        escogerLugarPension.add(etiPro1);

        VentanaPrincipal.mostrarPantallaActual(escogerLugarPension);
    }

    public void mostrarConfirmacionReserva(JPanel pantallaAnterior, int lugar) {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel de confirmación y se configura la fuente
        JPanel confirmacionReserva = new JPanel();
        confirmacionReserva.setLayout(null);
        Font fuenteArial = new Font("Arial", Font.PLAIN, 25);

        // Mensaje de confirmación y detalles de la reserva
        JLabel mensajeConfirmacion = new JLabel("Reserva confirmada!");
        mensajeConfirmacion.setBounds(20, 20, 400, 50);
        mensajeConfirmacion.setFont(fuenteArial);
        confirmacionReserva.add(mensajeConfirmacion);

        JLabel detallesReserva = new JLabel("Detalles de la reserva:");
        detallesReserva.setBounds(20, 70, 300, 50);
        detallesReserva.setFont(fuenteArial);
        confirmacionReserva.add(detallesReserva);

        JLabel lugarLabel = new JLabel("Número de Lugar: " + (lugar + 1));
        lugarLabel.setBounds(20, 120, 300, 50);
        lugarLabel.setFont(fuenteArial);
        confirmacionReserva.add(lugarLabel);

        int duracion = claseGLP.getDuracionDias(lugar);
        JLabel duracionLabel = new JLabel("Duración: " + duracion + " días");
        duracionLabel.setBounds(20, 170, 300, 50);
        duracionLabel.setFont(fuenteArial);
        confirmacionReserva.add(duracionLabel);

        // Botón de confirmación
        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.setBounds(335, 411, 200, 60);
        botonConfirmar.setBackground(Color.decode("#4C5C68"));
        botonConfirmar.setForeground(Color.decode("#FFFFFF"));
        botonConfirmar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(confirmacionReserva));
        confirmacionReserva.add(botonConfirmar);

        // Fondo de la pantalla de confirmación
        ImageIcon ConfirmacionPension = new ImageIcon(new ImageIcon("ConfirmacionPension.png")
                .getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
        JLabel etiPro1_conf = new JLabel();
        etiPro1_conf.setIcon(ConfirmacionPension);
        etiPro1_conf.setBounds(-15, -30, 900, 600);
        confirmacionReserva.add(etiPro1_conf);

        VentanaPrincipal.mostrarPantallaActual(confirmacionReserva);
    }

    public void mostrarSalidaPension(JPanel pantallaAnterior) {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel para la salida de pensión
        panelSalidaPension = new JPanel();
        panelSalidaPension.setLayout(null);

        JLabel despedida = new JLabel("Por favor, ingresa tu número de boleto para salir.");
        despedida.setBounds(10, 10, 400, 50);
        panelSalidaPension.add(despedida);

        JTextField campoBoleto = new JTextField();
        campoBoleto.setBounds(10, 70, 200, 30);
        panelSalidaPension.add(campoBoleto);

        JButton botonVerificar = new JButton("Verificar Boleto");
        botonVerificar.setBounds(220, 70, 150, 30);
        botonVerificar.setBackground(Color.decode("#4C5C68"));
        botonVerificar.setForeground(Color.decode("#FFFFFF"));
        botonVerificar.addActionListener(e -> {
            String numeroBoletoStr = campoBoleto.getText();
            claseGLP.verificarBoleto(panelSalidaPension, numeroBoletoStr);
        });
        panelSalidaPension.add(botonVerificar);

        VentanaPrincipal.mostrarPantallaActual(panelSalidaPension);
    }

    public void mostrarPantallaPago(JPanel pantallaAnterior, int lugar, double costoTotal) {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel de pago y se configura la fuente
        JPanel pantallaPago = new JPanel();
        pantallaPago.setLayout(null);
        Font fuenteArial = new Font("Arial", Font.PLAIN, 25);

        JLabel mensajePago = new JLabel("Por favor, realice su pago para completar el proceso de salida.");
        mensajePago.setBounds(10, 10, 400, 50);
        mensajePago.setFont(fuenteArial);
        pantallaPago.add(mensajePago);

        JLabel detallesPago = new JLabel("Detalles del Pago:");
        detallesPago.setBounds(10, 70, 200, 50);
        detallesPago.setFont(fuenteArial);
        pantallaPago.add(detallesPago);

        int duracion = claseGLP.getDuracionDias(lugar);
        JLabel duracionLabel = new JLabel("Duración: " + duracion + " días");
        duracionLabel.setBounds(10, 130, 300, 50);
        duracionLabel.setFont(fuenteArial);
        pantallaPago.add(duracionLabel);

        JLabel costoLabel = new JLabel("Costo total: $" + costoTotal);
        costoLabel.setBounds(10, 190, 300, 50);
        costoLabel.setFont(fuenteArial);
        pantallaPago.add(costoLabel);

        JButton botonPagoTarjeta = new JButton("Pagar con Tarjeta");
        botonPagoTarjeta.setBounds(10, 250, 150, 50);
        botonPagoTarjeta.setBackground(Color.decode("#4C5C68"));
        botonPagoTarjeta.setForeground(Color.decode("#FFFFFF"));
        botonPagoTarjeta.addActionListener(e -> procesarPagoTarjetaPension(pantallaPago));
        pantallaPago.add(botonPagoTarjeta);

        JButton botonPagoEfectivo = new JButton("Pagar en Efectivo");
        botonPagoEfectivo.setBounds(170, 250, 150, 50);
        botonPagoEfectivo.setBackground(Color.decode("#4C5C68"));
        botonPagoEfectivo.setForeground(Color.decode("#FFFFFF"));
        botonPagoEfectivo.addActionListener(e -> procesarPagoEfectivoPension(pantallaPago, costoTotal));
        pantallaPago.add(botonPagoEfectivo);

        VentanaPrincipal.mostrarPantallaActual(pantallaPago);
    }

    public void mostrarPantallaConfirmacion(JPanel pantallaAnterior) {
        VentanaPrincipal.ocultarPantallaAnterior(pantallaAnterior);

        // Se instancia el panel de confirmación de pago y se configura la fuente
        JPanel pantallaConfirmacion = new JPanel();
        pantallaConfirmacion.setLayout(null);
        Font fuenteArial = new Font("Arial", Font.PLAIN, 25);

        JLabel mensajeConfirmacion = new JLabel("Pago realizado con éxito. Gracias por su visita.");
        mensajeConfirmacion.setBounds(10, 10, 400, 50);
        mensajeConfirmacion.setFont(fuenteArial);
        pantallaConfirmacion.add(mensajeConfirmacion);

        JButton botonFinalizar = new JButton("Finalizar");
        botonFinalizar.setBounds(10, 70, 100, 50);
        botonFinalizar.setBackground(Color.decode("#4C5C68"));
        botonFinalizar.setForeground(Color.decode("#FFFFFF"));
        botonFinalizar.addActionListener(e -> VentanaPrincipal.mostrarPrimeraPantalla(pantallaConfirmacion));
        pantallaConfirmacion.add(botonFinalizar);

        VentanaPrincipal.mostrarPantallaActual(pantallaConfirmacion);
    }
    
 // Método para procesar pago con tarjeta en pensión
    private void procesarPagoTarjetaPension(JPanel pantallaPension) 
    {
        String numeroTarjeta;
        boolean tarjetaValida = false;

        do {
            numeroTarjeta = JOptionPane.showInputDialog(pantallaPension, "Ingrese su número de tarjeta (16 dígitos):");
            if (numeroTarjeta != null && numeroTarjeta.matches("\\d{16}")) {
                tarjetaValida = true;
                JOptionPane.showMessageDialog(pantallaPension, "Pago de pensión realizado con éxito. Gracias.");
                mostrarPantallaConfirmacion(pantallaPension);
            } else {
                JOptionPane.showMessageDialog(pantallaPension, "Número de tarjeta inválido. Intente nuevamente.");
            }
        } while (!tarjetaValida);
    }

    // Método para procesar pago en efectivo en pensión
    private void procesarPagoEfectivoPension(JPanel pantallaPension, double costoPension) 
    {
        boolean pagoValido = false;

        do {
            String cantidadIngresada = JOptionPane.showInputDialog(pantallaPension, "Ingrese la cantidad con la que pagará:");
            if (cantidadIngresada != null) {
                try {
                    double pago = Double.parseDouble(cantidadIngresada);
                    if (pago >= costoPension) {
                        double cambio = pago - costoPension;
                        JOptionPane.showMessageDialog(pantallaPension, "Pago de pensión realizado con éxito.\nCambio: $" + cambio);
                        pagoValido = true;
                        mostrarPantallaConfirmacion(pantallaPension);
                    } else {
                        JOptionPane.showMessageDialog(pantallaPension, "Monto insuficiente. Intente nuevamente.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(pantallaPension, "Ingrese un valor numérico válido.");
                }
            }
        } while (!pagoValido);
    }

}
