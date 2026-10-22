/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo;
import javax.swing.JOptionPane;
/**
 *
 * @author JOSE PABLO
 */
public class RegistroReservaciones {

    private Reservacion[] reservaciones;
    private int capacidadParqueo;
    private int espaciosDisponibles;

    public RegistroReservaciones() {

        reservaciones = new Reservacion[10];
        capacidadParqueo = 0;
        espaciosDisponibles = 0;
    }

    public void configurarCapacidad(int capacidad) {

        capacidadParqueo = capacidad;
        espaciosDisponibles = capacidad;
    }

    public int getCapacidadParqueo() {
        return capacidadParqueo;
    }

    public int getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    public boolean verificarCapacidad() {
        return espaciosDisponibles > 0;
    }

    public boolean registrarReservacion(Reservacion reservacion) {

        if (!verificarCapacidad()) {
            return false;
        }

        for (int i = 0; i < reservaciones.length; i++) {

            if (reservaciones[i] == null) {

                espaciosDisponibles--;

                reservacion.setCapacidadParqueo(capacidadParqueo);
                reservacion.setEspaciosDisponibles(espaciosDisponibles);

                reservaciones[i] = reservacion;

                return true;
            }
        }

        return false;
    }

    public Reservacion buscarReservacion(int idReserva) {

        for (int i = 0; i < reservaciones.length; i++) {

            if (reservaciones[i] != null
                    && reservaciones[i].getIdReserva() == idReserva) {

                return reservaciones[i];
            }
        }

        return null;
    }

    public boolean eliminarReservacion(int idReserva) {

        for (int i = 0; i < reservaciones.length; i++) {

            if (reservaciones[i] != null
                    && reservaciones[i].getIdReserva() == idReserva) {

                reservaciones[i] = null;
                espaciosDisponibles++;

                return true;
            }
        }

        return false;
    }

    public void mostrarReservaciones() {

        String lista = "REPORTE DE RESERVACIONES\n\n";
        boolean hayReservaciones = false;

        for (int i = 0; i < reservaciones.length; i++) {

            if (reservaciones[i] != null) {

                lista += "ID de reserva: "
                        + reservaciones[i].getIdReserva()
                        + "\nDías: "
                        + reservaciones[i].getDias()
                        + "\nHora de ingreso: "
                        + reservaciones[i].getHoraIngreso()
                        + "\nHora de salida: "
                        + reservaciones[i].getHoraSalida()
                        + "\nTiempo de ocupación: "
                        + reservaciones[i].getTiempoOcupacion()
                        + " horas"
                        + "\n---------------------------\n";

                hayReservaciones = true;
            }
        }

        if (!hayReservaciones) {
            lista += "No hay reservaciones registradas.\n";
        }

        lista += "\nCapacidad total: " + capacidadParqueo;
        lista += "\nEspacios disponibles: " + espaciosDisponibles;

        JOptionPane.showMessageDialog(
                null,
                lista,
                "Reporte de asignación",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
