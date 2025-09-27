/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package happyfeet.Repository;

/**
 *
 * @author Prog. Junior Daniel
 */
import happyfeet.Model.CitaEstado;
import java.util.List;

public interface ICitaEstadoRepository {
    boolean agregar(CitaEstado estado);
    List<CitaEstado> obtenerTodos();
    CitaEstado obtenerPorId(int id);
    boolean actualizar(CitaEstado estado);
    boolean eliminar(int id);
}

