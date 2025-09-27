/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

import happyfeet.Model.Especie;
import happyfeet.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecieDAO implements IEspecieRepository {

    private final conexionBD conexionBD = new conexionBD();

    @Override
    public boolean agregar(Especie especie) {
        String sql = "INSERT INTO especies (nombre) VALUES (?)"; // ✅ plural
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, especie.getNombre());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar especie: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Especie buscarPorId(int id) {
        String sql = "SELECT * FROM especies WHERE id=?"; // ✅ plural
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Especie(rs.getInt("id"), rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar especie: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Especie> listar() {
        List<Especie> especies = new ArrayList<>();
        String sql = "SELECT * FROM especies"; // ✅ plural
        try (Connection conn = conexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                especies.add(new Especie(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar especies: " + e.getMessage());
        }
        return especies;
    }

    @Override
    public boolean actualizar(Especie especie) {
        String sql = "UPDATE especies SET nombre=? WHERE id=?"; // ✅ plural
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, especie.getNombre());
            stmt.setInt(2, especie.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar especie: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM especies WHERE id=?"; // ✅ plural
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar especie: " + e.getMessage());
            return false;
        }
    }
}
