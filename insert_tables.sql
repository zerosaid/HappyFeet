use Happy_Feet;

-- Inserciones en la tabla especies
INSERT INTO especies (id, nombre) VALUES
(1, 'Perro'),
(2, 'Gato'),
(3, 'Ave'),
(4, 'Reptil'),
(5, 'Roedor');

-- Inserciones en la tabla razas
INSERT INTO razas (id, especie_id, nombre) VALUES
-- Perros
(1, 1, 'Labrador Retriever'),
(2, 1, 'Pastor Alemán'),
(3, 1, 'Bulldog'),
(4, 1, 'Golden Retriever'),
(5, 1, 'Chihuahua'),

-- Gatos
(6, 2, 'Persa'),
(7, 2, 'Siamés'),
(8, 2, 'Maine Coon'),
(9, 2, 'Bengalí'),
(10, 2, 'Esfinge'),

-- Aves
(11, 3, 'Canario'),
(12, 3, 'Periquito'),
(13, 3, 'Cacatúa'),

-- Reptiles
(14, 4, 'Iguana'),
(15, 4, 'Camaleón'),
(16, 4, 'Serpiente Pitón'),

-- Roedores
(17, 5, 'Hamster'),
(18, 5, 'Cobayo'),
(19, 5, 'Rata'),
(20, 5, 'Ratón');


CREATE TABLE producto_subcategorias (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL, -- Ej: 'Anestésicos', 'Analgésicos', etc.
  producto_tipo_id INT NOT NULL,
  FOREIGN KEY (producto_tipo_id) REFERENCES producto_tipos(id)
);

ALTER TABLE inventario
DROP column subcategoria;

ALTER TABLE inventario
ADD CONSTRAINT fk_producto_subcategoria
FOREIGN KEY (producto_subcategoria_id) REFERENCES producto_subcategorias(id);

ALTER TABLE inventario
ADD COLUMN id_proveedor INT;

ALTER TABLE inventario
ADD CONSTRAINT fk_proveedor_id
FOREIGN KEY (proveedor_id) REFERENCES proveedores(id);

INSERT INTO producto_tipos (id, nombre) VALUES
(1, 'Medicamentos'),
(2, 'Vacunas'),
(3, 'Insumos Médicos'),
(4, 'Alimentos');

INSERT INTO producto_subcategorias (id, nombre, producto_tipo_id) VALUES
(1, 'Anestésicos', 1),
(2, 'Analgésicos / Antiinflamatorios', 1),
(3, 'Antibióticos', 1),
(4, 'Antiparasitarios', 1),
(5, 'Corticoides / Inmunosupresores', 1),
(6, 'Gastroprotectores / Metabólicos', 1),
(7, 'Suplementos y Vitaminas', 1),
(8, 'Otros Medicamentos Especiales', 1),
(9, 'Vacunas Caninos', 2),
(10, 'Vacunas Felinos', 2),
(11, 'Vacunas Otras especies', 2),
(12, 'Material Quirúrgico', 3),
(13, 'Esterilización y Desinfección', 3),
(14, 'Suministros de Inyección y Vía Endovenosa', 3),
(15, 'Material de Curación', 3),
(16, 'Diagnóstico y Examen', 3),
(17, 'Limpieza General', 3),
(18, 'Alimentos para Caninos', 4),
(19, 'Alimentos para Felinos', 4),
(20, 'Alimentos para Animales Exóticos', 4);


INSERT INTO inventario (
    nombre_producto, producto_tipo_id, descripcion, fabricante, lote,
    cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, producto_subcategoria_id
) VALUES
-- Medicamentos: Anestésicos
('Propofol', 1, 'Anestésico intravenoso', 'Laboratorio X', 'L001', 50, 10, '2026-06-30', 45000.00, 1),
('Ketamina', 1, 'Anestésico disociativo', 'Laboratorio Y', 'L002', 40, 8, '2025-12-31', 38000.00, 1),
('Medetomidina', 1, 'Anestésico sedante', 'Laboratorio Z', 'L003', 30, 5, '2025-10-31', 32000.00, 1),
('Isofluorano (inhalatorio)', 1, 'Anestésico inhalatorio', 'Laboratorio A', 'L004', 25, 5, '2026-01-31', 67000.00, 1),
('Diazepam', 1, 'Ansiolítico y sedante', 'Laboratorio B', 'L005', 60, 15, '2026-05-31', 15000.00, 1),
('Lidocaína (anestesia local)', 1, 'Anestésico local', 'Laboratorio C', 'L006', 70, 20, '2026-08-31', 12000.00, 1),

