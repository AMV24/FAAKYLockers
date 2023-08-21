-- Crear la base de datos "FAAKYCORP"
create database FAAKYCORPV2;

-- Tabla "Carrera" que representa las carreras disponibles en la institución
create table Carrera(
    Num int primary key,
    Descripcion varchar(80) not null
);

-- Tabla "Alumno" que representa a los estudiantes
create table Alumno(
    Matricula int primary key, 
    Grupo varchar(10) not null,
    Carrera int,
    foreign key Carrera(Carrera) references Carrera(Num)
);

-- Tabla "Docente" que representa a los profesores
create table Docente(
    Num_Emp int primary key,
    Descripcion varchar(80),
    Carrera int,
    foreign key (Carrera) references Carrera(Num)
);

-- Tabla "Puesto" que representa los puestos disponibles
create table Puesto(
    Codigo char(5) primary key,
    Nombre varchar(60)
);

-- Tabla "Departamento" que representa los departamentos en la institución
create table Departamento(
    Num int primary key auto_increment,
    Codigo_Depa char(5) not null,
    Nombre varchar(40) not null
);

-- Tabla "Depa_Puesto" que representa la relación entre departamentos y puestos
create table Depa_Puesto(
    Puesto char(5),
    Departamento int,
    primary key (Puesto, Departamento),
    foreign key Puesto (Puesto) references Puesto(Codigo),
    foreign key Departamento (Departamento) references Departamento(Num)
);

-- Tabla "Admnistrativo" que representa a los empleados administrativos
create table Admnistrativo(
    Num_Emp int primary key,
    Descripcion varchar(60),
    Puesto char(5),
    Departamento int, 
    foreign Key (Puesto) references Puesto(Codigo),
    foreign Key (Departamento) references Departamento (Num)
);

-- Tabla "Solicitante" que representa a los solicitantes de lockers (estudiantes, docentes o administrativos)
create table Solicitante(
    Num_Solicitante int primary key auto_increment,
    Nombre_Pila Varchar(30) not null,
    Ap_Paterno varchar(40) not null,
    ApMaterno varchar(40),
    Alumno int,
    Docente int,
    Admnistrativo int,
    foreign key (Alumno) references Alumno (Matricula),
    foreign key (Docente) references Docente (Num_Emp),
    foreign key (Admnistrativo) references Admnistrativo (Num_Emp)
);

-- Tabla "telefono" que representa los números de teléfono de los solicitantes
create table telefono(
    Numero int primary key auto_increment,
    Num_Tel char(15) not null,
    Num_Solicitante int,
    foreign key (Num_Solicitante) references Solicitante(Num_Solicitante)
);

-- Tabla "Tamaño" que representa los tamaños disponibles de los lockers
create table Tamaño(
    Num int primary key,
    Descripcion varchar(60),
    precio float not null
);

-- Tabla "Ubicacion" que representa las ubicaciones disponibles para los lockers
create table Ubicacion(
    Codigo varchar(10) primary key,
    Doncencia int not null,
    Piso int not null
);

-- Tabla "Lockers" que representa los lockers disponibles
create table Lockers(
    Num int primary key auto_increment,
    Tamaño int,
    Ubicacion varchar(10),
    Disponibilidad int,
    foreign key Tamaño (Tamaño) references Tamaño(Num),
    foreign key Ubicacion(Ubicacion) references Ubicacion(Codigo)
);

-- Tabla "Renta" que representa las rentas de los lockers
create table Renta(
    Num_Renta int primary key auto_increment,
    Fecha_Inicio date not null,
    Fecha_Final date not null,
    Num_Solicitante int,
    Lockers int,
    foreign key Solicitante (Num_Solicitante) references Solicitante(Num_Solicitante),
    foreign key Lockers (Lockers) references Lockers(Num)
);

-- Tabla "Metodo_De_Pago" que representa los métodos de pago disponibles
create table Metodo_De_Pago(
    Num_Metodo int primary key,
    Descripcion varchar(50) not null
);

-- Tabla "Pago" que representa los pagos realizados por las rentas de lockers
create table Pago(
    Num_Pago int primary key auto_increment,
    Cantidad float not null,
    Fecha date not null,
    Metodo_De_Pago int,
    Num_Renta int,
    foreign key Metodo_De_Pago (Metodo_De_Pago) references Metodo_De_Pago(Num_Metodo),
    foreign Key Renta (Num_Renta) references Renta(Num_Renta)
);

-- Registros para la base de datos

INSERT INTO Carrera VALUES
(1, 'DESARROLLO DE NEGOCIOS ÁREA MERCADOTECNIA'),
(2, 'LOGÍSTICA ÁREA CADENA DE SUMINISTROS'),
(3, 'CONTADURÍA'),
(4, 'MANTENIMIENTO ÁREA INDUSTRIAL'),
(5, 'PROCESOS INDUSTRIALES ÁREA MANUFACTURA'),
(6, 'PROCESOS INDUSTRIALES ÁREA SISTEMAS DE GESTIÓN DE CALIDAD'),
(7, 'MANUFACTURA AERONÁUTICA ÁREA MAQUINADOS DE PRECISIÓN'),
(8, 'MECATRÓNICA ÁREA SISTEMAS DE MANUFACTURA FLEXIBLE'),
(9, 'MECATRÓNICA ÁREA AUTOMATIZACIÓN'),
(10, 'OPERACIONES COMERCIALES INTERNACIONALES'),
(11, 'ENERGÍAS RENOVABLES ÁREA ENERGÍA SOLAR'),
(12, 'QUÍMICA ÁREA TECNOLOGÍA AMBIENTAL'),
(13, 'TECNOLOGÍAS DE LA INFORMACIÓN')

