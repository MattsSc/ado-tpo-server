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

        Proveedor proovedor = new Proveedor("proveedor1", 203333);
        proovedor.save();

        articulo.save();
        articulo2.save();
        articulo3.save();
        cliente.save();

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
