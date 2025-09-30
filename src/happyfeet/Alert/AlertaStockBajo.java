/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Alert;

import happyfeet.Model.Producto;
import happyfeet.Repository.InventarioDAO;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public class AlertaStockBajo implements Alerta {
    private final InventarioDAO dao;

    public AlertaStockBajo(InventarioDAO dao) {
        this.dao = dao;
    }

    @Override
    public void ejecutarAlerta() {
        List<Producto> productosConStockBajo = dao.obtenerProductosConStockBajo();
        for (Producto p : productosConStockBajo) {
            System.out.println("⚠ STOCK BAJO: " + p.getNombre() +
                    " - Stock actual: " + p.getCantidadStock() +
                    " - Stock mínimo: " + p.getStockMinimo());
        }
    }
}
