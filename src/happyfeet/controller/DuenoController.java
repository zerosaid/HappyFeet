/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

import happyfeet.Model.Dueno;
import happyfeet.Repository.DuenoDAO;
import happyfeet.view.DuenoView;
import java.util.List;

public class DuenoController {

    private final DuenoView view;
    private final DuenoDAO duenoDAO;

    // Constructor: inyecta la vista y crea el DAO directamente
    public DuenoController(DuenoView view) {
        this.view = view;
        this.duenoDAO = new DuenoDAO();
    }

    // Crear nuevo dueño
    public void crearDueno(Dueno dueno) {
        duenoDAO.agregar(dueno);
        view.mostrarMensaje("✅ Dueño agregado correctamente: " + dueno.getNombreCompleto());
    }

    // Listar todos los dueños
    public void listarDuenos() {
        List<Dueno> lista = duenoDAO.listarTodos();
        if (lista.isEmpty()) {
            view.mostrarMensaje("⚠ No hay dueños registrados.");
        } else {
            view.mostrarDuenos(lista);
        }
    }

    // Buscar dueño por ID
    public void buscarDueno(int id) {
        Dueno dueno = duenoDAO.obtenerPorId(id);
        if (dueno != null) {
            view.mostrarDueno(dueno);
        } else {
            view.mostrarMensaje("⚠ No se encontró dueño con ID: " + id);
        }
    }

    // Actualizar dueño
    public void actualizarDueno(Dueno dueno) {
        duenoDAO.actualizar(dueno);
        view.mostrarMensaje("✅ Dueño actualizado: " + dueno.getNombreCompleto());
    }

    // Eliminar dueño
    public void eliminarDueno(int id) {
        duenoDAO.eliminar(id);
        view.mostrarMensaje("✅ Dueño eliminado con ID: " + id);
    }
}
