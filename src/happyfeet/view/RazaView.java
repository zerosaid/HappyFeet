/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.view;

/**
 *
 * @author Prog. Junior Daniel
 */
import happyfeet.controller.RazaController;
import java.util.Scanner;

public class RazaView {

    private final RazaController controller;
    private final Scanner scanner;

    public RazaView() {
        this.controller = new RazaController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        String opcion;
        do {
            System.out.println("\n===== MENÚ DE RAZAS =====");
            System.out.println("1. Agregar Raza");
            System.out.println("2. Listar Razas");
            System.out.println("3. Buscar Raza por ID");
            System.out.println("4. Actualizar Raza");
            System.out.println("5. Eliminar Raza");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1" -> controller.agregarRaza();
                case "2" -> controller.listarRazas();
                case "3" -> controller.buscarRazaPorId();
                case "4" -> controller.actualizarRaza();
                case "5" -> controller.eliminarRaza();
                case "0" -> System.out.println("🔙 Volviendo al menú principal...");
                default -> System.out.println("❌ Opción inválida. Intente de nuevo.");
            }
        } while (!opcion.equals("0"));
    }
}