-- Medicamentos: Analgésicos / Antiinflamatorios
('Meloxicam', 1, 'Antiinflamatorio no esteroideo', 'Laboratorio D', 'L007', 55, 12, '2025-11-30', 25000.00, 2),
('Carprofeno', 1, 'Antiinflamatorio para perros', 'Laboratorio E', 'L008', 45, 10, '2026-02-28', 27000.00, 2),
('Tramadol', 1, 'Analgésico opioide', 'Laboratorio F', 'L009', 35, 8, '2025-09-30', 30000.00, 2),
('Buprenorfina', 1, 'Analgésico opioide', 'Laboratorio G', 'L010', 20, 5, '2026-03-31', 35000.00, 2),
('Prednisolona', 1, 'Corticoide', 'Laboratorio H', 'L011', 40, 10, '2026-07-31', 18000.00, 2),
('Dexametasona', 1, 'Corticoide potente', 'Laboratorio I', 'L012', 60, 15, '2026-04-30', 22000.00, 2),

-- Medicamentos: Antibióticos
('Cefalexina', 1, 'Antibiótico de amplio espectro', 'Laboratorio J', 'L013', 70, 20, '2025-12-31', 28000.00, 3),
('Amoxicilina + Ácido clavulánico', 1, 'Antibiótico combinado', 'Laboratorio K', 'L014', 65, 18, '2026-06-30', 32000.00, 3),
('Enrofloxacina', 1, 'Antibiótico fluoroquinolona', 'Laboratorio L', 'L015', 50, 15, '2025-10-31', 40000.00, 3),
('Clindamicina', 1, 'Antibiótico lincosamida', 'Laboratorio M', 'L016', 45, 12, '2026-01-31', 30000.00, 3),
('Gentamicina', 1, 'Antibiótico aminoglucósido', 'Laboratorio N', 'L017', 40, 10, '2026-05-31', 35000.00, 3),
('Metronidazol', 1, 'Antibiótico y antiparasitario', 'Laboratorio O', 'L018', 55, 15, '2026-08-31', 27000.00, 3),

-- Medicamentos: Antiparasitarios
('Ivermectina', 1, 'Antiparasitario sistémico', 'Laboratorio P', 'L019', 80, 25, '2026-07-31', 20000.00, 4),
('Fenbendazol', 1, 'Antiparasitario de amplio espectro', 'Laboratorio Q', 'L020', 90, 30, '2026-03-31', 22000.00, 4),
('Praziquantel', 1, 'Antiparasitario para cestodos', 'Laboratorio R', 'L021', 60, 20, '2026-06-30', 28000.00, 4),
('Moxidectina', 1, 'Antiparasitario endectocida', 'Laboratorio S', 'L022', 55, 18, '2025-12-31', 35000.00, 4),
('Selamectina', 1, 'Antiparasitario tópico', 'Laboratorio T', 'L023', 50, 15, '2026-04-30', 32000.00, 4),

-- Medicamentos: Corticoides / Inmunosupresores
('Dexametasona', 1, 'Corticoide potente', 'Laboratorio U', 'L024', 60, 20, '2026-01-31', 22000.00, 5),
('Prednisolona', 1, 'Corticoide', 'Laboratorio V', 'L025', 45, 15, '2026-03-31', 18000.00, 5),

-- Medicamentos: Gastroprotectores / Metabólicos
('Omeprazol', 1, 'Inhibidor de la bomba de protones', 'Laboratorio W', 'L026', 40, 10, '2026-08-31', 25000.00, 6),
('Ranitidina', 1, 'Antagonista H2', 'Laboratorio X', 'L027', 50, 15, '2025-11-30', 18000.00, 6),
('Sucralfato', 1, 'Protector gástrico', 'Laboratorio Y', 'L028', 35, 10, '2026-05-31', 15000.00, 6),

-- Medicamentos: Suplementos y Vitaminas
('Complejo B', 1, 'Vitaminas del grupo B', 'Laboratorio Z', 'L029', 70, 20, '2026-09-30', 12000.00, 7),
('Hierro', 1, 'Suplemento de hierro', 'Laboratorio A', 'L030', 60, 18, '2026-04-30', 14000.00, 7),
('Multivitamínicos', 1, 'Suplemento multivitamínico', 'Laboratorio B', 'L031', 50, 15, '2026-07-31', 16000.00, 7),

