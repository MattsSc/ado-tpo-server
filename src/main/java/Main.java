import dao.ClienteDAO;
import model.Cliente;

public class Main {

    public static void main(String[] args) {
        Cliente cliente = new Cliente(123456789,
                "pepe",
                "argento",
                "la concha 123",
                "20-35156545-5",
                "queVaAca",
                50000,
                25000);

        //ClienteDAO.save(cliente);
        ClienteDAO.getCliente(33545334);

    }
}
