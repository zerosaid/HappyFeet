/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Model;

/**
 *
 * @author Prog. Junior Daniel
 */

public class Raza {
    private int id;
    private String nombre;
    private int especieId;

    // Constructor
    public Raza(int id, String nombre, int especieId) {
        this.id = id;
        this.nombre = nombre;
        this.especieId = especieId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEspecieId() {
        return especieId;
    }

    public void setEspecieId(int especieId) {
        this.especieId = especieId;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " (Especie: " + especieId + ")";
    }
}

