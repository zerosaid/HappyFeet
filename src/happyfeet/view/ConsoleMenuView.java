/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.view;


import happyfeet.controller.MenuController;
import java.util.Scanner;
/**
 *
 * @author Prog. Junior Daniel
 */
public class ConsoleMenuView {

    private final MenuController controller;
    private final Scanner scanner;

    public ConsoleMenuView() {
        this.controller = new MenuController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        String opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestión de Dueños");
            System.out.println("2. Gestión de Mascotas");
            System.out.println("3. Gestión de Razas");
            System.out.println("4. Gestión de Citas"); // 👈 nueva opción
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1" -> controller.abrirGestionDuenos();
                case "2" -> controller.abrirGestionMascotas();
                case "3" -> controller.abrirGestionRazas();
                case "4" -> controller.abrirGestionCitas(); // 👈 llamada al controlador de citas
                case "0" -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("❌ Opción inválida. Debe ser un número válido.");
            }
        } while (!opcion.equals("0"));
    }
}
