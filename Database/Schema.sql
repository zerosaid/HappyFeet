create database Happy_Feet;

-- =========== TABLAS DE CONSULTA (LOOKUP TABLES) ===========

CREATE TABLE especies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL -- Ej: 'Perro', 'Gato', 'Ave'
);

CREATE TABLE razas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    especie_id INT,
    nombre VARCHAR(100) NOT NULL,
    FOREIGN KEY (especie_id) REFERENCES especies(id)
);

CREATE TABLE producto_tipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL -- 'Medicamento', 'Vacuna', 'Insumo Médico', 'Alimento'
);

CREATE TABLE evento_tipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL -- 'Vacunación', 'Consulta', 'Cirugía', 'Desparasitación'
);

CREATE TABLE cita_estados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL -- 'Programada', 'En Proceso', 'Finalizada', 'Cancelada'
);
​
​
-- =========== TABLAS OPERACIONALES DEL NEGOCIO ===========

CREATE TABLE duenos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    documento_identidad VARCHAR(20) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE mascotas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dueno_id INT,
    nombre VARCHAR(100) NOT NULL,
    raza_id INT,
    fecha_nacimiento DATE,
    sexo ENUM('Macho', 'Hembra'),
    url_foto VARCHAR(255),
    FOREIGN KEY (dueno_id) REFERENCES duenos(id),
    FOREIGN KEY (raza_id) REFERENCES razas(id)
);

CREATE TABLE historial_medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mascota_id INT,
    fecha_evento DATE NOT NULL,
    evento_tipo_id INT,
    descripcion TEXT,
    diagnostico TEXT,
    tratamiento_recomendado TEXT,
    FOREIGN KEY (mascota_id) REFERENCES mascotas(id),
    FOREIGN KEY (evento_tipo_id) REFERENCES evento_tipos(id)
);

CREATE TABLE inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(255) NOT NULL,
    producto_tipo_id INT,
    descripcion TEXT,
    fabricante VARCHAR(100),
    lote VARCHAR(50),
    cantidad_stock INT NOT NULL,
    stock_minimo INT NOT NULL,
    fecha_vencimiento DATE,
    precio_venta DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (producto_tipo_id) REFERENCES producto_tipos(id)
);

CREATE TABLE citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mascota_id INT,
    fecha_hora DATETIME NOT NULL,
    motivo VARCHAR(255),
    estado_id INT,
    FOREIGN KEY (mascota_id) REFERENCES mascotas(id),
    FOREIGN KEY (estado_id) REFERENCES cita_estados(id)
);

CREATE TABLE facturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dueno_id INT,
    fecha_emision DATETIME NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (dueno_id) REFERENCES duenos(id)
);

CREATE TABLE items_factura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    factura_id INT,
    producto_id INT, -- Puede ser un producto del inventario
    servicio_descripcion VARCHAR(255), -- O un servicio
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (factura_id) REFERENCES facturas(id),
    FOREIGN KEY (producto_id) REFERENCES inventario(id)
);

use Happy_Feet;


CREATE TABLE producto_subcategorias (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL, -- Ej: 'Anestésicos', 'Analgésicos', etc.
  producto_tipo_id INT NOT NULL,
  FOREIGN KEY (producto_tipo_id) REFERENCES producto_tipos(id)
);

CREATE TABLE proveedores (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- ID único del proveedor
    nombre VARCHAR(255) NOT NULL,  -- Nombre del proveedor
    contacto VARCHAR(255) NOT NULL,  -- Persona de contacto
    telefono VARCHAR(15),  -- Número de teléfono
    email VARCHAR(255),  -- Correo electrónico
    direccion VARCHAR(255)  -- Dirección del proveedor
);

ALTER TABLE inventario
ADD COLUMN producto_subcategoria_id INT;

ALTER TABLE inventario
ADD CONSTRAINT fk_producto_subcategoria
FOREIGN KEY (producto_subcategoria_id) REFERENCES producto_subcategorias(id);


ALTER TABLE inventario
ADD COLUMN proveedor_id INT;

