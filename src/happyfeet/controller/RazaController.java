/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

import happyfeet.Model.Raza;
import happyfeet.Repository.RazaDAO;
import java.util.List;
import java.util.Scanner;

public class RazaController {

    private final Scanner scanner;
    private final RazaDAO razaDAO;

    public RazaController() {
        this.scanner = new Scanner(System.in);
        this.razaDAO = new RazaDAO();
    }

    // Agregar nueva raza
    public void agregarRaza() {
        System.out.print("Ingrese nombre de la raza: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Ingrese ID de la especie a la que pertenece: ");
        int especieId = Integer.parseInt(scanner.nextLine().trim());

        Raza nuevaRaza = new Raza(0, nombre, especieId); // el id lo genera la BD
        if (razaDAO.agregar(nuevaRaza)) {
            System.out.println("✅ Raza agregada correctamente en BD.");
        } else {
            System.out.println("❌ Error al agregar raza.");
        }
    }

    // Listar todas las razas
    public void listarRazas() {
        List<Raza> razas = razaDAO.obtenerTodas();
        if (razas.isEmpty()) {
            System.out.println("⚠️ No hay razas registradas en la BD.");
        } else {
            System.out.println("\n--- LISTA DE RAZAS ---");
            for (Raza r : razas) {
                System.out.println("ID: " + r.getId() +
                        " | Nombre: " + r.getNombre() +
                        " | EspecieID: " + r.getEspecieId());
            }
        }
    }

    // Buscar raza por ID
    public void buscarRazaPorId() {
        System.out.print("Ingrese ID de la raza a buscar: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Raza encontrada = razaDAO.obtenerPorId(id);
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

        Raza encontrada = razaDAO.obtenerPorId(id);
        if (encontrada != null) {
            System.out.print("Ingrese nuevo nombre de la raza: ");
            String nuevoNombre = scanner.nextLine().trim();
            encontrada.setNombre(nuevoNombre);

            if (razaDAO.actualizar(encontrada)) {
                System.out.println("✅ Raza actualizada en BD.");
            } else {
                System.out.println("❌ Error al actualizar raza.");
            }
        } else {
            System.out.println("❌ No se encontró la raza con ID " + id);
        }
    }

    // Eliminar raza
    public void eliminarRaza() {
        System.out.print("Ingrese ID de la raza a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        if (razaDAO.eliminar(id)) {
            System.out.println("✅ Raza eliminada en BD.");
        } else {
            System.out.println("❌ No se encontró la raza con ID " + id);
        }
    }
    
    // Listar razas por especie
    public List<Raza> listarPorEspecie(int especieId) {
        return razaDAO.obtenerPorEspecie(especieId);
    }

}
