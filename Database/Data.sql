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

-- Inserción de Laboratorios
INSERT INTO proveedores (nombre, contacto, telefono, email, direccion)
VALUES
('Laboratorio A', 'Miguel Ángel', '310 444 4444', 'contacto@laboratorioa.com', 'Calle 7 #10-20, Pereira, Colombia'),
('Laboratorio B', 'Mariana Torres', '310 555 5555', 'contacto@laboratoriob.com', 'Carrera 8 #30-40, Cartagena, Colombia'),
('Laboratorio C', 'José Ramírez', '310 666 6666', 'contacto@laboratorioc.com', 'Calle 11 #15-20, Cali, Colombia'),
('Laboratorio D', 'Andrés Pérez', '310 777 7777', 'contacto@laboratoriod.com', 'Carrera 5 #55-15, Bogotá, Colombia'),
('Laboratorio E', 'Lucía Fernández', '310 888 8888', 'contacto@laboratorioe.com', 'Calle 3 #25-30, Medellín, Colombia'),
('Laboratorio F', 'Fernando Torres', '310 999 9999', 'contacto@laboratoriof.com', 'Calle 9 #20-30, Barranquilla, Colombia'),
('Laboratorio G', 'Paola Sánchez', '310 101 0101', 'contacto@laboratoriog.com', 'Carrera 7 #10-30, Cali, Colombia'),
('Laboratorio H', 'Juan Carlos López', '310 202 0202', 'contacto@laboratorioh.com', 'Calle 12 #50-60, Cartagena, Colombia'),
('Laboratorio I', 'Carlos Martínez', '310 303 0303', 'contacto@laboratorioi.com', 'Av. 3 #15-25, Medellín, Colombia'),
('Laboratorio J', 'Ana Gómez', '310 404 0404', 'contacto@laboratorioj.com', 'Calle 5 #30-40, Bogotá, Colombia'),
('Laboratorio K', 'Sandra Rodríguez', '310 505 0505', 'contacto@laboratoriok.com', 'Carrera 2 #10-20, Barranquilla, Colombia'),
('Laboratorio L', 'Ricardo Jiménez', '310 606 0606', 'contacto@laboratoriol.com', 'Av. 8 #35-40, Cartagena, Colombia'),
('Laboratorio M', 'Patricia López', '310 707 0707', 'contacto@laboratoriom.com', 'Calle 10 #20-30, Cali, Colombia'),
('Laboratorio N', 'Claudia Hernández', '310 808 0808', 'contacto@laboratorion.com', 'Carrera 3 #25-35, Bogotá, Colombia'),
('Laboratorio O', 'Javier Hernández', '310 909 0909', 'contacto@laboratorioo.com', 'Calle 15 #40-50, Barranquilla, Colombia'),
('Laboratorio P', 'Laura Sánchez', '310 111 1111', 'contacto@laboratoriop.com', 'Av. 10 #55-65, Medellín, Colombia'),
('Laboratorio Q', 'Miguel Ángel Gómez', '310 222 2222', 'contacto@laboratorioq.com', 'Carrera 1 #30-40, Cali, Colombia'),
('Laboratorio R', 'Alejandra Martínez', '310 333 3333', 'contacto@laboratorior.com', 'Calle 20 #45-50, Bogotá, Colombia'),
('Laboratorio S', 'Felipe González', '310 444 4444', 'contacto@laboratorios.com', 'Carrera 6 #30-35, Cartagena, Colombia'),
('Laboratorio T', 'Patricia Rodríguez', '310 555 5555', 'contacto@laboratoriot.com', 'Calle 7 #35-40, Medellín, Colombia'),
('Laboratorio U', 'Luis Martínez', '310 666 6666', 'contacto@laboratoriou.com', 'Av. 12 #10-15, Barranquilla, Colombia'),
('Laboratorio V', 'Carlos Pérez', '310 777 7777', 'contacto@laboratoriov.com', 'Carrera 3 #50-55, Bogotá, Colombia'),
('Laboratorio W', 'Ana González', '310 888 8888', 'contacto@laboratoriow.com', 'Calle 4 #15-20, Cali, Colombia'),
('Laboratorio X', 'Fernando López', '310 999 9999', 'contacto@laboratoriox.com', 'Carrera 5 #30-40, Medellín, Colombia'),
('Laboratorio Y', 'Lucía Rodríguez', '310 101 0101', 'contacto@laboratorioy.com', 'Calle 6 #20-25, Cartagena, Colombia'),
('Laboratorio Z', 'Juan Gómez', '310 202 0202', 'contacto@laboratorioz.com', 'Av. 7 #35-40, Bogotá, Colombia'),
('Proveedor A', 'Juan Pérez', '310 123 1234', 'contacto@proveedora.com', 'Calle 12 #10-20, Bogotá, Colombia'),
('Proveedor B', 'Luis Gómez', '310 234 2345', 'contacto@proveedorb.com', 'Carrera 4 #15-25, Medellín, Colombia'),
('Proveedor C', 'Mariana López', '310 345 3456', 'contacto@proveedorc.com', 'Av. 9 #20-30, Barranquilla, Colombia'),
('Proveedor D', 'Ricardo Torres', '310 456 4567', 'contacto@proveedord.com', 'Calle 7 #25-35, Cali, Colombia'),
('Proveedor E', 'Carlos Ramírez', '310 567 5678', 'contacto@proveedore.com', 'Carrera 3 #30-40, Cartagena, Colombia'),
('Proveedor F', 'Patricia Fernández', '310 678 6789', 'contacto@proveedorf.com', 'Calle 5 #35-45, Bogotá, Colombia'),
('Proveedor G', 'Felipe Pérez', '310 789 7890', 'contacto@proveedorg.com', 'Carrera 2 #40-50, Medellín, Colombia'),
('Proveedor H', 'Lucía Torres', '310 890 8901', 'contacto@proveedorh.com', 'Calle 8 #45-55, Barranquilla, Colombia'),
('Proveedor I', 'Javier Martínez', '310 901 9012', 'contacto@proveedori.com', 'Carrera 4 #50-60, Cali, Colombia'),
('Proveedor J', 'Sandra López', '310 012 0123', 'contacto@proveedorj.com', 'Calle 6 #55-65, Cartagena, Colombia'),
('Proveedor K', 'Ana Gómez', '310 123 1234', 'contacto@proveedork.com', 'Calle 9 #60-70, Medellín, Colombia'),
('Proveedor L', 'Carlos Rodríguez', '310 234 2345', 'contacto@proveedorl.com', 'Calle 10 #70-80, Barranquilla, Colombia'),
('Proveedor M', 'Ricardo Fernández', '310 345 3456', 'contacto@proveedorm.com', 'Carrera 11 #80-90, Cali, Colombia'),
('Proveedor N', 'Juan Carlos Pérez', '310 456 4567', 'contacto@proveedorn.com', 'Calle 12 #90-100, Cartagena, Colombia'),
('Proveedor O', 'Lucía Martínez', '310 567 5678', 'contacto@proveedoro.com', 'Carrera 5 #100-110, Medellín, Colombia'),
('Proveedor P', 'Patricia López', '310 678 6789', 'contacto@proveedorp.com', 'Calle 15 #110-120, Barranquilla, Colombia'),
('Proveedor Q', 'Carlos González', '310 789 7890', 'contacto@proveedorq.com', 'Carrera 6 #120-130, Cali, Colombia'),
('Proveedor R', 'Javier Ramírez', '310 890 8901', 'contacto@proveedorr.com', 'Calle 16 #130-140, Cartagena, Colombia'),
('Proveedor S', 'Ana Rodríguez', '310 901 9012', 'contacto@proveedors.com', 'Calle 17 #140-150, Medellín, Colombia'),
('Proveedor T', 'Carlos Torres', '310 012 0123', 'contacto@proveedort.com', 'Carrera 8 #150-160, Barranquilla, Colombia'),
('NutriPet', 'Alejandra Pérez', '310 123 1234', 'contacto@nutripet.com', 'Calle 18 #160-170, Bogotá, Colombia'),
('CatFood', 'Felipe Gómez', '310 234 2345', 'contacto@catfood.com', 'Carrera 9 #170-180, Medellín, Colombia'),
('ExoticPets', 'Ricardo Ramírez', '310 345 3456', 'contacto@exoticpets.com', 'Calle 19 #180-190, Barranquilla, Colombia');

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

