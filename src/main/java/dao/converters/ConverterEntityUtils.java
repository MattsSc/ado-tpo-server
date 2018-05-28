package dao.converters;

import entities.*;
import model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ConverterEntityUtils {

    public static ClienteEntity clienteToEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDomicilio(),
                cliente.getCuit(),
                cliente.getRazonSocial(),
                cliente.getLimiteCredito(),
                cliente.getMontoDisponible()
        );
    }

    public static LoteEntity loteToEntity(Lote lote){
        ProveedorEntity proveedorEntity = new ProveedorEntity(lote.getProveedor().getNombre(), lote.getProveedor().getCuit());
        proveedorEntity.setId(lote.getProveedor().getId());
        LoteEntity loteEntity = new LoteEntity(
                lote.getFechaVencimiento(),
                lote.getStock(),
                proveedorEntity,
                null
        );

        if(lote.getId() != null)
            loteEntity.setId(lote.getId());

        return loteEntity;
    }

    public static LoteEntity loteToEntity(Lote lote, Articulo articulo){
        ProveedorEntity proveedorEntity = new ProveedorEntity(lote.getProveedor().getNombre(), lote.getProveedor().getCuit());
        proveedorEntity.setId(lote.getProveedor().getId());
        return new LoteEntity(
                lote.getFechaVencimiento(),
                lote.getStock(),
                proveedorEntity,
                articuloToEntity(articulo)
        );
    }

    public static ArticuloEntity articuloToEntity(Articulo articulo){
        return new ArticuloEntity(
                articulo.getCodigo(),
                articulo.getDescripcion(),
                articulo.getPresentacion(),
                articulo.getTamanio(),
                articulo.getUnidad(),
                articulo.getCantReposicion(),
                articulo.getPrecio());
    }

    public static PedidoEntity pedidoToEntity(Pedido pedido){
        List<ItemPedidoEntity> itemPedidoEntityList = pedido.getItems().stream().map(item -> {
            ArticuloEntity articuloEntity = articuloToEntity(item.getArticulo());
            return new ItemPedidoEntity(
                    item.getCantidad(),
                    articuloEntity);
        }).collect(Collectors.toList());

        PedidoEntity pedidoEntity =  new PedidoEntity(
                clienteToEntity(pedido.getCliente()),
                pedido.getFechaSolicitudOrden(),
                pedido.getFechaDespacho(),
                pedido.getFechaEntrega(),
                pedido.getEstado(),
                pedido.getDireccionEntrega(),
                itemPedidoEntityList
        );

        if(pedido.getId() != null)
            pedidoEntity.setId(pedido.getId());

        return pedidoEntity;
    }

    public static ProveedorEntity proveedorToEntity(Proveedor proveedor){
        ProveedorEntity proveedorEntity = new ProveedorEntity(
                proveedor.getNombre(),
                proveedor.getCuit()
        );

        if(proveedor.getId() != null)
            proveedorEntity.setId(proveedor.getId());

        return proveedorEntity;
    }

}
