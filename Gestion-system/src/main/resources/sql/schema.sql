-- Habilitamos el soporte de llaves foráneas (Debe ejecutarse en cada conexión)
PRAGMA foreign_keys = ON;

-- ==========================================
-- TABLA: usuarios
-- Descripción: Almacena los usuarios que tendrán acceso al sistema.
-- ==========================================
CREATE TABLE IF NOT EXISTS usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,          -- Aquí deberá guardarse el hash (ej. BCrypt), nunca texto plano
    rol TEXT NOT NULL CHECK(rol IN ('admin', 'vendedor')),
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Índice para acelerar el login
CREATE INDEX IF NOT EXISTS idx_usuarios_username ON usuarios(username);

-- ==========================================
-- TABLA: categorias
-- Descripción: Clasificación para ordenar los productos.
-- ==========================================
CREATE TABLE IF NOT EXISTS categorias (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL UNIQUE,
    descripcion TEXT
);

-- ==========================================
-- TABLA: productos
-- Descripción: Catálogo principal del inventario.
-- ==========================================
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
    
    -- Si se borra una categoría, los productos quedan como "Sin categoría" (NULL)
    FOREIGN KEY (id_categoria) REFERENCES categorias(id) ON DELETE SET NULL
);

-- Índice para las búsquedas rápidas con escáner láser en el punto de venta
CREATE INDEX IF NOT EXISTS idx_productos_codigo_barras ON productos(codigo_barras);

-- ==========================================
-- TABLA: ventas
-- Descripción: Cabecera de la transacción / ticket.
-- ==========================================
CREATE TABLE IF NOT EXISTS ventas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER NOT NULL,
    fecha_venta DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_venta REAL NOT NULL CHECK(total_venta >= 0),
    metodo_pago TEXT NOT NULL CHECK(metodo_pago IN ('efectivo', 'tarjeta', 'transferencia')),
    
    -- Evita que se borre un usuario si ya procesó ventas (mantiene auditoría intacta)
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE RESTRICT
);

-- ==========================================
-- TABLA: detalle_venta
-- Descripción: Lista de productos vendidos en cada transacción.
-- ==========================================
CREATE TABLE IF NOT EXISTS detalle_venta (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_venta INTEGER NOT NULL,
    id_producto INTEGER NOT NULL,
    cantidad INTEGER NOT NULL CHECK(cantidad > 0),
    precio_unitario REAL NOT NULL CHECK(precio_unitario >= 0),
    subtotal REAL NOT NULL CHECK(subtotal >= 0),
    
    -- CASCADE: Si se anula (elimina) una venta entera, se borran sus filas de detalle.
    FOREIGN KEY (id_venta) REFERENCES ventas(id) ON DELETE CASCADE,
    
    -- RESTRICT: Evita borrar un producto del sistema si ya se vendió en el pasado.
    FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE RESTRICT
);

-- Nota sobre AUTOINCREMENT en SQLite:
-- Al declarar una columna como "INTEGER PRIMARY KEY AUTOINCREMENT", SQLite 
-- autogenera y gestiona una tabla interna llamada "sqlite_sequence".
-- Se encarga de garantizar que un ID nunca sea reutilizado, incluso si se borra la fila.
