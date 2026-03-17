package com.Empresa.DAO;

import com.Empresa.config.DatabaseManager;
import com.Empresa.model.DetalleVenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {

    /**
     * Guarda un producto individual que pertenece a una venta.
     */
    public boolean guardar(DetalleVenta detalle) {
        String sql = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, detalle.getIdVenta());
            pstmt.setInt(2, detalle.getIdProducto());
            pstmt.setInt(3, detalle.getCantidad());
            pstmt.setDouble(4, detalle.getPrecioUnitario());
            pstmt.setDouble(5, detalle.getSubtotal());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al guardar detalle de venta: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Lista todos los productos que se vendieron en un ticket/venta específico.
     * Sirve para re-imprimir el ticket o ver el historial de qué se llevó el cliente.
     */
    public List<DetalleVenta> listarPorIdVenta(int idVenta) {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta WHERE id_venta = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idVenta);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DetalleVenta d = new DetalleVenta();
                    d.setId(rs.getInt("id"));
                    d.setIdVenta(rs.getInt("id_venta"));
                    d.setIdProducto(rs.getInt("id_producto"));
                    d.setCantidad(rs.getInt("cantidad"));
                    d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    d.setSubtotal(rs.getDouble("subtotal"));
                    lista.add(d);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los detalles de la venta: " + e.getMessage());
        }
        return lista;
    }
}
