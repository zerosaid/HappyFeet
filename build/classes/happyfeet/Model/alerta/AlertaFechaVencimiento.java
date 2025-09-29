package Happy_Feet.bd.model.alerta;

import Happy_Feet.bd.model.InventarioDAO;
import Happy_Feet.bd.model.Producto;

import java.util.List;

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
            System.out.println("⚠️ Producto próximo a vencer: " + p.getNombre() +
                    " - Fecha vencimiento: " + p.getFechaVencimiento());
        }
    }
}