## **Prueba Técnica Gestión de Empleados** 

En esta práctica vamos a realizar la gestión de una empresa en el que necesitan empleados
para eso, interactuamos con una DB que tenemos presente para realizar las operaciones 
CRUD(crear, leer, actualizar y eliminar) y empleando JPA un estándar que gestiona la persistencia
de objetos en base de datos relacionales.

## Antes de comenzar:

  1. **Clonar el directorio de GitHub**: Para eso abre el 'Símbolo de sistemas' (cmd) de tu ordenador.
      ```
        cd Desktop
        git clone https://github.com/lucia2652003/JVB020N_Lucia.git
      ```
  2. **Abrir proyecto**: Abrimos Intellij IDEA (si no lo tienes instálalo). Necesario JDK 17 para hacerlo funcionar.
     Una vez hecho abrimos el proyecto Maven donde está la aplicación, así que ve a File > Open (Ctrl + O) > BlancoLucia_pruebatec1.
  3. **Encender el XAMPP**: Enciende MySQL y Apache (Start 'Empezar').
  4. **Workbench**: Enciéndelo y crear la conexión. 
  5. Coge el script **empleados.sql**, copia su sintaxis y lo pegas en Workbench. 
    Una vez hecho lo ejecutas pinchando en el icono del primer rayo :zap: que veas. Refresca
    DB '**SCHEMAS**' :refresh:. Crear otro script SQL y comprueba con la consulta 'SELECT * FROM empleados'.
  6. Comprobar los archivos de configuración. Debemos ver si los parámetros están bien.
     * **pon.xml**: Debe tener las librerías externas de Hibernate. Te mostrará el icono de Maven para refrescar.
          ```
            <!--Instalar las librerías externas para la conexión de una DB-->
               <dependencies>
                 <!--  JPA (Hibernate)  -->
                 <dependency>
                     <groupId>org.hibernate</groupId>
                     <artifactId>hibernate-core</artifactId>
                     <version>6.2.7.Final</version>
                 </dependency>
                 <!--  JPA API  -->
                 <dependency>
                    <groupId>jakarta.persistence</groupId>
                    <artifactId>jakarta.persistence-api</artifactId>
                    <version>3.1.0</version>
                 </dependency>
                <!-- Conector mysql workbench 8.0.33 -->
                <dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <version>8.0.33</version>
                </dependency>
               </dependencies>
          ```
     * **/main/resources/META-INF/persistence.xml**: Comprobar la base de datos, el user, el password y el persistence-unit
      porque con él podemos realizar la JPA (Java Persistence API).
          ```
           <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">
            <persistence-unit name="gt_empleados"> <!--Ojo con este elemento-->
              <class>com.ejemplo.Empleado</class>
              <properties>
                 <!--  Configuración de la base de datos  -->
                 <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
                 <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/empleados?serverTimezone=UTC"/>
                 <property name="javax.persistence.jdbc.user" value="root"/>
                 <property name="javax.persistence.jdbc.password" value=""/>
                 <!--  Mostrar sentencias SQL  -->
                 <property name="hibernate.show_sql" value="true"/>
                 <property name="hibernate.format_sql" value="true"/>
                 <!--  Crear las tablas automáticamente  -->
                 <property name="hibernate.hbm2ddl.auto" value="update"/>
              </properties>
            </persistence-unit>
           </persistence>
          ```
     * **/main/java/org/example/persistence/ConfigJPA**: Para establecer la conexión a la DB empleados, debe coincidir con el
      persistence-unit. **¡¡No lo cambies !!**
         ```
           package org.example.persistence;

           import jakarta.persistence.EntityManager;
           import jakarta.persistence.EntityManagerFactory;
           import jakarta.persistence.Persistence;

           public class ConfigJPA {

             //Para establecer la conexión con la DB
             private static final EntityManagerFactory emf =
                                  Persistence.createEntityManagerFactory("gt_empleados");

             public static EntityManager getEntityManager() {
                     return emf.createEntityManager();
             }

             public static void close(){//Cerrar la sentencia
                      emf.close();
             }

           }
         ```
       
## Estructura de JPA
 En el proyecto lo dividimos en tres directorios específicos para mejor organización 
 y limpieza de código. 
 Se dividen en:
   * controllers: Métodos que se realizan bajo las operaciones CRUD sobre la entidad Empleado.
   * entities: Se encuentran la plantilla de Empleado donde tiene los datos de la tabla 
     con sus especificaciones. Métodos para cambiar y mostrar variables. Especificar los parámetros
     para realizar mejor el mapeo de DB.
   * persistence: Configuración de la DB "ConfigJPA" y el mapeo para realizar las operaciones CRUD "EmpleadoJPA".
   * Main: Fichero en donde interactuaremos todo el rato. Introduciendo datos mientras que la app 
     realizará la entrada y salida de datos, la validación de datos y las operaciones CRUD. 

## ¿Cómo ejecutar?
   Una vez conectado las conexiones a la base de datos y comprobado los parámetros debemos encender la aplicación,
   para eso debemos dirigirnos al Main y ejecutar poniendo en el panel derecho una lista desplegable y poner
   'Current File' y pinchar la flecha derecha. Al principio como todo programa nos pide introducir el nombre explicará
   la gestión de empleados bajo las diferentes opciones que ofrece.
     
   