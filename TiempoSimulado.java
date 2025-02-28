package com.estacionamiento;

import javax.swing.*;
import java.time.LocalDateTime;

public class TiempoSimulado {
    private static LocalDateTime tiempoActual;
    private static Timer timer;

    public static void iniciarReloj() {
        tiempoActual = LocalDateTime.now();

        // Creamos un Timer que se ejecuta cada segundo real
        timer = new Timer(1000, e -> {
            // Incrementamos el tiempo simulado
            tiempoActual = tiempoActual.plusMinutes(1); // Cada segundo real equivale a 1 minuto simulado
            // tiempoActual = tiempoActual.plusHours(1); // Descomenta esta línea si quieres que cada segundo sea 1 hora simulada
        });
        timer.start();
    }

    public static LocalDateTime ahora() {
        return tiempoActual;
    }

    public static void detenerReloj() {
        timer.stop();
    }
}