ALTER TABLE inventario
ADD CONSTRAINT fk_proveedor_id
FOREIGN KEY (proveedor_id) REFERENCES proveedores(id);

DELETE FROM proveedores;

UPDATE inventario
SET proveedor_id = CASE fabricante
    WHEN 'Laboratorio X' THEN 1
    WHEN 'Laboratorio Y' THEN 2
    WHEN 'Laboratorio Z' THEN 3
    WHEN 'Laboratorio A' THEN 4
    WHEN 'Laboratorio B' THEN 5
    WHEN 'Laboratorio C' THEN 6
    WHEN 'Laboratorio D' THEN 7
    WHEN 'Laboratorio E' THEN 8
    WHEN 'Laboratorio F' THEN 9
    WHEN 'Laboratorio G' THEN 10
    WHEN 'Laboratorio H' THEN 11
    WHEN 'Laboratorio I' THEN 12
    WHEN 'Laboratorio J' THEN 13
    WHEN 'Laboratorio K' THEN 14
    WHEN 'Laboratorio L' THEN 15
    WHEN 'Laboratorio M' THEN 16
    WHEN 'Laboratorio N' THEN 17
    WHEN 'Laboratorio O' THEN 18
    WHEN 'Laboratorio P' THEN 19
    WHEN 'Laboratorio Q' THEN 20
    WHEN 'Laboratorio R' THEN 21
    WHEN 'Laboratorio S' THEN 22
    WHEN 'Laboratorio T' THEN 23
    WHEN 'Laboratorio U' THEN 24
    WHEN 'Laboratorio V' THEN 25
    WHEN 'Laboratorio W' THEN 26
    WHEN 'Proveedor A' THEN 27
    WHEN 'Proveedor B' THEN 28
    WHEN 'Proveedor C' THEN 29
    WHEN 'Proveedor D' THEN 30
    WHEN 'Proveedor E' THEN 31
    WHEN 'Proveedor F' THEN 32
    WHEN 'Proveedor G' THEN 33
    WHEN 'Proveedor H' THEN 34
    WHEN 'Proveedor I' THEN 35
    WHEN 'Proveedor J' THEN 36
    WHEN 'Proveedor K' THEN 37
    WHEN 'Proveedor L' THEN 38
    WHEN 'Proveedor M' THEN 39
    WHEN 'Proveedor N' THEN 40
    WHEN 'Proveedor O' THEN 41
    WHEN 'Proveedor P' THEN 42
    WHEN 'Proveedor Q' THEN 43
    WHEN 'Proveedor R' THEN 44
    WHEN 'Proveedor S' THEN 45
    WHEN 'Proveedor T' THEN 46
    WHEN 'NutriPet' THEN 47
    WHEN 'CatFood' THEN 48
    WHEN 'ExoticPets' THEN 49
    ELSE NULL
END;

DELETE FROM items_factura;
DELETE FROM inventario;

DELETE FROM items_factura;
DELETE FROM producto_subcategorias ;

DELETE FROM items_factura;
DELETE FROM producto_tipos;

DELETE FROM items_factura;
DELETE FROM empleados;

CREATE TABLE adoptantes (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    telefono VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE contratos_adopcion (
    id INT PRIMARY KEY,
    id_mascota INT,
    id_adoptante INT,
    fecha_adopcion DATE,
    contrato_texto TEXT,
    FOREIGN KEY(id_mascota) REFERENCES mascotas(id),
    FOREIGN KEY(id_adoptante) REFERENCES adoptantes(id)
);

DELETE FROM items_factura;
DELETE FROM inventario;

DELETE FROM items_factura;
DELETE FROM producto_subcategorias ;

DELETE FROM items_factura;
DELETE FROM producto_tipos;CREATE TABLE roles (
    id_rol INT PRIMARY KEY AUTO_INCREMENT,
    nombre_rol VARCHAR(100) NOT NULL,
    descripcion TEXT
);

CREATE TABLE empleados (
    id_empleado INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    documento_identidad VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(150),
    fecha_ingreso DATE,
    id_rol INT,
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);