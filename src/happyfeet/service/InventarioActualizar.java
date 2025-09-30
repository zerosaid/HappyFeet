/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.service;

import happyfeet.Model.Producto;
import happyfeet.Repository.InventarioDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */

public class InventarioActualizar {

    private final InventarioDAO dao = new InventarioDAO();

    // Obtener productos por proveedor
    public List<Producto> obtenerProductosPorProveedor(int idProveedor) {
        return dao.obtenerProductosPorProveedor(idProveedor);
    }

    // Actualizar inventario de un producto
    public boolean actualizarInventario(int idProducto, int nuevoStock, int stockMinimo, Date fechaVencimiento) {
        Producto p = new Producto();
        p.setId(idProducto);
        p.setCantidadStock(nuevoStock);
        p.setStockMinimo(stockMinimo);
        p.setFechaVencimiento(fechaVencimiento);
        return dao.actualizarProducto(p);
    }
}
