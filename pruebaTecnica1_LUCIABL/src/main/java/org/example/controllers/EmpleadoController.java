package org.example.controllers;

import org.example.entities.Empleado;
import org.example.persistence.EmpleadoJPA;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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

    //Tanto en actualización como en eliminación necesitan el ID para buscar el usuario
    public Empleado gestionEmpleado(Scanner t, String opcion) {
        System.out.println("Introduce un id de un empleado para "+opcion);
        int id = t.nextInt();
        return bucarEmpleado(id);
    }

    public Empleado validacionEmpleado(Scanner t){
        //Bucle se detiene cuando los campos están llenos
        String nombre = "", apellido = "", trabaj = "", fecha_in = ""; int salario = 0;
        while (nombre.isEmpty() || apellido.isEmpty() || fecha_in.isEmpty() || trabaj.isEmpty() ||  salario <= 0) {

            System.out.println("Introduce el nombre");
            nombre = t.nextLine();
            System.out.println("Introduce el apellido");
            apellido = t.nextLine();
            System.out.println("Introduce el trabajo");
            trabaj = t.nextLine();
            System.out.println("Introduce la fecha de inicio (YYYY-MM-DD)");
            fecha_in = t.nextLine();//Considerando una fecha
            System.out.println("Introduce el salario");
            salario = Integer.parseInt(t.nextLine());

            if(nombre.isEmpty() || apellido.isEmpty() || fecha_in.isEmpty() || trabaj.isEmpty() || salario <= 0){
                System.out.println("Los campos son obligatorios");
            }else{
                System.out.println("Datos válidos");
            }
        }
        return new Empleado(null, nombre, apellido, trabaj, salario,LocalDate.parse(fecha_in));

    }

}
