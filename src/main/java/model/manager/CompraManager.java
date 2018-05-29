package model.manager;

import dao.*;
import model.*;
import model.enums.TipoMovimiento;

import java.util.Date;
import java.util.List;

public class CompraManager {

    public OrdenDeCompra crearOrdenDeCompra(OrdenDeCompra oc){
        Articulo articulo = oc.getArticulo();

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

        return oc;
    }

    public void cerrarOrdenDeCompra(Integer ocId, Date fechaVencimiento){
        OrdenDeCompra oc = OrdenDeCompraDAO.getById(ocId);
        oc.resolver();

        generarMovimientoCompra(oc);

        crearLote(fechaVencimiento, oc);

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
                pedido.aprobarPedido();
            }
        });
    }

    private void crearLote(Date fechaVencimiento, OrdenDeCompra oc) {
        Lote loteNuevo = new Lote(
                fechaVencimiento,
                oc.getCantidad(),
                oc.getProovedor()
        );

        loteNuevo.save(oc.getArticulo());
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
