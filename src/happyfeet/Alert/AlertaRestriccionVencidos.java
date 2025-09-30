/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Alert;

import happyfeet.Model.Producto;
import happyfeet.Repository.InventarioDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */

public class AlertaRestriccionVencidos implements Alerta {

    private final InventarioDAO dao;

    // Inyectar el DAO en el constructor
    public AlertaRestriccionVencidos(InventarioDAO dao) {
        this.dao = dao;
    }

    @Override
    public void ejecutarAlerta() {
        List<Producto> productos = dao.obtenerTodos(); // ahora es método de instancia
        Date hoy = new Date();

        for (Producto p : productos) {
            Date fechaVencimiento = p.getFechaVencimiento();
            if (fechaVencimiento != null && fechaVencimiento.before(hoy)) {
                System.out.println("❌ PRODUCTO VENCIDO: " + p.getNombre()
                        + " | Fecha vencimiento: " + fechaVencimiento);
            }
        }
    }
}

