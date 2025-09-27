/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package happyfeet.Repository;

/**
 *
 * @author Prog. Junior Daniel
 */

import happyfeet.Model.Cita;
import java.util.List;

public interface ICitaRepository {
    boolean agregar(Cita cita);
    List<Cita> obtenerTodas();
    Cita obtenerPorId(int id);
    boolean actualizar(Cita cita);
    boolean eliminar(int id);
}
