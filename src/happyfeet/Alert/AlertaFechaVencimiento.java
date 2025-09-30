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
public class AlertaFechaVencimiento implements Alerta {

    private final InventarioDAO inventarioDAO;
    private static final int DIAS_ANTES_VENCIMIENTO = 15;

    public AlertaFechaVencimiento(InventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    @Override
    public void ejecutarAlerta() {
        List<Producto> productos = inventarioDAO.obtenerProductosPorVencer(DIAS_ANTES_VENCIMIENTO);
        for (Producto p : productos) {
            System.out.println("⚠ Producto próximo a vencer: " + p.getNombre() +
                    " - Fecha vencimiento: " + p.getFechaVencimiento());
        }
    }
}
