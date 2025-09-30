/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Alert;

import happyfeet.Repository.InventarioDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
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
