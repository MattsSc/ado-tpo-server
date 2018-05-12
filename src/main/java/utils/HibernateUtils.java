package utils;

import entities.ClienteEntity;
import entities.MovCCEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
            System.getProperty("java.security.policy");
            Configuration config = new Configuration();
            config.addAnnotatedClass(ClienteEntity.class);
            config.addAnnotatedClass(MovCCEntity.class);

            sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("La creacion de la SessionFactory fallo - Msg de HibernateUtil." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
