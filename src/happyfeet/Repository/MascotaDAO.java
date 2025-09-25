/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

import happyfeet.Model.Mascota;
import happyfeet.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Prog. Junior Daniel
 */

public class MascotaDAO implements IMascotaRepository {

    @Override
    public boolean agregar(Mascota mascota) {
        String sql = "INSERT INTO mascotas (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, url_foto) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, mascota.getDuenoId());
            stmt.setString(2, mascota.getNombre());
            stmt.setInt(3, mascota.getRazaId());
            stmt.setString(4, mascota.getFechaNacimiento());
            stmt.setString(5, mascota.getSexo());
            stmt.setString(6, mascota.getUrlFoto());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar mascota: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Mascota> obtenerTodas() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";
        try (Connection conn = conexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Mascota m = new Mascota(
                    rs.getInt("id"),
                    rs.getInt("dueno_id"),
                    rs.getString("nombre"),
                    rs.getInt("raza_id"),
                    rs.getString("fecha_nacimiento"),
                    rs.getString("sexo"),
                    rs.getString("url_foto")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener mascotas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Mascota obtenerPorId(int id) {
        String sql = "SELECT * FROM mascotas WHERE id=?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Mascota(
                    rs.getInt("id"),
                    rs.getInt("dueno_id"),
                    rs.getString("nombre"),
                    rs.getInt("raza_id"),
                    rs.getString("fecha_nacimiento"),
                    rs.getString("sexo"),
                    rs.getString("url_foto")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener mascota por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Mascota mascota) {
        String sql = "UPDATE mascotas SET dueno_id=?, nombre=?, raza_id=?, fecha_nacimiento=?, sexo=?, url_foto=? WHERE id=?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, mascota.getDuenoId());
            stmt.setString(2, mascota.getNombre());
            stmt.setInt(3, mascota.getRazaId());
            stmt.setString(4, mascota.getFechaNacimiento());
            stmt.setString(5, mascota.getSexo());
            stmt.setString(6, mascota.getUrlFoto());
            stmt.setInt(7, mascota.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar mascota: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM mascotas WHERE id=?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar mascota: " + e.getMessage());
            return false;
        }
    }
}