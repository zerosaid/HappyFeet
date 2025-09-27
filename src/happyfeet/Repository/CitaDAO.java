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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO implements ICitaRepository {

    private final Connection conexion;

    public CitaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregar(Cita cita) {
        String sql = "INSERT INTO citas (mascota_id, fecha_hora, motivo, estado_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cita.getMascotaId());
            stmt.setTimestamp(2, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(3, cita.getMotivo());
            stmt.setInt(4, cita.getEstadoId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al agregar cita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cita> obtenerTodas() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getInt("id"),
                        rs.getString("motivo"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getInt("mascota_id"),
                        rs.getInt("estado_id")
                );
                citas.add(cita);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener citas: " + e.getMessage());
        }
        return citas;
    }

    @Override
    public Cita obtenerPorId(int id) {
        String sql = "SELECT * FROM citas WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cita(
                            rs.getInt("id"),
                            rs.getString("motivo"),
                            rs.getTimestamp("fecha_hora").toLocalDateTime(),
                            rs.getInt("mascota_id"),
                            rs.getInt("estado_id")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cita por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Cita cita) {
        String sql = "UPDATE citas SET mascota_id = ?, fecha_hora = ?, motivo = ?, estado_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cita.getMascotaId());
            stmt.setTimestamp(2, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(3, cita.getMotivo());
            stmt.setInt(4, cita.getEstadoId());
            stmt.setInt(5, cita.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
            return false;
        }
    }
}
