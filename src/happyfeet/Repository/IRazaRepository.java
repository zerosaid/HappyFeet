/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

/**
 *
 * @author Prog. Junior Daniel
 */
import happyfeet.Model.Raza;
import java.util.List;

public interface IRazaRepository {
    boolean agregar(Raza raza);
    List<Raza> obtenerTodas();
    Raza obtenerPorId(int id);
    boolean actualizar(Raza raza);
    boolean eliminar(int id);
}