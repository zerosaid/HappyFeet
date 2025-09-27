/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.view;

import happyfeet.Model.Especie;
import happyfeet.Model.Mascota;
import happyfeet.Model.Raza;
import happyfeet.controller.EspecieController;
import happyfeet.controller.MascotaController;
import happyfeet.controller.RazaController;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Prog. Junior Daniel
 */
public class MascotaView {

    private final MascotaController controller;
    private final EspecieController especieController;
    private final RazaController razaController;
    private final Scanner scanner;

    public MascotaView() {
        this.controller = new MascotaController();
        this.especieController = new EspecieController();
        this.razaController = new RazaController();
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

        try {
            // Pedir dueño
            System.out.print("ID del dueño: ");
            int duenoId = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            // Pedir nombre
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("❌ El nombre no puede estar vacío.");
                return;
            }

            // 1. Mostrar especies
            List<Especie> especies = especieController.listarEspecies();
            if (especies == null || especies.isEmpty()) {
                System.out.println("❌ No hay especies registradas. Registre una especie primero.");
                return;
            }

            System.out.println("\nEspecies disponibles:");
            especies.forEach(e -> System.out.println(e.getId() + ". " + e.getNombre()));

            System.out.print("Seleccione el ID de la especie: ");
            int especieId = scanner.nextInt();
            scanner.nextLine();

            Especie especieSeleccionada = especies.stream()
                    .filter(e -> e.getId() == especieId)
                    .findFirst()
                    .orElse(null);

            if (especieSeleccionada == null) {
                System.out.println("❌ Especie inválida.");
                return;
            }

            // 2. Mostrar razas de esa especie
            List<Raza> razas = razaController.listarPorEspecie(especieId);
            if (razas == null || razas.isEmpty()) {
                System.out.println("❌ No hay razas registradas para esta especie.");
                return;
            }

            System.out.println("\nRazas disponibles para " + especieSeleccionada.getNombre() + ":");
            razas.forEach(r -> System.out.println(r.getId() + ". " + r.getNombre()));

            System.out.print("Seleccione el ID de la raza: ");
            int razaId = scanner.nextInt();
            scanner.nextLine();

            Raza razaSeleccionada = razas.stream()
                    .filter(r -> r.getId() == razaId)
                    .findFirst()
                    .orElse(null);

            if (razaSeleccionada == null) {
                System.out.println("❌ Raza inválida.");
                return;
            }

            // Datos adicionales
            System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
            String fechaNacimiento = scanner.nextLine().trim();

            System.out.print("Sexo (Macho/Hembra): ");
            String sexo = scanner.nextLine().trim();

            if (!(sexo.equalsIgnoreCase("Macho") || sexo.equalsIgnoreCase("Hembra"))) {
                System.out.println("❌ Sexo inválido. Debe ser 'Macho' o 'Hembra'.");
                return;
            }

            System.out.print("URL de la foto: ");
            String urlFoto = scanner.nextLine().trim();

            // Crear objeto mascota
            Mascota mascota = new Mascota(
                    0, // id autoincremental
                    duenoId,
                    nombre,
                    razaId,
                    fechaNacimiento,
                    sexo,
                    urlFoto
            );

            // Guardar
            boolean exito = controller.agregarMascota(mascota);
            System.out.println(exito ? "✅ Mascota registrada con éxito." : "❌ Error al registrar la mascota.");

        } catch (InputMismatchException e) {
            System.out.println("⚠️ Entrada inválida. Asegúrese de ingresar números donde corresponde.");
            scanner.nextLine(); // limpiar buffer para evitar bucles infinitos
        }
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
            System.out.println(mascota);
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
