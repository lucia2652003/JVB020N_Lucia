--  Usando la DB
USE empleados;

--  Creación de la Tabla Empleado
CREATE TABLE IF NOT EXISTS empleado (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    job VARCHAR(255) NOT NULL,
    wage INT NOT NULL,
    start_date DATE NOT NULL
);

--  Insertando datos en la Tabla Empleado
INSERT INTO empleado (name, surname, job, wage, start_date)
VALUES
('Carlos', "Rivera", "Informático", 3500, "1999-05-12"),
('Jose', "García", "Administrador", 2600, "2003-07-01"),
('Brian', "Vázquez", "Informático", 5000, "2010-02-28"),
('Paula', "Otero", "Contable", 6000, "1987-01-12"),
('María', "Castro", "Jueza", 4800, "2024-11-23"),
('Iago', "Lopéz", "Contable", 7000, "1645-05-23"),
('Lola', "Grandes", "Contable", 1500, "2004-10-10"),
('Marcos', "Moutos", "Programador", 5520, "2001-03-14"),
('Fiona', "Lama", "Contable", 1500, "2002-09-27"),
('Luis', "Maneiro", "Informático", 1250, "2000-04-22");
