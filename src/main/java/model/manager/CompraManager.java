package model.manager;

import dao.OrdenDeCompraDAO;
import dao.OrdenDePedidoDAO;
import model.Articulo;
import model.OrdenDeCompra;
import model.OrdenDePedido;
import model.Proveedor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompraManager {

    public void crearOrdenDeCompra(){
        List<OrdenDePedido> ordenDePedidos = OrdenDePedidoDAO.obtenerOrdenesDePedido();
        Map<Articulo, List<OrdenDePedido>> all = ordenDePedidos.stream().collect(Collectors.groupingBy(OrdenDePedido::getArticulo));
        all.forEach((articulo, ordenes) ->{
            int reposicion = articulo.getCantReposicion();
            if(!ordenes.isEmpty()){
                OrdenDeCompra oc = new OrdenDeCompra(articulo,articulo.getCantReposicion(), false);
                oc.save();
                //TODO: terminar logica
            }
        });

    }

    public void asignarProveedorAOrdenCompra(Integer idOC, Proveedor proveedor){
        OrdenDeCompra oc = OrdenDeCompraDAO.getById(idOC);
        oc.setProovedor(proveedor);
        oc.update();
    }
}
