package com.Empresa.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase encargada de manejar la conexión centralizada a SQLite.
 * Implementa el Patrón Singleton (solo una forma de conectarse en todo el programa).
 */
public class DatabaseManager {
    
    // URL de conexión a la BD en la raíz del proyecto.
    private static final String URL = "jdbc:sqlite:Inventario.db";

    /**
     * Devuelve una nueva conexión a la base de datos lista para usar.
     * @return Connection
     * @throws SQLException si falla la conexión.
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        
        // ¡VITAL! Activamos las llaves foráneas para que los borrados en Cascada y Restrict funcionen.
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }
        
        return conn;
    }
}
