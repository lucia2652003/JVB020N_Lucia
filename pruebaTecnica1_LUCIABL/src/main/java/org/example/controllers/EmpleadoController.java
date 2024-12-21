package org.example.controllers;

import org.example.entities.Empleado;
import org.example.persistence.EmpleadoJPA;

import java.util.List;

//Las operaciones CRUD sobre la entidad Empleado
public class EmpleadoController {
    EmpleadoJPA empJPA = new EmpleadoJPA();

    public void crearEmpleado(Empleado emp) {
        empJPA.create(emp);
    }

    public List<Empleado> listarEmpleados() {
        return empJPA.findAll();
    }

    public List<Empleado> buscarCargo(String job) {
       return empJPA.findJob(job);
    }

    public void eliminarEmpleado(Integer idBuscar) {
        empJPA.delete(idBuscar);
    }


    public Empleado bucarEmpleado(int id) {
        return empJPA.find(id);
    }

    public void actualizarEmpleado(Empleado encontrado) {
        empJPA.update(encontrado);
    }
}