INSERT INTO Alumno VALUES
(0202300001, '1A', 13),
(0202300002, '2B', 3),
(0202300003, '3C', 2),
(0202300004, '4D', 5),
(0202300005, '1A', 7),
(0202300006, '2B', 6),
(0202300007, '3C', 9),
(0202300008, '4D', 4),
(0202300009, '1A', 8),
(0202300010, '2B', 10),
(0202300011, '3C', 12),
(0202300012, '4D', 11),
(0202300013, '1A', 13),
(0202300014, '2B', 2),
(0202300015, '3C', 5),
(0202300016, '4D', 6),
(0202300017, '1A', 4),
(0202300018, '2B', 9),
(0202300019, '3C', 3),
(0202300020, '4D', 1)

INSERT INTO Docente VALUES
(100, 'Desarrollo de Negocios Área Mercadotecnia', 1),
(101, 'Logística Área Cadena de Suministros', 2),
(102, 'Contaduría', 3),
(103, 'Mantenimiento Área Industrial', 4),
(104, 'Procesos Industriales Área Manufactura', 5),
(105, 'Procesos Industriales Área Sistemas de Gestión de Calidad', 6),
(106, 'Manufactura Aeronáutica Área Maquinados de Precisión', 7),
(107, 'Mecatrónica Área Sistemas de Manufactura Flexible', 8),
(108, 'Mecatrónica Área Automatización', 9),
(109, 'Operaciones Comerciales Internacionales', 10),
(110, 'Energías Renovables Área Energía Solar', 11),
(111, 'Química Área Tecnología Ambiental', 12),
(112, 'Tecnologías de la Información', 13),
(113, 'Contaduría', 3),
(114, 'Manufactura Aeronáutica Área Maquinados de Precisión', 7),
(115, 'Procesos Industriales Área Manufactura', 5),
(116, 'Mecatrónica Área Automatización', 9),
(117, 'Logística Área Cadena de Suministros', 2),
(118, 'Energías Renovables Área Energía Solar', 11),
(119, 'Desarrollo de Negocios Área Mercadotecnia', 1),
(120, 'Procesos Industriales Área Sistemas de Gestión de Calidad', 6)

INSERT INTO Puesto VALUES
('PST01', 'Director'),
('PST02', 'Coordinador'),
('PST03', 'Jefe de Departamento'),
('PST04', 'Asistente Administrativo'),
('PST05', 'Secretario'),
('PST06', 'Analista Financiero'),
('PST07', 'Gerente de Recursos Humanos'),
('PST08', 'Coordinador de Admisiones'),
('PST09', 'Asistente de Dirección'),
('PST10', 'Coordinador Académico'),
('PST11', 'Jefe de Recursos Humanos'),
('PST12', 'Analista de Proyectos'),
('PST13', 'Coordinador de Finanzas'),
('PST14', 'Asistente de Coordinación'),
('PST15', 'Jefe de Finanzas'),
('PST16', 'Asistente de Recursos Humanos'),
('PST17', 'Coordinador de Recursos Humanos'),
('PST18', 'Analista Administrativo'),
('PST19', 'Coordinador de Proyectos'),
('PST20', 'Asistente de Admisiones')


INSERT INTO Departamento VALUES
(1, 'DEP01', 'Departamento de Dirección'),
(2, 'DEP02', 'Departamento Académico'),
(3, 'DEP03', 'Departamento de Finanzas'),
(4, 'DEP04', 'Departamento de Recursos Humanos'),
(5, 'DEP05', 'Departamento de Admisiones')

INSERT INTO Depa_Puesto VALUES
('PST01', 1),
('PST02', 5),
('PST03', 2),
('PST04', 4),
('PST05', 1),
('PST06', 3),
('PST07', 4),
('PST08', 2),
('PST09', 1),
('PST10', 2),
('PST11', 4),
('PST12', 3),
('PST13', 3),
('PST14', 5),
('PST15', 3),
('PST16', 4),
('PST17', 4),
('PST18', 5),
('PST19', 2),
('PST20', 5)

INSERT INTO Admnistrativo VALUES
(300, 'Administrativo de Dirección', 'PST01', 1),
(301, 'Secretario de Dirección', 'PST05', 1),
(302, 'Asistente de Dirección', 'PST09', 1),
(304, 'Jefe de Departamento', 'PST03', 2),
(305, 'Coordinador Académico', 'PST10', 2),
(306, 'Coordinador de Admisiones', 'PST08', 2),
(307, 'Asistente de Admisiones', 'PST14', 5),
(308, 'Coordinador de Finanzas', 'PST02', 5),
(309, 'Analista Administrativo', 'PST20', 5),
(310, 'Coordinador de Proyectos', 'PST19', 2),
(311, 'Analista de Proyectos', 'PST12', 3),
(312, 'Analista Financiero', 'PST18', 3),
(313, 'Jefe de Finanzas', 'PST06', 3),
(314, 'Gerente de Recursos Humanos', 'PST11', 4),
(315, 'Asistente de Recursos Humanos', 'PST07', 4),
(316, 'Coordinador de Recursos Humanos', 'PST16', 4),
(317, 'Asistente Administrativo', 'PST04', 4),
(318, 'Coordinador de Admisiones', 'PST17', 5),
(319, 'Asistente de Coordinación', 'PST13', 5),
(320, 'Jefe de Recursos Humanos', 'PST15', 3)

