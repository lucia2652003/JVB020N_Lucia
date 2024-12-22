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

    //Elegir una de las opciones para actualizar al empleado
    public void actualizarEmpleado(Scanner t,Empleado buscar) {

        int opcion;
        do{
            System.out.println("Que quieres cambiar: " +
                "\n 1. Nombre" +
                "\n 2. Apellido "+
                "\n 3. Trabajo"+
                "\n 4. Salario "+
                "\n 5. Fecha de inicio " );
            opcion = t.nextInt();
            switch(opcion){
               case 1:
                   String nombre = validacionTexto(t,"nombre");
                   buscar.setName(nombre);
                   System.out.println(buscar);
                break;
              case 2:
                  String apellido = validacionTexto(t,"apellido");
                  buscar.setSurname(apellido);
                  System.out.println(buscar);
                break;
              case 3:
                  String cargo = validacionTexto(t,"cargo");
                  buscar.setJob(cargo);
                  System.out.println(buscar);
                break;
               case 4:
                System.out.println("Introduce el salario");
                break;
               case 5:
                System.out.println("Introduce la fecha de inicio");
                break;
               default:
                 System.out.println("Lo siento pero no existe ese valor");
                break;
            }// fin switch

        }while(opcion < 1 || opcion > 5);
        empJPA.update(buscar);
    }

    private String validacionTexto(Scanner t, String opcion) {
        t.nextLine();
        //Mientras se un dato inválido
        // * Si es un campo vacío
        System.out.println("Introduce el "+opcion);
        String valor = t.nextLine();
        while(valor.isEmpty()){
            System.out.println("Vuelve a introducirlo");
            valor = t.nextLine();
        }
        //Darle el formato, la primera letra en Mayúscula y el resto minúscula
        valor = valor.substring(0,1).toUpperCase()+valor.substring(1).toLowerCase();
        return valor;
    }
}
