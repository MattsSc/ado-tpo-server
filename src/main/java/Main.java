import dao.*;
import delegates.ClienteDelegate;
import model.*;
import model.enums.TipoMovimiento;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Articulo articulo = new Articulo(
                111111,
                "Coca cola 1.5L",
                "Botella",
                1,
                20,
                12.30F);

        Articulo articulo2 = new Articulo(
                222222,
                "Pepsi 1.5L",
                "Botella",
                1,
                20,
                12.30F);

        Proveedor proveedor1 = new Proveedor("mama",1111);
        Proveedor proveedor2 = new Proveedor("pepe", 2222);

        ProovedorDAO.save(proveedor1);
        List<Proveedor> p = ProovedorDAO.getAll();

        Lote lote1 = new Lote(
                new Date(),
                40,
                p.get(0)
        );

        Lote lote2 = new Lote(
                new Date(),
                40,
                proveedor2
        );

        Lote lote3 = new Lote(
                new Date(),
                40,
                p.get(0)
        );

        Lote lote4 = new Lote(
                new Date(),
                40,
                proveedor2
        );

        Cliente cliente = new Cliente(
                110201,
                "matias",
                "scandroglio",
                "la rioja 241",
                "20-35156545-5",
                "pobre",
                10000F,
                10000F
        );

        ArticuloDAO.save(articulo);
        LoteDAO.save(lote1,articulo);
        LoteDAO.save(lote2, articulo);

        ArticuloDAO.save(articulo2);
        LoteDAO.save(lote3,articulo2);
        LoteDAO.save(lote4, articulo2);

        ClienteDAO.save(cliente);

        Articulo coca = ArticuloDAO.getById(111111);
        Articulo pesi = ArticuloDAO.getById(222222);

        Pedido pe = new Pedido(
                cliente,
                new Date(),
                null,
                null,
                "EN_ESPERA",
                "TUCASA",
                Arrays.asList(
                        new ItemPedido(10,coca),
                        new ItemPedido(50,pesi)
                )
        );
        PedidoDAO.save(pe);

        //new Servidor();
    }
}
