## **Prueba Técnica Gestión de Empleados** 

En esta práctica vamos a realizar la gestión de una empresa en el que necesitan empleados
para eso, debemos interacturar con una DB que tenemos presente para realizar las operaciones 
CRUD(crear, leer, actualizar y eliminar).

## Antes de comenzar:

  1. **Encender el XAMPP**: Enciende MySQL y Apache (Start 'Empezar')
  2. **Workbench**: Enciendelo y crear la conexión
  3. Coge el script **empleados.sql**, copia sus sintaxis y lo pegas en Workbench. 
    Una vez hecho lo ejecutas pinchando en el icono del primer rayo que veas. Refresca
    BD '**SCHEMAS**'. Crear otro script SQL y comprueba con la consulta 'SELECT * FROM empleados'.
  4. Comprobar los archivos de configuración. Debemos ver si los parámetros están bien 
        * pon.xml: Debe terner las librerías externas de Hibernate
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
        * /main/resources/META-INF/persistence.xml: Comprobar la base de datos, el user y el password
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
        * /main/java/org/example/persistence/ConfigJPA:
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
        * 

## Estructura de JPA
 En el proyecto lo dividimos en tres directorios específicos para mejor organización. 
 Se dividen en:
   * controllers: Métodos que se realizan bajo las operaciones CRUD sobre la entidad Empleado
   * entities: Se encuentran la plantilla de Empleado donde tiene los datos de la tabla 
     con sus especificaciones. Métodos para cambiar y mostrar variables.
   * persistence: Configuración de la DB "ConfigJPA"
        y el mapeo para realizar las operaciones CRUD "EmpleadoJPA".

