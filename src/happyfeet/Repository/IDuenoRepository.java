/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

import happyfeet.Model.Dueno;
import java.util.List;
/**
 *
 * @author Prog. Junior Daniel
 */

public interface IDuenoRepository {
    boolean agregar(Dueno dueno);
    Dueno buscarPorId(int id);
    List<Dueno> listar();
    boolean actualizar(Dueno dueno);
    boolean eliminar(int id);

    public boolean registrar(Dueno nuevo);
}
