

CREATE TABLE PACIENTE (
    idPaciente INT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidoPaterno VARCHAR(100) NOT NULL,
    apellidoMaterno VARCHAR(100) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    peso NUMERIC(5,2) NOT NULL,
    altura NUMERIC(5,2) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    telefono VARCHAR(13) NOT NULL,
    tipoSangre VARCHAR(22) NOT NULL,
    estado BOOL NOT NULL  
);



INSERT INTO PACIENTE (idPaciente, nombres, apellidoPaterno, apellidoMaterno, dni, peso, altura, fecha_nacimiento, telefono, tipoSangre, estado) VALUES
(1,'Piero', 'Ramirez', 'Lopez', '12345678', 70.5, 1.75, '1990-01-01', '123456789', 'O+', true),
(2, 'Lucia', 'Mendoza', 'Gutierrez', '87654321', 60.0, 1.60, '1988-03-15', '987654321', 'A+', true),
(3, 'Andres', 'Salazar', 'Rojas', '11223344', 85.0, 1.80, '1975-06-20', '923456789', 'B+', true),
(4, 'Jose', 'Perez', 'Diaz', '44332211', 78.2, 1.70, '1982-12-10', '912345678', 'AB-', true),
(5, 'Ana', 'Martinez', 'Silva', '55667788', 65.0, 1.65, '1995-09-25', '921234567', 'O-', true),
(6, 'María', 'Lopez', 'Gomez', '23456789', 55.3, 1.60, '1992-11-30', '934567890', 'A-', true),
(7, 'Carlos', 'Ruiz', 'Castillo', '34567890', 82.7, 1.78, '1985-04-12', '945678901', 'B-', true),
(8, 'Sofía', 'Torres', 'Espinoza', '45678901', 68.0, 1.65, '1993-07-07', '956789012', 'AB+', true),
(9, 'Fernando', 'Hidalgo', 'Mora', '56789012', 75.0, 1.72, '1989-02-18', '967890123', 'O+', true),
(10, 'Gabriela', 'Ramirez', 'Paredes', '67890123', 60.5, 1.58, '1990-05-09', '978901234', 'A+', true);


	CREATE TABLE MEDICO (
		idMedico INT PRIMARY KEY,
		dni char(8) not null UNIQUE,
		nombres VARCHAR(100) NOT NULL,
		apellidoPaterno VARCHAR(100) NOT NULL,
		apellidoMaterno VARCHAR(255),
		fecha_nacimiento DATE NOT NULL,
		correo VARCHAR(100) NOT NULL ,
		vigencia BOOL NOT NULL 
	);
INSERT INTO MEDICO (idMedico, dni, nombres, apellidoPaterno, apellidoMaterno, fecha_nacimiento, correo, vigencia) VALUES
(1, '12345678', 'Carmen', 'Gonzalez', 'Mejia', '1980-02-20', 'Carmen@gmail.com', true),
(2, '23456789', 'Roberto', 'Lopez', 'Castro', '1978-05-05', 'Roberto@gmail.com', true),
(3, '98165432', 'Paula', 'Fernandez', 'Ramos', '1985-07-22', 'Paula@gmail.com', true),
(4, '15535679', 'Diana', 'Lopez', 'Diaz', '1992-10-30', 'Diana@gmail.com', true),
(5, '03698391', 'Jorge', 'Perez', 'Gomez', '1990-04-12', 'Jorge@gmail.com', true),
(6, '74125896', 'Alberto', 'Garcia', 'Lozano', '1988-01-15', 'Alberto@gmail.com', true),
(7, '85296374', 'María', 'Vega', 'Campos', '1983-06-18', 'Maria@gmail.com', true),
(8, '15975384', 'Luis', 'Rojas', 'Quispe', '1975-12-25', 'Luis@gmail.com', true),
(9, '95135748', 'Lucía', 'Reyes', 'Aguilar', '1989-09-09', 'Lucia@gmail.com', true),
(10, '75395162', 'Fernando', 'Cruz', 'Benitez', '1991-03-04', 'Fernando@gmail.com', true);

