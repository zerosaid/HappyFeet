package Happy_Feet.bd.model;

import java.util.Date;

public class Producto {
    private int id;
    private String nombre;
    private int productoTipoId;
    private String descripcion;
    private String fabricante;
    private String lote;
    private int cantidadStock;
    private int stockMinimo;
    private Date fechaVencimiento;
    private double precioVenta;
    private int productoSubcategoriaId;

    public Producto() {
        // Constructor vacío
    }

    // Constructor sin ID (para insertar)
    public Producto(String nombre, int productoTipoId, String descripcion, String fabricante,
                    String lote, int cantidadStock, int stockMinimo, Date fechaVencimiento,
                    double precioVenta, int productoSubcategoriaId) {
        this.nombre = nombre;
        this.productoTipoId = productoTipoId;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.lote = lote;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
        this.fechaVencimiento = fechaVencimiento;
        this.precioVenta = precioVenta;
        this.productoSubcategoriaId = productoSubcategoriaId;
    }

    // ✅ Constructor completo con ID (usado en mapearProducto)
    public Producto(int id, String nombre, int productoTipoId, String descripcion, String fabricante,
                    String lote, int cantidadStock, int stockMinimo, Date fechaVencimiento,
                    double precioVenta, int productoSubcategoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.productoTipoId = productoTipoId;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.lote = lote;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
        this.fechaVencimiento = fechaVencimiento;
        this.precioVenta = precioVenta;
        this.productoSubcategoriaId = productoSubcategoriaId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getProductoTipoId() { return productoTipoId; }
    public void setProductoTipoId(int productoTipoId) { this.productoTipoId = productoTipoId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }

    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }

    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }

    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public int getProductoSubcategoriaId() { return productoSubcategoriaId; }
    public void setProductoSubcategoriaId(int productoSubcategoriaId) { this.productoSubcategoriaId = productoSubcategoriaId; }
}