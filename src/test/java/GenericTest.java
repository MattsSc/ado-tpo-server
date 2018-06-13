import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import utils.HibernateUtils;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class GenericTest {

    private static List<String> tables= Arrays.asList(
            "Articulo",
            "OrdenDePedido",
            "Pedido",
            "ReservaArticulo",
            "Proveedor",
            "Cliente",
            "OrdenDeCompra",
            "Movimiento",
            "ItemPedido",
            "CuentaCorriente",
            "Factura",
            "ItemFactura",
            "Remito",
            "ItemRemito"
    );

   // @After
    //Clear DB
    public void after() {
        SessionFactory sf= HibernateUtils.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        tables.forEach(table -> {
            Query query = s.createSQLQuery("truncate table " + table);
            query.executeUpdate();
        });

        s.getTransaction().commit();
        s.close();
    }
}