INSERT INTO Solicitante VALUES
(1, 'Juan', 'Ramirez', 'Perez', 0202300001, NULL, NULL),
(2, 'Maria', 'Martinez', 'Garcia', 0202300002, NULL, NULL),
(3, 'Andres', 'Gonzalez', 'Rodriguez', 0202300003, NULL, NULL),
(4, 'Sofia', 'Ramirez', 'Hernandez', 0202300004, NULL, NULL),
(5, 'Carlos', 'Hernandez', 'Gomez', 0202300005, NULL, NULL),
(6, 'Laura', 'Lopez', 'Fernandez', 0202300006, NULL, NULL),
(7, 'Luis', 'Perez', 'Gonzalez', 0202300007, NULL, NULL),
(8, 'Gabriela', 'Gomez', 'Lopez', 0202300008, NULL, NULL),
(9, 'Daniel', 'Hernandez', 'Ramirez', 0202300009, NULL, NULL),
(10, 'Paola', 'Ramirez', 'Garcia', 0202300010, NULL, NULL),
(11, 'Oscar', 'Gomez', 'Rodriguez', 0202300011, NULL, NULL),
(12, 'Valeria', 'Rodriguez', 'Fernandez', 0202300012, NULL, NULL),
(13, 'Ricardo', 'Fernandez', 'Hernandez', 0202300013, NULL, NULL),
(14, 'Carmen', 'Ramirez', 'Gonzalez', 0202300014, NULL, NULL),
(15, 'Brenda', 'Gonzalez', 'Gomez', 0202300015, NULL, NULL),
(16, 'Jose', 'Lopez', 'Ramirez', 0202300016, NULL, NULL),
(17, 'Mariana', 'Ramirez', 'Perez', 0202300017, NULL, NULL),
(18, 'Alberto', 'Gomez', 'Garcia', 0202300018, NULL, NULL),
(19, 'Monica', 'Hernandez', 'Rodriguez', 0202300019, NULL, NULL),
(20, 'David', 'Gonzalez', 'Fernandez', 0202300020, NULL, NULL),
(21, 'Ricardo', 'Fernandez', 'Hernandez', NULL, NULL, 300),
(22, 'Carmen', 'Ramirez', 'Gonzalez', NULL, NULL, 301),
(23, 'Brenda', 'Gonzalez', 'Gomez', NULL, NULL, 302),
(24, 'Jose', 'Lopez', 'Ramirez', NULL, NULL, 304),
(25, 'Mariana', 'Ramirez', 'Perez', NULL, NULL, 305),
(26, 'Alberto', 'Gomez', 'Garcia', NULL, NULL, 306),
(27, 'Monica', 'Hernandez', 'Rodriguez', NULL, NULL, 307),
(28, 'David', 'Gonzalez', 'Fernandez', NULL, NULL, 308),
(29, 'Laura', 'Lopez', 'Fernandez', NULL, NULL, 309),
(30, 'Luis', 'Perez', 'Gonzalez', NULL, NULL, 310),
(31, 'Ana', 'Hernandez', 'Ramirez', NULL, NULL, 311),
(32, 'Carlos', 'Ramirez', 'Gonzalez', NULL, NULL, 312),
(33, 'Sofia', 'Gonzalez', 'Gomez', NULL, NULL, 313),
(34, 'Martin', 'Lopez', 'Ramirez', NULL, NULL, 314),
(35, 'Diana', 'Ramirez', 'Perez', NULL, NULL, 315),
(36, 'Juan', 'Gomez', 'Garcia', NULL, NULL, 316),
(37, 'Patricia', 'Hernandez', 'Rodriguez', NULL, NULL, 317),
(38, 'Manuel', 'Gonzalez', 'Fernandez', NULL, NULL, 318),
(39, 'Alejandra', 'Lopez', 'Fernandez', NULL, NULL, 319),
(40, 'Eduardo', 'Perez', 'Gonzalez', NULL, NULL, 320),
(41, 'Gabriela', 'Aguilar', 'Ramos', NULL, 100, NULL),
(42, 'Rafael', 'Morales', 'Soto', NULL, 101, NULL),
(43, 'Alejandra', 'Pineda', 'Mendez', NULL, 102, NULL),
(44, 'Fernando', 'Rivas', 'Fuentes', NULL, 103, NULL),
(45, 'Veronica', 'Ortega', 'Castro', NULL, 104, NULL),
(46, 'Arturo', 'Navarro', 'Silva', NULL, 105, NULL),
(47, 'Natalia', 'Guzman', 'Valencia', NULL, 106, NULL),
(48, 'Sebastian', 'Castillo', 'Mora', NULL, 107, NULL),
(49, 'Lucia', 'Herrera', 'Gomez', NULL, 108, NULL),
(50, 'Emilio', 'Ruiz', 'Estrada', NULL, 109, NULL),
(51, 'Camila', 'Diaz', 'Vargas', NULL, 110, NULL),
(52, 'Andres', 'Silva', 'Romero', NULL, 111, NULL),
(53, 'Isabella', 'Mendez', 'Rios', NULL, 112, NULL),
(54, 'Santiago', 'Castro', 'Alvarez', NULL, 113, NULL),
(55, 'Valentina', 'Morales', 'Hernandez', NULL, 114, NULL),
(56, 'Leonardo', 'Pineda', 'Fuentes', NULL, 115, NULL),
(57, 'Maria', 'Rivas', 'Valencia', NULL, 116, NULL),
(58, 'Felipe', 'Ortega', 'Mendoza', NULL, 117, NULL),
(59, 'Mariana', 'Soto', 'Fuentes', NULL, 118, NULL),
(60, 'Christian', 'Ramos', 'Mora', NULL, 119, NULL),
(61, 'Jose Javier', 'Martinez', 'Lopez', NULL, 120, NULL)

