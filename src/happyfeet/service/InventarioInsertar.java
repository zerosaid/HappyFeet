/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.service;

import happyfeet.Model.Producto;
import happyfeet.Repository.InventarioDAO;

/**
 *
 * @author Prog. Junior Daniel
 */
public class InventarioInsertar {
    private final InventarioDAO dao = new InventarioDAO();

    public boolean insertarProducto(Producto producto) {
        return dao.insertarProducto(producto);
    }
}

