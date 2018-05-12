package dao;

import entities.ClienteEntity;
import model.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDAO {

    public static void save(Cliente cliente) {
        saveTransaction(toEntity(cliente));
    }

    public static List<Cliente> getClientes(){
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        List<ClienteEntity> result = s.createQuery("from ClienteEntity").list();
        s.getTransaction().commit();

        return result.stream().map(ClienteDAO::toNegocio).collect(Collectors.toList());

    }

    public static Cliente getCliente(Integer dni){ //
        ClienteEntity ClienteE;
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        ClienteE = (ClienteEntity) s.createQuery("from ClienteEntity c where c.dni=?").setParameter(0, dni).uniqueResult();
        s.getTransaction().commit();

        return toNegocio(ClienteE);

    }

    private static void saveTransaction(Object e){
        SessionFactory sf= HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        s.save(e);
        s.getTransaction().commit();
        s.close();
    }

    private static ClienteEntity toEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDomicilio(),
                cliente.getCuit(),
                cliente.getRazonSocial(),
                cliente.getLimiteCredito(),
                cliente.getMontoDisponible()
        );
    }

    private static Cliente toNegocio(ClienteEntity clienteE) {
        return new Cliente(clienteE.getDni(),
                clienteE.getNombre(),
                clienteE.getApellido(),
                clienteE.getDomicilio(),
                clienteE.getCuit(),
                clienteE.getRazonSocial(),
                clienteE.getLimiteCredito(),
                clienteE.getMontoDisponible());
    }


}
