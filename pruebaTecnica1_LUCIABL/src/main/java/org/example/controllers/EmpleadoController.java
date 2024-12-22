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

    //Elegir una de las opciones para actualizar al empleado
    public void actualizarEmpleado(Scanner t, Empleado buscar) {

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
                   String nombre = validationTexto(t,"nombre");
                   buscar.setName(nombre); //Parámetro modificado
                break;
              case 2:
                  String apellido = validationTexto(t,"apellido");
                  buscar.setSurname(apellido);
                break;
              case 3:
                  String cargo = validationTexto(t,"cargo");
                  buscar.setJob(cargo);
                break;
              case 4:
                  int salario = validationSalario(t, "salario");
                  buscar.setMiWage(salario);
                  break;
              case 5:
                  LocalDate fecha = validationFechaInicio(t,"fecha de incio");
                  buscar.setStart_date(fecha);
                  break;
              default:
                 System.out.println("Lo siento pero no existe ese valor");
                 break;
            }// fin switch

        }while(opcion < 1 || opcion > 5);
        System.out.println(buscar);
        empJPA.update(buscar);//Actualizar empleado
    }

    public LocalDate validationFechaInicio(Scanner t, String fechaDeIncio) {
        // Debemos comprobar el año YYYY, el mes MM y el día DD por separado
        // * El año debe ser mayor a 0 y menor que el año actual
        // * El mes 1 - 12
        // * El día 1 - 31
        System.out.println("Introduce la "+fechaDeIncio+" siguiendo los parámetros: " +
                "\n Introduce el año YYYY menor o igual a "+LocalDate.now().getYear()+", nunca menor a 0");
        int ano = t.nextInt();
        System.out.println("Introduce el mes MM o M 01-12");
        int mes = t.nextInt();
        System.out.println("Introduce el dia DD o D 01-31");
        int dia = t.nextInt();

        while((ano <= 0 || ano > LocalDate.now().getYear()) || (mes < 1 || mes > 12) || (dia < 1 || dia > 31) ){
            System.out.println("Los parámetros no son los correctos. Vuleve a introducirlos " +
                    "\n Introduce el año YYYY menor o igual a "+LocalDate.now().getYear()+", nunca menor a 0");
            ano = t.nextInt();
            System.out.println("Introduce el mes MM o M 01-12");
            mes = t.nextInt();
            System.out.println("Introduce el dia DD o D 01-31");
            dia = t.nextInt();
        }// fin while

        System.out.println("Fecha correcto");
        //Pasarlo a fecha
        return LocalDate.of(ano ,mes, dia);
    }

    public Integer validationSalario(Scanner t, String opcion) {
        //Mientras se un dato inválido
        // * Introducimos valor negativos o 0
        System.out.println("Introduce el "+opcion);
        int valor = t.nextInt(); //No va a salir del escaneo hasta teclear un número entero

        while(valor <= 0){
            System.out.println("No puedes introducir un salario negativo o 0");
            valor = t.nextInt();
        }// fin while

        System.out.println("Salario correcto");
        return valor;
    }

    public String validationTexto(Scanner t, String opcion) {
        //Mientras se un dato inválido
        // * Si es un campo vacío
        System.out.println("Introduce el "+opcion);
        String valor = t.nextLine();

        while(valor.isEmpty()){
            System.out.println("Vuelve a introducirlo");
            valor = t.nextLine();
        }// fin while

        //Darle el formato, la primera letra en Mayúscula y el resto minúscula
        valor = valor.substring(0,1).toUpperCase().trim()+valor.substring(1).toLowerCase().trim();
        opcion = opcion.substring(0,1).toUpperCase().trim()+opcion.substring(1).toLowerCase().trim();
        System.out.println(opcion+" válido");
        return valor;
    }
}
