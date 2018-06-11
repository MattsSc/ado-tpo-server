import dao.OrdenDePedidoDAO;
import dao.ReservaArticuloDAO;
import model.*;
import model.enums.EstadoPedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.H2Dialect;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.DatosUtils;
import utils.HibernateUtils;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

public class PedidoTest {

    private static List<String> tables= Arrays.asList(
            "Articulo",
            "OrdenDePedido",
            "Pedido",
            "ReservaArticulo",
            "Proveedor",
            "Cliente",
            "OrdenDeCompra",
            "Movimiento",
            "ItemPedido"
    );

    @Before
    public void before() {
        // setup the session factory
    }

    @Test
    public void aprobarPedidoOk() {
        Articulo articulo1 = DatosUtils.crearArticulo(1);
        Articulo articulo2 = DatosUtils.crearArticulo(2);
        Articulo articulo3 = DatosUtils.crearArticulo(3);

        Proveedor proveedor = DatosUtils.crearProveedor(1);

        DatosUtils.crearOC(articulo1, 500, proveedor);
        DatosUtils.crearOC(articulo2, 500, proveedor);
        DatosUtils.crearOC(articulo3, 500, proveedor);

        Pedido pedido = new Pedido(
                DatosUtils.crearCliente(),
                EstadoPedido.RECIBIDO.name(),
                "Calle falsa 123",
                Arrays.asList(
                        DatosUtils.crearItem(articulo1, 100),
                        DatosUtils.crearItem(articulo2, 200),
                        DatosUtils.crearItem(articulo3, 300)
                )
        );

        pedido.save();
        pedido.aprobar();

        List<ReservaArticulo> reservaArticuloList = ReservaArticuloDAO.getByPedidoId(pedido.getId());

        Assert.assertEquals(3,reservaArticuloList.size());

        Assert.assertEquals(100,reservaArticuloList.get(0).getCantidad());
        Assert.assertEquals(200,reservaArticuloList.get(1).getCantidad());
        Assert.assertEquals(300,reservaArticuloList.get(2).getCantidad());

        Assert.assertTrue(reservaArticuloList.get(0).isEsCompleta());
        Assert.assertTrue(reservaArticuloList.get(1).isEsCompleta());
        Assert.assertTrue(reservaArticuloList.get(2).isEsCompleta());

        Assert.assertEquals(EstadoPedido.DESPACHABLE.name(), pedido.getEstado());
        Assert.assertNotNull(pedido.getFechaSolicitudOrden());
    }

    @Test
    public void aprobarPedidoFallaPorStock() {
        Articulo articulo1 = DatosUtils.crearArticulo(1);
        Articulo articulo2 = DatosUtils.crearArticulo(2);
        Articulo articulo3 = DatosUtils.crearArticulo(3);

        Proveedor proveedor = DatosUtils.crearProveedor(1);

        DatosUtils.crearOC(articulo1, 500, proveedor);
        DatosUtils.crearOC(articulo2, 500, proveedor);
        DatosUtils.crearOC(articulo3, 200, proveedor);

        Pedido pedido = new Pedido(
                DatosUtils.crearCliente(),
                EstadoPedido.RECIBIDO.name(),
                "Calle falsa 123",
                Arrays.asList(
                        DatosUtils.crearItem(articulo1, 100),
                        DatosUtils.crearItem(articulo2, 200),
                        DatosUtils.crearItem(articulo3, 300)
                )
        );

        pedido.save();
        pedido.aprobar();

        List<ReservaArticulo> reservaArticuloList = ReservaArticuloDAO.getByPedidoId(pedido.getId());
        List<OrdenDePedido> ordenDePedidoList = OrdenDePedidoDAO.obtenerOrdenesDePedido();

        Assert.assertEquals(3,reservaArticuloList.size());
        Assert.assertEquals(1, ordenDePedidoList.size());

        Assert.assertEquals(100,reservaArticuloList.get(0).getCantidad());
        Assert.assertEquals(200,reservaArticuloList.get(1).getCantidad());
        Assert.assertEquals(200,reservaArticuloList.get(2).getCantidad());
        Assert.assertTrue(reservaArticuloList.get(0).isEsCompleta());
        Assert.assertTrue(reservaArticuloList.get(1).isEsCompleta());
        Assert.assertFalse(reservaArticuloList.get(2).isEsCompleta());

        Assert.assertEquals(Integer.valueOf(1),ordenDePedidoList.get(0).getIdPedido());
        Assert.assertEquals(Integer.valueOf(100),ordenDePedidoList.get(0).getCantidad());
        Assert.assertEquals(Integer.valueOf(3),ordenDePedidoList.get(0).getArticulo().getCodigo());
        Assert.assertNull(ordenDePedidoList.get(0).getIdOrdenCompra());

        Assert.assertEquals(EstadoPedido.FALTA_STOCK.name(), pedido.getEstado());
        Assert.assertNotNull(pedido.getFechaSolicitudOrden());
    }

    @After
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