-- Medicamentos: Otros Medicamentos Especiales
('Atropina (bradicardia, premedicación)', 1, 'Bradicardia y premedicación', 'Laboratorio C', 'L032', 30, 10, '2026-06-30', 30000.00, 8),
('Succinilcolina (relajante muscular)', 1, 'Relajante muscular', 'Laboratorio D', 'L033', 25, 8, '2025-12-31', 35000.00, 8),
('Suero glucosado / fisiológico / Ringer lactato', 1, 'Sueros para fluidoterapia', 'Laboratorio E', 'L034', 100, 40, '2026-05-31', 15000.00, 8),
('Epinefrina (emergencias)', 1, 'Uso en emergencias', 'Laboratorio F', 'L035', 40, 12, '2026-04-30', 40000.00, 8),

-- Vacunas Caninos
('Moquillo', 2, 'Vacuna para moquillo canino', 'Laboratorio G', 'L036', 80, 20, '2026-08-31', 80000.00, 9),
('Parvovirus', 2, 'Vacuna para parvovirus canino', 'Laboratorio H', 'L037', 70, 15, '2026-12-31', 85000.00, 9),
('Rabia', 2, 'Vacuna antirrábica', 'Laboratorio I', 'L038', 90, 25, '2027-01-31', 90000.00, 9),
('Leptospirosis', 2, 'Vacuna contra leptospirosis', 'Laboratorio J', 'L039', 65, 18, '2026-11-30', 75000.00, 9),
('Bordetella (Tos de las perreras)', 2, 'Vacuna Bordetella', 'Laboratorio K', 'L040', 50, 15, '2026-10-31', 70000.00, 9),

-- Vacunas Felinos
('Triple felina (panleucopenia, rinotraqueitis, calicivirus)', 2, 'Vacuna triple felina', 'Laboratorio L', 'L041', 60, 15, '2026-09-30', 85000.00, 10),
('Rabia', 2, 'Vacuna antirrábica felina', 'Laboratorio M', 'L042', 55, 12, '2026-12-31', 90000.00, 10),

-- Vacunas Otras especies
('Vacunas específicas para animales exóticos o grandes animales (equinos, aves, etc.)', 2, 'Vacunas especiales', 'Laboratorio N', 'L043', 40, 10, '2026-08-31', 120000.00, 11),

-- Insumos Médicos Material Quirúrgico
('Bisturís', 3, 'Instrumento quirúrgico', 'Proveedor A', 'L044', 100, 40, NULL, 5000.00, 12),
('Hojas de bisturí', 3, 'Hojas para bisturí', 'Proveedor B', 'L045', 150, 50, NULL, 2000.00, 12),
('Pinzas (hemostáticas, de disección, etc.)', 3, 'Pinzas quirúrgicas', 'Proveedor C', 'L046', 120, 40, NULL, 4500.00, 12),
('Porta agujas', 3, 'Instrumento quirúrgico', 'Proveedor D', 'L047', 110, 35, NULL, 4800.00, 12),
('Tijeras quirúrgicas', 3, 'Tijeras para cirugía', 'Proveedor E', 'L048', 100, 30, NULL, 4700.00, 12),
('Campos quirúrgicos', 3, 'Campos estériles', 'Proveedor F', 'L049', 200, 70, NULL, 1200.00, 12),
('Guantes estériles', 3, 'Guantes para cirugía', 'Proveedor G', 'L050', 300, 100, NULL, 3500.00, 12),
('Ropa quirúrgica', 3, 'Ropa para quirófano', 'Proveedor H', 'L051', 150, 50, NULL, 6500.00, 12),

-- Insumos Médicos Esterilización y Desinfección
('Solución desinfectante', 3, 'Desinfectante para superficies', 'Proveedor I', 'L052', 250, 80, NULL, 9000.00, 13),
('Autoclave', 3, 'Esterilizador', 'Proveedor J', 'L053', 5, 2, NULL, 1200000.00, 13),

