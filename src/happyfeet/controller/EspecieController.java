/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

import happyfeet.Model.Especie;
import happyfeet.Repository.EspecieDAO;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public class EspecieController {

    private final EspecieDAO especieDAO;

    public EspecieController() {
        this.especieDAO = new EspecieDAO();
    }

    public List<Especie> listarEspecies() {
        try {
            return especieDAO.listar();
        } catch (Exception e) {
            System.out.println("❌ Error al listar especies: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    public boolean agregarEspecie(Especie especie) {
        try {
            return especieDAO.agregar(especie);
        } catch (Exception e) {
            System.out.println("❌ Error al agregar especie: " + e.getMessage());
            return false;
        }
    }

    public Especie buscarPorId(int id) {
        try {
            return especieDAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println("❌ Error al buscar especie: " + e.getMessage());
            return null;
        }
    }

    public boolean eliminarEspecie(int id) {
        try {
            return especieDAO.eliminar(id);
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar especie: " + e.getMessage());
            return false;
        }
    }
}
