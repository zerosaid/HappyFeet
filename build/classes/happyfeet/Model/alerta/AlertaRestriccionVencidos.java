package Happy_Feet.bd.model.alerta;

import Happy_Feet.bd.model.InventarioDAO;
import Happy_Feet.bd.model.Producto;

import java.util.Date;
import java.util.List;

public class AlertaRestriccionVencidos implements Alerta {

    public AlertaRestriccionVencidos() {
    }

    @Override
    public void ejecutarAlerta() {
        List<Producto> productos = InventarioDAO.obtenerTodos();
        for (Producto p : productos) {
            Date fechaVencimiento = p.getFechaVencimiento();
            if (fechaVencimiento != null && fechaVencimiento.before(new Date())) {
                System.out.println("❌ PRODUCTO VENCIDO: " + p.getNombre() +
                        " - Fecha vencimiento: " + fechaVencimiento);
            }
        }
    }
}