CREATE TABLE ADMINISTRADOR (
    idAdministrador INT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidoPaterno VARCHAR(100) NOT NULL,
    apellidoMaterno VARCHAR(100),
    fecha_nacimiento DATE,
    telefono VARCHAR(13),
	dni char(8) unique,
	vigencia bool not null
);
INSERT INTO ADMINISTRADOR (idAdministrador, nombres, apellidoPaterno, apellidoMaterno, fecha_nacimiento, telefono,dni,vigencia) VALUES
(1, 'Tadeo', 'Mendoza', 'Gastulo', '2004-05-17', '920857938','70808728',true),
(2, 'Wilson', 'Mendoza', 'Maldonado', '1971-10-22', '966634406','03698391',true); 


CREATE TABLE USUARIO (
    idUsuario INT PRIMARY KEY,
    nombre_usuario VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,  
    correo VARCHAR(100) NOT NULL UNIQUE,
    vigencia BOOL NOT NULL,
    rol VARCHAR(50) NOT NULL,
    idMedico INT unique,
    idAdministrador INT unique,
    CHECK (rol IN ('administrador', 'medico')),
    CHECK (
        (rol = 'medico' AND idMedico IS NOT NULL AND idAdministrador IS NULL) OR
        (rol = 'administrador' AND idAdministrador IS NOT NULL AND idMedico IS NULL)
    )
);


CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO usuario (idUsuario, nombre_usuario, contraseña, correo, vigencia, rol, idMedico, idAdministrador) 
VALUES 
(1, 'tadeo', crypt('tadeo', gen_salt('bf')), 'tadeomendozatsm@gmail.com', true, 'administrador', NULL, 1),
(2, 'wilson', crypt('wilson', gen_salt('bf')), 'wilson@gmail.com', true, 'administrador', NULL,2),
	(3, 'Carmen', crypt('carmen', gen_salt('bf')), 'carmen@gmail.com', true, 'medico', 1, NULL),
(4, 'roberto', crypt('roberto', gen_salt('bf')), 'roberto@gmail.com', true, 'medico', 2, NULL);


CREATE TABLE ENFERMEDAD (
    idEnfermedad INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);
