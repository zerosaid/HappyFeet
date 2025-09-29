/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package happyfeet.Repository;

import happyfeet.Model.EventoTipo;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public interface IEventoTipoRepository {
    
    // Crear un nuevo tipo de evento
    boolean agregar(EventoTipo evento);

    // Obtener todos los tipos de evento
    List<EventoTipo> obtenerTodos();

    // Obtener un tipo de evento por ID
    EventoTipo obtenerPorId(int id);

    // Actualizar un tipo de evento existente
    boolean actualizar(EventoTipo evento);

    // Eliminar un tipo de evento por ID
    boolean eliminar(int id);
}

