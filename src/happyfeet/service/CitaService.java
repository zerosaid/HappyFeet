/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.service;

import happyfeet.Model.Cita;
import happyfeet.Repository.CitaDAO;
import happyfeet.Repository.CitaEstadoDAO;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public class CitaService {

    private final CitaDAO citaDAO;
    private final CitaEstadoDAO estadoDAO;

    public CitaService() {
        this.citaDAO = new CitaDAO();
        this.estadoDAO = new CitaEstadoDAO();
    }

    // ================== CREAR ==================
    public boolean registrarCita(Cita cita) {
        // 1. Validar que la fecha/hora no esté en el pasado
        if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            System.out.println("❌ La fecha y hora de la cita no pueden estar en el pasado.");
            return false;
        }

        // 2. Validar que no exista otra cita para la misma mascota en el mismo horario
        List<Cita> citasExistentes = citaDAO.obtenerTodas();
        for (Cita c : citasExistentes) {
            if (c.getMascotaId() == cita.getMascotaId() &&
                c.getFechaHora().equals(cita.getFechaHora())) {
                System.out.println("❌ La mascota ya tiene una cita en esa fecha y hora.");
                return false;
            }
        }

        // 3. Validar que el estado de la cita exista en la tabla cita_estados
        if (estadoDAO.obtenerPorId(cita.getEstadoId()) == null) {
            System.out.println("❌ El estado de la cita no es válido.");
            return false;
        }

        // 4. Validar que el motivo no esté vacío
        if (cita.getMotivo() == null || cita.getMotivo().isBlank()) {
            System.out.println("❌ El motivo de la cita es obligatorio.");
            return false;
        }

        // ✅ Si pasa todas las validaciones, registrar
        return citaDAO.agregar(cita);
    }

    // ================== LISTAR ==================
    public List<Cita> obtenerTodas() {
        return citaDAO.obtenerTodas();
    }

    // ================== BUSCAR ==================
    public Cita obtenerPorId(int id) {
        return citaDAO.obtenerPorId(id);
    }

    // ================== ACTUALIZAR ==================
    public boolean actualizarCita(Cita cita) {
        // Validaciones similares a registrar
        if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            System.out.println("❌ La fecha y hora de la cita no pueden estar en el pasado.");
            return false;
        }

        if (estadoDAO.obtenerPorId(cita.getEstadoId()) == null) {
            System.out.println("❌ El estado de la cita no es válido.");
            return false;
        }

        if (cita.getMotivo() == null || cita.getMotivo().isBlank()) {
            System.out.println("❌ El motivo de la cita es obligatorio.");
            return false;
        }

        return citaDAO.actualizar(cita);
    }

    // ================== ELIMINAR ==================
    public boolean eliminarCita(int id) {
        return citaDAO.eliminar(id);
    }
}

