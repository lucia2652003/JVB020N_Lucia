package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "empleado")
public class Empleado {
    //Las columnas de la tabla creada de nuestra DB

    //Cada vez que insertemos un empleado se genera un ID automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Como si fuera un AUTO_INCREMENT
    private Integer id;

    @Column
    private String name; //Nombre

    @Column
    private String surname; //Apellido

    @Column
    private String job;//Trabajo

    @Column(name = "wage")
    private Integer miWage;//Salario

    @Column
    private LocalDate start_date; //Fecha que empezó a trabajar

    //Constructores
    public Empleado() {
    }

    public Empleado(Integer id, String name, String surname, String job, Integer miWage, LocalDate start_date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.miWage = miWage;
        this.start_date = start_date;
    }

    //Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMiWage() {
        return miWage;
    }

    public void setMiWage(Integer miWage) {
        this.miWage = miWage;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }


    //toString()

    @Override
    public String toString() {
        return "Empleado: ID:"+ id +" Nombre Completo: "+ name +" "+surname+
                ", Ocupación: " + job  +
                ", Salario: " + miWage +" €"+", Fecha inicio: " + start_date ;
    }

    public String toStringJob() {//Para ver los empleados según trabajo
        return "Empleado: ID:"+ id +" Nombre Completo: "+ name +" "+surname+
                ", Salario: " + miWage +" €"+", Fecha inicio: " + start_date ;
    }
}
