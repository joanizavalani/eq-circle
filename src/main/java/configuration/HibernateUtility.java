package configuration;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import entities.CircleCoordinates;
import entities.CircleData;

public class HibernateUtility {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws HibernateException {

        if(sessionFactory == null){
            Configuration configuration = new Configuration();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            configuration.addAnnotatedClass(CircleCoordinates.class);
            configuration.addAnnotatedClass(CircleData.class);

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
