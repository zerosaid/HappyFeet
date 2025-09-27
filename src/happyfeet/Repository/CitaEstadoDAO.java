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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaEstadoDAO implements ICitaEstadoRepository {

    private final Connection conexion;

    public CitaEstadoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregar(CitaEstado estado) {
        String sql = "INSERT INTO cita_estados (nombre) VALUES (?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, estado.getNombre());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al agregar estado de cita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<CitaEstado> obtenerTodos() {
        List<CitaEstado> estados = new ArrayList<>();
        String sql = "SELECT * FROM cita_estados";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CitaEstado estado = new CitaEstado(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                estados.add(estado);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener estados de cita: " + e.getMessage());
        }
        return estados;
    }

    @Override
    public CitaEstado obtenerPorId(int id) {
        String sql = "SELECT * FROM cita_estados WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CitaEstado(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener estado de cita por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(CitaEstado estado) {
        String sql = "UPDATE cita_estados SET nombre = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, estado.getNombre());
            stmt.setInt(2, estado.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado de cita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cita_estados WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar estado de cita: " + e.getMessage());
            return false;
        }
    }
}
