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
    private LocalDateTime fechaHora; // datetime completo
    private int mascotaId;
    private String motivo;
    private int estadoId;

    public Cita() {}

    // Constructor completo (id incluido)
    public Cita(int id, LocalDateTime fechaHora, int mascotaId, String motivo, int estadoId) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.mascotaId = mascotaId;
        this.motivo = motivo;
        this.estadoId = estadoId;
    }

    // Constructor sin id (para insertar)
    public Cita(LocalDateTime fechaHora, int mascotaId, String motivo, int estadoId) {
        this.fechaHora = fechaHora;
        this.mascotaId = mascotaId;
        this.motivo = motivo;
        this.estadoId = estadoId;
    }

    // getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int mascotaId) { this.mascotaId = mascotaId; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public int getEstadoId() { return estadoId; }
    public void setEstadoId(int estadoId) { this.estadoId = estadoId; }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", mascotaId=" + mascotaId +
                ", motivo='" + motivo + '\'' +
                ", estadoId=" + estadoId +
                '}';
    }
}
