package dao.converters;

import entities.*;
import model.*;
import model.enums.TipoMovimiento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterNegocioUtils {

    public static Cliente clienteToNegocio(ClienteEntity clienteE) {
        if(!clienteE.getMovimientosCC().isEmpty()){
            return new Cliente(clienteE.getDni(),
                    clienteE.getNombre(),
                    clienteE.getApellido(),
                    clienteE.getDomicilio(),
                    clienteE.getCuit(),
                    clienteE.getRazonSocial(),
                    clienteE.getLimiteCredito(),
                    clienteE.getMontoDisponible(),
                    clienteE.getMovimientosCC().stream().map(movCC -> new MovimientoCC(movCC.getFecha(),movCC.getImporte(),movCC.getTipo())).collect(Collectors.toList()));
        }
        return new Cliente(clienteE.getDni(),
                clienteE.getNombre(),
                clienteE.getApellido(),
                clienteE.getDomicilio(),
                clienteE.getCuit(),
                clienteE.getRazonSocial(),
                clienteE.getLimiteCredito(),
                clienteE.getMontoDisponible());
    }

    public static Articulo articuloToNegocio(ArticuloEntity entity){
        return new Articulo(
                entity.getCodigo(),
                entity.getDescripcion(),
                entity.getPresentacion(),
                entity.getTamanio(),
                entity.getUnidad(),
                entity.getPrecio(),
                entity.getLotes().isEmpty() ? new ArrayList<>(): lotesToNegocio(entity.getLotes()),
                entity.getMovimientos().isEmpty() ? new ArrayList<>(): movBasicoToNegocio(entity.getMovimientos())
        );
    }

    public static List<Lote> lotesToNegocio(List<LoteEntity> entities){
        return entities.stream().map(lote ->
                new Lote(
                        lote.getId(),
                        lote.getFechaVencimiento(),
                        lote.getCantidad(),
                        new Proveedor(
                                lote.getProovedor().getId(),
                                lote.getProovedor().getNombre(),
                                lote.getProovedor().getCuit())
                )).collect(Collectors.toList());

    }

    public static List<Movimiento> movBasicoToNegocio(List<MovimientoBasicoEntity> entities){
        return entities.stream().map(mov -> new MovimientoBasico(
                mov.getId(),
                mov.getFecha(),
                mov.getCantidad(),
                TipoMovimiento.valueOf(mov.getTipo()),
                mov.getEstado()
        )).collect(Collectors.toList());
    }

    public static Pedido pedidoToNegocio(PedidoEntity entity){
        return new Pedido(
                entity.getId(),
                ConverterNegocioUtils.clienteToNegocio(entity.getCliente()),
                entity.getFechaSolicitudOrden(),
                entity.getFechaDespacho(),
                entity.getFechaEntrega(),
                entity.getEstado(),
                entity.getDireccionEntrega(),
                entity.getItems().stream().map(it -> new ItemPedido(
                        it.getId(),
                        it.getCantidad(),
                        articuloToNegocio(it.getArticulo()))
                ).collect(Collectors.toList())
        );
    }


}
