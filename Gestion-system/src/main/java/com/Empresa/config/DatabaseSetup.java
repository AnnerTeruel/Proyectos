package com.Empresa.config;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseSetup {

    public static void inicializarBaseDeDatos() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("Iniciando la construcción de la Base de Datos...");

            // ==========================================
            // 1. CREACIÓN DE TABLAS
            // ==========================================
            String tablaUsuarios = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    rol TEXT NOT NULL CHECK(rol IN ('admin', 'vendedor')),
                    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
                );""";

            String tablaCategorias = """
                CREATE TABLE IF NOT EXISTS categorias (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL UNIQUE,
                    descripcion TEXT
                );""";

            String tablaProductos = """
                CREATE TABLE IF NOT EXISTS productos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    codigo_barras TEXT UNIQUE,
                    nombre TEXT NOT NULL,
                    descripcion TEXT,
                    precio_compra REAL NOT NULL CHECK(precio_compra >= 0),
                    precio_venta REAL NOT NULL CHECK(precio_venta >= 0),
                    stock_actual INTEGER NOT NULL DEFAULT 0 CHECK(stock_actual >= 0),
                    stock_minimo INTEGER NOT NULL DEFAULT 0 CHECK(stock_minimo >= 0),
                    id_categoria INTEGER,
                    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (id_categoria) REFERENCES categorias(id) ON DELETE SET NULL
                );""";

            String tablaVentas = """
                CREATE TABLE IF NOT EXISTS ventas (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_usuario INTEGER NOT NULL,
                    fecha_venta DATETIME DEFAULT CURRENT_TIMESTAMP,
                    total_venta REAL NOT NULL CHECK(total_venta >= 0),
                    metodo_pago TEXT NOT NULL CHECK(metodo_pago IN ('efectivo', 'tarjeta', 'transferencia')),
                    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE RESTRICT
                );""";

            String tablaDetalles = """
                CREATE TABLE IF NOT EXISTS detalle_venta (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_venta INTEGER NOT NULL,
                    id_producto INTEGER NOT NULL,
                    cantidad INTEGER NOT NULL CHECK(cantidad > 0),
                    precio_unitario REAL NOT NULL CHECK(precio_unitario >= 0),
                    subtotal REAL NOT NULL CHECK(subtotal >= 0),
                    FOREIGN KEY (id_venta) REFERENCES ventas(id) ON DELETE CASCADE,
                    FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE RESTRICT
                );""";

            stmt.execute(tablaUsuarios);
            stmt.execute(tablaCategorias);
            stmt.execute(tablaProductos);
            stmt.execute(tablaVentas);
            stmt.execute(tablaDetalles);

            System.out.println("Tablas creadas correctamente.");

            // ==========================================
            // 2. INSERCIÓN DE DATOS DE PRUEBA
            // ==========================================
            
            // Usamos OR IGNORE para que si ya existen no duplique ni dé error.
            String insertUsuarios = """
                INSERT OR IGNORE INTO usuarios (username, password, rol) VALUES
                ('admin_carlos', '$2a$10$hash_ficticio_1', 'admin'),
                ('vend_ana', '$2a$10$hash_ficticio_2', 'vendedor'),
                ('vend_luis', '$2a$10$hash_ficticio_3', 'vendedor');""";

            String insertCategorias = """
                INSERT OR IGNORE INTO categorias (nombre, descripcion) VALUES
                ('Bebidas', 'Refrescos, jugos, aguas'),
                ('Snacks', 'Papas, galletas, chocolates'),
                ('Lácteos', 'Leches, quesos, yogures'),
                ('Limpieza', 'Detergentes, cloros, jabones');""";

            String insertProductos = """
                INSERT OR IGNORE INTO productos (codigo_barras, nombre, descripcion, precio_compra, precio_venta, stock_actual, stock_minimo, id_categoria) VALUES
                ('7501055310883', 'Coca-Cola 600ml', 'Refresco de cola regular', 10.50, 15.00, 50, 15, 1),
                ('7501000111201', 'Galletas Oreo', 'Paquete de 114g', 12.00, 18.00, 20, 10, 2),
                ('7501020523450', 'Leche Entera 1L', 'Cartón pasteurizado', 18.50, 24.00, 30, 10, 3),
                ('7501082201015', 'Cloro Líquido 1L', 'Blanqueador botellón', 15.00, 20.00, 25, 5, 4),
                ('7509876543210', 'Agua Natural 1L', 'Botella pet sin gas', 8.00, 13.00, 60, 20, 1);""";

            stmt.execute(insertUsuarios);
            stmt.execute(insertCategorias);
            stmt.execute(insertProductos);

            System.out.println("Datos de prueba inyectados.");
            System.out.println("¡BASE DE DATOS LISTA AL 100%! Puedes entrar al Login.");

        } catch (SQLException e) {
            System.err.println("Error crítico al configurar la Base de Datos: " + e.getMessage());
        }
    }

    // Para ejecutar esto directamente desde tu IDE dando 'Play'
    public static void main(String[] args) {
        inicializarBaseDeDatos();
    }
}