INSERT INTO ENFERMEDAD (idEnfermedad, nombre, descripcion) VALUES
(1, 'Diabetes', 'Enfermedad crónica que afecta la producción de insulina'),
(2, 'Hipertensión', 'Presión arterial elevada de manera constante'),
(3, 'Asma', 'Enfermedad respiratoria que provoca dificultad para respirar'),
(4, 'Artritis', 'Inflamación de las articulaciones que causa dolor y rigidez'),
(5, 'Gastritis', 'Inflamación de la mucosa del estómago que provoca dolor'),
(6, 'Migraña', 'Cefalea intensa que puede provocar sensibilidad a la luz y el sonido'),
(7, 'Depresión', 'Trastorno del estado de ánimo que causa tristeza persistente'),
(8, 'Alergia', 'Reacción exagerada del sistema inmunitario a ciertas sustancias'),
(9, 'Anemia', 'Disminución de los glóbulos rojos en la sangre, causa fatiga'),
(10, 'Hipotiroidismo', 'Trastorno donde la glándula tiroides no produce suficientes hormonas'),
(11, 'Hepatitis', 'Inflamación del hígado causada por virus o consumo excesivo de alcohol'),
(12, 'Bronquitis', 'Inflamación de los bronquios que causa tos persistente'),
(13, 'Psoriasis', 'Enfermedad crónica de la piel que causa escamas y picazón'),
(14, 'Alzheimer', 'Trastorno neurodegenerativo que afecta la memoria y la cognición'),
(15, 'Esclerosis múltiple', 'Enfermedad autoinmune que afecta el sistema nervioso central'),
(16, 'Parkinson', 'Trastorno progresivo que afecta el movimiento'),
(17, 'Epilepsia', 'Trastorno del sistema nervioso central que provoca convulsiones'),
(18, 'Osteoporosis', 'Pérdida de densidad ósea que aumenta el riesgo de fracturas'),
(19, 'Insuficiencia renal', 'Pérdida de la función de los riñones para filtrar desechos'),
(20, 'Colesterol alto', 'Exceso de colesterol en la sangre que puede obstruir las arterias'),
(21, 'Enfermedad de Crohn', 'Inflamación crónica del tracto gastrointestinal'),
(22, 'Úlcera péptica', 'Lesión en la mucosa del estómago o duodeno'),
(23, 'Esquizofrenia', 'Trastorno mental severo que afecta la percepción de la realidad'),
(24, 'Cáncer de mama', 'Crecimiento descontrolado de células en el tejido mamario'),
(25, 'Cáncer de pulmón', 'Crecimiento descontrolado de células en los pulmones'),
(26, 'Cáncer de piel', 'Crecimiento descontrolado de células en la piel'),
(27, 'Cáncer de próstata', 'Crecimiento anormal de células en la glándula prostática'),
(28, 'Cáncer de colon', 'Crecimiento anormal de células en el colon o recto'),
(29, 'Enfermedad celíaca', 'Reacción inmunitaria al consumo de gluten'),
(30, 'Fibromialgia', 'Trastorno que causa dolor generalizado y fatiga'),
(31, 'Trastorno bipolar', 'Alteraciones extremas en el estado de ánimo'),
(32, 'Obesidad', 'Acumulación excesiva de grasa en el cuerpo que afecta la salud'),
(33, 'Síndrome de Down', 'Trastorno genético causado por una copia extra del cromosoma 21'),
(34, 'Trombosis', 'Formación de un coágulo de sangre en un vaso sanguíneo'),
(35, 'Varicela', 'Infección viral que causa erupciones en la piel y fiebre'),
(36, 'Sarampión', 'Infección viral que causa fiebre y erupciones en la piel'),
(37, 'Hernia discal', 'Desplazamiento de un disco intervertebral que causa dolor'),
(38, 'Síndrome del túnel carpiano', 'Compresión del nervio mediano en la muñeca'),
(39, 'Enfermedad pulmonar obstructiva crónica', 'Afección pulmonar que dificulta la respiración'),
(40, 'Esguince', 'Lesión en los ligamentos que rodean una articulación'),
(41, 'Cataratas', 'Opacidad del cristalino del ojo que afecta la visión'),
(42, 'Glaucoma', 'Daño en el nervio óptico que puede causar pérdida de visión'),
(43, 'Rinitis alérgica', 'Reacción alérgica que causa inflamación en las fosas nasales'),
(44, 'Otitis', 'Inflamación del oído que puede causar dolor'),
(45, 'Síndrome de apnea del sueño', 'Interrupciones en la respiración durante el sueño'),
(46, 'Leucemia', 'Cáncer de los tejidos que forman la sangre'),
(47, 'Enfermedad de Lyme', 'Infección bacteriana transmitida por garrapatas'),
(48, 'Anorexia nerviosa', 'Trastorno alimenticio caracterizado por la pérdida extrema de peso'),
(49, 'Bulimia', 'Trastorno alimenticio caracterizado por episodios de atracones y purgas'),
(50, 'Esófago de Barrett', 'Lesión en el esófago causada por el reflujo ácido crónico');

CREATE TABLE PACIENTE_ENFERMEDAD (
    idPaciente INT NOT NULL,
    idEnfermedad INT NOT NULL,
    PRIMARY KEY (idPaciente, idEnfermedad),
    FOREIGN KEY (idPaciente) REFERENCES PACIENTE(idPaciente),
    FOREIGN KEY (idEnfermedad) REFERENCES ENFERMEDAD(idEnfermedad)
);
INSERT INTO PACIENTE_ENFERMEDAD (idPaciente, idEnfermedad) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 5), (3, 6),
(4, 7), (4, 8),
(5, 9), (5, 10),
(6, 1), (6, 5),
(7, 2), (7, 8),
(8, 3), (8, 4),
(9, 6), (9, 7),
(10, 8), (10, 9);



CREATE TABLE ESPECIALIDAD (
    idEspecialidad INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL unique,
    descripcion VARCHAR(255),
    estado bool not null 
);

