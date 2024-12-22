package org.example;

import org.example.controllers.EmpleadoController;
import org.example.entities.Empleado;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpleadoController controller = new EmpleadoController();

        Scanner t = new Scanner(System.in);//Interactuar en el programa

        int eleccion = 0;
        System.out.println("Bienvenido. Por favor introduzca el móvil");
        String nb = t.nextLine();
        System.out.println("Hola "+nb+", vamos a realizar la gestión de empleados");


        while(!(eleccion == -1)){ //Mientras que no se cumpla la condición
            System.out.println("Elija una de estas opciones: " +
                    "\n 1. Agregar empleado" +
                    "\n 2. Listar empleados "+
                    "\n 3. Actualizar empleado"+
                    "\n 4. Eliminar un empleado "+
                    "\n 5. Buscar empleados por cargo " +
                    "\n ¿Quieres continuar? Pulse -1 para finalizar");
            eleccion = t.nextInt();
            switch (eleccion){
                case 1:
                    System.out.println("Agregar un empleado");
                    t.nextLine();//Evita el salto
                    //Validación de datos
                    String nombre = controller.validationTexto(t,"nombre");
                    String apellido = controller.validationTexto(t,"apellido");
                    String cargo = controller.validationTexto(t,"cargo");
                    int salario = controller.validationSalario(t, "salario");
                    LocalDate fecha = controller.validationFechaInicio(t, "fecha de incio");

                    //Creación de datos
                    Empleado nuevo = new Empleado(null, nombre,apellido,cargo,salario,fecha);
                    controller.crearEmpleado(nuevo);
                    break;
                case 2:
                    List<Empleado> listaEmpleado = controller.listarEmpleados();
                    System.out.println("Lista de los empleados: ");
                    for(Empleado empleado : listaEmpleado){
                        System.out.println(empleado);
                    }
                    System.out.println();
                    break;
                case 3:
                    //Actualización de un empleado
                    controller.gestionEmpleado(t,"actualizar");
                    break;
                case 4:
                    //Eliminación de empleado
                    controller.gestionEmpleado(t,"eliminar");
                    break;
                case 5:
                    //Buscar a los empleados por cargo
                    t.nextLine();//Para evitar el salto
                    System.out.println("Introduce un cargo para buscar dichos empleados");
                    String trabajo = t.nextLine();
                    List<Empleado> listaCargo = controller.buscarCargo(trabajo);
                    if(listaCargo.isEmpty()){
                        System.out.println("No hay un listado de ese cargo");
                    }else{
                        System.out.println("Empleados del cargo "+trabajo+": ");
                        for(Empleado empleado : listaCargo){
                            System.out.println(empleado.toStringJob());
                        }
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Debes introducir valores (1 a 5). Introduzca de nuevo");
                    break;
            }// fin switch

        }// fin do...while

        System.out.println("Muchas gracias. Que tengas un bien día "+nb);

    }
}