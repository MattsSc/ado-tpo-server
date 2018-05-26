package model.manager;

import dao.OrdenDeCompraDAO;
import dao.PedidoDAO;
import dao.ReservaArticuloDAO;
import model.*;
import model.enums.EstadoPedido;

import java.util.List;


public class PedidoManager {

    public PedidoManager(){
    }

    public void agregarPedido(Pedido pedido){
        pedido.save();
    }

    public void aprobarPedido(Integer codigoPedido){
        Pedido pedido = PedidoDAO.getById(codigoPedido);
        Boolean estaCompleto = Boolean.TRUE;

        for(ItemPedido item: pedido.getItems()){
            Articulo articulo = item.getArticulo();

            Integer totalStock = articulo.stockRestante() - getCantidadReservadaDeArticulo(articulo);
            OrdenDeCompra ultimaOC = OrdenDeCompraDAO.getUltimaOrdenDeCompra(articulo.getCodigo());

            if(ultimaOC == null || ultimaOC.isResuelto()){
                if(totalStock >= item.getCantidad()){
                    generarReservaDeStock(pedido, item.getCantidad(), articulo, true);
                }else{
                    generarReservaDeStock(pedido, totalStock, articulo, false);
                    generarOrdenDePedido(pedido, item.getArticulo(), (item.getCantidad() - totalStock));
                    estaCompleto = Boolean.FALSE;
                }
            }else{
                generarOrdenDePedido(pedido, item.getArticulo(), item.getCantidad());
                estaCompleto = Boolean.FALSE;
            }
        }

        if(estaCompleto)
            pedido.setEstado(EstadoPedido.DESPACHABLE.name());
        else
            pedido.setEstado(EstadoPedido.FALTA_STOCK.name());

        pedido.update();
    }

    private Integer getCantidadReservadaDeArticulo(Articulo articulo) {
        List<ReservaArticulo> result = ReservaArticuloDAO.getByArticuloId(articulo.getCodigo());
        return result.stream().mapToInt(ReservaArticulo::getCantidad).sum();
    }

    public void rechazarPedido(Integer codigoPedido){
        Pedido pedido = PedidoDAO.getById(codigoPedido);
        pedido.setEstado(EstadoPedido.RECHAZADO.name());
        pedido.update();
    }


    //Private methods
    private void generarReservaDeStock(Pedido pedido, Integer cantidad, Articulo articulo, boolean esCompleta) {
        ReservaArticulo reservaArticulo = new ReservaArticulo(cantidad, pedido.getId(), esCompleta);
        reservaArticulo.save(articulo.getCodigo());
    }

    private void generarOrdenDePedido(Pedido pedido, Articulo articulo, Integer cantidad) {
        OrdenDePedido ordenDePedido = new OrdenDePedido(articulo,cantidad,pedido.getId());
        ordenDePedido.save();
    }
}
