/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package happyfeet.Repository;

import happyfeet.Model.HistorialMedico;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public interface IHistorialMedicoRepository {

    // Crear un registro en historial médico
    void crear(HistorialMedico historial);

    // Obtener todos los historiales médicos
    List<HistorialMedico> listarTodos();

    // Obtener historial médico por ID
    HistorialMedico obtenerPorId(int idHistorial);

    // Actualizar un historial médico existente
    void actualizar(HistorialMedico historial);

    // Eliminar un historial médico por ID
    void eliminar(int idHistorial);
}