import controlador.ControladorCompra;
import dao.OrdenDeCompraDAO;
import dao.OrdenDePedidoDAO;
import dtos.OrdenDeCompraDTO;
import model.*;
import model.enums.EstadoPedido;
import org.junit.Assert;
import org.junit.Test;
import utils.DatosUtils;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;


public class OrdenCompraTest extends GenericTest{

    @Test
    public void crearOC(){
        Articulo articulo3 = DatosUtils.crearArticulo(3,10);

        Proveedor proveedor = DatosUtils.crearProveedor(1);

        DatosUtils.crearOC(articulo3, 200, proveedor);

        Pedido pedido = new Pedido(
                DatosUtils.crearCliente(),
                EstadoPedido.RECIBIDO.name(),
                "Calle falsa 123",
                Arrays.asList(
                        DatosUtils.crearItem(articulo3, 300)
                )
        );

        pedido.save();
        pedido.aprobar();
        List<Articulo> articulo = OrdenDePedidoDAO.getArticulosFaltantes();
        List<Proveedor> proveedors = OrdenDeCompraDAO.getUltimos3Proveedores(articulo.get(0).getCodigo());

        OrdenDeCompraDTO ordenDeCompraDTO = new OrdenDeCompraDTO(
                articulo.get(0).toDto(),
                proveedors.get(0).toDto()
        );

        try {
            ControladorCompra.getInstance().crearOrdenDeCompra(ordenDeCompraDTO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1,proveedors.size());
        Assert.assertEquals(1, articulo.size());
    }
}
