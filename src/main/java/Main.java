import dao.ArticuloDAO;
import dao.LoteDAO;
import dao.ProovedorDAO;
import model.Articulo;
import model.Lote;
import model.Proovedor;
import server.Servidor;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Proovedor proovedor = new Proovedor("Proveedor A", 2055);
        Articulo articulo = new Articulo(
                111111,
                "Coca cola 1.5L",
                "Botella",
                1,
                20,
                12.30F);

        proovedor.setId(1);
        Lote lote = new Lote(new Date(),20, proovedor);

        ProovedorDAO.save(proovedor);
        ArticuloDAO.save(articulo);
        LoteDAO.save(lote, articulo);
        //new Servidor();
    }
}
