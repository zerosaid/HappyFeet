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
import happyfeet.Repository.ICitaRepository;
import happyfeet.Repository.CitaDAO;
import happyfeet.view.CitaView;
import java.util.List;

public class CitaController {

    private final CitaView view;
    private final ICitaRepository repository;

    public CitaController(CitaView view) {
        this.view = view;
        this.repository = new CitaDAO();
        
    }

    // Crear una nueva cita
    public void crearCita(Cita cita) {
        if (repository.agregar(cita)) {
            view.mostrarMensaje("✅ Cita registrada exitosamente.");
        } else {
            view.mostrarMensaje("❌ Error al registrar la cita.");
        }
    }

    // Listar todas las citas
    public void listarCitas() {
        List<Cita> citas = repository.obtenerTodas();
        if (citas.isEmpty()) {
            view.mostrarMensaje("⚠ No hay citas registradas.");
        } else {
            view.mostrarCitas(citas);
        }
    }

    // Buscar cita por ID
    public void buscarCita(int id) {
        Cita cita = repository.obtenerPorId(id);
        if (cita != null) {
            view.mostrarCita(cita);
        } else {
            view.mostrarMensaje("⚠ No se encontró una cita con ID " + id);
        }
    }

    // Actualizar cita
    public void actualizarCita(Cita cita) {
        if (repository.actualizar(cita)) {
            view.mostrarMensaje("✅ Cita actualizada correctamente.");
        } else {
            view.mostrarMensaje("❌ No se pudo actualizar la cita.");
        }
    }

    // Eliminar cita
    public void eliminarCita(int id) {
        if (repository.eliminar(id)) {
            view.mostrarMensaje("✅ Cita eliminada correctamente.");
        } else {
            view.mostrarMensaje("❌ No se pudo eliminar la cita.");
        }
    }
}
