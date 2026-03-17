package com.Empresa.DAO;

import com.Empresa.config.DatabaseManager;
import com.Empresa.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    /**
     * Mapea un ResultSet a un objeto Producto. Evita repetir código en métodos de buscar y listar.
     */
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setCodigoBarras(rs.getString("codigo_barras"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecioCompra(rs.getDouble("precio_compra"));
        p.setPrecioVenta(rs.getDouble("precio_venta"));
        p.setStockActual(rs.getInt("stock_actual"));
        p.setStockMinimo(rs.getInt("stock_minimo"));
        
        // Categoría es Integer (puede venir nulo desde la BD)
        int idCat = rs.getInt("id_categoria");
        if (!rs.wasNull()) {
            p.setIdCategoria(idCat);
        } else {
            p.setIdCategoria(null);
        }
        
        return p;
    }

    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY nombre ASC";
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                lista.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los productos: " + e.getMessage());
        }
        return lista;
    }

    public Producto buscarPorCodigoBarras(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo_barras = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, codigo);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearProducto(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error Crítico al buscar producto por código: " + e.getMessage());
        }
        return null;
    }
}
