/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

import happyfeet.Model.EventoTipo;
import happyfeet.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prog. Junior Daniel
 */
public class EventoTipoDAO implements IEventoTipoRepository {

    private final Connection conexion;

    public EventoTipoDAO() {
        this.conexion = conexionBD.getConnection();
    }

    @Override
    public boolean agregar(EventoTipo evento) {
        String sql = "INSERT INTO evento_tipos (nombre) VALUES (?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, evento.getNombreEvento());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar tipo de evento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<EventoTipo> obtenerTodos() {
        List<EventoTipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM evento_tipos";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapResultSetToEventoTipo(rs));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener tipos de evento: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public EventoTipo obtenerPorId(int id) {
        String sql = "SELECT * FROM evento_tipos WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEventoTipo(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar tipo de evento: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(EventoTipo evento) {
        String sql = "UPDATE evento_tipos SET nombre = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, evento.getNombreEvento());
            stmt.setInt(2, evento.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar tipo de evento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM evento_tipos WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar tipo de evento: " + e.getMessage());
            return false;
        }
    }

    // 🔹 Método auxiliar
    private EventoTipo mapResultSetToEventoTipo(ResultSet rs) throws SQLException {
        return new EventoTipo(
                rs.getInt("id"),
                rs.getString("nombre")
        );
    }
}
