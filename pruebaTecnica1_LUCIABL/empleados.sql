--  Creación de la DB
CREATE DATABASE IF NOT EXISTS empleados;

--  Usando la DB
USE empleados;

--  Creación de la Tabla Movies
CREATE TABLE IF NOT EXISTS empleado (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    job VARCHAR(255) NOT NULL,
    wage DOUBLE,
    start_date DATETIME(6) 
);

--  Insertando datos en la Tabla Movies
INSERT INTO empleado (name, surname, job, wage, start_date)
VALUES
('Carlos', "Rivera", "Informático", 3500, "1999-05-12"),
('Jose', "García", "Administrador", 2600, "2003-07-01"),
('Brian', "Vázquez", "Informático", 5000, "2010-02-28"),
('Paula', "Otero", "Contable", 6000, "1987-01-12"),
('María', "Castro", "Juez", 4800, "2024-11-23"),
('Iago', "Lopéz", "Contable", 7000, "1645-05-23");