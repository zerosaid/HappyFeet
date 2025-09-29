package Happy_Feet.bd.model.alerta;

import Happy_Feet.bd.model.InventarioDAO;
import Happy_Feet.bd.model.Producto;

import java.util.List;

/**
 * Alerta para detectar productos cuyo stock está por debajo del mínimo permitido.
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
            System.out.println("⚠️ STOCK BAJO: " + p.getNombre() +
                    " - Stock actual: " + p.getCantidadStock() +
                    " - Stock mínimo: " + p.getStockMinimo());
        }
    }
}