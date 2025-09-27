/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

/**
 *
 * @author Prog. Junior Daniel
 */
import happyfeet.Model.CitaEstado;
import happyfeet.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaEstadoDAO implements ICitaEstadoRepository {

    private final Connection conn;

    public CitaEstadoDAO() {
        this.conn = conexionBD.getConnection();
    }

    @Override
    public void agregar(CitaEstado citaEstado) {
        String sql = "INSERT INTO cita_estados (nombre) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, citaEstado.getNombre());
            stmt.executeUpdate();
            System.out.println("✅ Estado agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar estado: " + e.getMessage());
        }
    }

    @Override
    public CitaEstado obtenerPorId(int id) {
        String sql = "SELECT * FROM cita_estados WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CitaEstado(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener estado: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<CitaEstado> listarTodos() {
        List<CitaEstado> estados = new ArrayList<>();
        String sql = "SELECT * FROM cita_estados";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                estados.add(new CitaEstado(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar estados: " + e.getMessage());
        }
        return estados;
    }

    @Override
    public void actualizar(CitaEstado citaEstado) {
        String sql = "UPDATE cita_estados SET nombre = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, citaEstado.getNombre());
            stmt.setInt(2, citaEstado.getId());
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Estado actualizado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró el estado con ID: " + citaEstado.getId());
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar estado: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cita_estados WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Estado eliminado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró el estado con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar estado: " + e.getMessage());
        }
    }
}
