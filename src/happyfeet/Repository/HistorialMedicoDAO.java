/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

/**
 *
 * @author Prog. Junior Daniel
 */


import happyfeet.Model.HistorialMedico;
import happyfeet.util.conexionBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistorialMedicoDAO implements IHistorialMedicoRepository {

    private final Connection conexion;

    public HistorialMedicoDAO() {
        this.conexion = conexionBD.getConnection(); // ✅ asegúrate que conexionBD es singleton/gestor de conexiones
    }

    @Override
    public void crear(HistorialMedico historial) {
        String sql = "INSERT INTO historial_medico " +
                     "(mascota_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, historial.getMascotaId());
            stmt.setDate(2, Date.valueOf(historial.getFechaEvento()));
            stmt.setInt(3, historial.getEventoTipoId());
            stmt.setString(4, historial.getDescripcion());
            stmt.setString(5, historial.getDiagnostico());
            stmt.setString(6, historial.getTratamientoRecomendado());
            stmt.executeUpdate();
            System.out.println("✅ Historial médico registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al crear historial médico: " + e.getMessage());
        }
    }

    @Override
    public List<HistorialMedico> listarTodos() {
        List<HistorialMedico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_medico";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapResultSetToHistorial(rs));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar historiales médicos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public HistorialMedico obtenerPorId(int idHistorial) {
        String sql = "SELECT * FROM historial_medico WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idHistorial);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToHistorial(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar historial médico: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(HistorialMedico historial) {
        String sql = "UPDATE historial_medico " +
                     "SET mascota_id=?, fecha_evento=?, evento_tipo_id=?, descripcion=?, diagnostico=?, tratamiento_recomendado=? " +
                     "WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, historial.getMascotaId());
            stmt.setDate(2, Date.valueOf(historial.getFechaEvento()));
            stmt.setInt(3, historial.getEventoTipoId());
            stmt.setString(4, historial.getDescripcion());
            stmt.setString(5, historial.getDiagnostico());
            stmt.setString(6, historial.getTratamientoRecomendado());
            stmt.setInt(7, historial.getId());
            stmt.executeUpdate();
            System.out.println("✅ Historial médico actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar historial médico: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int idHistorial) {
        String sql = "DELETE FROM historial_medico WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idHistorial);
            stmt.executeUpdate();
            System.out.println("✅ Historial médico eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar historial médico: " + e.getMessage());
        }
    }

    // 🔹 Extra: Listar por mascota
    public List<HistorialMedico> listarPorMascota(int mascotaId) {
        List<HistorialMedico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_medico WHERE mascota_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, mascotaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetToHistorial(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar historiales por mascota: " + e.getMessage());
        }
        return lista;
    }

    // 🔹 Método auxiliar para mapear ResultSet → HistorialMedico
    private HistorialMedico mapResultSetToHistorial(ResultSet rs) throws SQLException {
        return new HistorialMedico(
                rs.getInt("id"),
                rs.getInt("mascota_id"),
                rs.getDate("fecha_evento").toLocalDate(),
                rs.getInt("evento_tipo_id"),
                rs.getString("descripcion"),
                rs.getString("diagnostico"),
                rs.getString("tratamiento_recomendado")
        );
    }
}