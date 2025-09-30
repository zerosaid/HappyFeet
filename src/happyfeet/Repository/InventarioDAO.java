package happyfeet.Repository;

import happyfeet.Model.Factura;
import happyfeet.Model.Producto;
import happyfeet.util.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    // ✅ Inserta un producto en inventario
    public boolean insertarProducto(Producto p) {
        String sql = "INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, producto_subcategoria_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNombre());
            stmt.setInt(2, p.getProductoTipoId());
            stmt.setString(3, p.getDescripcion());
            stmt.setString(4, p.getFabricante());
            stmt.setString(5, p.getLote());
            stmt.setInt(6, p.getCantidadStock());
            stmt.setInt(7, p.getStockMinimo());

            if (p.getFechaVencimiento() != null) {
                stmt.setDate(8, new java.sql.Date(p.getFechaVencimiento().getTime()));
            } else {
                stmt.setNull(8, Types.DATE);
            }

            stmt.setDouble(9, p.getPrecioVenta());
            stmt.setInt(10, p.getProductoSubcategoriaId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    // ✅ Actualiza datos básicos de inventario
    public boolean actualizarProducto(Producto p) {
        String sql = "UPDATE inventario SET cantidad_stock = ?, stock_minimo = ?, fecha_vencimiento = ? WHERE id = ?";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getCantidadStock());
            stmt.setInt(2, p.getStockMinimo());

            if (p.getFechaVencimiento() != null) {
                stmt.setDate(3, new java.sql.Date(p.getFechaVencimiento().getTime()));
            } else {
                stmt.setNull(3, Types.DATE);
            }

            stmt.setInt(4, p.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // ✅ Productos por proveedor
    public List<Producto> obtenerProductosPorProveedor(int idProveedor) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE proveedor_id = ?";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProveedor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener productos por proveedor: " + e.getMessage());
        }
        return productos;
    }

    // ✅ Productos con stock bajo
    public List<Producto> obtenerProductosConStockBajo() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE cantidad_stock < stock_minimo";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener productos con stock bajo: " + e.getMessage());
        }
        return productos;
    }

    // ✅ Productos por vencer en X días
    public List<Producto> obtenerProductosPorVencer(int dias) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE fecha_vencimiento <= DATE_ADD(CURDATE(), INTERVAL ? DAY)";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dias);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener productos por vencer: " + e.getMessage());
        }
        return productos;
    }

    // ✅ Mapear ResultSet a Producto
    private static Producto mapearProducto(ResultSet rs) throws SQLException {
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

    // ✅ Obtener todos los productos
    public static List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener todos los productos: " + e.getMessage());
        }

        return productos;
    }

    // ✅ Obtener producto por ID
    public Producto obtenerProductoPorId(int productoId) {
        Producto producto = null;
        String sql = "SELECT * FROM inventario WHERE id = ?";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = mapearProducto(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener producto por ID: " + e.getMessage());
        }
        return producto;
    }

    // ⚡ Aquí dejaría solo la versión con `Factura` para insertar
    public int insertarFactura(Factura factura) {
        String sql = "INSERT INTO facturas (dueno_id, fecha_emision, total) VALUES (?, ?, ?)";

        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, factura.getDuenoId());
            stmt.setDate(2, new java.sql.Date(factura.getFechaEmision().getTime()));
            stmt.setDouble(3, factura.getTotal());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar factura: " + e.getMessage());
        }
        return -1;
    }
}
