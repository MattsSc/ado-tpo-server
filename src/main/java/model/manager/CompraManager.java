package model.manager;

import dao.ArticuloDAO;
import dao.OrdenDeCompraDAO;
import dao.OrdenDePedidoDAO;
import dao.ProovedorDAO;
import model.Articulo;
import model.OrdenDeCompra;
import model.OrdenDePedido;
import model.Proveedor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompraManager {

    public void crearOrdenDeCompra(Integer articuloId, Integer proveedorId){
        Articulo articulo = ArticuloDAO.getById(articuloId);
        Proveedor proveedor = ProovedorDAO.getById(proveedorId);
        OrdenDeCompra oc = new OrdenDeCompra(
                articulo,
                articulo.getCantReposicion(),
                proveedor
        );
        oc.save();

        List<OrdenDePedido> ordenes = OrdenDePedidoDAO.obtenerOrdenesDePedidoSinOc(articuloId);
        Integer cantidad = articulo.getCantReposicion();

        if(!ordenes.isEmpty()){
            int indice = 0;
            while(cantidad > 0 && indice < ordenes.size()){
                OrdenDePedido ordenDePedido = ordenes.get(indice);
                cantidad = cantidad - ordenDePedido.getCantidad();
                if(cantidad >= 0){
                    ordenDePedido.setIdOrdenCompra(oc.getId());
                    ordenDePedido.update();
                }
                indice++;
            }
        }
    }

    public void cerrarOrdenDeCompra(Integer ocId){
        OrdenDeCompra oc = OrdenDeCompraDAO.getById(ocId);
        oc.setResuelto(Boolean.TRUE);
        oc.update();

        //TODO: Eliminar ordenes de pedido afectado y cambiar estado de pedido si es que es necesario a DESPACHABLE. Se hace verificando que no existan otras ordenes de pedido para
        //TODO: el pedido que se resolvio esta OC.
    }

}