INSERT INTO ESPECIALIDAD (idEspecialidad, nombre, descripcion, estado) VALUES
(1, 'Cardiología', 'Especialidad médica que se encarga del estudio, diagnóstico y tratamiento de las enfermedades del corazón', true),
(2, 'Endocrinología', 'Especialidad que se ocupa de las enfermedades del sistema endocrino y problemas hormonales', true),
(3, 'Gastroenterología', 'Especialidad médica que estudia las enfermedades del sistema digestivo', true),
(4, 'Dermatología', 'Especialidad que se encarga del tratamiento de las enfermedades de la piel', true),
(5, 'Neurología', 'Especialidad médica que estudia y trata los trastornos del sistema nervioso', true),
(6, 'Pediatría', 'Especialidad médica dedicada a la atención de la salud de los niños y adolescentes', true),
(7, 'Oncología', 'Especialidad médica que estudia y trata el cáncer', true),
(8, 'Neumología', 'Especialidad médica que trata las enfermedades del sistema respiratorio', true),
(9, 'Urología', 'Especialidad dedicada al tratamiento de las enfermedades del sistema urinario', true),
(10, 'Oftalmología', 'Especialidad médica encargada del cuidado y tratamiento de las enfermedades de los ojos', true),
(11, 'Reumatología', 'Estudio de enfermedades que afectan las articulaciones y los tejidos conectivos', true),
(12, 'Nefrología', 'Tratamiento de enfermedades renales', true),
(13, 'Geriatría', 'Cuidado y tratamiento médico de personas mayores', true),
(14, 'Hematología', 'Estudio de enfermedades de la sangre', true),
(15, 'Psiquiatría', 'Estudio y tratamiento de trastornos mentales', true),
(16, 'Medicina Interna', 'Diagnóstico y manejo de enfermedades complejas en adultos', true),
(17, 'Traumatología', 'Especialidad que trata lesiones del sistema musculoesquelético', true),
(18, 'Otorrinolaringología', 'Tratamiento de enfermedades del oído, nariz y garganta', true),
(19, 'Ginecología', 'Cuidado y tratamiento de enfermedades del sistema reproductor femenino', true),
(20, 'Obstetricia', 'Atención médica durante el embarazo y el parto', true),
(21, 'Anestesiología', 'Administración de anestesia y manejo del dolor', true),
(22, 'Cirugía General', 'Diagnóstico y tratamiento quirúrgico de enfermedades generales', true),
(23, 'Cirugía Plástica', 'Reparación, reconstrucción o modificación estética del cuerpo', true),
(24, 'Cirugía Cardiotorácica', 'Tratamiento quirúrgico de enfermedades del corazón y pulmones', true),
(25, 'Cirugía Pediátrica', 'Procedimientos quirúrgicos en niños', true),
(26, 'Cirugía Oncológica', 'Cirugía específica para extirpar tumores cancerosos', true),
(27, 'Neurocirugía', 'Cirugía del sistema nervioso', true),
(28, 'Radiología', 'Diagnóstico y tratamiento mediante imágenes médicas', true),
(29, 'Medicina Física y Rehabilitación', 'Recuperación de funciones tras enfermedades o lesiones', true),
(30, 'Medicina del Deporte', 'Tratamiento y prevención de lesiones relacionadas con la actividad física', true),
(31, 'Alergología', 'Tratamiento de alergias e hipersensibilidad', true),
(32, 'Inmunología', 'Estudio y manejo de trastornos del sistema inmunitario', true),
(33, 'Epidemiología', 'Estudio de enfermedades en poblaciones', true),
(34, 'Medicina Preventiva', 'Prevención de enfermedades y promoción de la salud', true),
(35, 'Toxicología', 'Tratamiento de intoxicaciones y efectos de sustancias tóxicas', true),
(36, 'Patología', 'Estudio de enfermedades mediante análisis de tejidos y fluidos', true),
(37, 'Genética Médica', 'Diagnóstico y manejo de enfermedades genéticas', true),
(38, 'Cuidados Paliativos', 'Atención a pacientes con enfermedades avanzadas o terminales', true),
(39, 'Medicina de Urgencias', 'Atención médica inmediata en situaciones de emergencia', true),
(40, 'Neuropsicología', 'Evaluación y tratamiento de funciones cognitivas relacionadas con el cerebro', true),
(41, 'Medicina Forense', 'Aplicación médica para investigaciones legales', true),
(42, 'Flebología', 'Estudio y tratamiento de enfermedades de las venas', true),
(43, 'Medicina del Trabajo', 'Cuidado de la salud en el ámbito laboral', true),
(44, 'Terapia Ocupacional', 'Rehabilitación para recuperar funciones en la vida diaria', true),
(45, 'Logopedia', 'Tratamiento de trastornos del habla y lenguaje', true),
(46, 'Fisioterapia', 'Recuperación física mediante ejercicios y técnicas manuales', true),
(47, 'Nutrición Clínica', 'Estudio y tratamiento relacionado con la alimentación', true),
(48, 'Farmacología', 'Estudio del uso de medicamentos para tratar enfermedades', true),
(49, 'Sexología', 'Estudio y tratamiento de problemas relacionados con la sexualidad', true),
(50, 'Terapia Intensiva', 'Atención médica para pacientes críticos', true);



