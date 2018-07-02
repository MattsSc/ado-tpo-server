import controlador.ControladorCompra;
import model.*;
import model.enums.TipoProducto;
import server.Servidor;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Servidor();

        Cliente cliente = new Cliente(
                12345,
                "Matias",
                "Scandroglio",
                "La rioja 241",
                "20-111111111-5",
                "Razon Social",
                200000F,
                200000F
        );

        Articulo coca = new Articulo(
                1111,
                "Coca-cola 1.5L",
                TipoProducto.BOTELLA.name(),
                1,
                1,
                22.5f,
                5000
        );

        Articulo pepsi = new Articulo(
                2222,
                "Pepsi 1.5L",
                TipoProducto.BOTELLA.name(),
                1,
                1,
                21.5f,
                4500
        );

        Articulo cheetos = new Articulo(
                3333,
                "Cheetos",
                TipoProducto.BOLSA.name(),
                1,
                1,
                15f,
                3000
        );

        Articulo noStock1 = new Articulo(
                4444,
                "NoStock",
                TipoProducto.BOLSA.name(),
                1,
                1,
                15f,
                3000
        );

        Articulo noStock2 = new Articulo(
                5555,
                "NoStock2",
                TipoProducto.BOLSA.name(),
                1,
                1,
                15f,
                3000
        );

        Articulo stock800 = new Articulo(
                6666,
                "stock800",
                TipoProducto.BOLSA.name(),
                1,
                1,
                15f,
                800
        );



        coca.save();
        pepsi.save();
        cheetos.save();
        noStock1.save();
        noStock2.save();
        stock800.save();

        cliente.save();

        Proveedor proovedor = new Proveedor("Potigian S.A", 21134504);
        proovedor.save();

        Proveedor proovedor2 = new Proveedor("Negro Monte S.R.L.", 21344033);
        proovedor2.save();

        Proveedor proovedor3 = new Proveedor("Quilmes S.R.L", 2333455);
        proovedor3.save();


        //Generar Ubicaciones
        //Las primeras de cada zona para prueba

        List<String> letters = Arrays.asList("A","B","C","D","E","F");

        for(String let : letters){
            for(int i = 0; i <10 ; i++){
                Ubicacion ubicacion = new Ubicacion(let + "01010" + i);
                ubicacion.save();
            }
            for(int i = 10; i < 22 ; i++){
                Ubicacion ubicacion = new Ubicacion(let + "0101" + i);
                ubicacion.save();
            }
        }

        OrdenDeCompra ordenCoca1 = new OrdenDeCompra(coca,proovedor);
        ordenCoca1.save();
        OrdenDeCompra ordenPepsi1 = new OrdenDeCompra(pepsi,proovedor2);
        ordenPepsi1.save();
        OrdenDeCompra ordenCheetos1 = new OrdenDeCompra(cheetos,proovedor);
        ordenCheetos1.save();
        OrdenDeCompra ordenCoca2 = new OrdenDeCompra(coca,proovedor2);
        ordenCoca2.save();
        OrdenDeCompra ordenSotck800 = new OrdenDeCompra(stock800,proovedor3);
        ordenSotck800.save();


        try {
            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha1 = sourceFormat.parse("02/09/2018");
            Date fecha2 = sourceFormat.parse("02/08/2018");
            ControladorCompra.getInstance().cerrarOrdenDeCompra(ordenCoca1.getId(), 40000f,fecha2);
            ControladorCompra.getInstance().cerrarOrdenDeCompra(ordenCoca2.getId(), 35000f, fecha1);
            ControladorCompra.getInstance().cerrarOrdenDeCompra(ordenPepsi1.getId(), 37000f, fecha1);
            ControladorCompra.getInstance().cerrarOrdenDeCompra(ordenCheetos1.getId(), 25000f, fecha1);
            ControladorCompra.getInstance().cerrarOrdenDeCompra(ordenSotck800.getId(), 10000f, fecha2);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        System.out.println("SERVER UP!");
    }
}
