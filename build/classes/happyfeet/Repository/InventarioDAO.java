package Happy_Feet.bd.model;

import Happy_Feet.bd.view.Conexiondb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    public boolean insertarProducto(Producto p) {
        String sql = "INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, producto_subcategoria_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexiondb.obtenerConexion();
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
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarProducto(Producto p) {
        String sql = "UPDATE inventario SET cantidad_stock = ?, stock_minimo = ?, fecha_vencimiento = ? WHERE id = ?";

        try (Connection conn = Conexiondb.obtenerConexion();
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
            e.printStackTrace();
            return false;
        }
    }

    public List<Producto> obtenerProductosPorProveedor(int idProveedor) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE proveedor_id = ?";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProveedor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public List<Producto> obtenerProductosConStockBajo() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE cantidad_stock < stock_minimo";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public List<Producto> obtenerProductosPorVencer(int dias) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE fecha_vencimiento <= DATE_ADD(CURDATE(), INTERVAL ? DAY)";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dias);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

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

    public static List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public Producto obtenerProductoPorId(int productoId) {
        Producto producto = null;
        String sql = "SELECT * FROM inventario WHERE id = ?";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = mapearProducto(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    public List<ItemFactura> obtenerServiciosMasSolicitados() {
        List<ItemFactura> serviciosMasSolicitados = new ArrayList<>();
        String sql = "SELECT i.servicio_descripcion, SUM(i.cantidad) AS cantidad_total, i.producto_id " +
                "FROM items_factura i " +
                "GROUP BY i.servicio_descripcion, i.producto_id " +
                "ORDER BY cantidad_total DESC";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String descripcion = rs.getString("servicio_descripcion");
                int cantidadTotal = rs.getInt("cantidad_total");
                int productoId = rs.getInt("producto_id");

                Producto producto = obtenerProductoPorId(productoId);
                if (producto != null) {
                    double precioUnitario = producto.getPrecioVenta();
                    ItemFactura item = new ItemFactura(descripcion, cantidadTotal, precioUnitario, producto);
                    serviciosMasSolicitados.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serviciosMasSolicitados;
    }

    public List<ItemFactura> obtenerProductosPorFactura(int idFactura) {
        List<ItemFactura> items = new ArrayList<>();
        String sql = "SELECT i.servicio_descripcion, i.cantidad, i.precio_unitario, i.subtotal, i.producto_id " +
                "FROM items_factura i " +
                "WHERE i.factura_id = ?";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFactura);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String descripcion = rs.getString("servicio_descripcion");
                    int cantidad = rs.getInt("cantidad");
                    double precioUnitario = rs.getDouble("precio_unitario");
                    double subtotal = rs.getDouble("subtotal");
                    int productoId = rs.getInt("producto_id");

                    // Aquí usamos el constructor que recibe productoId
                    ItemFactura item = new ItemFactura(descripcion, cantidad, precioUnitario, subtotal, productoId);
                    items.add(item);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<Producto> obtenerProductosPorVencer() {
        return null;
    }

    public int insertarFactura(int duenoId, Date fechaEmision, double total) {
        String sql = "INSERT INTO facturas (dueno_id, fecha_emision, total) VALUES (?, ?, ?)";
        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, duenoId);
            stmt.setDate(2, new java.sql.Date(fechaEmision.getTime()));
            stmt.setDouble(3, total);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // ✅ ID autogenerado de la factura
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int obtenerUltimaFacturaId() {
        String sql = "SELECT LAST_INSERT_ID()";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public String generarFacturaTexto(int facturaId) {
        StringBuilder sb = new StringBuilder();

        // 🔹 Consulta de la factura con dueno_id
        String sqlFactura = "SELECT f.id, f.dueno_id, f.fecha_emision, f.total, c.nombre AS cliente_nombre " +
                "FROM facturas f " +
                "JOIN clientes c ON f.dueno_id = c.id " +
                "WHERE f.id = ?";

        // 🔹 Consulta de los items de la factura
        String sqlItems = "SELECT i.servicio_descripcion, i.cantidad, i.precio_unitario, i.subtotal " +
                "FROM items_factura i " +
                "WHERE i.factura_id = ?";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmtFactura = conn.prepareStatement(sqlFactura);
             PreparedStatement stmtItems = conn.prepareStatement(sqlItems)) {

            // ✅ Buscar factura
            stmtFactura.setInt(1, facturaId);
            try (ResultSet rsFactura = stmtFactura.executeQuery()) {
                if (rsFactura.next()) {
                    int id = rsFactura.getInt("id");
                    int duenoId = rsFactura.getInt("dueno_id");
                    Date fecha = rsFactura.getDate("fecha_emision");
                    double total = rsFactura.getDouble("total");
                    String clienteNombre = rsFactura.getString("cliente_nombre");

                    sb.append("====================================\n");
                    sb.append("         FACTURA N° ").append(id).append("\n");
                    sb.append("====================================\n");
                    sb.append("Dueño ID: ").append(duenoId).append("\n");
                    sb.append("Cliente: ").append(clienteNombre).append("\n");
                    sb.append("Fecha emisión: ").append(fecha).append("\n\n");
                    sb.append("Detalles:\n");
                    sb.append("Descripción\tCant\tPrecio\tSubtotal\n");
                    sb.append("------------------------------------\n");

                    // ✅ Buscar items asociados
                    stmtItems.setInt(1, facturaId);
                    try (ResultSet rsItems = stmtItems.executeQuery()) {
                        while (rsItems.next()) {
                            String descripcion = rsItems.getString("servicio_descripcion");
                            int cantidad = rsItems.getInt("cantidad");
                            double precioUnitario = rsItems.getDouble("precio_unitario");
                            double subtotal = rsItems.getDouble("subtotal");

                            sb.append(descripcion).append("\t")
                                    .append(cantidad).append("\t")
                                    .append(precioUnitario).append("\t")
                                    .append(subtotal).append("\n");
                        }
                    }

                    sb.append("------------------------------------\n");
                    sb.append("TOTAL: ").append(total).append("\n");
                    sb.append("====================================\n");
                } else {
                    sb.append("❌ No se encontró la factura con ID ").append(facturaId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "❌ Error al generar factura.";
        }

        return sb.toString();
    }


    public Factura obtenerFacturaPorId(int idFactura) {
        Factura factura = null;
        String sql = "SELECT * FROM facturas WHERE id = ?";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFactura);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    factura = new Factura(
                            rs.getInt("id"),
                            rs.getInt("dueno_id"),
                            rs.getDate("fecha_emision"),
                            rs.getDouble("total")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return factura;
    }

    public boolean insertarItemFactura(int facturaId, ItemFactura item) {
        String sql = "INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, facturaId);  // FK a facturas.id
            stmt.setInt(2, item.getProductoId());
            stmt.setString(3, item.getDescripcion());
            stmt.setInt(4, item.getCantidad());
            stmt.setDouble(5, item.getPrecioUnitario());
            stmt.setDouble(6, item.getSubtotal());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Dueno obtenerDuenoPorId(int duenoId) {
        Dueno dueno = null;
        String sql = "SELECT * FROM duenos WHERE id = ?";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, duenoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dueno = new Dueno(
                            rs.getInt("id"),
                            rs.getString("nombre_completo"),
                            rs.getString("documento_identidad"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("email")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dueno;
    }

    public int insertarFactura(Factura factura) {
        String sql = "INSERT INTO facturas (dueno_id, fecha_emision, total) VALUES (?, ?, ?)";
        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, factura.getDuenoId());
            stmt.setDate(2, new java.sql.Date(factura.getFechaEmision().getTime()));
            stmt.setDouble(3, factura.getTotal());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // ✅ devolvemos el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // ❌ si falla
    }
}