INSERT INTO telefono VALUES
(1, '6641234567', 1),
(2, '6642345678', 2),
(3, '6643456789', 3),
(4, '6644567890', 4),
(5, '6645678901', 5),
(6, '6646789012', 6),
(7, '6647890123', 7),
(8, '6648901234', 8),
(9, '6649012345', 9),
(10, '6640123456', 10),
(11, '6643210987', 11),
(12, '6644321098', 12),
(13, '6645432109', 13),
(14, '6646543210', 14),
(15, '6647654321', 15),
(16, '6648765432', 16),
(17, '6649876543', 17),
(18, '6640987654', 18),
(19, '6649876543', 19),
(20, '6648765432', 20),
(21, '6647654321', 21),
(22, '6646543210', 22),
(23, '6645432109', 23),
(24, '6644321098', 24),
(25, '6643210987', 25),
(26, '6640123456', 26),
(27, '6649012345', 27),
(28, '6648901234', 28),
(29, '6647890123', 29),
(30, '6646789012', 30),
(31, '6645678901', 31),
(32, '6644567890', 32),
(33, '6643456789', 33),
(34, '6642345678', 34),
(35, '6641234567', 35),
(36, '6642345678', 36),
(37, '6643456789', 37),
(38, '6644567890', 38),
(39, '6645678901', 39),
(40, '6646789012', 40),
(41, '6647890123', 41),
(42, '6648901234', 42),
(43, '6649012345', 43),
(44, '6640123456', 44),
(45, '6643210987', 45),
(46, '6644321098', 46),
(47, '6645432109', 47),
(48, '6646543210', 48),
(49, '6647654321', 49),
(50, '6648765432', 50),
(51, '6649876543', 51),
(52, '6640987654', 52),
(53, '6649876543', 53),
(54, '6648765432', 54),
(55, '6647654321', 55),
(56, '6646543210', 56),
(57, '6645432109', 57),
(58, '6644321098', 58),
(59, '6643210987', 59),
(60, '6640123456', 60),
(61, '6640123457', 61)

INSERT INTO Tamaño VALUES
(1, 'Grande', 600),
(2, 'Chico', 400)

INSERT INTO Ubicacion VALUES
('UB001-DOC1', 1, 1),
('UB002-DOC1', 1, 2),
('UB003-DOC2', 2, 1),
('UB004-DOC2', 2, 2),
('UB005-DOC3', 3, 1),
('UB006-DOC3', 3, 2),
('UB007-DOC4', 4, 1),
('UB008-DOC4', 4, 2)

INSERT INTO Lockers VALUES
(1, 1, 'UB001-DOC1', 0),
(2, 1, 'UB001-DOC1', 0),
(3, 2, 'UB001-DOC1', 0),
(4, 2, 'UB001-DOC1', 0),
(5, 1, 'UB002-DOC1', 0),
(6, 1, 'UB002-DOC1', 1),
(7, 2, 'UB002-DOC1', 0),
(8, 2, 'UB002-DOC1', 0),
(9, 1, 'UB003-DOC2', 0),
(10, 1, 'UB003-DOC2', 1),
(11, 2, 'UB003-DOC2', 0),
(12, 2, 'UB003-DOC2', 0),
(13, 1, 'UB004-DOC2', 0),
(14, 1, 'UB004-DOC2', 0),
(15, 2, 'UB004-DOC2', 0),
(16, 2, 'UB004-DOC2', 1),
(17, 1, 'UB005-DOC3', 0),
(18, 1, 'UB005-DOC3', 1),
(19, 2, 'UB005-DOC3', 0),
(20, 2, 'UB005-DOC3', 1),
(21, 1, 'UB006-DOC3', 0),
(22, 1, 'UB006-DOC3', 0),
(23, 2, 'UB006-DOC3', 0),
(24, 2, 'UB006-DOC3', 0),
(25, 1, 'UB007-DOC4', 0),
(26, 1, 'UB007-DOC4', 0),
(27, 2, 'UB007-DOC4', 0),
(28, 2, 'UB007-DOC4', 1),
(29, 1, 'UB008-DOC4', 0),
(30, 1, 'UB008-DOC4', 1),
(31, 2, 'UB008-DOC4', 0),
(32, 2, 'UB008-DOC4', 1),
(33, 1, 'UB001-DOC1', 0),
(34, 1, 'UB001-DOC1', 1),
(35, 2, 'UB001-DOC1', 0),
(36, 2, 'UB001-DOC1', 1),
(37, 1, 'UB002-DOC1', 0),
(38, 1, 'UB002-DOC1', 1),
(39, 2, 'UB002-DOC1', 0),
(40, 2, 'UB002-DOC1', 1),
(41, 1, 'UB003-DOC2', 0),
(42, 1, 'UB003-DOC2', 1),
(43, 2, 'UB003-DOC2', 0),
(44, 2, 'UB003-DOC2', 1),
(45, 1, 'UB004-DOC2', 0),
(46, 1, 'UB004-DOC2', 0),
(47, 2, 'UB004-DOC2', 0),
(48, 2, 'UB004-DOC2', 0),
(49, 1, 'UB005-DOC3', 0),
(50, 1, 'UB005-DOC3', 0),
(51, 2, 'UB005-DOC3', 0),
(52, 2, 'UB005-DOC3', 1),
(53, 1, 'UB006-DOC3', 0),
(54, 1, 'UB006-DOC3', 1),
(55, 2, 'UB006-DOC3', 0),
(56, 2, 'UB006-DOC3', 1),
(57, 1, 'UB007-DOC4', 0),
(58, 1, 'UB007-DOC4', 1),
(59, 2, 'UB007-DOC4', 0),
(60, 2, 'UB007-DOC4', 0),
(61, 1, 'UB008-DOC4', 0),
(62, 1, 'UB008-DOC4', 0),
(63, 2, 'UB008-DOC4', 0),
(64, 2, 'UB008-DOC4', 1),
(65, 1, 'UB001-DOC1', 0),
(66, 1, 'UB001-DOC1', 0),
(67, 2, 'UB001-DOC1', 0),
(68, 2, 'UB001-DOC1', 1),
(69, 1, 'UB002-DOC1', 0),
(70, 1, 'UB002-DOC1', 0),
(71, 2, 'UB002-DOC1', 0),
(72, 2, 'UB002-DOC1', 0),
(73, 1, 'UB003-DOC2', 0),
(74, 1, 'UB003-DOC2', 0),
(75, 2, 'UB003-DOC2', 0),
(76, 2, 'UB003-DOC2', 0),
(77, 1, 'UB004-DOC2', 0),
(78, 1, 'UB004-DOC2', 1),
(79, 2, 'UB004-DOC2', 0),
(80, 2, 'UB004-DOC2', 0),
(81, 1, 'UB005-DOC3', 1),
(82, 1, 'UB006-DOC3', 1),
(83, 2, 'UB006-DOC3', 0),
(84, 2, 'UB006-DOC3', 1),
(85, 1, 'UB007-DOC4', 1),
(86, 1, 'UB007-DOC4', 1),
(87, 2, 'UB007-DOC4', 1),
(88, 2, 'UB007-DOC4', 1),
(89, 1, 'UB008-DOC4', 1),
(90, 1, 'UB008-DOC4', 1),
(91, 2, 'UB008-DOC4', 1),
(92, 2, 'UB008-DOC4', 1),
(93, 1, 'UB001-DOC1', 1),
(94, 1, 'UB001-DOC1', 1),
(95, 2, 'UB001-DOC1', 1),
(96, 2, 'UB001-DOC1', 1),
(97, 1, 'UB002-DOC1', 1),
(98, 1, 'UB002-DOC1', 1),
(99, 2, 'UB002-DOC1', 1),
(100, 2, 'UB002-DOC1', 1),
(101, 1, 'UB003-DOC2', 1),
(102, 1, 'UB003-DOC2', 1),
(103, 2, 'UB003-DOC2', 1),
(104, 2, 'UB003-DOC2', 1),
(105, 1, 'UB004-DOC2', 1),
(106, 1, 'UB004-DOC2', 1),
(107, 2, 'UB004-DOC2', 1),
(108, 2, 'UB004-DOC2', 1),
(109, 1, 'UB005-DOC3', 1),
(110, 1, 'UB005-DOC3', 1),
(111, 2, 'UB005-DOC3', 1),
(112, 2, 'UB005-DOC3', 1),
(113, 1, 'UB006-DOC3', 1),
(114, 1, 'UB006-DOC3', 1),
(115, 2, 'UB006-DOC3', 1),
(116, 2, 'UB006-DOC3', 1),
(117, 1, 'UB007-DOC4', 1),
(118, 1, 'UB007-DOC4', 1),
(119, 2, 'UB007-DOC4', 0),
(120, 2, 'UB007-DOC4', 1)

