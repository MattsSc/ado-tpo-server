package model.manager;

import dao.*;
import model.*;
import model.enums.TipoMovimiento;

import java.util.Date;
import java.util.List;

public class CompraManager {

    public Integer crearOrdenDeCompra(OrdenDeCompra oc){
        Articulo articulo = oc.getArticulo();
        oc.setCantidad(articulo.getCantReposicion());
        oc.save();

        List<OrdenDePedido> ordenes = OrdenDePedidoDAO.obtenerOrdenesDePedidoSinOc(articulo.getCodigo());
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

        return oc.getId();
    }

    public OrdenDeCompra cerrarOrdenDeCompra(Integer ocId, float precioTotal){
        OrdenDeCompra oc = OrdenDeCompraDAO.getById(ocId);
        oc.resolver(precioTotal);
        generarMovimientoCompra(oc);

        List<OrdenDePedido> ordenes = OrdenDePedidoDAO.obtenerOrdenesDePedidoParaOC(oc.getId());
        ordenes.stream().forEach(ordenDePedido -> {
            ReservaArticulo reservaArticulo = new ReservaArticulo(
                    ordenDePedido.getCantidad(),
                    ordenDePedido.getIdPedido(),
                    Boolean.TRUE
            );
            reservaArticulo.save(ordenDePedido.getArticulo().getCodigo());
            ordenDePedido.delete();

            if(OrdenDePedidoDAO.obtenerOrdenesDePedidoParaPedido(ordenDePedido.getIdPedido()).isEmpty()){
                Pedido pedido = PedidoDAO.getById(ordenDePedido.getIdPedido());
                pedido.aprobarCambiandoEstado();
            }
        });

        return oc;
    }

    private void generarMovimientoCompra(OrdenDeCompra oc) {
        MovimientoBasico movimientoCompra = new MovimientoBasico(
                new Date(),
                oc.getCantidad(),
                TipoMovimiento.COMPRA,
                "resuelto"
        );

        movimientoCompra.save(oc.getArticulo());
    }
}
