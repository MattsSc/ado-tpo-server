package utils;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
            System.getProperty("java.security.policy");
            Configuration config = new Configuration();
            config.addAnnotatedClass(ArticuloEntity.class);
            config.addAnnotatedClass(ClienteEntity.class);
            config.addAnnotatedClass(FacturaEntity.class);
            config.addAnnotatedClass(ItemFacturaEntity.class);
            config.addAnnotatedClass(ItemPedidoEntity.class);
            config.addAnnotatedClass(LoteEntity.class);
            config.addAnnotatedClass(MovCCEntity.class);
            config.addAnnotatedClass(MovimientoBasicoEntity.class);
            config.addAnnotatedClass(MovimientoEntity.class);
            config.addAnnotatedClass(MovimientoPorEliminacionEntity.class);
            config.addAnnotatedClass(OrdenDeCompraEntity.class);
            config.addAnnotatedClass(OrdenDePedidoEntity.class);
            config.addAnnotatedClass(PedidoEntity.class);
            config.addAnnotatedClass(ProveedorEntity.class);
            config.addAnnotatedClass(ReservaArticuloEntity.class);
            config.addAnnotatedClass(UbicacionEntity.class);

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

    public static void saveTransaction(Object e){
        SessionFactory sf= HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        s.save(e);
        s.getTransaction().commit();
        s.close();
    }

    public static void updateTransaction(Object e){
        SessionFactory sf= HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        s.update(e);
        s.getTransaction().commit();
        s.close();
    }

    public static void deleteTransaction(Object e){
        SessionFactory sf= HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        s.delete(e);
        s.getTransaction().commit();
        s.close();
    }

    public static <T> List<T> getResultList(String query){
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        List<T> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public static  Optional getOneResult(String query){
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        Optional result = s.createQuery(query).uniqueResultOptional();
        s.getTransaction().commit();

        return result;
    }

    public static <T> T  getById(Class<T> E, Integer id){
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        T result= s.get(E, id);
        s.getTransaction().commit();

        return result;
    }
}
