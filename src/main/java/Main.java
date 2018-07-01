import controlador.ControladorCompra;
import model.*;
import model.enums.EstadoPedido;
import model.enums.TipoProducto;
import server.Servidor;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Servidor();

        Cliente cliente = new Cliente(
                12345,
                "Cliente",
                "Falso",
                "La rioja 241",
                "20-111111111-5",
                "Razon Social",
                200000F,
                200000F
        );

        Cliente cliente2 = new Cliente(
                54321,
                "Esteban",
                "Ramirez",
                "La rioja 878",
                "23-346567093-2",
                "Razon Social SA",
                150000F,
                250000F
        );

        Articulo articulo = new Articulo(
                1111,
                "Coca-cola 1.5L",
                TipoProducto.BOTELLA.name(),
                1,
                1,
                10F,
                1500
        );


        Articulo articulo2 = new Articulo(
                2222,
                "Pepsi 1.5L",
                TipoProducto.BOTELLA.name(),
                1,
                1,
                10F,
                1500
        );


        Articulo articulo3 = new Articulo(
                3333,
                "Cheetos",
                TipoProducto.BOLSA.name(),
                1,
                1,
                10F,
                1500
        );

        Articulo articulo4 = new Articulo(
                4444,
                "Pringles",
                TipoProducto.BOLSA.name(),
                2,
                1,
                10F,
                1500
        );

        Articulo articulo5 = new Articulo(
                5555,
                "Manaos",
                TipoProducto.BOLSA.name(),
                1,
                1,
                20F,
                1200
        );

        Proveedor proovedor = new Proveedor("proveedor1", 27335455);
        Proveedor proovedor2 = new Proveedor("proveedor2", 2756754);
        Proveedor proovedor3 = new Proveedor("proveedor3", 27445683);
        proovedor.save();
        proovedor2.save();
        proovedor3.save();

        articulo.save();
        articulo2.save();
        articulo3.save();
        articulo4.save();
        articulo5.save();
        cliente.save();
        cliente2.save();

        List<String> letters = Arrays.asList("A","B","C","D","E","F");

        for(String let : letters){
            for(int i = 0; i <24 ; i++){
                Ubicacion ubicacion = new Ubicacion(let + "0101" + i);
                ubicacion.save();
            }
        }

        OrdenDeCompra ordenDeCompra1 = new OrdenDeCompra(articulo,proovedor);
        ordenDeCompra1.save();
        OrdenDeCompra ordenDeCompra2 = new OrdenDeCompra(articulo2,proovedor);
        ordenDeCompra2.save();
        OrdenDeCompra ordenDeCompra3 = new OrdenDeCompra(articulo3,proovedor);
        ordenDeCompra3.save();
        OrdenDeCompra ordenDeCompra4 = new OrdenDeCompra(articulo,proovedor);
        ordenDeCompra4.save();

        try {
            ControladorCompra.getInstance().cerrarOrdenDeCompra(ordenDeCompra1.getId(), 40000f, new Date());
            ControladorCompra.getInstance().cerrarOrdenDeCompra(ordenDeCompra2.getId(), 20000f, new Date());
            /*la orden de compra 3 y 4 nos las cerramos para que quede*/
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Pedido pedido = new Pedido(
                cliente,
                EstadoPedido.RECIBIDO.name(),
                "calle falsa 123",
                Arrays.asList(new ItemPedido(300, articulo), new ItemPedido(300, articulo2))
        );
        pedido.save();
        pedido.aprobar("no hay porque");

        System.out.println("SERVER UP!");
    }
}