CREATE TABLE MEDICO_ESPECIALIDAD (
    idMedico INT NOT NULL,
    idEspecialidad INT NOT NULL,
    PRIMARY KEY (idMedico, idEspecialidad),
    FOREIGN KEY (idMedico) REFERENCES MEDICO(idMedico),
    FOREIGN KEY (idEspecialidad) REFERENCES ESPECIALIDAD(idEspecialidad)
);

INSERT INTO MEDICO_ESPECIALIDAD (idMedico, idEspecialidad) VALUES
(1, 1), 
(1, 4), 
(1, 10), 
(2, 2), 
(2, 24), 
(3, 3), 
(4, 5), 
(5, 22),
(5, 9), 
(6, 11), 
(6, 17), 
(7, 14), 
(7, 35), 
(8, 12), 
(9, 29), 
(9, 31), 
(9, 32), 
(10, 40), 
(10, 36), 
(10, 47), 
(10, 44); 


CREATE TABLE TURNO_MEDICO (
	idTurno INT PRIMARY KEY,
	fecha DATE NOT NULL,
	hora_inicio TIME NOT NULL,
	hora_fin TIME NOT NULL,
	estado bool not null, -- true = Activo, false = Inactivo
	cupos int not null,
	idMedico INT NOT NULL,
	FOREIGN KEY (idMedico) REFERENCES MEDICO(idMedico)
	);


INSERT INTO TURNO_MEDICO (idTurno, fecha, hora_inicio, hora_fin, estado, cupos, idMedico) VALUES
(1, '2024-11-15', '08:00', '12:00', false, 8, 1),   
(2, '2024-11-15', '13:00', '17:00', false, 8, 2),   
(3, '2024-11-16', '08:00', '12:00', false, 8, 3),  
(4, '2024-11-16', '13:00', '17:00', false, 8, 4),  
(5, '2024-11-17', '09:00', '13:00', false, 8, 5),   
(6, '2024-11-18', '08:00', '12:00', false, 8, 1),   
(7, '2024-11-18', '13:00', '17:00', false, 8, 2),  
(8, '2024-11-19', '08:00', '12:00', false, 8, 3),  
(9, '2024-11-19', '13:00', '17:00', false, 8, 4),   
(10, '2024-11-20', '09:00', '13:00', false, 8, 5); 

CREATE TABLE CITA (
    idCita INT PRIMARY KEY,
    estado BOOL NOT NULL, -- false = Pendiente, true = Completada
    motivo VARCHAR(255) NOT NULL,
    idMedico INT NOT NULL,
    idPaciente INT NOT NULL,
    idTurno INT NOT NULL, -- Relación con TURNO_MEDICO
    FOREIGN KEY (idMedico) REFERENCES MEDICO(idMedico),
    FOREIGN KEY (idPaciente) REFERENCES PACIENTE(idPaciente),
    FOREIGN KEY (idTurno) REFERENCES TURNO_MEDICO(idTurno)
);

