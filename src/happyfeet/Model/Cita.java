/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Model;

import java.time.LocalDateTime;

/**
 *
 * @author Prog. Junior Daniel
 */
public class Cita {
    
    private int id;
    private int mascotaId;         // FK hacia Mascota
    private LocalDateTime fechaHora;
    private String motivo;
    private int estadoId;          // FK hacia cita_estados

    public Cita(int aInt, String string, LocalDateTime toLocalDateTime, int aInt1, int aInt2) {
    }

    public Cita(int id, String motivo, LocalDateTime  fechaHora, int mascotaId, int estadoId, String motivo1, String estado) {
        this.id = id;
        this.mascotaId = mascotaId;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estadoId = estadoId;
    }

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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", mascotaId=" + mascotaId +
                ", fechaHora='" + fechaHora + '\'' +
                ", motivo='" + motivo + '\'' +
                ", estadoId=" + estadoId +
                '}';
    }
}
