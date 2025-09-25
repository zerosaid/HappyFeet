/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

import happyfeet.Model.Mascota;
import happyfeet.Repository.MascotaDAO;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public class MascotaController {
    private final MascotaDAO mascotaDAO;

    public MascotaController() {
        this.mascotaDAO = new MascotaDAO();
    }

    // Crear
    public boolean agregarMascota(Mascota mascota) {
        return mascotaDAO.agregar(mascota);
    }

    // Leer todas
    public List<Mascota> obtenerMascotas() {
        return mascotaDAO.obtenerTodas();
    }

    // Leer por ID
    public Mascota obtenerMascotaPorId(int id) {
        return mascotaDAO.obtenerPorId(id);
    }

    // Actualizar
    public boolean actualizarMascota(Mascota mascota) {
        return mascotaDAO.actualizar(mascota);
    }

    // Eliminar
    public boolean eliminarMascota(int id) {
        return mascotaDAO.eliminar(id);
    }
}