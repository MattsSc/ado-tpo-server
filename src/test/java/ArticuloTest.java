import model.Articulo;
import model.enums.TipoProducto;
import org.junit.Assert;
import org.junit.Test;

public class ArticuloTest extends GenericTest{

    @Test
    public void articuloNuevoStockCero(){
        Articulo nuevo = new Articulo(
                1111,
                "Campera NewFlo",
                TipoProducto.BOLSA.name(),
                1,
                1,
                20F,
                10000
        );
        nuevo.save();

        Assert.assertEquals(0,nuevo.stockRestante());
    }
}
