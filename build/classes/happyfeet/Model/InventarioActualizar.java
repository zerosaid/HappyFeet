package Happy_Feet.bd.model;

import java.util.Date;
import java.util.List;

public class InventarioActualizar {
    private final InventarioDAO dao = new InventarioDAO();

    public List<Producto> obtenerProductosPorProveedor(int idProveedor) {
        return dao.obtenerProductosPorProveedor(idProveedor);
    }

    public boolean actualizarInventario(int idProducto, int nuevoStock, int stockMinimo, Date fechaVencimiento) {
        Producto p = new Producto();
        p.setId(idProducto);
        p.setCantidadStock(nuevoStock);
        p.setStockMinimo(stockMinimo);
        p.setFechaVencimiento(fechaVencimiento);
        return dao.actualizarProducto(p);
    }
}
