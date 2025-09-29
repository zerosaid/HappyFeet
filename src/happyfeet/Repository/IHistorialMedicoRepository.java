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
    boolean agregar(HistorialMedico historial);

    // Obtener todos los historiales médicos
    List<HistorialMedico> obtenerTodos();

    // Obtener historial médico por ID
    HistorialMedico obtenerPorId(int id);

    // Actualizar un historial médico existente
    boolean actualizar(HistorialMedico historial);

    // Eliminar un historial médico por ID
    boolean eliminar(int id);
}
