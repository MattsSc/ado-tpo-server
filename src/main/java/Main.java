import model.Articulo;
import model.Cliente;
import model.enums.TipoProducto;
import server.Servidor;

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


        articulo.save();
        articulo2.save();
        articulo3.save();
        cliente.save();

        System.out.println("SERVER UP!");
    }
}