INSERT INTO Renta VALUES
(1, '2023-08-01', '2023-12-01', 1, 1),
(2, '2023-08-02', '2023-12-02', 2, 2),
(3, '2023-08-03', '2023-12-03', 3, 3),
(4, '2023-08-04', '2023-12-04', 4, 4),
(5, '2023-08-05', '2023-12-05', 5, 5),
(6, '2023-08-06', '2023-12-06', 6, 7),
(7, '2023-08-07', '2023-12-07', 7, 8),
(8, '2023-08-08', '2023-12-08', 8, 9),
(9, '2023-08-09', '2023-12-09', 9, 11),
(10, '2023-08-10', '2023-12-10', 10, 12),
(11, '2023-08-11', '2023-12-11', 11, 13),
(12, '2023-08-12', '2023-12-12', 12, 14),
(13, '2023-08-13', '2023-12-13', 13, 15),
(14, '2023-08-14', '2023-12-14', 14, 17),
(15, '2023-08-15', '2023-12-15', 15, 19),
(16, '2023-08-16', '2023-12-16', 16, 21),
(17, '2023-08-17', '2023-12-17', 17, 22),
(18, '2023-08-18', '2023-12-18', 18, 23),
(19, '2023-08-19', '2023-12-19', 19, 24),
(20, '2023-08-20', '2023-12-20', 20, 25),
(21, '2023-08-21', '2023-12-21', 21, 26),
(22, '2023-08-22', '2023-12-22', 22, 27),
(23, '2023-08-23', '2023-12-23', 23, 29),
(24, '2023-08-24', '2023-12-24', 24, 31),
(25, '2023-08-25', '2023-12-25', 25, 33),
(26, '2023-08-26', '2023-12-26', 26, 35),
(27, '2023-08-27', '2023-12-27', 27, 37),
(28, '2023-08-28', '2023-12-28', 28, 39),
(29, '2023-08-29', '2023-12-29', 29, 41),
(30, '2023-08-30', '2023-12-30', 30, 43),
(31, '2023-08-31', '2023-12-31', 31, 45),
(32, '2023-09-01', '2024-01-01', 32, 46),
(33, '2023-09-02', '2024-01-02', 33, 47),
(34, '2023-09-03', '2024-01-03', 34, 48),
(35, '2023-09-04', '2024-01-04', 35, 49),
(36, '2023-09-05', '2024-01-05', 36, 50),
(37, '2023-09-06', '2024-01-06', 37, 51),
(38, '2023-09-07', '2024-01-07', 38, 53),
(39, '2023-09-08', '2024-01-08', 39, 55),
(40, '2023-09-09', '2024-01-09', 40, 57),
(41, '2023-09-10', '2024-01-10', 41, 59),
(42, '2023-09-11', '2024-01-11', 42, 60),
(43, '2023-09-12', '2024-01-12', 43, 61),
(44, '2023-09-13', '2024-01-13', 44, 62),
(45, '2023-09-14', '2024-01-14', 45, 63),
(46, '2023-09-15', '2024-01-15', 46, 65),
(47, '2023-09-16', '2024-01-16', 47, 66),
(48, '2023-09-17', '2024-01-17', 48, 67),
(49, '2023-09-18', '2024-01-18', 49, 69),
(50, '2023-09-19', '2024-01-19', 50, 70),
(51, '2023-09-20', '2024-01-20', 51, 71),
(52, '2023-09-21', '2024-01-21', 52, 72),
(53, '2023-09-22', '2024-01-22', 53, 73),
(54, '2023-09-23', '2024-01-23', 54, 74),
(55, '2023-09-24', '2024-01-24', 55, 75),
(56, '2023-09-25', '2024-01-25', 56, 76),
(57, '2023-09-26', '2024-01-26', 57, 77),
(58, '2023-09-27', '2024-01-27', 58, 79),
(59, '2023-09-28', '2024-01-28', 59, 80),
(60, '2023-09-29', '2024-01-29', 60, 83),
(61, '2023-09-30', '2024-01-30', 60, 119)