-- Inserciones en CITA
INSERT INTO CITA (idCita, estado, motivo, idMedico, idPaciente, idTurno) VALUES
(1, true, 'Consulta general', 1, 1, 1),       
(2, true, 'Control de diabetes', 2, 2, 2),     
(3, true, 'Revisión de presión arterial', 3, 3, 3),  
(4, true, 'Chequeo respiratorio', 4, 4, 4),    
(5, true, 'Consulta de rutina', 5, 5, 5), 
(6, true, 'Consulta por alergia', 1, 2, 6),    
(7, true, 'Examen de tiroides', 2, 3, 7),     
(8, true, 'Control de artritis', 3, 1, 8),      
(9, true, 'Chequeo pediátrico', 4, 5, 9),   
(10, true, 'Consulta de oftalmología', 5, 4, 10);



CREATE TABLE DETALLE_CITA (
    idDetalleCita INT PRIMARY KEY ,
    descripcion VARCHAR(255),
    diagnostico VARCHAR(200) not null,
    medicamento VARCHAR(255),
    dosis VARCHAR(100),
    idCita INT not null,
    FOREIGN KEY (idCita) REFERENCES CITA(idCita)
);



CREATE TABLE SERVICIO (
    idServicio INT PRIMARY KEY ,
    nombre VARCHAR(100) NOT NULL unique	,
    descripcion VARCHAR(255),
    precio NUMERIC(9, 2) NOT NULL,
    estado bool not null 
);

INSERT INTO SERVICIO (idServicio, nombre, descripcion, precio, estado) VALUES
(1, 'Consulta prostata', 'Consulta médica general para evaluación de salud', 50.00, true),
(2, 'Ecografía', 'Estudio de imágenes para diagnóstico', 120.00, true),
(3, 'Radiografía', 'Imágenes de rayos X para diagnóstico óseo', 80.00, true),
(4, 'Laboratorio de Sangre', 'Análisis completo de sangre', 100.00, true),
(5, 'Electrocardiograma', 'Estudio del ritmo y actividad del corazón', 60.00, true),
(6, 'Tomografía', 'Estudio avanzado de imágenes', 300.00, true),
(7, 'Vacunación', 'Vacunas según calendario y prescripción', 30.00, true),
(8, 'Fisioterapia', 'Sesiones de rehabilitación física', 70.00, true),
(9, 'Consulta Dermatológica', 'Consulta especializada en salud de la piel', 90.00, true),
(10, 'Consulta Pediátrica', 'Consulta médica para niños y adolescentes', 75.00, true),
(11, 'Consulta Ginecológica', 'Consulta médica especializada en salud femenina', 85.00, true),
(12, 'Terapia Psicológica', 'Sesión de apoyo psicológico y tratamiento', 100.00, true),
(13, 'Consulta Cardiológica', 'Evaluación y diagnóstico de enfermedades del corazón', 120.00, true),
(14, 'Endoscopía', 'Examen visual del tracto digestivo', 350.00, true),
(15, 'Consulta Neurológica', 'Consulta especializada en trastornos del sistema nervioso', 150.00, true),
(16, 'Chequeo Médico Preventivo', 'Paquete de evaluación médica general', 200.00, true),
(17, 'Consulta Oncológica', 'Consulta especializada en diagnóstico y tratamiento de cáncer', 180.00, true),
(18, 'Consulta Oftalmológica', 'Consulta para evaluación de la salud ocular', 95.00, true),
(19, 'Sesión de Logopedia', 'Tratamiento de trastornos del habla y lenguaje', 70.00, true),
(21, 'Diálisis', 'Tratamiento para pacientes con insuficiencia renal', 400.00, true),
(22, 'Vacunación Internacional', 'Aplicación de vacunas necesarias para viajes internacionales', 50.00, true),
(23, 'Prueba de Alergia', 'Evaluación de reacciones alérgicas específicas', 150.00, true),
(24, 'Ecocardiograma', 'Evaluación visual del corazón mediante ultrasonido', 250.00, true),
(25, 'Consulta Psiquiátrica', 'Consulta especializada en trastornos mentales', 200.00, true),
(26, 'Revisión Odontológica', 'Examen dental básico', 60.00, true),
(27, 'Limpieza Dental', 'Procedimiento de limpieza y cuidado oral', 90.00, true),
(28, 'Prueba de Esfuerzo', 'Evaluación del funcionamiento cardiovascular durante ejercicio', 220.00, true),
(29, 'Consulta Urológica', 'Consulta médica especializada en sistema urinario y masculino', 130.00, true),
(30, 'Consulta Geriátrica', 'Consulta médica para personas mayores', 95.00, true),
(31, 'Sesión de Rehabilitación Respiratoria', 'Tratamiento para mejorar la función pulmonar', 80.00, true),
(36, 'Prueba de Tolerancia a la Glucosa', 'Evaluación para diagnóstico de diabetes', 60.00, true),
(37, 'Sesión de Cuidados Paliativos', 'Atención para pacientes con enfermedades avanzadas o terminales', 200.00, true),
(38, 'Consulta de Medicina del Trabajo', 'Evaluación médica relacionada con el ámbito laboral', 85.00, true),
(39, 'Consulta de Terapia Ocupacional', 'Rehabilitación para recuperar habilidades funcionales', 75.00, true),
(40, 'Consulta de Medicina Física', 'Evaluación para tratamiento físico y rehabilitación', 95.00, true),
(41, 'Consulta de Traumatología', 'Evaluación de lesiones musculoesqueléticas', 130.00, true),
(42, 'Consulta de Endocrinología', 'Consulta médica para trastornos hormonales y metabólicos', 140.00, true),
(43, 'Prueba de Coagulación', 'Evaluación del tiempo de coagulación de la sangre', 55.00, true),
(46, 'Terapia Intensiva', 'Atención médica avanzada para pacientes críticos', 500.00, true);



