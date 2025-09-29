package Happy_Feet.bd.view;

import Happy_Feet.bd.controller.FacturaController;
import Happy_Feet.bd.controller.InventarioController;
import Happy_Feet.bd.model.*;
import Happy_Feet.bd.model.alerta.AlertaService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainView {

    private final InventarioController inventarioController;
    private final FacturaController facturaController;
    private final InventarioActualizar inventarioActualizar;
    private final ReporteGerencial reporteGerencial;
    private final Scanner scanner = new Scanner(System.in);
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final InventarioDAO inventarioDAO = new InventarioDAO(); // ✅ Instancia DAO

    public MainView(InventarioController inventarioController, FacturaController facturaController,
                    InventarioActualizar inventarioActualizar, ReporteGerencial reporteGerencial) {
        this.inventarioController = inventarioController;
        this.facturaController = facturaController;
        this.inventarioActualizar = inventarioActualizar;
        this.reporteGerencial = reporteGerencial;
    }

    public void mostrarMenu() throws ParseException {
        while (true) {
            System.out.println("1. Insertar producto");
            System.out.println("2. Actualizar producto");
            System.out.println("3. Ejecutar alertas");
            System.out.println("4. Actualizar producto por proveedor");
            System.out.println("5. Generar factura");
            System.out.println("6. Reporte de servicios más solicitados");
            System.out.println("7. Reporte de estado del inventario");
            System.out.println("0. Salir");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> insertarProducto();
                case 2 -> actualizarProducto();
                case 3 -> ejecutarAlertas();
                case 4 -> actualizarProductoPorProveedor();
                case 5 -> generarFactura();
                case 6 -> reporteGerencial.reporteServiciosMasSolicitados();
                case 7 -> reporteGerencial.reporteEstadoInventario();
                case 0 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void insertarProducto() throws ParseException {
        Producto p = leerProductoNuevo();
        if (inventarioController.registrarProducto(p)) {
            System.out.println("✔ Producto registrado con éxito");
        } else {
            System.out.println("❌ Error al registrar producto");
        }
    }

    private void actualizarProducto() throws ParseException {
        Producto p = leerActualizacionProducto();
        if (inventarioController.actualizarProducto(p)) {
            System.out.println("✔ Producto actualizado con éxito");
        } else {
            System.out.println("❌ Error al actualizar producto");
        }
    }

    private void ejecutarAlertas() {
        AlertaService alertaService = new AlertaService(new InventarioDAO());
        alertaService.ejecutarAlertas();
    }

    private void actualizarProductoPorProveedor() throws ParseException {
        System.out.print("Ingrese ID del proveedor: ");
        int idProveedor = Integer.parseInt(scanner.nextLine());

        List<Producto> productos = inventarioActualizar.obtenerProductosPorProveedor(idProveedor);
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos para ese proveedor.");
            return;
        }

        System.out.println("Productos del proveedor:");
        for (Producto prod : productos) {
            System.out.printf("ID: %d, Nombre: %s\n", prod.getId(), prod.getNombre());
        }

        System.out.print("Ingrese ID del producto a actualizar: ");
        int idProducto = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese nuevo stock: ");
        int nuevoStock = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese stock mínimo: ");
        int stockMinimo = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese fecha de vencimiento (yyyy-MM-dd), puede dejar vacío: ");
        String fechaStr = scanner.nextLine();
        Date fechaVencimiento = null;
        if (!fechaStr.isBlank()) {
            try {
                fechaVencimiento = sdf.parse(fechaStr);
            } catch (Exception e) {
                System.out.println("Fecha inválida, se omitirá la actualización de fecha.");
            }
        }

        boolean actualizado = inventarioActualizar.actualizarInventario(idProducto, nuevoStock, stockMinimo, fechaVencimiento);
        if (actualizado) {
            System.out.println("✔ Producto actualizado con éxito");
        } else {
            System.out.println("❌ Error al actualizar producto");
        }
    }

    private void generarFactura() {
        List<ItemFactura> items = seleccionarProductos();
        double total = calcularTotal(items);

        Date fechaEmision = new Date();
        System.out.println("Fecha de emisión: " + fechaEmision);

        System.out.print("Ingrese el ID del dueño: ");
        int duenoId = Integer.parseInt(scanner.nextLine());

        if (facturaController.generarFactura(duenoId, fechaEmision, items)) {
            System.out.println("✔ Factura generada con éxito.");
            System.out.println("Total de la factura: $" + total);

            // ✅ Obtener el id de la última factura insertada
            int ultimaFacturaId = facturaController.obtenerUltimaFacturaId();

            // ✅ Mostrar la factura en texto
            String facturaTexto = facturaController.generarFacturaTexto(ultimaFacturaId);
            System.out.println(facturaTexto);

        } else {
            System.out.println("❌ Error al generar factura.");
        }
    }

    // ✅ corregido
    private List<ItemFactura> seleccionarProductos() {
        List<ItemFactura> itemsSeleccionados = new ArrayList<>();
        String respuesta = null;
        do {
            try {
                System.out.print("Ingrese el ID del producto que desea comprar: ");
                int idProducto = Integer.parseInt(scanner.nextLine().trim());

                System.out.print("Ingrese la cantidad: ");
                int cantidad = Integer.parseInt(scanner.nextLine().trim());
                if (cantidad <= 0) {
                    System.out.println("Cantidad inválida.");
                    continue;
                }

                Producto producto = inventarioDAO.obtenerProductoPorId(idProducto);
                if (producto != null) {
                    double precioUnitario = producto.getPrecioVenta();
                    double subtotal = cantidad * precioUnitario;

                    ItemFactura item = new ItemFactura(
                            producto.getNombre(),
                            cantidad,
                            precioUnitario,
                            subtotal,
                            producto.getId()
                    );
                    itemsSeleccionados.add(item);
                    System.out.println("✔ Producto agregado: " + producto.getNombre() + " - Subtotal: $" + subtotal);
                } else {
                    System.out.println("❌ Producto no encontrado.");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Entrada inválida. Use números.");
            }

            System.out.print("¿Desea agregar otro producto? (S/N): ");
            respuesta = scanner.nextLine().trim();

        } while (respuesta.equalsIgnoreCase("S"));

        return itemsSeleccionados;
    }

    private double calcularTotal(List<ItemFactura> items) {
        double total = 0;
        for (ItemFactura item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    private Producto leerProductoNuevo() throws ParseException {
        Producto p = new Producto();
        System.out.print("Nombre: ");
        p.setNombre(scanner.nextLine());

        System.out.print("ID Tipo Producto: ");
        p.setProductoTipoId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Descripción: ");
        p.setDescripcion(scanner.nextLine());

        System.out.print("Fabricante: ");
        p.setFabricante(scanner.nextLine());

        System.out.print("Lote: ");
        p.setLote(scanner.nextLine());

        System.out.print("Cantidad stock: ");
        p.setCantidadStock(Integer.parseInt(scanner.nextLine()));

        System.out.print("Stock mínimo: ");
        p.setStockMinimo(Integer.parseInt(scanner.nextLine()));

        System.out.print("Fecha vencimiento (yyyy-MM-dd): ");
        p.setFechaVencimiento(sdf.parse(scanner.nextLine()));

        System.out.print("Precio venta: ");
        p.setPrecioVenta(Double.parseDouble(scanner.nextLine()));

        System.out.print("ID Subcategoría: ");
        p.setProductoSubcategoriaId(Integer.parseInt(scanner.nextLine()));

        return p;
    }

    private Producto leerActualizacionProducto() throws ParseException {
        Producto p = new Producto();
        System.out.print("ID del producto a actualizar: ");
        p.setId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Nueva cantidad stock: ");
        p.setCantidadStock(Integer.parseInt(scanner.nextLine()));

        System.out.print("Nuevo stock mínimo: ");
        p.setStockMinimo(Integer.parseInt(scanner.nextLine()));

        System.out.print("Nueva fecha vencimiento (yyyy-MM-dd): ");
        p.setFechaVencimiento(sdf.parse(scanner.nextLine()));

        return p;
    }
}