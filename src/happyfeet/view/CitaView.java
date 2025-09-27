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
import java.util.List;
import java.util.Scanner;


public class CitaView {

    private final Scanner scanner = new Scanner(System.in);
    private CitaController controller;

    // Setter para inyectar el controller
    public void setController(CitaController controller) {
        this.controller = controller;
    }

    // Mostrar menú de Citas
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== GESTIÓN DE CITAS =====");
            System.out.println("1. Registrar cita");
            System.out.println("2. Listar citas");
            System.out.println("3. Buscar cita por ID");
            System.out.println("4. Actualizar cita");
            System.out.println("5. Eliminar cita");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> registrarCita();
                case 2 -> controller.listarCitas();
                case 3 -> buscarCita();
                case 4 -> actualizarCita();
                case 5 -> eliminarCita();
                case 0 -> System.out.println("↩ Volviendo al menú principal...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Registrar cita
    private void registrarCita() {
        System.out.println("\n--- Registrar Cita ---");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("ID de la Mascota: ");
        int mascotaId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID del Veterinario: ");
        int veterinarioId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Motivo: ");
        String motivo = scanner.nextLine();
        System.out.print("Estado (Programada/Finalizada/Cancelada): ");
        String estado = scanner.nextLine();

        Cita cita = new Cita(id, fecha, mascotaId, veterinarioId, motivo, estado);
        controller.crearCita(cita);
    }

    // Buscar cita
    private void buscarCita() {
        System.out.print("Ingrese ID de la cita: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.buscarCita(id);
    }

    // Actualizar cita
    private void actualizarCita() {
        System.out.println("\n--- Actualizar Cita ---");
        System.out.print("ID de la cita: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nueva fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Nuevo ID de Mascota: ");
        int mascotaId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo ID de Veterinario: ");
        int veterinarioId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo motivo: ");
        String motivo = scanner.nextLine();
        System.out.print("Nuevo estado: ");
        String estado = scanner.nextLine();

        Cita cita = new Cita(id, fecha, mascotaId, veterinarioId, motivo, estado);
        controller.actualizarCita(cita);
    }

    // Eliminar cita
    private void eliminarCita() {
        System.out.print("Ingrese ID de la cita a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.eliminarCita(id);
    }

    // Métodos de apoyo usados por el controller
    public void mostrarCitas(List<Cita> lista) {
        lista.forEach(c -> System.out.println(
            "ID: " + c.getId() +
            " | Fecha: " + c.getFechaHora() +
            " | MascotaID: " + c.getMascotaId() +
            " | Motivo: " + c.getMotivo() +
            " | Estado: " + c.getEstadoId()
        ));
    }

    public void mostrarCita(Cita cita) {
        System.out.println("\n--- Detalles de la Cita ---");
        System.out.println("ID: " + cita.getId());
        System.out.println("Fecha: " + cita.getFechaHora());
        System.out.println("MascotaID: " + cita.getMascotaId());
        System.out.println("Motivo: " + cita.getMotivo());
        System.out.println("Estado: " + cita.getEstadoId());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
