/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package happyfeet.Repository;

import happyfeet.Model.Dueno;
import happyfeet.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuenoDAO {

    private final Connection conn;

    public DuenoDAO() {
        conn = conexionBD.getConnection();
    }

    // INSERTAR
    public void agregar(Dueno dueno) {
        String sql = "INSERT INTO Duenos (id, nombre_Completo, documento_identidad, direccion, telefono, email) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dueno.getId());
            stmt.setString(2, dueno.getNombreCompleto());
            stmt.setString(3, dueno.getDocumentoIdentidad());
            stmt.setString(4, dueno.getDireccion());
            stmt.setString(5, dueno.getTelefono());
            stmt.setString(6, dueno.getEmail());
            stmt.executeUpdate();
            System.out.println("Dueño agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar dueño: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Dueno> listarTodos() {
        List<Dueno> lista = new ArrayList<>();
        String sql = "SELECT * FROM Duenos"; 

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dueno dueno = new Dueno(
                    rs.getInt("id"),
                    rs.getString("nombre_Completo"),
                    rs.getString("documento_identidad"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("email")
                );
                lista.add(dueno);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar dueños: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR POR ID
    public Dueno obtenerPorId(int id) {
        String sql = "SELECT * FROM Duenos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Dueno(
                        rs.getInt("id"),
                        rs.getString("nombre_Completo"),
                        rs.getString("documento_identidad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener dueño por ID: " + e.getMessage());
        }
        return null;
    }

    // ACTUALIZAR
    public void actualizar(Dueno dueno) {
        String sql = "UPDATE Duenos SET nombre_Completo = ?, documento_identidad = ?, direccion = ?, telefono = ?, email = ? "
                   + "WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dueno.getNombreCompleto());
            stmt.setString(2, dueno.getDocumentoIdentidad());
            stmt.setString(3, dueno.getDireccion());
            stmt.setString(4, dueno.getTelefono());
            stmt.setString(5, dueno.getEmail());
            stmt.setInt(6, dueno.getId());
            stmt.executeUpdate();
            System.out.println("Dueño actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar dueño: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(int id) {
        String sql = "DELETE FROM Duenos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Dueño eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar dueño: " + e.getMessage());
        }
    }
}