-- Insumos Médicos Suministros de Inyección y Vía Endovenosa
('Jeringas', 3, 'Jeringas descartables', 'Proveedor K', 'L054', 1000, 400, NULL, 2500.00, 14),
('Agujas', 3, 'Agujas para inyección', 'Proveedor L', 'L055', 1500, 500, NULL, 1500.00, 14),
('Catéteres intravenosos', 3, 'Catéteres IV', 'Proveedor M', 'L056', 500, 200, NULL, 7000.00, 14),

-- Insumos Médicos Material de Curación
('Vendajes', 3, 'Vendajes para heridas', 'Proveedor N', 'L057', 800, 300, NULL, 4000.00, 15),
('Gasas', 3, 'Gasas estériles', 'Proveedor O', 'L058', 1000, 350, NULL, 3500.00, 15),
('Esparadrapo', 3, 'Cinta adhesiva', 'Proveedor P', 'L059', 900, 300, NULL, 2500.00, 15),

-- Insumos Médicos Diagnóstico y Examen
('Termómetros', 3, 'Termómetro digital', 'Proveedor Q', 'L060', 100, 40, NULL, 15000.00, 16),
('Microscopios', 3, 'Microscopio óptico', 'Proveedor R', 'L061', 10, 3, NULL, 500000.00, 16),

-- Insumos Médicos Limpieza General
('Jabón antibacterial', 3, 'Jabón para manos', 'Proveedor S', 'L062', 300, 100, NULL, 12000.00, 17),
('Guantes de látex', 3, 'Guantes para limpieza', 'Proveedor T', 'L063', 500, 150, NULL, 10000.00, 17),

-- Alimentos para Caninos
('Alimento balanceado para perros adultos', 4, 'Alimento seco para perros adultos', 'NutriPet', 'ALC123', 150, 40, '2025-12-31', 90000.00, 18),
-- Alimentos para Felinos
('Alimento húmedo para gatos', 4, 'Alimento húmedo para gatos adultos', 'CatFood', 'ALF456', 120, 30, '2025-11-30', 75000.00, 19),
-- Alimentos para Animales Exóticos
('Alimento para conejos', 4, 'Mezcla balanceada para conejos', 'ExoticPets', 'ALE789', 80, 20, '2026-02-28', 60000.00, 20);

UPDATE inventario
SET proveedor_id = CASE fabricante
    WHEN 'Laboratorio X' THEN 50
    WHEN 'Laboratorio Y' THEN 51
    WHEN 'Laboratorio Z' THEN 52
    WHEN 'Laboratorio A' THEN 53
    WHEN 'Laboratorio B' THEN 54
    WHEN 'Laboratorio C' THEN 55
    WHEN 'Laboratorio D' THEN 56
    WHEN 'Laboratorio E' THEN 57
    WHEN 'Laboratorio F' THEN 58
    WHEN 'Laboratorio G' THEN 59
    WHEN 'Laboratorio H' THEN 60
    WHEN 'Laboratorio I' THEN 61
    WHEN 'Laboratorio J' THEN 62
    WHEN 'Laboratorio K' THEN 63
    WHEN 'Laboratorio L' THEN 64
    WHEN 'Laboratorio M' THEN 65
    WHEN 'Laboratorio N' THEN 66
    WHEN 'Laboratorio O' THEN 67
    WHEN 'Laboratorio P' THEN 68
    WHEN 'Laboratorio Q' THEN 69
    WHEN 'Laboratorio R' THEN 70
    WHEN 'Laboratorio S' THEN 71
    WHEN 'Laboratorio T' THEN 72
    WHEN 'Laboratorio U' THEN 73
    WHEN 'Laboratorio V' THEN 74
    WHEN 'Laboratorio W' THEN 75
    WHEN 'Proveedor A' THEN 76
    WHEN 'Proveedor B' THEN 77
    WHEN 'Proveedor C' THEN 78
    WHEN 'Proveedor D' THEN 79
    WHEN 'Proveedor E' THEN 80
    WHEN 'Proveedor F' THEN 81
    WHEN 'Proveedor G' THEN 82
    WHEN 'Proveedor H' THEN 83
    WHEN 'Proveedor I' THEN 84
    WHEN 'Proveedor J' THEN 85
    WHEN 'Proveedor K' THEN 86
    WHEN 'Proveedor L' THEN 87
    WHEN 'Proveedor M' THEN 88
    WHEN 'Proveedor N' THEN 89
    WHEN 'Proveedor O' THEN 90
    WHEN 'Proveedor P' THEN 91
    WHEN 'Proveedor Q' THEN 92
    WHEN 'Proveedor R' THEN 93
    WHEN 'Proveedor S' THEN 94
    WHEN 'Proveedor T' THEN 95
    WHEN 'NutriPet' THEN 96
    WHEN 'CatFood' THEN 97
    WHEN 'ExoticPets' THEN 98
    ELSE NULL