INSERT INTO Metodo_De_Pago VALUES
('1', 'Efectivo'),
('2', 'Tarjeta')

INSERT INTO Pago VALUES
(1, 600, '2023-08-01', 1, 1),
(2, 600, '2023-08-02', 2, 2),
(3, 400, '2023-08-03', 1, 3),
(4, 400, '2023-08-04', 1, 4),
(5, 600, '2023-08-05', 2, 5),
(6, 400, '2023-08-06', 1, 6),
(7, 400, '2023-08-07', 2, 7),
(8, 600, '2023-08-08', 1, 8),
(9, 400, '2023-08-09', 2, 9),
(10, 400, '2023-08-10', 1, 10),
(11, 600, '2023-08-11', 2, 11),
(12, 600, '2023-08-12', 1, 12),
(13, 400, '2023-08-13', 2, 13),
(14, 600, '2023-08-14', 1, 14),
(15, 400, '2023-08-15', 2, 15),
(16, 600, '2023-08-16', 1, 16),
(17, 600, '2023-08-17', 2, 17),
(18, 400, '2023-08-18', 1, 18),
(19, 400, '2023-08-19', 2, 19),
(20, 600, '2023-08-20', 1, 20),
(21, 600, '2023-08-21', 2, 21),
(22, 400, '2023-08-22', 1, 22),
(23, 600, '2023-08-23', 1, 23),
(24, 400, '2023-08-24', 2, 24),
(25, 600, '2023-08-25', 1, 25),
(26, 400, '2023-08-26', 2, 26),
(27, 600, '2023-08-27', 1, 27),
(28, 400, '2023-08-28', 2, 28),
(29, 600, '2023-08-29', 1, 29),
(30, 400, '2023-08-30', 2, 30),
(31, 600, '2023-08-31', 1, 31),
(32, 600, '2023-09-01', 2, 32),
(33, 400, '2023-09-02', 1, 33),
(34, 400, '2023-09-03', 2, 34),
(35, 600, '2023-09-04', 1, 35),
(36, 600, '2023-09-05', 2, 36),
(37, 400, '2023-09-06', 1, 37),
(38, 600, '2023-09-07', 2, 38),
(39, 400, '2023-09-08', 1, 39),
(40, 600, '2023-09-09', 2, 40),
(41, 400, '2023-09-10', 1, 41),
(42, 400, '2023-09-11', 2, 42),
(43, 600, '2023-09-12', 1, 43),
(44, 600, '2023-09-13', 1, 44),
(45, 400, '2023-09-14', 2, 45),
(46, 600, '2023-09-15', 1, 46),
(47, 600, '2023-09-16', 2, 47),
(48, 400, '2023-09-17', 1, 48),
(49, 600, '2023-09-18', 2, 49),
(50, 600, '2023-09-19', 1, 50),
(51, 400, '2023-09-20', 2, 51),
(52, 400, '2023-09-21', 1, 52),
(53, 600, '2023-09-22', 2, 53),
(54, 600, '2023-09-23', 1, 54),
(55, 400, '2023-09-24', 2, 55),
(56, 400, '2023-09-25', 1, 56),
(57, 600, '2023-09-26', 2, 57),
(58, 400, '2023-09-27', 1, 58),
(59, 400, '2023-09-28', 2, 59),
(60, 400, '2023-09-29', 1, 60),
(61, 400, '2023-09-30', 1, 61)




--
-- Consultas pedidas por Mti. Cleotilde
--

