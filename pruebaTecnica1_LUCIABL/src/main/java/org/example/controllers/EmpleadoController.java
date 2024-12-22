package org.example.controllers;

import jdk.swing.interop.SwingInterOpUtils;
import org.example.entities.Empleado;
import org.example.persistence.EmpleadoJPA;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
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
                   buscar.setName(nombre); //Parámetro modificado
                break;
              case 2:
                  String apellido = validacionTexto(t,"apellido");
                  buscar.setSurname(apellido);
                break;
              case 3:
                  String cargo = validacionTexto(t,"cargo");
                  buscar.setJob(cargo);
                break;
              case 4:
                  int salario = validacionInt(t, "salario");
                  buscar.setMiWage(salario);
                  break;
              case 5:
                  LocalDate fecha = validacionLocalDate(t,"fecha de incio");
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

    private LocalDate validacionLocalDate(Scanner t, String fechaDeIncio) {
        // Debemos comprobar el año YYYY, el mes MM y el día DD por separado
        // * El mayor a 0 y menor que el año actual
        // * El mes 1 - 12
        // * El día 1 - 31
        System.out.println("Introduce la "+fechaDeIncio+" siguiendo los parámetros: " +
                "\n Introduce el año YYYY");
        int ano = t.nextInt();
        System.out.println("Introduce el mes MM");
        int mes = t.nextInt();
        System.out.println("Introduce el dia DD");
        int dia = t.nextInt();

        while((ano <= 0 || ano > LocalDate.now().getYear()) || (mes < 1 || mes > 12) || (dia < 1 || dia > 31) ){
            System.out.println("Los parámetros no son los correctos. Vuleve a introducirlos " +
                    "\n Introduce el año YYYY");
            ano = t.nextInt();
            System.out.println("Introduce el mes MM");
            mes = t.nextInt();
            System.out.println("Introduce el dia DD");
            dia = t.nextInt();
        }// fin while

        System.out.println("Fecha correcto");
        LocalDate fecha = LocalDate.of(ano,mes,dia);//Pasarlo a fecha
        return fecha;
    }

    private Integer validacionInt(Scanner t, String opcion) {
        //Mientras se un dato inválido
        // * Introducimos valor negativos o 0
        System.out.println("Introduce el "+opcion);
        int valor = t.nextInt(); //No va a salir del escaneo hasta teclear un número entero

        while(valor <= 0){
            System.out.println("No puedes introducir un salario negativo o 0");
            valor = t.nextInt();
        }// fin while

        System.out.println("Fecha correcto");
        return valor;
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
        }// fin while

        //Darle el formato, la primera letra en Mayúscula y el resto minúscula
        valor = valor.substring(0,1).toUpperCase().trim()+valor.substring(1).toLowerCase().trim();
        opcion = opcion.substring(0,1).toUpperCase().trim()+opcion.substring(1).toLowerCase().trim();
        System.out.println(opcion+" válido");
        return valor;
    }
}
