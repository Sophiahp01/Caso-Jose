/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sistemaparqueo;

import javax.swing.JOptionPane;

/**
 *
 * @author JOSE PABLO
 */
public class SistemaParqueo {

    public static void main(String[] args) {

        RegistroVehiculos registroVehiculos
                = new RegistroVehiculos();

        RegistroReservaciones registroReservaciones
                = new RegistroReservaciones();

        int opcion = 0;

        do {

            try {

                String entrada = JOptionPane.showInputDialog(
                        null,
                        "SISTEMA DE PARQUEO\n\n"
                        + "1. Registrar lugar de parqueo\n"
                        + "2. Registrar vehículo\n"
                        + "3. Mostrar vehículos\n"
                        + "4. Reservación de espacios\n"
                        + "5. Reporte de asignación\n"
                        + "6. Eliminar reservaciones\n"        
                        + "7. Salir\n\n"
                        + "Seleccione una opción:",
                        "Menú principal",
                        JOptionPane.QUESTION_MESSAGE
                );

                if (entrada == null) {
                    opcion = 7;
                } else {
                    opcion = Integer.parseInt(entrada);
                }

                switch (opcion) {

                    case 1:
                        configurarParqueo(registroReservaciones);
                        break;

                    case 2:
                        registrarVehiculo(registroVehiculos);
                        break;

                    case 3:
                        registroVehiculos.mostrarVehiculos();
                        break;

                    case 4:
                        realizarReservacion(
                                registroVehiculos,
                                registroReservaciones
                        );
                        break;

                    case 5:
                        mostrarReporte(
                                registroVehiculos,
                                registroReservaciones
                        );
                        break;

                    case 6:
                       eliminarReservacion(registroReservaciones);
                        break;
                    case 7:
                             JOptionPane.showMessageDialog(
                                null,
                                "Gracias por utilizar el sistema."
                        );
                        break;

                    default:
                        JOptionPane.showMessageDialog(
                                null,
                                "Seleccione una opción válida.",
                                "Opción incorrecta",
                                JOptionPane.WARNING_MESSAGE
                        );
                }

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Debe ingresar un número válido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } while (opcion != 7);
    }

    public static void configurarParqueo(
            RegistroReservaciones registro) {

        try {

            String entrada = JOptionPane.showInputDialog(
                    "Ingrese la capacidad total del parqueo:"
            );

            if (entrada == null) {
                return;
            }

            int capacidad = Integer.parseInt(entrada);

            if (capacidad <= 0 || capacidad > 10) {

                JOptionPane.showMessageDialog(
                        null,
                        "La capacidad debe estar entre 1 y 10.",
                        "Capacidad inválida",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            registro.configurarCapacidad(capacidad);

            JOptionPane.showMessageDialog(
                    null,
                    "Lugar de parqueo registrado correctamente.\n"
                    + "Capacidad: " + capacidad + " espacios."
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Ingrese una capacidad numérica.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void registrarVehiculo(
            RegistroVehiculos registro) {

        try {

            String placa = JOptionPane.showInputDialog(
                    "Ingrese la placa del vehículo:"
            );

            if (placa == null) {
                return;
            }

            placa = placa.trim().toUpperCase();

            if (placa.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "La placa no puede estar vacía."
                );

                return;
            }

            if (registro.buscarVehiculo(placa) != null) {

                JOptionPane.showMessageDialog(
                        null,
                        "La placa ya está registrada.",
                        "Vehículo existente",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String formaPago = JOptionPane.showInputDialog(
                    "Ingrese la forma de pago:\n"
                    + "Efectivo, tarjeta o SINPE."
            );

            if (formaPago == null) {
                return;
            }

            String entradaTiempo = JOptionPane.showInputDialog(
                    "Ingrese el tiempo de parqueo en horas:"
            );

            if (entradaTiempo == null) {
                return;
            }

            int tiempo = Integer.parseInt(entradaTiempo);

            if (!registro.validarDatos(
                    placa,
                    formaPago,
                    tiempo)) {

                JOptionPane.showMessageDialog(
                        null,
                        "Los datos ingresados no son válidos.",
                        "Datos incorrectos",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            Vehiculo vehiculo = new Vehiculo();

            vehiculo.setPlaca(placa);
            vehiculo.setFormaPago(formaPago.trim());
            vehiculo.setTiempo(tiempo);

            boolean registrado
                    = registro.registrarVehiculo(vehiculo);

            if (registrado) {

                JOptionPane.showMessageDialog(
                        null,
                        "Vehículo registrado correctamente.\n\n"
                        + "Placa: " + vehiculo.getPlaca()
                        + "\nForma de pago: "
                        + vehiculo.getFormaPago()
                        + "\nTiempo: "
                        + vehiculo.getTiempo() + " horas"
                );

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "No se pueden registrar más vehículos.",
                        "Registro lleno",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "El tiempo debe ser un número entero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void realizarReservacion(
            RegistroVehiculos registroVehiculos,
            RegistroReservaciones registroReservaciones) {

        try {

            if (registroReservaciones.getCapacidadParqueo() == 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Primero debe registrar la capacidad del parqueo.",
                        "Parqueo no configurado",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (!registroReservaciones.verificarCapacidad()) {

                JOptionPane.showMessageDialog(
                        null,
                        "No hay espacios disponibles.",
                        "Parqueo lleno",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String placa = JOptionPane.showInputDialog(
                    "Ingrese la placa del vehículo:"
            );

            if (placa == null) {
                return;
            }

            Vehiculo vehiculo
                    = registroVehiculos.buscarVehiculo(placa.trim());

            if (vehiculo == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "El vehículo no está registrado.",
                        "Vehículo no encontrado",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String entradaId = JOptionPane.showInputDialog(
                    "Ingrese el ID de la reservación:"
            );

            if (entradaId == null) {
                return;
            }

            int idReserva = Integer.parseInt(entradaId);

            if (registroReservaciones.buscarReservacion(idReserva)
                    != null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Ya existe una reservación con ese ID.",
                        "ID repetido",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int dias = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "Ingrese la cantidad de días:"
                    )
            );

            String horaIngreso = JOptionPane.showInputDialog(
                    "Ingrese la hora de ingreso:"
            );

            if (horaIngreso == null) {
                return;
            }

            String horaSalida = JOptionPane.showInputDialog(
                    "Ingrese la hora de salida:"
            );

            if (horaSalida == null) {
                return;
            }

            int tiempoOcupacion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "Ingrese el tiempo de ocupación en horas:"
                    )
            );

            if (dias <= 0 || tiempoOcupacion <= 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Los días y el tiempo deben ser mayores que cero."
                );

                return;
            }

            Reservacion reservacion = new Reservacion();

            reservacion.setIdReserva(idReserva);
            reservacion.setDias(dias);
            reservacion.setHoraIngreso(horaIngreso);
            reservacion.setHoraSalida(horaSalida);
            reservacion.setTiempoOcupacion(tiempoOcupacion);

            boolean registrada
                    = registroReservaciones
                            .registrarReservacion(reservacion);

            if (registrada) {

                JOptionPane.showMessageDialog(
                        null,
                        "Reservación realizada correctamente.\n\n"
                        + "Vehículo: " + vehiculo.getPlaca()
                        + "\nID de reserva: "
                        + reservacion.getIdReserva()
                        + "\nDías: " + reservacion.getDias()
                        + "\nHora de ingreso: "
                        + reservacion.getHoraIngreso()
                        + "\nHora de salida: "
                        + reservacion.getHoraSalida()
                        + "\nTiempo de ocupación: "
                        + reservacion.getTiempoOcupacion()
                        + " horas"
                        + "\nEspacios disponibles: "
                        + registroReservaciones
                                .getEspaciosDisponibles()
                );

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "No fue posible registrar la reservación.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Debe ingresar números válidos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void mostrarReporte(
            RegistroVehiculos registroVehiculos,
            RegistroReservaciones registroReservaciones) {

        registroVehiculos.mostrarVehiculos();
        registroReservaciones.mostrarReservaciones();
    }
    
    public static void eliminarReservacion(
        RegistroReservaciones registroReservaciones) {

    try {

        String entradaId = JOptionPane.showInputDialog(
                "Ingrese el ID de la reservación que desea eliminar:"
        );

        if (entradaId == null) {
            return;
        }

        int idReserva = Integer.parseInt(entradaId);

        Reservacion reservacion =
                registroReservaciones.buscarReservacion(idReserva);

        if (reservacion == null) {

            JOptionPane.showMessageDialog(
                    null,
                    "No existe una reservación con ese ID.",
                    "Reservación no encontrada",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Desea eliminar la reservación número "
                + idReserva + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            boolean eliminada =
                    registroReservaciones.eliminarReservacion(idReserva);

            if (eliminada) {

                JOptionPane.showMessageDialog(
                        null,
                        "Reservación eliminada correctamente.\n"
                        + "El espacio fue liberado."
                );
            }
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                null,
                "Debe ingresar un ID numérico.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
}
