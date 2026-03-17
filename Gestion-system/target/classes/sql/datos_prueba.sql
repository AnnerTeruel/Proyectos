-- ==========================================
-- SCRIPT DE DATOS DE PRUEBA (SEED)
-- Este archivo carga datos iniciales para hacer pruebas en el sistema.
-- CUIDADO: Ejecutar este script asume que las tablas ya existen (ejecutar schema.sql primero).
-- ==========================================

-- Aseguramos que las llaves foráneas estén activas
PRAGMA foreign_keys = ON;

-- 1. Insertar Usuarios
-- Contraseñas son hashes simulados (siempre debes encriptar en tu código Java, e.g. usando BCrypt)
INSERT INTO usuarios (username, password, rol) VALUES
('admin_carlos', '$2a$10$hash_ficticio_1', 'admin'),
('vend_ana', '$2a$10$hash_ficticio_2', 'vendedor'),
('vend_luis', '$2a$10$hash_ficticio_3', 'vendedor');

-- 2. Insertar Categorías
INSERT INTO categorias (nombre, descripcion) VALUES
('Bebidas', 'Refrescos, jugos, aguas'),
('Snacks', 'Papas, galletas, chocolates'),
('Lácteos', 'Leches, quesos, yogures'),
('Limpieza', 'Detergentes, cloros, jabones');

-- 3. Insertar Productos
INSERT INTO productos (codigo_barras, nombre, descripcion, precio_compra, precio_venta, stock_actual, stock_minimo, id_categoria) VALUES
('7501055310883', 'Coca-Cola 600ml', 'Refresco de cola regular', 10.50, 15.00, 50, 15, 1),
('7501000111201', 'Galletas Oreo', 'Paquete de 114g', 12.00, 18.00, 20, 10, 2),
('7501020523450', 'Leche Entera 1L', 'Cartón pasteurizado', 18.50, 24.00, 30, 10, 3),
('7501082201015', 'Cloro Líquido 1L', 'Blanqueador botellón', 15.00, 20.00, 25, 5, 4),
('7509876543210', 'Agua Natural 1L', 'Botella pet sin gas', 8.00, 13.00, 60, 20, 1);

-- 4. Insertar Ventas (Cabeceras)
-- Nota: La fecha se autogenera si no se especifica, pero aquí ponemos fechas fijas para claridad en las pruebas
INSERT INTO ventas (id_usuario, total_venta, metodo_pago, fecha_venta) VALUES
(2, 45.00, 'efectivo', '2023-10-01 10:30:00'),     -- Atendió Ana
(2, 102.00, 'tarjeta', '2023-10-01 11:15:00'),    -- Atendió Ana
(3, 48.00, 'efectivo', '2023-10-01 12:45:00'),    -- Atendió Luis
(1, 33.00, 'transferencia', '2023-10-02 09:20:00'); -- Atendió Carlos

-- 5. Insertar Detalles de las Ventas
-- Venta 1: 3 Coca-Colas (3 * 15.00 = 45.00)
INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 3, 15.00, 45.00);

-- Venta 2: 3 Galletas Oreo (3 * 18.00 = 54.00) + 2 Leche Entera (2 * 24.00 = 48.00) -> Total = 102.00
INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES
(2, 2, 3, 18.00, 54.00),
(2, 3, 2, 24.00, 48.00);

-- Venta 3: 2 Leches Enteras (2 * 24.00 = 48.00) -> Total = 48.00
INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES
(3, 3, 2, 24.00, 48.00);

-- Venta 4: 1 Cloro (20.00) y 1 Agua Natural (13.00) -> Total = 33.00
INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES
(4, 4, 1, 20.00, 20.00),
(4, 5, 1, 13.00, 13.00);
