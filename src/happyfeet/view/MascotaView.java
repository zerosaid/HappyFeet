/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.view;

import happyfeet.Model.Mascota;
import happyfeet.controller.MascotaController;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Prog. Junior Daniel
 */
public class MascotaView {

    private final MascotaController controller;
    private final Scanner scanner;

    public MascotaView() {
        this.controller = new MascotaController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== GESTIÓN DE MASCOTAS =====");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Listar mascotas");
            System.out.println("3. Buscar mascota por ID");
            System.out.println("4. Actualizar mascota");
            System.out.println("5. Eliminar mascota");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> registrarMascota();
                case 2 -> listarMascotas();
                case 3 -> buscarMascota();
                case 4 -> actualizarMascota();
                case 5 -> eliminarMascota();
                case 0 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrarMascota() {
        System.out.println("\n--- Registrar Mascota ---");
        System.out.print("ID del dueño: ");
        int duenoId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("ID de raza: ");
        int razaId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Sexo (Macho/Hembra): ");
        String sexo = scanner.nextLine();

        System.out.print("URL de la foto: ");
        String urlFoto = scanner.nextLine();

        Mascota mascota = new Mascota(0, duenoId, nombre, razaId, fechaNacimiento, sexo, urlFoto);
        boolean exito = controller.agregarMascota(mascota);

        System.out.println(exito ? "✅ Mascota registrada con éxito." : "❌ Error al registrar la mascota.");
    }

    private void listarMascotas() {
        System.out.println("\n--- Lista de Mascotas ---");
        List<Mascota> mascotas = controller.obtenerMascotas();
        if (mascotas.isEmpty()) {
            System.out.println("⚠️ No hay mascotas registradas.");
        } else {
            int contador = 1;
            for (Mascota m : mascotas) {
                System.out.println(contador + ". " + m); // Usa el toString() de Mascota
                contador++;
            }
        }
    }

    private void buscarMascota() {
        System.out.println("\n--- Buscar Mascota ---");
        System.out.print("Ingrese el ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Mascota mascota = controller.obtenerMascotaPorId(id);
        if (mascota != null) {
            System.out.println("✅ Mascota encontrada:");
            System.out.println(mascota); // Usa el toString() de Mascota
        } else {
            System.out.println("❌ No se encontró ninguna mascota con ese ID.");
        }
    }

    private void actualizarMascota() {
        System.out.println("\n--- Actualizar Mascota ---");
        System.out.print("Ingrese el ID de la mascota a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Mascota mascota = controller.obtenerMascotaPorId(id);
        if (mascota == null) {
            System.out.println("❌ Mascota no encontrada.");
            return;
        }

        System.out.print("Nuevo nombre (" + mascota.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) mascota.setNombre(nombre);

        System.out.print("Nuevo ID de raza (" + mascota.getRazaId() + "): ");
        String razaStr = scanner.nextLine();
        if (!razaStr.isEmpty()) mascota.setRazaId(Integer.parseInt(razaStr));

        System.out.print("Nueva fecha nacimiento (" + mascota.getFechaNacimiento() + "): ");
        String fechaNacimiento = scanner.nextLine();
        if (!fechaNacimiento.isEmpty()) mascota.setFechaNacimiento(fechaNacimiento);

        System.out.print("Nuevo sexo (" + mascota.getSexo() + "): ");
        String sexo = scanner.nextLine();
        if (!sexo.isEmpty()) mascota.setSexo(sexo);

        System.out.print("Nueva URL foto (" + mascota.getUrlFoto() + "): ");
        String urlFoto = scanner.nextLine();
        if (!urlFoto.isEmpty()) mascota.setUrlFoto(urlFoto);

        boolean exito = controller.actualizarMascota(mascota);
        System.out.println(exito ? "✅ Mascota actualizada con éxito." : "❌ Error al actualizar mascota.");
    }

    private void eliminarMascota() {
        System.out.println("\n--- Eliminar Mascota ---");
        System.out.print("Ingrese el ID de la mascota a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean exito = controller.eliminarMascota(id);
        System.out.println(exito ? "✅ Mascota eliminada con éxito." : "❌ Error al eliminar mascota.");
    }
}
