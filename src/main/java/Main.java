import controlador.ControladorCliente;
import server.Servidor;

public class Main {

    public static void main(String[] args) {
        //new Servidor();
    	System.out.println(ControladorCliente.getInstance().obtenerCliente(1234).getNombre());
        System.out.println("SERVER UP!");
    }
}
