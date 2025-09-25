/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.view;

import happyfeet.Model.Dueno;
import happyfeet.controller.DuenoController;
import java.util.Scanner;

public class DuenoView {
    private final Scanner scanner = new Scanner(System.in);
    private DuenoController controller;

    public void setController(DuenoController controller) {
        this.controller = controller;
    }

    // Mostrar menú de dueños
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== GESTIÓN DE DUEÑOS =====");
            System.out.println("1. Registrar dueño");
            System.out.println("2. Listar dueños");
            System.out.println("3. Buscar dueño por ID");
            System.out.println("4. Actualizar dueño");
            System.out.println("5. Eliminar dueño");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> registrarDueno();
                case 2 -> controller.listarDuenos();
                case 3 -> buscarDueno();
                case 4 -> actualizarDueno();
                case 5 -> eliminarDueno();
                case 0 -> System.out.println("↩ Volviendo al menú principal...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Métodos auxiliares
    private void registrarDueno() {
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Documento de identidad: ");
        String doc = scanner.nextLine();
        System.out.print("Dirección: ");
        String dir = scanner.nextLine();
        System.out.print("Teléfono: ");
        String tel = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Dueno dueno = new Dueno(0, nombre, doc, dir, tel, email);
        controller.crearDueno(dueno);
    }

    private void buscarDueno() {
        System.out.print("Ingrese ID del dueño: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.buscarDueno(id);
    }

    private void actualizarDueno() {
        System.out.print("ID del dueño a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo documento: ");
        String doc = scanner.nextLine();
        System.out.print("Nueva dirección: ");
        String dir = scanner.nextLine();
        System.out.print("Nuevo teléfono: ");
        String tel = scanner.nextLine();
        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();

        Dueno dueno = new Dueno(id, nombre, doc, dir, tel, email);
        controller.actualizarDueno(dueno);
    }

    private void eliminarDueno() {
        System.out.print("Ingrese ID del dueño a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.eliminarDueno(id);
    }

    // Métodos que usa el controller
    public void mostrarDuenos(java.util.List<Dueno> lista) {
        lista.forEach(d -> System.out.println(
            d.getId() + " | " + d.getNombreCompleto() + " | " + d.getDocumentoIdentidad()
        ));
    }

    public void mostrarDueno(Dueno dueno) {
        System.out.println("ID: " + dueno.getId());
        System.out.println("Nombre: " + dueno.getNombreCompleto());
        System.out.println("Documento: " + dueno.getDocumentoIdentidad());
        System.out.println("Dirección: " + dueno.getDireccion());
        System.out.println("Teléfono: " + dueno.getTelefono());
        System.out.println("Email: " + dueno.getEmail());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