END;

DELETE FROM items_factura;
DELETE FROM inventario;

DELETE FROM items_factura;
DELETE FROM producto_subcategorias ;

DELETE FROM items_factura;
DELETE FROM producto_tipos;


CREATE TABLE roles (
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

-- Insertar roles
INSERT INTO roles (nombre_rol, descripcion) VALUES
('Veterinario', 'Profesional encargado de diagnosticar y tratar animales'),
('Técnico Veterinario', 'Asiste al veterinario en procedimientos médicos y cuidados de los animales'),
('Auxiliar Veterinario', 'Apoya en tareas básicas de manejo, limpieza y organización de la clínica'),
('Recepcionista', 'Atiende al cliente, agenda citas, responde llamadas y gestiona pagos'),
('Gerente de Clínica', 'Supervisa la operación general de la veterinaria y gestiona el personal'),
('Peluquero Canino', 'Realiza grooming y baño de mascotas para mantener su higiene y estética'),
('Limpieza y Mantenimiento', 'Mantiene limpias las instalaciones, desinfecta áreas y organiza espacios'),
('Nutricionista Animal', 'Asesora sobre dietas y alimentación adecuada para diferentes tipos de animales'),
('Especialista en Comportamiento', 'Ayuda a corregir problemas de conducta y realiza entrenamientos de mascotas'),
('Farmacéutico Veterinario', 'Dispensa medicamentos y asesora sobre tratamientos farmacológicos');


INSERT INTO empleados (nombre, apellido, documento_identidad, telefono, email, direccion, fecha_ingreso, id_rol) VALUES
-- Veterinarios
('Ana', 'Martínez', '90837465', '3001112222', 'ana@veterinaria.com', 'Calle 10 #20-30, Bogotá, Colombia', '2023-01-15', 1),  
('Carlos', 'Gómez', '10384920', '3003334444', 'carlos@veterinaria.com', 'Carrera 15 #45-60, Medellín, Colombia', '2023-03-10', 1),  
('Laura', 'Pérez', '45820394', '3005556666', 'laura@veterinaria.com', 'Av. 5 #70-80, Cali, Colombia', '2023-05-01', 1),  
('Jorge', 'Herrera', '30294750', '3017778888', 'jorge@veterinaria.com', 'Calle 7 #10-20, Barranquilla, Colombia', '2023-06-12', 1),

-- Técnicos Veterinarios
('Sofía', 'Ramírez', '76839250', '3009990000', 'sofia@veterinaria.com', 'Carrera 9 #50-65, Bucaramanga, Colombia', '2023-02-05', 2),  
('Diego', 'Vargas', '95732084', '3011234567', 'diego@veterinaria.com', 'Av. 3 #12-34, Pereira, Colombia', '2023-03-20', 2),  
('Ricardo', 'González', '60489231', '3022345678', 'ricardo@veterinaria.com', 'Calle 8 #22-44, Cartagena, Colombia', '2023-04-15', 2),  
('Valentina', 'Ruiz', '48039265', '3034567890', 'valentina@veterinaria.com', 'Av. 9 #24-48, Neiva, Colombia', '2023-05-30', 2),

-- Auxiliares Veterinarios
('Andrés', 'Gómez', '30184852', '3025678901', 'andres@veterinaria.com', 'Calle 6 #14-36, Ibagué, Colombia', '2023-06-21', 3),  
('Catalina', 'López', '73948206', '3036789012', 'catalina@veterinaria.com', 'Carrera 11 #27-50, Santa Marta, Colombia', '2023-07-30', 3),  
('Paula', 'Sánchez', '68234750', '3039012345', 'paula@veterinaria.com', 'Carrera 10 #40-60, Popayán, Colombia', '2023-08-15', 3),

-- Recepcionistas
('Fernando', 'Torres', '81493762', '3040123456', 'fernando@veterinaria.com', 'Calle 3 #7-19, Montería, Colombia', '2023-02-20', 4),  
('Natalia', 'Rojas', '57293841', '3051234567', 'natalia@veterinaria.com', 'Carrera 8 #13-27, Pasto, Colombia', '2023-04-10', 4),  
('Javier', 'Hernández', '45928370', '3062345678', 'javier@veterinaria.com', 'Av. 12 #16-34, Manizales, Colombia', '2023-06-08', 4),

-- Gerentes de Clínica
('Claudia', 'Morales', '30942817', '3073456789', 'claudia@veterinaria.com', 'Calle 4 #18-28, Barranquilla, Colombia', '2023-01-22', 5),  
('Ricardo', 'Sánchez', '50398276', '3084567890', 'ricardo@veterinaria.com', 'Carrera 11 #20-40, Bogotá, Colombia', '2023-03-12', 5),

-- Peluqueros Caninos
('Paola', 'Castillo', '51823946', '3095678901', 'paola@veterinaria.com', 'Av. 14 #22-42, San Andrés, Colombia', '2023-06-14', 6),  
('Esteban', 'Martínez', '62983740', '3106789012', 'esteban@veterinaria.com', 'Carrera 16 #25-45, Leticia, Colombia', '2023-07-30', 6),  
('Diana', 'Morales', '48302751', '3117890123', 'diana@veterinaria.com', 'Calle 5 #19-35, Florencia, Colombia', '2023-08-25', 6),

-- Limpieza y Mantenimiento
('Mario', 'González', '38049276', '3128901234', 'mario@veterinaria.com', 'Av. 15 #30-50, Riohacha, Colombia', '2023-01-18', 7),  
('Lucía', 'Fernández', '92837461', '3139012345', 'lucia@veterinaria.com', 'Calle 9 #25-40, Pasto, Colombia', '2023-04-09', 7),

-- Nutricionistas Animales
('Carlos', 'López', '18503492', '3140123456', 'carlos@veterinaria.com', 'Carrera 3 #11-22, Tunja, Colombia', '2023-03-25', 8),  
('María', 'Pérez', '74629381', '3151234567', 'maria@veterinaria.com', 'Calle 7 #28-44, Ibagué, Colombia', '2023-05-10', 8),

-- Especialistas en Comportamiento
('Andrés', 'Torres', '23895764', '3162345678', 'andres@veterinaria.com', 'Av. 5 #13-25, Cali, Colombia', '2023-04-14', 9),  
('Lucía', 'Ramírez', '39576284', '3173456789', 'lucia@veterinaria.com', 'Calle 6 #10-30, Bucaramanga, Colombia', '2023-06-22', 9),

-- Farmacéuticos Veterinarios
('María', 'Gómez', '68493021', '3184567890', 'maria@veterinaria.com', 'Calle 11 #33-53, Popayán, Colombia', '2023-05-17', 10),  
('Ricardo', 'Sánchez', '93048361', '3195678901', 'ricardo@veterinaria.com', 'Carrera 7 #18-38, Valledupar, Colombia', '2023-07-09', 10);


DELETE FROM items_factura;
DELETE FROM empleados;

INSERT INTO duenos (
    nombre_completo, documento_identidad, direccion, telefono, email
) VALUES
('Juan Pérez', '12345678A', 'Calle Ficticia 123, Ciudad X', '987654321', 'juan.perez@email.com'),
('María Gómez', '87654321B', 'Avenida Siempre Viva 456, Ciudad Y', '912345678', 'maria.gomez@email.com'),
('Carlos López', '11223344C', 'Calle Larga 789, Ciudad Z', '934567890', 'carlos.lopez@email.com'),
('Ana Rodríguez', '22334455D', 'Calle Real 101, Ciudad W', '921234567', 'ana.rodriguez@email.com'),
('Luis Martínez', '33445566E', 'Calle Sol 202, Ciudad V', '930123456', 'luis.martinez@email.com'),
('Laura Fernández', '44556677F', 'Calle Luna 303, Ciudad U', '922345678', 'laura.fernandez@email.com'),
('Pedro Sánchez', '55667788G', 'Calle Agua 404, Ciudad T', '987654123', 'pedro.sanchez@email.com'),
('Beatriz Torres', '66778899H', 'Calle Viento 505, Ciudad S', '913456789', 'beatriz.torres@email.com'),
('Raúl González', '77889900I', 'Calle Nieve 606, Ciudad R', '914567890', 'raul.gonzalez@email.com'),
('Susana Morales', '88990011J', 'Calle Cielo 707, Ciudad Q', '915678901', 'susana.morales@email.com');