-- Consulta #1
SELECT
r.Num_Renta AS 'Número de la renta',
CONCAT(
    IFNULL(CONCAT(s.Ap_Paterno, ' '), ' '),
    IFNULL(CONCAT(s.ApMaterno, ' '), ' '),
    IFNULL(CONCAT(s.Nombre_Pila, ' '), ' ')
) AS 'Nombre completo del alumno',
t.Num_Tel AS 'Número de teléfono del alumno',
c.Descripcion AS 'Nombre de la carrera',
a.Grupo AS 'Grupo del alumno',
DATE_FORMAT(r.Fecha_Inicio, '%d-%m-%y') AS 'Fecha de inicio de la renta',
l.Num AS 'Número del locker rentado',
tam.Descripcion AS 'Tamaño del locker',
CONCAT('Docencia: ', u.Doncencia, ' - Piso: ', u.Piso) AS 'Ubicación del locker',
tam.Precio AS 'Precio del locker',
pa.Num_Pago AS 'Número de pago del locker',
DATE_FORMAT(pa.Fecha, '%d-%m-%y') AS 'Fecha del pago',
pa.Cantidad AS 'Monto del pago',
mp.Descripcion AS 'Método de pago'
FROM Renta r
INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante
INNER JOIN Alumno a ON s.Alumno = a.Matricula
INNER JOIN telefono t ON s.Num_Solicitante = t.Num_Solicitante
INNER JOIN Carrera c ON a.Carrera = c.Num
INNER JOIN Lockers l ON r.Lockers = l.Num
INNER JOIN Tamaño tam ON l.Tamaño = tam.Num
INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo
INNER JOIN Pago pa ON r.Num_Renta = pa.Num_Renta
INNER JOIN Metodo_De_Pago mp ON pa.Metodo_De_Pago = mp.Num_Metodo
WHERE s.Num_Solicitante = 1

-- Consulta #2
SELECT
r.Num_Renta AS 'Número de la renta',
CONCAT(
    IFNULL(CONCAT(s.Ap_Paterno, ' '), ' '),
    IFNULL(CONCAT(s.ApMaterno, ' '), ' '),
    IFNULL(CONCAT(s.Nombre_Pila, ' '), ' ')
) AS 'Nombre completo del docente',
t.Num_Tel AS 'Número de teléfono del docente',
c.Descripcion AS 'Nombre de la carrera',
DATE_FORMAT(r.Fecha_Inicio, '%d-%m-%y') AS 'Fecha de inicio de la renta',
l.Num AS 'Número del locker rentado',
tam.Descripcion AS 'Tamaño del locker',
CONCAT('Docencia: ', u.Doncencia, ' - Piso: ', u.Piso) AS 'Ubicación del locker',
tam.Precio AS 'Precio del locker',
pa.Num_Pago AS 'Número de pago del locker',
DATE_FORMAT(pa.Fecha, '%d-%m-%y') AS 'Fecha del pago',
pa.Cantidad AS 'Monto del pago',
mp.Descripcion AS 'Método de pago'
FROM Renta r
INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante
INNER JOIN Docente d ON s.Docente = d.Num_Emp
INNER JOIN telefono t ON s.Num_Solicitante = t.Num_Solicitante
INNER JOIN Carrera c ON d.Carrera = c.Num
INNER JOIN Lockers l ON r.Lockers = l.Num
INNER JOIN Tamaño tam ON l.Tamaño = tam.Num
INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo
INNER JOIN Pago pa ON r.Num_Renta = pa.Num_Renta
INNER JOIN Metodo_De_Pago mp ON pa.Metodo_De_Pago = mp.Num_Metodo
WHERE s.Num_Solicitante = 41

-- Consulta #3
SELECT
    r.Num_Renta AS 'Número de la renta',
    CONCAT(
        IFNULL(CONCAT(s.Ap_Paterno, ' '), ' '),
        IFNULL(CONCAT(s.ApMaterno, ' '), ' '),
        IFNULL(CONCAT(s.Nombre_Pila, ' '), ' ')
    ) AS 'Nombre completo del administrativo',
    t.Num_Tel AS 'Número de teléfono del administrativo',
    dep.Nombre AS 'Nombre del departamento',
    p.Nombre AS 'Nombre del puesto',
    DATE_FORMAT(r.Fecha_Inicio, '%d-%m-%y') AS 'Fecha de inicio de la renta',
    l.Num AS 'Número del locker rentado',
    tam.Descripcion AS 'Tamaño del locker',
    CONCAT('Docencia: ', u.Doncencia, ' - Piso: ', u.Piso) AS 'Ubicación del locker',
    tam.Precio AS 'Precio del locker',
    pa.Num_Pago AS 'Número de pago del locker',
    DATE_FORMAT(pa.Fecha, '%d-%m-%y') AS 'Fecha del pago',
    pa.Cantidad AS 'Monto del pago',
    mp.Descripcion AS 'Método de pago'
FROM
    Renta r
INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante
INNER JOIN Admnistrativo adm ON s.Admnistrativo = adm.Num_Emp
INNER JOIN telefono t ON s.Num_Solicitante = t.Num_Solicitante
INNER JOIN Departamento dep ON adm.Departamento = dep.Num
INNER JOIN Puesto p ON adm.Puesto = p.Codigo
INNER JOIN Lockers l ON r.Lockers = l.Num
INNER JOIN Tamaño tam ON l.Tamaño = tam.Num
INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo
INNER JOIN Pago pa ON r.Num_Renta = pa.Num_Renta
INNER JOIN Metodo_De_Pago mp ON pa.Metodo_De_Pago = mp.Num_Metodo
WHERE s.Num_Solicitante = 21

-- Consulta #4
SELECT
    r.Num_Renta AS 'Número de renta',
    CONCAT(
        IFNULL(CONCAT(s.Ap_Paterno, ' '), ' '),
        IFNULL(CONCAT(s.ApMaterno, ' '), ' '),
        IFNULL(CONCAT(s.Nombre_Pila, ' '), ' ')
    ) AS 'Nombre completo del alumno',
    c.Descripcion AS 'Nombre de la carrera',
    l.Num AS 'Número del locker',
    tam.Descripcion AS 'Tamaño del locker',
    CONCAT('Docencia ', u.Doncencia, ' - Piso ', u.Piso) AS 'Ubicación del locker'
FROM
    Renta r
INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante
INNER JOIN Alumno a ON s.Alumno = a.Matricula
INNER JOIN Carrera c ON a.Carrera = c.Num
INNER JOIN Lockers l ON r.Lockers = l.Num
INNER JOIN Tamaño tam ON l.Tamaño = tam.Num
INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo
ORDER BY c.Descripcion

