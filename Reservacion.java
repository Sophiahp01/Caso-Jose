/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaparqueo;

/**
 *
 * @author JOSE PABLO
 */
public class Reservacion {

    private int idReserva;
    private int dias;
    private String horaIngreso;
    private String horaSalida;
    private int tiempoOcupacion;
    private int capacidadParqueo;
    private int espaciosDisponibles;

    public Reservacion() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getTiempoOcupacion() {
        return tiempoOcupacion;
    }

    public void setTiempoOcupacion(int tiempoOcupacion) {
        this.tiempoOcupacion = tiempoOcupacion;
    }

    public int getCapacidadParqueo() {
        return capacidadParqueo;
    }

    public void setCapacidadParqueo(int capacidadParqueo) {
        this.capacidadParqueo = capacidadParqueo;
    }

    public int getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    public void setEspaciosDisponibles(int espaciosDisponibles) {
        this.espaciosDisponibles = espaciosDisponibles;
    }

    public boolean verificarCapacidad() {
        return espaciosDisponibles > 0;
    }

    public void reservarEspacio() {

        if (verificarCapacidad()) {
            espaciosDisponibles--;
        }
    }
}