*-- Inserciones en la tabla especies
INSERT INTO especies (id, nombre) VALUES
(1, 'Perro'),
(2, 'Gato'),
(3, 'Ave'),
(4, 'Reptil'),
(5, 'Roedor');

-- Inserciones en la tabla razas
INSERT INTO razas (id, especie_id, nombre) VALUES
(1, 1, 'Labrador Retriever'),
(2, 1, 'Pastor Alemán'),
(3, 1, 'Bulldog'),
(4, 1, 'Golden Retriever'),
(5, 1, 'Chihuahua'),
(6, 2, 'Persa'),
(7, 2, 'Siamés'),
(8, 2, 'Maine Coon'),
(9, 2, 'Bengalí'),
(10, 2, 'Esfinge'),
(11, 3, 'Canario'),
(12, 3, 'Periquito'),
(13, 3, 'Cacatúa'),
(14, 4, 'Iguana'),
(15, 4, 'Camaleón'),
(16, 4, 'Serpiente Pitón'),
(17, 5, 'Hamster'),
(18, 5, 'Cobayo'),
(19, 5, 'Rata'),
(20, 5, 'Ratón');


INSERT INTO cita_estados (id, nombre) VALUES
(1, 'Pendiente'),
(2, 'Confirmada'),
(3, 'Cancelada'),
(4, 'Completada');

-- Inserciones para eventos veterinarios
INSERT INTO evento_tipos (id, nombre) VALUES (1, 'Consulta general');
INSERT INTO evento_tipos (id, nombre) VALUES (2, 'Vacunación');
INSERT INTO evento_tipos (id, nombre) VALUES (3, 'Desparasitación');
INSERT INTO evento_tipos (id, nombre) VALUES (4, 'Cirugía');
INSERT INTO evento_tipos (id, nombre) VALUES (5, 'Control postoperatorio');
INSERT INTO evento_tipos (id, nombre) VALUES (6, 'Hospitalización');
INSERT INTO evento_tipos (id, nombre) VALUES (7, 'Examen de laboratorio');
INSERT INTO evento_tipos (id, nombre) VALUES (8, 'Control de peso');
INSERT INTO evento_tipos (id, nombre) VALUES (9, 'Urgencia');
INSERT INTO evento_tipos (id, nombre) VALUES (10, 'Chequeo anual');
INSERT INTO evento_tipos (id, nombre) VALUES (11, 'Terapia física');
INSERT INTO evento_tipos (id, nombre) VALUES (12, 'Baño medicado');
INSERT INTO evento_tipos (id, nombre) VALUES (13, 'Esterilización');
INSERT INTO evento_tipos (id, nombre) VALUES (14, 'Implantación de microchip');
INSERT INTO evento_tipos (id, nombre) VALUES (15, 'Consulta especializada');