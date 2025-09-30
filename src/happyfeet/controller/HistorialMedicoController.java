/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

import happyfeet.Model.HistorialMedico;
import happyfeet.Repository.HistorialMedicoDAO;
import happyfeet.view.HistorialMedicoView;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public class HistorialMedicoController {

    private final HistorialMedicoDAO historialMedicoDAO;
    private final HistorialMedicoView view;

    public HistorialMedicoController(HistorialMedicoView view) {
        this.historialMedicoDAO = new HistorialMedicoDAO();
        this.view = view;
    }

    // Registrar historial médico
    public void registrarHistorial(int mascotaId, String fechaEvento, int eventoTipoId,
                                   String descripcion, String diagnostico, String tratamiento) {
        try {
            HistorialMedico historial = new HistorialMedico(
                    0, // ID autoincremental en la BD
                    mascotaId,
                    LocalDate.parse(fechaEvento),
                    eventoTipoId,
                    descripcion,
                    diagnostico,
                    tratamiento
            );

            historialMedicoDAO.crear(historial);
            view.mostrarMensaje("✅ Historial médico registrado correctamente.");

        } catch (Exception e) {
            view.mostrarMensaje("❌ Error en los datos ingresados: " + e.getMessage());
        }
    }

    // Listar todos los historiales
    public void listarHistoriales() {
        List<HistorialMedico> lista = historialMedicoDAO.listarTodos();
        view.mostrarHistoriales(lista);
    }

    // Buscar historial por ID
    public void buscarHistorialPorId(int id) {
        HistorialMedico historial = historialMedicoDAO.obtenerPorId(id);
        if (historial != null) {
            view.mostrarHistorial(historial);
        } else {
            view.mostrarMensaje("⚠️ No se encontró el historial con ID " + id);
        }
    }

    // Listar historiales por mascota
    public void listarHistorialesPorMascota(int mascotaId) {
        List<HistorialMedico> lista = historialMedicoDAO.listarPorMascota(mascotaId);
        if (lista.isEmpty()) {
            view.mostrarMensaje("⚠️ No hay historiales para la mascota con ID " + mascotaId);
        } else {
            view.mostrarHistoriales(lista);
        }
    }

    // Actualizar historial médico
    public void actualizarHistorial(int id, String descripcion, String diagnostico, String tratamiento) {
        HistorialMedico historial = historialMedicoDAO.obtenerPorId(id);

        if (historial == null) {
            view.mostrarMensaje("⚠️ No se encontró el historial con ID " + id);
            return;
        }

        historial.setDescripcion(descripcion);
        historial.setDiagnostico(diagnostico);
        historial.setTratamientoRecomendado(tratamiento);

        historialMedicoDAO.actualizar(historial);
        view.mostrarMensaje("✅ Historial actualizado correctamente.");
    }

    // Eliminar historial médico
    public void eliminarHistorial(int id) {
        HistorialMedico historial = historialMedicoDAO.obtenerPorId(id);

        if (historial == null) {
            view.mostrarMensaje("⚠️ No se encontró el historial con ID " + id);
            return;
        }

        historialMedicoDAO.eliminar(id);
        view.mostrarMensaje("✅ Historial eliminado correctamente.");
    }
}