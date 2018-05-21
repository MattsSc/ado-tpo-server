import dao.ArticuloDAO;
import dao.LoteDAO;
import dao.MovimientoDAO;
import dao.ProovedorDAO;
import model.Articulo;
import model.Lote;
import model.MovimientoPorInventario;
import model.Proovedor;
import model.enums.TipoMovimiento;
import server.Servidor;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Articulo articulo = new Articulo(
                111111,
                "Coca cola 1.5L",
                "Botella",
                1,
                20,
                12.30F);


        MovimientoPorInventario movimientoPorInventario = new MovimientoPorInventario(new Date(),10,TipoMovimiento.COMPRA,"la pepa","activo");

        ArticuloDAO.save(articulo);
        MovimientoDAO.save(movimientoPorInventario,articulo);

        //new Servidor();
    }
}
