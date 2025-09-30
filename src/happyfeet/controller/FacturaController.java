/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

import happyfeet.Model.Factura;
import happyfeet.Model.ItemFactura;
import happyfeet.Repository.InventarioDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public class FacturaController {
    private InventarioDAO inventarioDAO;

    public FacturaController() {
        this.inventarioDAO = new InventarioDAO();
    }

    public boolean generarFactura(int duenoId, Date fechaEmision, List<ItemFactura> items) {
        double total = items.stream().mapToDouble(ItemFactura::getSubtotal).sum();

        // 1️⃣ Crear objeto factura
        Factura factura = new Factura();
        factura.setDuenoId(duenoId);
        factura.setFechaEmision(fechaEmision);
        factura.setTotal(total);

        // 2️⃣ Insertar factura y obtener su ID
        int facturaId = inventarioDAO.insertarFactura(factura);
        if (facturaId <= 0) {
            System.out.println("❌ No se pudo crear la factura.");
            return false;
        }

        // 3️⃣ Insertar ítems
        boolean exito = true;
        for (ItemFactura item : items) {
            if (!inventarioDAO.insertarItemFactura(facturaId, item)) {
                exito = false;
            }
        }
        return exito;
    }

    // ✅ Obtener la última factura insertada
    public int obtenerUltimaFacturaId() {
        return inventarioDAO.obtenerUltimaFacturaId();
    }

    // ✅ Generar texto de la factura (consulta a BD)
    public String generarFacturaTexto(int facturaId) {
        return inventarioDAO.generarFacturaTexto(facturaId);
    }
}
