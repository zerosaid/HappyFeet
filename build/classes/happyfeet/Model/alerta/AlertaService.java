package Happy_Feet.bd.model.alerta;

import Happy_Feet.bd.model.InventarioDAO;

import java.util.ArrayList;
import java.util.List;

public class AlertaService {

    private final List<Alerta> alertas = new ArrayList<>();

    public AlertaService(InventarioDAO dao) {
        alertas.add(new AlertaStockBajo(dao));
        alertas.add(new AlertaFechaVencimiento(dao));
        alertas.add(new AlertaRestriccionVencidos());
    }

    public void ejecutarAlertas() {
        for (Alerta alerta : alertas) {
            alerta.ejecutarAlerta();
        }
    }
}