import dao.ClienteDAO;
import dao.MovCCDAO;
import model.*;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

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

        MovimientoCC mov = new MovimientoCC(new Date(),100F,"VENTA");

        ClienteDAO.save(cliente);
        MovCCDAO.save(cliente,mov);

        Cliente c = ClienteDAO.getById(110201);

        System.out.println("AYUDA " + c.getMovimientosCC().size());

        //new Servidor();
    }
}
