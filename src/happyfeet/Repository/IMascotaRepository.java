/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

/**
 *
 * @author Prog. Junior Daniel
 */
import happyfeet.Model.Mascota;
import java.util.List;

public interface IMascotaRepository {
    boolean agregar(Mascota mascota);
    List<Mascota> obtenerTodas();
    Mascota obtenerPorId(int id);
    boolean actualizar(Mascota mascota);
    boolean eliminar(int id);
}