package dao.converters;

import entities.*;
import model.*;
import model.enums.TipoMovimiento;

public class ConverterNegocioUtils {

    public static Cliente clienteToNegocio(ClienteEntity clienteE) {
        return new Cliente(clienteE.getDni(),
                clienteE.getNombre(),
                clienteE.getApellido(),
                clienteE.getDomicilio(),
                clienteE.getCuit(),
                clienteE.getRazonSocial(),
                clienteE.getLimiteCredito(),
                clienteE.getMontoDisponible(),
                null);
    }

    public static Articulo articuloToNegocio(ArticuloEntity entity){
        return new Articulo(
                entity.getCodigo(),
                entity.getDescripcion(),
                entity.getPresentacion(),
                entity.getTamanio(),
                entity.getUnidad(),
                entity.getPrecio(),
                entity.getCantReposicion()
        );
    }

    public static Lote loteToNegocio(LoteEntity loteEntity){
        return new Lote(
                loteEntity.getId(),
                loteEntity.getFechaVencimiento(),
                loteEntity.getCantidad(),
                null
                );

    }

    public static Movimiento movBasicoToNegocio(MovimientoBasicoEntity mov){
        return new MovimientoBasico(
                mov.getId(),
                mov.getFecha(),
                mov.getCantidad(),
                TipoMovimiento.valueOf(mov.getTipo()),
                mov.getEstado()
        );
    }

    public static Movimiento movEliminacionToNegocio(MovimientoPorEliminacionEntity mov){
        return new MovimientoPorEliminacion(
                mov.getId(),
                mov.getFecha(),
                mov.getCantidad(),
                mov.getEncargado(),
                mov.getDestino(),
                mov.getAuotorizador()
        );
    }

    public static ItemPedido itemPedidoToNegocio(ItemPedidoEntity it){
        return new ItemPedido(
                it.getId(),
                it.getCantidad());
    }

    public static Pedido pedidoToNegocio(PedidoEntity entity){
        return new Pedido(
                entity.getId(),
                entity.getFechaSolicitudOrden(),
                entity.getFechaDespacho(),
                entity.getFechaEntrega(),
                entity.getEstado(),
                entity.getDireccionEntrega()
        );
    }

    public static Movimiento movToNegocio(MovimientoEntity movimiento){
        if(TipoMovimiento.valueOf(movimiento.getTipo()).equals(TipoMovimiento.ELIMINACION))
            return movEliminacionToNegocio((MovimientoPorEliminacionEntity) movimiento);
        else
            return movBasicoToNegocio((MovimientoBasicoEntity) movimiento);
    }
}
