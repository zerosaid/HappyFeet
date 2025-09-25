/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

/**
 *
 * @author Prog. Junior Daniel
 */
package happyfeet.controller;

import happyfeet.model.Raza;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RazaController {

    private final List<Raza> razas;
    private final Scanner scanner;

    public RazaController() {
        this.razas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Agregar nueva raza
    public void agregarRaza() {
        System.out.print("Ingrese ID de la raza: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Ingrese nombre de la raza: ");
        String nombre = scanner.nextLine().trim();

        Raza nuevaRaza = new Raza(id, nombre);
        razas.add(nuevaRaza);
        System.out.println("✅ Raza agregada correctamente.");
    }

    // Listar todas las razas
    public void listarRazas() {
        if (razas.isEmpty()) {
            System.out.println("⚠️ No hay razas registradas.");
        } else {
            System.out.println("\n--- LISTA DE RAZAS ---");
            for (Raza r : razas) {
                System.out.println("ID: " + r.getId() + " | Nombre: " + r.getNombre());
            }
        }
    }

    // Buscar raza por ID
    public void buscarRazaPorId() {
        System.out.print("Ingrese ID de la raza a buscar: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Raza encontrada = razas.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
        if (encontrada != null) {
            System.out.println("✅ Raza encontrada: " + encontrada.getNombre());
        } else {
            System.out.println("❌ No se encontró la raza con ID " + id);
        }
    }

    // Actualizar raza
    public void actualizarRaza() {
        System.out.print("Ingrese ID de la raza a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Raza encontrada = razas.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
        if (encontrada != null) {
            System.out.print("Ingrese nuevo nombre de la raza: ");
            String nuevoNombre = scanner.nextLine().trim();
            encontrada.setNombre(nuevoNombre);
            System.out.println("✅ Raza actualizada correctamente.");
        } else {
            System.out.println("❌ No se encontró la raza con ID " + id);
        }
    }

    // Eliminar raza
    public void eliminarRaza() {
        System.out.print("Ingrese ID de la raza a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        boolean removed = razas.removeIf(r -> r.getId() == id);
        if (removed) {
            System.out.println("✅ Raza eliminada correctamente.");
        } else {
            System.out.println("❌ No se encontró la raza con ID " + id);
        }
    }
}
