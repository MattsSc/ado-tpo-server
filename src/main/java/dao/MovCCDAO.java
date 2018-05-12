package dao;

import entities.ClienteEntity;
import entities.MovCCEntity;
import model.Cliente;
import model.MovimientoCC;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

public class MovCCDAO {

    public static void save(Cliente cliente, MovimientoCC movimientoCC){
        saveTransaction(toEntity(movimientoCC, cliente));
    }

    private static MovCCEntity toEntity(MovimientoCC movimientoCC, Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        return new MovCCEntity(
                movimientoCC.getFecha(),
                movimientoCC.getImporte(),
                movimientoCC.getTipo(),
                new ClienteEntity(
                        cliente.getDni(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getDomicilio(),
                        cliente.getCuit(),
                        cliente.getRazonSocial(),
                        cliente.getLimiteCredito(),
                        cliente.getMontoDisponible()
                )
        );
    }


    private static void saveTransaction(Object e){
        SessionFactory sf= HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        s.save(e);
        s.getTransaction().commit();
        s.close();
    }
}
