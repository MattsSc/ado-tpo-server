import dao.ClienteDAO;
import dao.LoteDAO;
import dao.MovCCDAO;
import dao.MovimientoDAO;
import model.*;
import model.enums.EstadoPedido;
import model.enums.TipoMovimiento;
import model.manager.CompraManager;
import model.manager.DocumentosManager;
import model.manager.PedidoManager;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Cliente cliente = new Cliente(
                1234,
                "Matias",
                "Scandroglio",
                "La rioja",
                "2351565455",
                "nose",
                10000F,
                10000F
        );

        cliente.save();

        Articulo articulo1 = new Articulo(
                1234,
                "Coca-Cola 1.5L",
                "Botella",
                1,
                1,
                12F,
                200);

        articulo1.save();

        Proveedor proveedor1 = new Proveedor("mama", 123);
        Proveedor proveedor2 = new Proveedor("papa", 124);
        proveedor1.save();
        proveedor2.save();

        Lote lote1 = new Lote(
                new Date(),
                100,
                proveedor1
        );
        Lote lote2 = new Lote(
                new Date(),
                100,
                proveedor2
        );

        lote1.save(articulo1);
        lote2.save(articulo1);

        MovimientoBasico movimientoBasico = new MovimientoBasico(
                new Date(),
                200,
                TipoMovimiento.COMPRA,
                "resuelto"
        );

        movimientoBasico.save(articulo1);

        Ubicacion ubicacion1 = new Ubicacion(
                "A010101",
                true,
                LoteDAO.getById(1),
                50
        );
        Ubicacion ubicacion2 = new Ubicacion(
                "A010102",
                true,
                LoteDAO.getById(1),
                50
        );
        Ubicacion ubicacion3 = new Ubicacion(
                "A010103",
                true,
                LoteDAO.getById(2),
                50
        );
        Ubicacion ubicacion4 = new Ubicacion(
                "A010104",
                true,
                LoteDAO.getById(2),
                50
        );

        ubicacion1.save();
        ubicacion2.save();
        ubicacion3.save();
        ubicacion4.save();

        Pedido pedido = new Pedido(
                cliente,
                EstadoPedido.RECIBIDO.name(),
                "calle falsa 123",
                Arrays.asList(
                        new ItemPedido(250, articulo1) //CAMBIEN LA CANTIDAD A MAS DE 200 PARA VER SI GENERA ORDEN DE PEDIDO
                )
        );

        pedido.save();

        PedidoManager pedidoManager = new PedidoManager();
        DocumentosManager documentosManager = new DocumentosManager();
        CompraManager compraManager = new CompraManager();
        pedidoManager.aprobarPedido(pedido.getId());
       // documentosManager.crearFactura("A",pedido.getId(), pedidoManager.despacharPedido(pedido.getId()));
        compraManager.crearOrdenDeCompra(articulo1.getCodigo(),proveedor1.getId());


        //new Servidor();
    }
}
