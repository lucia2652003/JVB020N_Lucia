package org.example;

import org.example.controllers.EmpleadoController;
import org.example.entities.Empleado;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpleadoController controller = new EmpleadoController();

        Scanner t = new Scanner(System.in);//Interactuar en el programa

        int eleccion = 0;
        /*System.out.println("Bienvenido. Por favor introduzca el móvil");
        String nombre = t.nextLine();
        System.out.println("Hola "+nombre+", vamos a realizar la gestión de empleados");*/


        while(!(eleccion == -1)){ //Mientras que no se cumpla la condición
            System.out.println("Elija una opción: " +
                    "\n 1. Agregar empleado" +
                    "\n 2. Listar empleados "+
                    "\n 3. Actualizar empleado"+
                    "\n 4. Eliminar un empleado "+
                    "\n 5. Buscar empleados por cargo " +
                    "\n ¿Quieres continuar? Pulse -1 para finalizar");
            eleccion = t.nextInt();
            switch (eleccion){
                case 1:
                    t.nextLine();//Evitar el salto
                    System.out.println("Agregar un empleado");
                    //Validación de datos y creación
                    Empleado nuevo =controller.validacionEmpleado(t);
                    controller.crearEmpleado(nuevo);
                    System.out.println("Empleado ingresado");
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
                    Empleado buscar =controller.gestionEmpleado(t,"actualizar");
                    if(buscar == null){ //No encontró el empleado en DB
                        System.out.println("Ese empleado no existe en BD");
                    }else{
                        controller.actualizarEmpleado(t,buscar);
                        //System.out.println("El empleado "+buscar.getId() +" ha sido actualizado");
                    }
                    break;
                case 4:
                    //Eliminación de empleado
                    Empleado emp = controller.gestionEmpleado(t,"eliminar");
                    if(emp == null){ //No encontró el empleado en DB
                        System.out.println("Ese empleado no existe en BD");
                    }else{
                        controller.eliminarEmpleado(emp.getId());
                        System.out.println("El empleado "+emp.getId() +" ha sido eliminado");
                    }
                    break;
                case 5:
                    //Buscar a los empleados por cargo
                    t.nextLine();//Para evitar el salto
                    System.out.println("Introduce un cargo para buscar dichos empleados");
                    String cargo = t.nextLine();
                    List<Empleado> listaCargo = controller.buscarCargo(cargo);
                    if(listaCargo.isEmpty()){
                        System.out.println("No hay un listado de ese cargo");
                    }else{
                        System.out.println("Empleados del cargo "+cargo+": ");
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

    }
}