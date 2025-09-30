/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.service;

import happyfeet.Model.HistorialMedico;
import happyfeet.Repository.IHistorialMedicoRepository;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public class HistorialMedicoService {
    private final IHistorialMedicoRepository historialRepo;

    public HistorialMedicoService(IHistorialMedicoRepository historialRepo) {
        this.historialRepo = historialRepo;
    }

    // Crear un historial médico
    public void registrarHistorial(HistorialMedico historial) {
        historialRepo.crear(historial);
    }

    // Obtener todos los historiales médicos
    public List<HistorialMedico> listarHistoriales() {
        return historialRepo.listarTodos();
    }

    // Buscar un historial por su ID
    public HistorialMedico buscarPorId(int idHistorial) {
        return historialRepo.obtenerPorId(idHistorial);
    }

    // Actualizar un historial
    public void actualizarHistorial(HistorialMedico historial) {
        historialRepo.actualizar(historial);
    }

    // Eliminar un historial
    public void eliminarHistorial(int idHistorial) {
        historialRepo.eliminar(idHistorial);
    }
}
