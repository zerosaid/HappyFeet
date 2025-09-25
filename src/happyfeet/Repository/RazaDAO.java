/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

import happyfeet.Model.Raza;
import happyfeet.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Prog. Junior Daniel
 */
public class RazaDAO implements IRazaRepository {

    @Override
    public boolean agregar(Raza raza) {
        String sql = "INSERT INTO razas (nombre, especie_id) VALUES (?, ?)";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, raza.getNombre());
            stmt.setInt(2, raza.getEspecieId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al agregar raza: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Raza> obtenerTodas() {
        List<Raza> lista = new ArrayList<>();
        String sql = "SELECT * FROM razas";
        try (Connection conn = conexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Raza r = new Raza(
                    rs.getInt("id"),
                    rs.getString("nombre"));
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener razas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Raza obtenerPorId(int id) {
        String sql = "SELECT * FROM razas WHERE id=?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Raza(
                    rs.getInt("id"),
                    rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener raza por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Raza raza) {
        String sql = "UPDATE razas SET nombre=?, especie_id=? WHERE id=?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, raza.getNombre());
            stmt.setInt(2, raza.getEspecieId());
            stmt.setInt(3, raza.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar raza: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM razas WHERE id=?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar raza: " + e.getMessage());
            return false;
        }
    }
}