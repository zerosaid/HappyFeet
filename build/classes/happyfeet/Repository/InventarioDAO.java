package Happy_Feet.bd.model;

import Happy_Feet.bd.view.Conexiondb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    private double subtotal;

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
                stmt.setDate(8, new Date(p.getFechaVencimiento().getTime()));
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
                stmt.setDate(3, new Date(p.getFechaVencimiento().getTime()));
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
                    ItemFactura item = new ItemFactura(descripcion, cantidadTotal, precioUnitario, subtotal, producto);
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
            stmt.setDate(2, new Date(fechaEmision.getTime()));
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
        String updateStockSql = "UPDATE inventario SET cantidad_stock = cantidad_stock - ? WHERE id = ?";

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement updateStockStmt = conn.prepareStatement(updateStockSql)) {

            // Insertar el item en la tabla 'items_factura'
            stmt.setInt(1, facturaId);  // FK a facturas.id
            stmt.setInt(2, item.getProductoId());
            stmt.setString(3, item.getDescripcion());
            stmt.setInt(4, item.getCantidad());
            stmt.setDouble(5, item.getPrecioUnitario());
            stmt.setDouble(6, item.getSubtotal());

            // Ejecutar la consulta de inserción del item
            stmt.executeUpdate();

            // Actualizar el stock del producto correspondiente
            updateStockStmt.setInt(1, item.getCantidad());  // Decrementar la cantidad del producto
            updateStockStmt.setInt(2, item.getProductoId());  // Producto a actualizar

            // Ejecutar la consulta para actualizar el stock
            updateStockStmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int generarFactura(int duenoId, Date fechaEmision, List<ItemFactura> items) {
        // Paso 1: Crear la factura y asignar datos
        Factura factura = new Factura();
        factura.setDuenoId(duenoId);
        factura.setFechaEmision(fechaEmision);

        // Paso 2: Calcular el total de la factura sumando los subtotales de los productos
        double total = 0.0;
        for (ItemFactura item : items) {
            total += item.getSubtotal();  // Calcula el total sumando los subtotales de los productos
        }
        factura.setTotal(total);

        // Paso 3: Insertar la factura en la base de datos
        int facturaId = insertarFactura(factura);  // Ahora devuelve el ID de la factura insertada

        if (facturaId > 0) {
            // Paso 4: Insertar los items de la factura
            for (ItemFactura item : items) {
                insertarItemFactura(facturaId, item);  // Insertar cada item en la tabla 'items_factura'
            }

            // Mostrar la factura recién creada
            System.out.println("Factura generada con éxito. ID de factura: " + facturaId);
            System.out.println("Total: $" + total);
            mostrarFactura(facturaId);  // Mostrar la factura con todos sus detalles

            return facturaId;  // Retornamos el ID de la factura generada
        } else {
            System.out.println("❌ Error al generar la factura.");
            return -1;  // Si no se pudo insertar la factura, devolvemos un valor negativo
        }
    }


    private void mostrarFactura(int facturaId) {
        // Obtener los detalles de la factura usando el ID
        Factura factura = this.obtenerFacturaPorId(facturaId);
        if (factura == null) {
            System.out.println("❌ No se encontró la factura con ID " + facturaId);
            return;
        }

        // Obtener los datos del dueño de la factura
        Dueno dueno = this.obtenerDuenoPorId(factura.getDuenoId());
        if (dueno == null) {
            System.out.println("❌ No se encontró el dueño asociado a esta factura.");
            return;
        }

        // Mostrar la información de la factura
        System.out.println("********** FACTURA **********");
        System.out.println("ID de la Factura: " + factura.getId());
        System.out.println("Fecha de Emisión: " + factura.getFechaEmision());
        System.out.println("Dueño: " + dueno.getNombreCompleto());
        System.out.println("Documento de Identidad: " + dueno.getDocumentoIdentidad());
        System.out.println("Dirección: " + dueno.getDireccion());
        System.out.println("Teléfono: " + dueno.getTelefono());
        System.out.println("Email: " + dueno.getEmail());
        System.out.println("====================================");

        // Obtener los items de la factura
        List<ItemFactura> itemsFactura = this.obtenerItemsFactura(facturaId);
        if (itemsFactura.isEmpty()) {
            System.out.println("No hay productos/servicios asociados a esta factura.");
            return;
        }

        // Mostrar los productos/servicios en la factura
        System.out.println("Productos y Servicios:");
        System.out.println("====================================");
        for (ItemFactura item : itemsFactura) {
            double subtotal = item.getSubtotal();
            System.out.println(item.getDescripcion() + " - Cantidad: " + item.getCantidad() +
                    " - Precio Unitario: $" + item.getPrecioUnitario() +
                    " - Subtotal: $" + subtotal);
        }
        System.out.println("====================================");

        // Mostrar el total de la factura
        System.out.println("Total a Pagar: $" + factura.getTotal());
        System.out.println("====================================");
    }

    public List<ItemFactura> obtenerItemsFactura(int facturaId) {
        List<ItemFactura> items = new ArrayList<>();
        String sql = "SELECT i.servicio_descripcion, i.cantidad, i.precio_unitario, i.subtotal, i.producto_id " +
                "FROM items_factura i " +
                "WHERE i.factura_id = ?";  // Consultamos los items que corresponden a la factura con el id dado

        try (Connection conn = Conexiondb.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, facturaId);  // Establecemos el id de la factura en la consulta
            try (ResultSet rs = stmt.executeQuery()) {

                // Mapeamos cada registro del ResultSet a un objeto ItemFactura
                while (rs.next()) {
                    String descripcion = rs.getString("servicio_descripcion");  // Descripción del servicio o producto
                    int cantidad = rs.getInt("cantidad");  // Cantidad del producto o servicio
                    double precioUnitario = rs.getDouble("precio_unitario");  // Precio unitario
                    double subtotal = rs.getDouble("subtotal");  // Subtotal del item
                    int productoId = rs.getInt("producto_id");  // ID del producto asociado

                    // Obtenemos el producto relacionado para obtener su información
                    Producto producto = obtenerProductoPorId(productoId);  // Método para obtener el producto por su ID

                    // Crear un objeto ItemFactura con los datos obtenidos
                    ItemFactura item = new ItemFactura(descripcion, cantidad, precioUnitario, subtotal, producto);

                    // Agregamos el item a la lista de items
                    items.add(item);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;  // Devolver la lista de items de la factura
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

            int affectedRows = stmt.executeUpdate();  // Ejecuta la inserción

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);  // Devuelve el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;  // Si no se pudo insertar, retornamos -1
    }
}