package com.Empresa.DAO;

import com.Empresa.config.DatabaseManager;
import com.Empresa.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    /**
     * Busca un usuario en la BD dado su username. Útil para el Login.
     */
    public Usuario buscarPorUsername(String username) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";
        
        // El bloque try-with-resources cierra automáticamente la conexión, evitando fugas de memoria.
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setRol(rs.getString("rol"));
                    // Si tienes el campo en tu entidad: u.setFechaCreacion(rs.getString("fecha_creacion"));
                    return u;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error Crítico al buscar usuario: " + e.getMessage());
        }
        return null; // Si devuelve null, significa que ese usuario no existe.
    }
}
