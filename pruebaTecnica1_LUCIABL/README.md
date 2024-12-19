## **Prueba Técnica Gestión de Empleados** 

En esta práctica vamos a realizar la gestión una empresa en qle que necesitan empleados
para realizar diferentes funciones.

## Antes de comenzar:

  1. **Encender el XAMPP**: Enciende MySQL y Apache (Start 'Empezar')
  2. **Workbench**: Enciendelo y crear la conexión
  3. Coge el script **empleados.sql**, copia sus sintaxis y lo pegas en Workbench. 
    Una vez hecho lo ejecutas pinchando en el icono del primer rayo que veas. Refresca
    BD '**SCHEMAS**' y lo muestra crear otro script SQL y lo compruebas 'SELECT * FROM empleados'.
  4. Comprobar los archivos de configuración. Debemos ver si los parámetros están bien 
        * pon.xml: Debe terner las librerías externas de Hibernate
             
        * META-INF/persistence.xml:

## Estructura de JPA
 En el proyecto lo dividimos en tres directorios específicos para mejor organización. 
 Se dividen en:
   * controllers: Métodos que se realizan bajo las operaciones CRUD sobre la entidad Empleado
   * entities: Se encuentran la plantilla de Empleado donde tiene los datos de la tabla 
     con sus especificaciones. Métodos para cambiar y mostrar variables.
   * persistence: Configuración de la DB "ConfigJPA"
        y el mapeo para realizar las operaciones CRUD "EmpleadoJPA"
   * 


