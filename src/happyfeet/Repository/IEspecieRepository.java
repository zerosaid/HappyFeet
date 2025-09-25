/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

/**
 *
 * @author Prog. Junior Daniel
 */

import java.util.List;
import happyfeet.Model.Especie;

public interface IEspecieRepository {
    boolean agregar(Especie especie);
    Especie buscarPorId(int id);
    List<Especie> listar();
    boolean actualizar(Especie especie);
    boolean eliminar(int id);
}