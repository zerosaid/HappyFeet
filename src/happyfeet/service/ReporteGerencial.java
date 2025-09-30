/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.service;

import com.sun.jdi.connect.spi.Connection;
import happyfeet.Model.ItemFactura;
import happyfeet.Model.Producto;
import happyfeet.Repository.InventarioDAO;
import happyfeet.util.conexionBD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public class ReporteGerencial {

    private final InventarioDAO dao;

    public ReporteGerencial(InventarioDAO dao) {
        this.dao = dao;
    }

    // ✅ Productos próximos a vencer (15 días)
    public List<Producto> obtenerProductosPorVencer() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE fecha_vencimiento <= DATE_ADD(CURDATE(), INTERVAL 15 DAY)";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener productos por vencer: " + e.getMessage());
        }

        return productos;
    }

    // ⚠️ Esto requiere que implementes en InventarioDAO un método que consulte facturas o ventas
    public void reporteServiciosMasSolicitados() {
        List<ItemFactura> servicios = dao.obtenerServiciosMasSolicitados(); // <- debes crear este método en tu DAO
        System.out.println("\n📊 Servicios más solicitados:");
        for (ItemFactura servicio : servicios) {
            System.out.println(servicio.getDescripcion() + " - Cantidad: " + servicio.getCantidad());
        }
    }

    // ✅ Estado del inventario (productos a vencer + stock bajo)
    public void reporteEstadoInventario() {
        List<Producto> productosPorVencer = obtenerProductosPorVencer();

        if (!productosPorVencer.isEmpty()) {
            System.out.println("\n📊 Productos próximos a vencer:");
            for (Producto producto : productosPorVencer) {
                System.out.println("- " + producto.getNombre() + " | Vence: " + producto.getFechaVencimiento());
            }
        } else {
            System.out.println("✅ No hay productos próximos a vencer.");
        }

        List<Producto> productosConStockBajo = dao.obtenerProductosConStockBajo();

        if (!productosConStockBajo.isEmpty()) {
            System.out.println("\n📊 Productos con stock bajo:");
            for (Producto producto : productosConStockBajo) {
                System.out.println("- " + producto.getNombre() + " | Stock actual: " +
                        producto.getCantidadStock() + " | Mínimo: " + producto.getStockMinimo());
            }
        } else {
            System.out.println("✅ No hay productos con stock bajo.");
        }
    }

    // ✅ Mapear ResultSet a Producto
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        return new Producto(
                rs.getInt("id"),
                rs.getString("nombre_producto"),
                rs.getInt("producto_tipo_id"),
                rs.getString("descripcion"),
                rs.getString("fabricante"),
                rs.getString("lote"),
                rs.getInt("cantidad_stock"),
                rs.getInt("stock_minimo"),
                rs.getDate("fecha_vencimiento"),
                rs.getDouble("precio_venta"),
                rs.getInt("producto_subcategoria_id")
        );
    }
}
