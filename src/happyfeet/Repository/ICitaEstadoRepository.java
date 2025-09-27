/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package happyfeet.Repository;

import happyfeet.Model.CitaEstado;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public interface ICitaEstadoRepository {
    
    // Crear nuevo estado de cita
    void agregar(CitaEstado citaEstado);
    
    // Obtener un estado de cita por su ID
    CitaEstado obtenerPorId(int id);
    
    // Listar todos los estados de cita
    List<CitaEstado> listarTodos();
    
    // Actualizar un estado existente
    void actualizar(CitaEstado citaEstado);
    
    // Eliminar un estado por ID
    void eliminar(int id);
}
