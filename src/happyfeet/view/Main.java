/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.view;

import happyfeet.Repository.InventarioDAO;
import happyfeet.controller.FacturaController;
import happyfeet.controller.InventarioController;
import happyfeet.service.InventarioActualizar;
import java.text.ParseException;

/**
 *
 * @author Prog. Junior Daniel
 */
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

