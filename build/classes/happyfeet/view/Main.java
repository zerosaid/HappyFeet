package Happy_Feet.bd.view;

import Happy_Feet.bd.controller.FacturaController;
import Happy_Feet.bd.controller.InventarioController;
import Happy_Feet.bd.model.InventarioActualizar;
import Happy_Feet.bd.model.InventarioDAO;
import Happy_Feet.bd.model.ReporteGerencial;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        // Inicializamos los objetos necesarios
        InventarioDAO dao = new InventarioDAO();
        InventarioController inventarioController = new InventarioController();
        FacturaController facturaController = new FacturaController();
        InventarioActualizar inventarioActualizar = new InventarioActualizar();
        ReporteGerencial reporteGerencial = new ReporteGerencial(dao);

        // Creamos la instancia de MainView y le pasamos los objetos necesarios
        MainView mainView = new MainView(inventarioController, facturaController, inventarioActualizar, reporteGerencial);

        // Llamamos al método que muestra el menú y gestiona la interacción con el usuario
        mainView.mostrarMenu();
    }
}