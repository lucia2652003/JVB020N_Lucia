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
