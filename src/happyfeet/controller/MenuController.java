/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.controller;

import happyfeet.view.DuenoView;
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
        duenoView.mostrarMenu(); // asumimos que DuenoView tiene su propio menú
    }

    // Método que abre la gestión de Mascotas
    public void abrirGestionMascotas() {
        MascotaView mascotaView = new MascotaView();
        mascotaView.mostrarMenu(); // asumimos que MascotaView tiene su propio menú
    }

    // Método que abre la gestión de Razas
    public void abrirGestionRazas() {
        RazaView razaView = new RazaView();
        razaView.mostrarMenu(); // asumimos que RazaView tiene su propio menú
    }
}
