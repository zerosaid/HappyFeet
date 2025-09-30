/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Model;

import java.util.Date;
import java.util.List;
/**
 *
 * @author Prog. Junior Daniel
 */
public class Factura {
    private int id;
    private int duenoId;        // ✅ Coincide con la BD (dueno_id)
    private Date fechaEmision;  // ✅ Coincide con la BD (fecha_emision)
    private double total;
    private List<ItemFactura> items;

    // Constructor completo
    public Factura(int duenoId, Date fechaEmision, double total, List<ItemFactura> items) {
        this.duenoId = duenoId;
        this.fechaEmision = fechaEmision;
        this.total = total;
        this.items = items;
    }

    // Constructor alternativo (para cargar desde BD sin lista de items)
    public Factura(int id, int duenoId, Date fechaEmision, double total) {
        this.id = id;
        this.duenoId = duenoId;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }

    // Constructor vacío
    public Factura() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }
}
