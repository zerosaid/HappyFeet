/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.view;

/**
 *
 * @author Prog. Junior Daniel
 */
/*
 * Vista para la gestión de Citas
 */

import happyfeet.Model.Cita;
import happyfeet.controller.CitaController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class CitaView {

    private CitaController controller;
    private final Scanner scanner;
    
     public void setController(CitaController controller) {
        this.controller = controller;
    }

    public CitaView() {
        this.scanner = new Scanner(System.in);
        this.controller = new CitaController(this);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE CITAS ===");
            System.out.println("1. Registrar nueva cita");
            System.out.println("2. Listar todas las citas");
            System.out.println("3. Buscar cita por ID");
            System.out.println("4. Actualizar cita");
            System.out.println("5. Eliminar cita");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarCita();
                case 2 -> controller.listarCitas();
                case 3 -> buscarCitaPorId();
                case 4 -> actualizarCita();
                case 5 -> eliminarCita();
                case 0 -> System.out.println("👋 Saliendo del módulo de Citas...");
                default -> System.out.println("⚠ Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void registrarCita() {
        System.out.println("\n--- Registrar nueva cita ---");

        System.out.print("ID de la mascota: ");
        int mascotaId = leerEntero();

        System.out.print("Motivo de la cita: ");
        String motivo = scanner.nextLine().trim();

        System.out.print("ID del estado: ");
        int estadoId = leerEntero();

        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine().trim();

        System.out.print("Hora (HH:mm): ");
        String hora = scanner.nextLine().trim();

        try {
            String fechaHoraStr = fecha + " " + hora;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);

            Cita cita = new Cita(fechaHora, mascotaId, motivo, estadoId);
            controller.crearCita(cita);
        } catch (Exception e) {
            System.out.println("❌ Error en el formato de fecha u hora. Intente de nuevo.");
        }
    }

    private void buscarCitaPorId() {
        System.out.print("Ingrese el ID de la cita: ");
        int id = leerEntero();
        controller.buscarCita(id);
    }

    private void actualizarCita() {
        System.out.print("Ingrese el ID de la cita a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo ID de la mascota: ");
        int mascotaId = leerEntero();

        System.out.print("Nuevo motivo: ");
        String motivo = scanner.nextLine().trim();

        System.out.print("Nuevo ID del estado: ");
        int estadoId = leerEntero();

        System.out.print("Nueva fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine().trim();

        System.out.print("Nueva hora (HH:mm): ");
        String hora = scanner.nextLine().trim();

        try {
            String fechaHoraStr = fecha + " " + hora;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);

            Cita cita = new Cita(id, fechaHora, mascotaId, motivo, estadoId);
            controller.actualizarCita(cita);
        } catch (Exception e) {
            System.out.println("❌ Error en el formato de fecha u hora. Intente de nuevo.");
        }
    }

    private void eliminarCita() {
        System.out.print("Ingrese el ID de la cita a eliminar: ");
        int id = leerEntero();
        controller.eliminarCita(id);
    }

    // ================= MÉTODOS DE APOYO =================

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarCita(Cita cita) {
        System.out.println("\n📌 Detalle de la cita:");
        System.out.println("ID: " + cita.getId());
        System.out.println("Mascota ID: " + cita.getMascotaId());
        System.out.println("Motivo: " + cita.getMotivo());
        System.out.println("Estado ID: " + cita.getEstadoId());
        System.out.println("Fecha y hora: " + cita.getFechaHora());
    }

    public void mostrarCitas(List<Cita> citas) {
        System.out.println("\n=== LISTA DE CITAS ===");
        for (Cita cita : citas) {
            System.out.println(cita);
        }
    }

    private int leerEntero() {
        while (true) {
            try {
                int numero = Integer.parseInt(scanner.nextLine().trim());
                return numero;
            } catch (NumberFormatException e) {
                System.out.print("⚠ Ingrese un número válido: ");
            }
        }
    }
}