CREATE TABLE METODO_PAGO (
    idMetodoPago INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE  
);
INSERT INTO METODO_PAGO (idMetodoPago, nombre) VALUES
(1, 'Tarjeta de crédito'),
(2, 'Efectivo'),
(3, 'YAPE'),
(4, 'Plin'),
(5, 'PayPal');


create table pago(
	idPago int primary key,
	fecha date not null default current_date,
	estado bool not null,--false=pendiente , true=cancelado
	idPaciente int not null,
	idMetodoPago int not null,
	foreign key (idPaciente) references paciente(idPaciente),
	foreign key (idMetodoPago) references metodo_pago(idMetodoPago)
);
INSERT INTO PAGO (idPago, fecha, estado, idPaciente, idMetodoPago) VALUES
(1, '2024-11-15', true, 1, 1),      
(2, '2024-11-16', true, 2, 2),   
(3, '2024-11-17', true, 3, 3), 
(4, '2024-11-18', true, 4, 4),
(5, '2024-11-19', true, 5, 5),   
(6, '2024-11-20', true, 1, 1),     
(7, '2024-11-21', true, 2, 2),    
(8, '2024-11-22', true, 3, 3),
(9, '2024-11-23', true, 4, 4), 
(10, '2024-11-24', true, 5, 5); 


CREATE SEQUENCE detalle_pago_seq START 1;
CREATE TABLE DETALLE_PAGO (
    idDetallePago INT DEFAULT nextval('detalle_pago_seq') primary key,
    idPago INT NOT NULL,
    idCita INT ,        
    idServicio INT ,     
	subtotal numeric(9,2) not null,
	igv numeric(4,2)not null,
    monto_total NUMERIC(10, 2), 
    descripcion varchar(255),
	FOREIGN KEY (idPago) REFERENCES PAGO(idPago),
    FOREIGN KEY (idCita) REFERENCES CITA(idCita),
    FOREIGN KEY (idServicio) REFERENCES SERVICIO(idServicio),
    CHECK (idCita IS NOT NULL OR idServicio IS NOT NULL) 
);



