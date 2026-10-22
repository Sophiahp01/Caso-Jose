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

public class RegistroVehiculos {

    private Vehiculo[] vehiculos = new Vehiculo[10];

    public boolean registrarVehiculo(Vehiculo vehiculo) {

        for (int i = 0; i < vehiculos.length; i++) {

            if (vehiculos[i] == null) {
                vehiculos[i] = vehiculo;
                return true;
            }
        }

        return false;
    }

    public Vehiculo buscarVehiculo(String placa) {

        for (int i = 0; i < vehiculos.length; i++) {

            if (vehiculos[i] != null
                    && vehiculos[i].getPlaca().equalsIgnoreCase(placa)) {

                return vehiculos[i];
            }
        }

        return null;
    }

    public void mostrarVehiculos() {

        String lista = "VEHÍCULOS REGISTRADOS\n\n";
        boolean hayVehiculos = false;

        for (int i = 0; i < vehiculos.length; i++) {

            if (vehiculos[i] != null) {

                lista += "Placa: " + vehiculos[i].getPlaca()
                        + "\nForma de pago: "
                        + vehiculos[i].getFormaPago()
                        + "\nTiempo: "
                        + vehiculos[i].getTiempo() + " horas"
                        + "\n---------------------------\n";

                hayVehiculos = true;
            }
        }

        if (!hayVehiculos) {
            lista = "No hay vehículos registrados.";
        }

        JOptionPane.showMessageDialog(
                null,
                lista,
                "Vehículos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public boolean eliminarVehiculo(String placa) {

        for (int i = 0; i < vehiculos.length; i++) {

            if (vehiculos[i] != null
                    && vehiculos[i].getPlaca().equalsIgnoreCase(placa)) {

                vehiculos[i] = null;
                return true;
            }
        }

        return false;
    }

    public boolean validarDatos(
            String placa,
            String formaPago,
            int tiempo) {

        return placa != null
                && formaPago != null
                && !placa.trim().isEmpty()
                && !formaPago.trim().isEmpty()
                && tiempo > 0;
    }
}
