package model.manager;

import dao.OrdenDeCompraDAO;
import dao.PedidoDAO;
import dao.ReservaArticuloDAO;
import dao.UbicacionDAO;
import model.*;
import model.enums.EstadoPedido;

import java.util.ArrayList;
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

    public void despacharPedido(Integer codigoPedido){
        Pedido pedido = PedidoDAO.getById(codigoPedido);
        for(ItemPedido item : pedido.getItems()){
            List<Ubicacion> ubicaciones = this.getLotesAUsar(item);

        }
    }

    private List<Ubicacion> getLotesAUsar(ItemPedido item) {
        Integer cantidadTotal = item.getCantidad();
        int i = 0;
        int j = 0;
        List<Ubicacion> ubicacionesAVaciar = new ArrayList<>();
        List<Lote> lotesARevisar = item.getArticulo().getLotes();

        while(cantidadTotal > 0){
            Lote lote = lotesARevisar.get(i);
            if(cantidadTotal - lote.getStock() >= 0){
                cantidadTotal = cantidadTotal - lote.getStock();
                ubicacionesAVaciar.addAll(UbicacionDAO.getUbicacionesDeLote(lote.getId()));
            }else{
                List<Ubicacion> ub = UbicacionDAO.getUbicacionesDeLote(lote.getId());
                while(cantidadTotal > 0) {
                    Ubicacion ubicacion = ub.get(j);
                    ubicacionesAVaciar.add(ubicacion);
                    cantidadTotal = cantidadTotal - ubicacion.getCantidad();
                    j++;
                }
                lote.setStock(lote.getStock() - cantidadTotal);
                cantidadTotal = 0;
            }
            i++;
            j=0;
        }


        return ubicacionesAVaciar;
    }

    public void rechazarPedido(Integer codigoPedido){
        Pedido pedido = PedidoDAO.getById(codigoPedido);
        pedido.setEstado(EstadoPedido.RECHAZADO.name());
        pedido.update();
    }

    private Integer getCantidadReservadaDeArticulo(Articulo articulo) {
        List<ReservaArticulo> result = ReservaArticuloDAO.getByArticuloId(articulo.getCodigo());
        return result.stream().mapToInt(ReservaArticulo::getCantidad).sum();
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
