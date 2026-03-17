package com.Empresa.DAO;

import com.Empresa.config.DatabaseManager;
import com.Empresa.model.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    /**
     * Guarda la cabecera de la venta. 
     * Retorna el ID autogenerado de la venta para poder usarlo después en los "DetalleVenta".
     * Retorna -1 si falla.
     */
    public int guardarVenta(Venta venta) {
        String sql = "INSERT INTO ventas (id_usuario, total_venta, metodo_pago) VALUES (?, ?, ?)";
        int idVentaGenerado = -1;
        
        // RETURN_GENERATED_KEYS es clave en SQLite para obtener el ID que le asignó a la nueva venta
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            pstmt.setInt(1, venta.getIdUsuario());
            pstmt.setDouble(2, venta.getTotalVenta());
            pstmt.setString(3, venta.getMetodoPago());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idVentaGenerado = rs.getInt(1); // Recupera el ID de la venta
                    }
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al guardar la venta principal: " + e.getMessage());
        }
        
        return idVentaGenerado;
    }

    /**
     * Listar todo el historial de ventas.
     */
    public List<Venta> listarTodas() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas ORDER BY fecha_venta DESC";
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("id"));
                v.setIdUsuario(rs.getInt("id_usuario"));
                v.setFechaVenta(rs.getString("fecha_venta"));
                v.setTotalVenta(rs.getDouble("total_venta"));
                v.setMetodoPago(rs.getString("metodo_pago"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las ventas: " + e.getMessage());
        }
        return lista;
    }
}
