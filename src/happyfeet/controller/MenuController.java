/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

import happyfeet.view.CitaView;
import happyfeet.view.DuenoView;
import happyfeet.view.HistorialMedicoView;
import happyfeet.view.MascotaView;
import happyfeet.view.RazaView;

/**
 *
 * @author Prog. Junior Daniel
 */

public class MenuController {

    // Método que abre la gestión de Dueños
    public void abrirGestionDuenos() {
        DuenoView duenoView = new DuenoView();
        DuenoController duenoController = new DuenoController(duenoView);
        duenoView.setController(duenoController);
        duenoView.mostrarMenu();
    }

    // Método que abre la gestión de Mascotas
    public void abrirGestionMascotas() {
        MascotaView mascotaView = new MascotaView();
        mascotaView.mostrarMenu();
    }

    // Método que abre la gestión de Razas
    public void abrirGestionRazas() {
        RazaView razaView = new RazaView();
        razaView.mostrarMenu();
    }
    
     public void abrirGestionCitas() {
        CitaView citaView = new CitaView();
        CitaController citaController = new CitaController(citaView);
        citaView.setController(citaController);  
        citaView.mostrarMenu();
    }
     
     public void abrirGestionHistorialMedico() {
        HistorialMedicoView historialView = new HistorialMedicoView();
        HistorialMedicoController historialController = new HistorialMedicoController(historialView);
        historialView.setController(historialController);
        historialView.mostrarMenu();
    }

}
