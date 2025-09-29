/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.view;

import happyfeet.controller.HistorialMedicoController;
import happyfeet.Model.HistorialMedico;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Prog. Junior Daniel
 */
public class HistorialMedicoView {

    private HistorialMedicoController controller;
    private final Scanner scanner;

    public HistorialMedicoView() {
        this.scanner = new Scanner(System.in);
    }

    // Conecta la vista con el controller
    public void setController(HistorialMedicoController controller) {
        this.controller = controller;
    }

    // Menú principal de gestión
    public void mostrarMenu() {
        String opcion;
        do {
            System.out.println("\n===== Gestión de Historial Médico =====");
            System.out.println("1. Registrar historial médico");
            System.out.println("2. Ver todos los historiales médicos");
            System.out.println("3. Buscar historial médico por ID");
            System.out.println("4. Actualizar historial médico");
            System.out.println("5. Eliminar historial médico");
            System.out.println("6. Listar historiales por mascota");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> registrarHistorial();
                case "2" -> controller.listarHistoriales();
                case "3" -> buscarHistorialPorId();
                case "4" -> actualizarHistorial();
                case "5" -> eliminarHistorial();
                case "6" -> listarPorMascota();
                case "0" -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("❌ Opción inválida, intente de nuevo.");
            }
        } while (!opcion.equals("0"));
    }

    // ======================== Métodos auxiliares ========================

    private void registrarHistorial() {
        try {
            System.out.print("ID Mascota: ");
            int mascotaId = Integer.parseInt(scanner.nextLine());

            System.out.print("Fecha del evento (YYYY-MM-DD): ");
            String fechaEvento = scanner.nextLine();

            System.out.print("ID Tipo de evento: ");
            int eventoTipoId = Integer.parseInt(scanner.nextLine());

            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Diagnóstico: ");
            String diagnostico = scanner.nextLine();

            System.out.print("Tratamiento recomendado: ");
            String tratamiento = scanner.nextLine();

            controller.registrarHistorial(mascotaId, fechaEvento, eventoTipoId, descripcion, diagnostico, tratamiento);
        } catch (Exception e) {
            System.out.println("❌ Error al registrar historial médico: " + e.getMessage());
        }
    }

    private void buscarHistorialPorId() {
        System.out.print("Ingrese el ID del historial: ");
        int id = Integer.parseInt(scanner.nextLine());
        controller.buscarHistorialPorId(id);
    }

    private void actualizarHistorial() {
        try {
            System.out.print("ID del historial a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Nueva descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Nuevo diagnóstico: ");
            String diagnostico = scanner.nextLine();

            System.out.print("Nuevo tratamiento recomendado: ");
            String tratamiento = scanner.nextLine();

            controller.actualizarHistorial(id, descripcion, diagnostico, tratamiento);
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar historial: " + e.getMessage());
        }
    }

    private void eliminarHistorial() {
        System.out.print("Ingrese el ID del historial a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        controller.eliminarHistorial(id);
    }

    private void listarPorMascota() {
        System.out.print("Ingrese el ID de la mascota: ");
        int mascotaId = Integer.parseInt(scanner.nextLine());
        controller.listarHistorialesPorMascota(mascotaId);
    }

    // ======================== Métodos de presentación ========================

    // Mostrar lista de historiales
    public void mostrarHistoriales(List<HistorialMedico> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("⚠️ No se encontraron historiales médicos.");
        } else {
            System.out.println("\n📋 Listado de historiales médicos:");
            for (HistorialMedico h : lista) {
                System.out.println(h);
            }
        }
    }

    // Mostrar un historial específico
    public void mostrarHistorial(HistorialMedico historial) {
        if (historial == null) {
            System.out.println("⚠️ No se encontró el historial solicitado.");
        } else {
            System.out.println("\n✅ Historial encontrado:");
            System.out.println(historial);
        }
    }

    // Mostrar mensajes genéricos (usado por el controller)
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
