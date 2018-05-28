package model.manager;

import dao.OrdenDeCompraDAO;
import dao.PedidoDAO;
import dao.ReservaArticuloDAO;
import dao.UbicacionDAO;
import model.*;
import model.enums.EstadoPedido;
import model.enums.TipoMovimiento;

import java.util.*;


public class PedidoManager {

    public PedidoManager(){
    }

    public void crearPedido(Cliente cliente, String direccionEntrega, List<ItemPedido> items){
        Pedido pedido = new Pedido(
                cliente,
                EstadoPedido.RECIBIDO.name(),
                direccionEntrega,
                items
        );
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

    public Map<ItemPedido, List<ItemAProcesar>> despacharPedido(Integer codigoPedido){
        Pedido pedido = PedidoDAO.getById(codigoPedido);
        pedido.setFechaDespacho(new Date());
        pedido.setEstado(EstadoPedido.DESPACHO.name());
        Map<ItemPedido, List<ItemAProcesar>> result = new HashMap<>();
        for(ItemPedido item : pedido.getItems()){
            result.put(item,this.llenarPedido(item));
        }
        pedido.update();
        return result;
    }

    public void rechazarPedido(Integer codigoPedido){
        Pedido pedido = PedidoDAO.getById(codigoPedido);
        pedido.setEstado(EstadoPedido.RECHAZADO.name());
        pedido.update();
    }

    public void completarPedido(Integer codigoPedido, Date fechaEntrega){
        Pedido pedido = PedidoDAO.getById(codigoPedido);
        pedido.setFechaEntrega(fechaEntrega);
        pedido.setEstado(EstadoPedido.COMPLETO.name());
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

    private Integer getCantidadReservadaDeArticulo(Articulo articulo) {
        List<ReservaArticulo> result = ReservaArticuloDAO.getByArticuloId(articulo.getCodigo());
        return result.stream().mapToInt(ReservaArticulo::getCantidad).sum();
    }

    private List<ItemAProcesar> llenarPedido(ItemPedido item) {
        Integer cantidadTotal = item.getCantidad();
        int i = 0;
        List<ItemAProcesar> itemsAProcesar = new ArrayList<>();
        List<Lote> lotesARevisar = item.getArticulo().getLotes();

        while(cantidadTotal > 0){
            Lote lote = lotesARevisar.get(i);
            if(cantidadTotal - lote.getStock() >= 0){
                ItemAProcesar itemAProcesar = new ItemAProcesar(lote.getStock(),lote.getProveedor());
                for(Ubicacion ubicacion : UbicacionDAO.getUbicacionesDeLote(lote.getId())){
                    vaciarUbicacion(ubicacion);
                    itemAProcesar.addUbicacion(ubicacion.getClave());
                }
                itemsAProcesar.add(itemAProcesar);
                cantidadTotal = cantidadTotal - lote.getStock();
                lote.delete();
            }else{
                List<Ubicacion> ub = UbicacionDAO.getUbicacionesDeLote(lote.getId());
                int j = 0;
                int cantidadAReducir = lote.getStock() - cantidadTotal;
                ItemAProcesar itemAProcesar = new ItemAProcesar(cantidadAReducir, lote.getProveedor());
                while(cantidadTotal > 0) {
                    Ubicacion ubicacion = ub.get(j);
                    if(cantidadTotal - ubicacion.getCantidad() >= 0){
                        cantidadTotal = cantidadTotal - ubicacion.getCantidad();
                        vaciarUbicacion(ubicacion);
                    }else{
                        ubicacion.setCantidad(ubicacion.getCantidad() - cantidadTotal);
                        ubicacion.update();
                        cantidadTotal = 0;
                    }
                    itemAProcesar.addUbicacion(ubicacion.getClave());
                    j++;
                }
                lote.setStock(cantidadAReducir);
                lote.update();
                itemsAProcesar.add(itemAProcesar);
                cantidadTotal = 0;
            }
            i++;
        }
        generarMovimientoDeVenta(item);
        return itemsAProcesar;
    }

    private void generarMovimientoDeVenta(ItemPedido item) {
        MovimientoBasico movimientoBasico = new MovimientoBasico(
                new Date(),
                item.getCantidad(),
                TipoMovimiento.VENTA,
                "Resuelto"
        );
        movimientoBasico.save(item.getArticulo());
    }

    private void vaciarUbicacion(Ubicacion ubicacion) {
        ubicacion.setOcupado(false);
        ubicacion.update();
    }
}
