import dao.ClienteDAO;
import dao.MovCCDAO;
import dao.MovimientoDAO;
import model.*;
import model.enums.EstadoPedido;
import model.enums.TipoMovimiento;
import model.manager.PedidoManager;

import java.util.Arrays;
import java.util.Date;

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

        Lote lote1 = new Lote(
                new Date(),
                100,
                new Proveedor("mama", 123)
        );
        Lote lote2 = new Lote(
                new Date(),
                100,
                new Proveedor("papa", 12)
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

        Pedido pedido = new Pedido(
                cliente,
                EstadoPedido.RECIBIDO.name(),
                "calle falsa 123",
                Arrays.asList(
                        new ItemPedido(225, articulo1) //CAMBIEN LA CANTIDAD A MAS DE 200 PARA VER SI GENERA ORDEN DE PEDIDO
                )
        );

        pedido.save();

        PedidoManager pedidoManager = new PedidoManager();
        pedidoManager.aprobarPedido(pedido.getId());
        //new Servidor();
    }
}