-- Consulta #5
SELECT
    u.Doncencia AS 'Número del edificio (docencia)',
    r.Num_Renta AS 'Número de la renta',
    CONCAT(
        IFNULL(CONCAT(s.Ap_Paterno, ' '), ' '),
        IFNULL(CONCAT(s.ApMaterno, ' '), ' '),
        IFNULL(CONCAT(s.Nombre_Pila, ' '), ' ')
    ) AS 'Nombre completo del solicitante',
    r.Fecha_Inicio AS 'Fecha de inicio',
    l.Num AS 'Número del locker rentado',
    tam.Descripcion AS 'Tamaño del locker',
    u.Piso AS 'Piso de ubicación del locker',
    tam.precio AS 'Precio del locker'
FROM
    Renta r
INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante
INNER JOIN Lockers l ON r.Lockers = l.Num
INNER JOIN Tamaño tam ON l.Tamaño = tam.Num
INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo
WHERE
    u.Doncencia = 1 AND
    l.Disponibilidad = 0
ORDER BY
    u.Doncencia

-- Consulta #6
SELECT
    u.Doncencia AS 'Número del edificio (docencia)',
    l.Num AS 'Número del locker',
    tam.Descripcion AS 'Tamaño',
    u.Piso AS 'Piso de ubicación',
    tam.precio AS 'Precio'
FROM
    Lockers l
INNER JOIN Tamaño tam ON l.Tamaño = tam.Num
INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo
WHERE
    u.Doncencia = 1 AND
    l.Disponibilidad = 1
ORDER BY
    u.Doncencia,
    l.Num

-- Consulta #7
SELECT
    u.Doncencia AS 'Número del edificio (docencia)',
    SUM(IF(l.Disponibilidad = 1, 1, 0)) AS 'Cantidad de lockers disponibles',
    SUM(IF(l.Disponibilidad = 0, 1, 0)) AS 'Cantidad de lockers no disponibles'
FROM
    Lockers l
INNER JOIN Ubicacion u ON l.Ubicacion = u.Codigo
WHERE
    u.Doncencia = 1
GROUP BY
    u.Doncencia

-- Consulta #8
SELECT
    t.Descripcion AS 'Tamaño del locker',
    t.precio AS 'Precio del locker'
FROM
    Tamaño t

-- Consulta #9
SELECT
    u.Doncencia AS 'Docencia',
    COUNT(l.Num) AS 'Cantidad de lockers'
FROM
    Ubicacion u
INNER JOIN Lockers l ON u.Codigo = l.Ubicacion
GROUP BY u.Doncencia

-- Consulta #10
SELECT
    u.Piso,
    COUNT(l.Num) AS 'Cantidad de lockers'
FROM
    Ubicacion u
INNER JOIN Lockers l ON u.Codigo = l.Ubicacion
WHERE u.Doncencia = 1
GROUP BY u.Piso

-- Consulta #11
SELECT
    Num AS 'ID',
    Descripcion
FROM
    Carrera


-- Consulta #12
SELECT
    Puesto.Codigo AS Puesto,
    Puesto.Nombre AS 'Nombre del puesto',
    Departamento.Num AS 'ID Departamento',
    Departamento.Nombre AS 'Nombre del departamento'
FROM
    Puesto
INNER JOIN
    Depa_Puesto ON Puesto.Codigo = Depa_Puesto.Puesto
INNER JOIN
    Departamento ON Depa_Puesto.Departamento = Departamento.Num


-- Consulta #13
SELECT
    Num_Solicitante AS ID,
    CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre
FROM
    Solicitante


-- Consulta #14
SELECT
    Num_Metodo AS ID,
    Descripcion
FROM
    Metodo_De_Pago


-- Consulta #15
SELECT
    r.Num_Renta AS ID,
    r.Fecha_Inicio AS Fecha,
    CONCAT(s.Nombre_Pila, ' ', s.Ap_Paterno, ' ', s.ApMaterno) AS Nombre
FROM
    Renta r
INNER JOIN
    Solicitante s ON r.Num_Solicitante = s.Num_Solicitante

-- 16
SELECT r.Num_Renta AS ID, r.Fecha_Inicio AS 'Fecha Inicio', r.Fecha_Final AS 'Fecha Fin', 
       CONCAT(s.Nombre_Pila, ' ', s.Ap_Paterno, ' ', s.ApMaterno) AS Nombre
FROM Renta r
INNER JOIN Solicitante s ON r.Num_Solicitante = s.Num_Solicitante
WHERE r.Fecha_Final <= DATE_ADD(CURDATE(), INTERVAL 7 DAY)

-- 17
SELECT Num_Solicitante AS ID, CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre
FROM Solicitante
WHERE Docente = 41;

-- 18
SELECT Num_Solicitante AS ID, CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre
FROM Solicitante
WHERE Alumno = 1;

-- 19
SELECT Num_Solicitante AS ID, CONCAT(Nombre_Pila, ' ', Ap_Paterno, ' ', ApMaterno) AS Nombre
FROM Solicitante
WHERE Admnistrativo = 21

-- 20
SELECT Num_Solicitante 
FROM Solicitante 
WHERE 202300001 IN (Alumno, Docente, Admnistrativo)

-- 21
SELECT MAX(Num_Renta) AS Ultimo_Num_Renta FROM Renta

-- 22
SELECT
dp.Puesto AS Puesto, p.Nombre
FROM depa_puesto dp
INNER JOIN puesto p ON dp.Puesto = p.Codigo
WHERE dp.Departamento = 1