/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Model;

import java.time.LocalDate;

/**
 *
 * @author Prog. Junior Daniel
 */
public class HistorialMedico {
    private int id;
    private int mascotaId;
    private LocalDate fechaEvento;
    private int eventoTipoId;
    private String descripcion;
    private String diagnostico;
    private String tratamientoRecomendado;

    // Constructor vacío
    public HistorialMedico() {
    }

    // Constructor con parámetros
    public HistorialMedico(int id, int mascotaId, LocalDate fechaEvento, int eventoTipoId,
                           String descripcion, String diagnostico, String tratamientoRecomendado) {
        this.id = id;
        this.mascotaId = mascotaId;
        this.fechaEvento = fechaEvento;
        this.eventoTipoId = eventoTipoId;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamientoRecomendado = tratamientoRecomendado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public int getEventoTipoId() {
        return eventoTipoId;
    }

    public void setEventoTipoId(int eventoTipoId) {
        this.eventoTipoId = eventoTipoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamientoRecomendado() {
        return tratamientoRecomendado;
    }

    public void setTratamientoRecomendado(String tratamientoRecomendado) {
        this.tratamientoRecomendado = tratamientoRecomendado;
    }

    @Override
    public String toString() {
        return "HistorialMedico{" +
                "id=" + id +
                ", mascotaId=" + mascotaId +
                ", fechaEvento=" + fechaEvento +
                ", eventoTipoId=" + eventoTipoId +
                ", descripcion='" + descripcion + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamientoRecomendado='" + tratamientoRecomendado + '\'' +
                '}';
    }
}