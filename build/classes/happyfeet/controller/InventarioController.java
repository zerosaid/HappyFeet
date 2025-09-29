package Happy_Feet.bd.controller;

import Happy_Feet.bd.model.InventarioActualizar;
import Happy_Feet.bd.model.InventarioInsertar;
import Happy_Feet.bd.model.Producto;

import java.util.Date;

public class InventarioController {
    private final InventarioInsertar insertar = new InventarioInsertar();
    private final InventarioActualizar actualizar = new InventarioActualizar();

    public boolean registrarProducto(Producto producto) {
        return insertar.insertarProducto(producto);
    }

    public boolean esProductoVencido(Producto producto) {
        if (producto.getFechaVencimiento() != null && producto.getFechaVencimiento().before(new Date())) {
            System.out.println("❌ El producto " + producto.getNombre() + " está vencido y no puede ser usado ni vendido.");
            return true;  // Producto vencido
        }
        return false;  // Producto no vencido
    }

    public boolean actualizarProducto(Producto producto) {
        if (esProductoVencido(producto)) {
            return false;  // Prohíbe la actualización si el producto está vencido
        }
        return actualizar.actualizarInventario(
                producto.getId(),
                producto.getCantidadStock(),
                producto.getStockMinimo(),
                producto.getFechaVencimiento()
        );
    }
}
