import com.sun.security.ntlm.Client;
import dao.*;
import model.*;
import model.enums.EstadoPedido;
import model.enums.TipoMovimiento;
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
            "ItemPedido",
            "CuentaCorriente",
            "Factura",
            "ItemFactura",
            "Remito",
            "ItemRemito"
    );

    @Before
    public void before() {
        // setup the session factory
    }

    @Test
    public void aprobarPedidoOk() {
        Articulo articulo1 = DatosUtils.crearArticulo(1,10);
        Articulo articulo2 = DatosUtils.crearArticulo(2,10);
        Articulo articulo3 = DatosUtils.crearArticulo(3,10);

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
        Articulo articulo1 = DatosUtils.crearArticulo(1,10);
        Articulo articulo2 = DatosUtils.crearArticulo(2,10);
        Articulo articulo3 = DatosUtils.crearArticulo(3,10);

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

    @Test
    public void pedidoFaltaStockPorPedidoAnterior(){
        Articulo articulo1 = DatosUtils.crearArticulo(1,10);
        Proveedor proveedor = DatosUtils.crearProveedor(1);
        DatosUtils.crearOC(articulo1, 500, proveedor);
        Cliente cliente = DatosUtils.crearCliente();

        Pedido pedido1 = new Pedido(
                cliente,
                EstadoPedido.RECIBIDO.name(),
                "Calle falsa 123",
                Arrays.asList(
                        DatosUtils.crearItem(articulo1, 600)
                )
        );

        Pedido pedido2 = new Pedido(
                cliente,
                EstadoPedido.RECIBIDO.name(),
                "Calle falsa 123",
                Arrays.asList(
                        DatosUtils.crearItem(articulo1, 200)
                )
        );

        pedido1.save();
        pedido2.save();
        pedido1.aprobar();
        pedido2.aprobar();

        List<ReservaArticulo> reservaArticulo1List = ReservaArticuloDAO.getByPedidoId(pedido1.getId());
        List<ReservaArticulo> reservaArticulo2List = ReservaArticuloDAO.getByPedidoId(pedido2.getId());
        List<OrdenDePedido> ordenDePedidoList = OrdenDePedidoDAO.obtenerOrdenesDePedido();


        Assert.assertEquals(500,reservaArticulo1List.get(0).getCantidad());
        Assert.assertFalse(reservaArticulo1List.get(0).isEsCompleta());
        Assert.assertTrue(reservaArticulo2List.isEmpty());

        Assert.assertEquals(2,ordenDePedidoList.size());
        Assert.assertEquals(Integer.valueOf(1), ordenDePedidoList.get(0).getIdPedido());
        Assert.assertEquals(Integer.valueOf(100), ordenDePedidoList.get(0).getCantidad());
        Assert.assertEquals(Integer.valueOf(2), ordenDePedidoList.get(1).getIdPedido());
        Assert.assertEquals(Integer.valueOf(200), ordenDePedidoList.get(1).getCantidad());

        Assert.assertEquals(EstadoPedido.FALTA_STOCK.name(), pedido1.getEstado());
        Assert.assertEquals(EstadoPedido.FALTA_STOCK.name(), pedido2.getEstado());

    }

    @Test
    public void despacharPedido(){
        Articulo articulo1 = DatosUtils.crearArticulo(1,10);
        Articulo articulo2 = DatosUtils.crearArticulo(2,10);

        Proveedor proveedor = DatosUtils.crearProveedor(1);

        DatosUtils.crearUbicacionLlena(DatosUtils.crearLote(articulo1, 100 , proveedor));
        Lote lote2 = DatosUtils.crearLote(articulo2, 300 , proveedor);
        DatosUtils.crearUbicacionLlena(lote2);

        DatosUtils.crearOC(articulo1, 500, proveedor);
        DatosUtils.crearOC(articulo2, 500, proveedor);

        Cliente cliente = DatosUtils.crearCliente();

        Pedido pedido = new Pedido(
                cliente,
                EstadoPedido.RECIBIDO.name(),
                "Calle falsa 123",
                Arrays.asList(
                        DatosUtils.crearItem(articulo1, 100),
                        DatosUtils.crearItem(articulo2, 200)
                )
        );

        pedido.save();
        pedido.aprobar();
        pedido.despachar("A");

        //VALIDACION UBICACIONES
        Assert.assertEquals(1,UbicacionDAO.getUbicacionesVacias().size());
        List<Ubicacion> ubicacionsLote2 = UbicacionDAO.getUbicacionesDeLote(lote2.getId());
        Assert.assertEquals(1,ubicacionsLote2.size());
        Assert.assertEquals(Integer.valueOf(100),ubicacionsLote2.get(0).getCantidad());

        //VALIDACION MOVIMIENTOS ARTICULO
        Assert.assertEquals(2,articulo1.getMovimientos().size());
        Assert.assertEquals(2,articulo2.getMovimientos().size());
        Assert.assertEquals(TipoMovimiento.VENTA,articulo1.getMovimientos().get(1).getTipo());
        Assert.assertEquals(100,articulo1.getMovimientos().get(1).getCantidad());
        Assert.assertEquals(TipoMovimiento.VENTA,articulo2.getMovimientos().get(1).getTipo());
        Assert.assertEquals(200,articulo2.getMovimientos().get(1).getCantidad());

        //VALIDACION RESERVAS
        List<ReservaArticulo> reservaArticuloList = ReservaArticuloDAO.getByPedidoId(pedido.getId());
        Assert.assertTrue(reservaArticuloList.isEmpty());

        //VALIDACION CLIENTE
        Float totalVenta = articulo1.getPrecio() * 100 + articulo2.getPrecio() * 200;
        Assert.assertEquals(cliente.getLimiteCredito() - totalVenta  , cliente.getMontoDisponible(),0.01F);
        Assert.assertEquals(1,cliente.getMovimientosCC().size());
        Assert.assertEquals(totalVenta,cliente.getMovimientosCC().get(0).getImporte(),0.01F);
        Assert.assertEquals("COMPRA",cliente.getMovimientosCC().get(0).getTipo());

        //VALIDACION FACTURA
        Factura factura = FacturaDAO.getByPedidoId(pedido.getId());
        Assert.assertNotNull(factura);
        Assert.assertEquals(2,factura.getItems().size());

        //VALIDACION PEDIDO
        Assert.assertEquals(EstadoPedido.DESPACHO.name(), pedido.getEstado());
        Assert.assertNotNull(pedido.getFechaSolicitudOrden());
        Assert.assertNotNull(pedido.getFechaDespacho());
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
