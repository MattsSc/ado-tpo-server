import controlador.ControladorCliente;
import model.Cliente;
import server.Servidor;

public class Main {

    public static void main(String[] args) {
        //new Servidor();

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

        cliente.save();

        System.out.println("SERVER UP!");
    }
}
