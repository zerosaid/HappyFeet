package Happy_Feet.bd.model;

public class InventarioInsertar {
    private final InventarioDAO dao = new InventarioDAO();

    public boolean insertarProducto(Producto producto) {
        return dao.insertarProducto(producto);
    }
}