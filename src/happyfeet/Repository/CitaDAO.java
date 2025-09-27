/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

/**
 *
 * @author Prog. Junior Daniel
 */
import happyfeet.Model.Cita;
import happyfeet.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO implements ICitaRepository {

    // usa la clase util que ya tienes para obtener conexiones
    private final conexionBD conexionBD = new conexionBD();

    public CitaDAO() {
        // constructor por defecto — coincide con otros DAOs del proyecto
    }

    @Override
    public boolean agregar(Cita cita) {
        String sql = "INSERT INTO citas (mascota_id, fecha_hora, motivo, estado_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cita.getMascotaId());
            stmt.setTimestamp(2, Timestamp.valueOf(cita.getFechaHora())); // LocalDateTime -> Timestamp
            stmt.setString(3, cita.getMotivo());
            stmt.setInt(4, cita.getEstadoId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al agregar cita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cita> obtenerTodas() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        try (Connection conn = conexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getInt("id"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getInt("mascota_id"),
                        rs.getString("motivo"),
                        rs.getInt("estado_id")
                );
                citas.add(cita);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener citas: " + e.getMessage());
        }
        return citas;
    }

    @Override
    public Cita obtenerPorId(int id) {
        String sql = "SELECT * FROM citas WHERE id = ?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cita(
                            rs.getInt("id"),
                            rs.getTimestamp("fecha_hora").toLocalDateTime(),
                            rs.getInt("mascota_id"),
                            rs.getString("motivo"),
                            rs.getInt("estado_id")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener cita por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Cita cita) {
        String sql = "UPDATE citas SET mascota_id = ?, fecha_hora = ?, motivo = ?, estado_id = ? WHERE id = ?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cita.getMascotaId());
            stmt.setTimestamp(2, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(3, cita.getMotivo());
            stmt.setInt(4, cita.getEstadoId());
            stmt.setInt(5, cita.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar cita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cita: " + e.getMessage());
            return false;
        }
    }
}
