/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package happyfeet;

import happyfeet.controller.DuenoController;
import happyfeet.view.DuenoView;
import happyfeet.view.MascotaView;
import java.util.Scanner;

/**
 *
 * @author Prog. Junior Daniel
 */
public class HappyFeet {

    public static void main(String[] args) {
        DuenoView duenoView = new DuenoView();

        Scanner scanner = new Scanner(System.in);
        DuenoController duenoController = new DuenoController(duenoView);
        duenoView.setController(duenoController);
        MascotaView mascotaView = new MascotaView();

        int opcion;
        do {
            System.out.println("\n===== SISTEMA HAPPYFEET =====");
            System.out.println("1. Gestionar Dueños");
            System.out.println("2. Gestionar Mascotas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> duenoView.mostrarMenu();
                case 2 -> mascotaView.mostrarMenu();
                case 0 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }
}

