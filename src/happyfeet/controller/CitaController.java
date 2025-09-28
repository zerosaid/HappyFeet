/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

/**
 *
 * @author Prog. Junior Daniel
 */

import happyfeet.Model.Cita;
import happyfeet.service.CitaService;
import happyfeet.view.CitaView;
import java.util.List;

public class CitaController {

    private final CitaView view;
    private final CitaService service;

    public CitaController(CitaView view) {
        this.view = view;
        this.service = new CitaService(); // el service ya maneja los DAO
    }

    // Crear una nueva cita con validaciones
    public void crearCita(Cita cita) {
        if (service.registrarCita(cita)) {
            view.mostrarMensaje("✅ Cita registrada exitosamente.");
        } else {
            view.mostrarMensaje("❌ Error al registrar la cita. Revise las validaciones.");
        }
    }

    // Listar todas las citas
    public void listarCitas() {
        List<Cita> citas = service.obtenerTodas();
        if (citas.isEmpty()) {
            view.mostrarMensaje("⚠ No hay citas registradas.");
        } else {
            view.mostrarCitas(citas);
        }
    }

    // Buscar cita por ID
    public void buscarCita(int id) {
        Cita cita = service.obtenerPorId(id);
        if (cita != null) {
            view.mostrarCita(cita);
        } else {
            view.mostrarMensaje("⚠ No se encontró una cita con ID " + id);
        }
    }

    // Actualizar cita (también pasa por las validaciones del service)
    public void actualizarCita(Cita cita) {
        if (service.actualizarCita(cita)) {
            view.mostrarMensaje("✅ Cita actualizada correctamente.");
        } else {
            view.mostrarMensaje("❌ No se pudo actualizar la cita. Revise las validaciones.");
        }
    }

    // Eliminar cita
    public void eliminarCita(int id) {
        if (service.eliminarCita(id)) {
            view.mostrarMensaje("✅ Cita eliminada correctamente.");
        } else {
            view.mostrarMensaje("❌ No se pudo eliminar la cita.");
        }
    }
}
