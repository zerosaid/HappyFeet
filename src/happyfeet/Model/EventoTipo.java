/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Model;

/**
 *
 * @author Prog. Junior Daniel
 */
public class EventoTipo {
    private int id;
    private String nombreEvento;

    // Constructores
    public EventoTipo() {
    }

    public EventoTipo(int id, String nombreEvento) {
        this.id = id;
        this.nombreEvento = nombreEvento;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    // Para mostrar en consola
    @Override
    public String toString() {
        return "EventoTipo {" +
                "ID=" + id +
                ", Nombre='" + nombreEvento + '\'' +
                '}';
    }
}
