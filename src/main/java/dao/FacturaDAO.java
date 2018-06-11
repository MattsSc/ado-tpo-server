package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.FacturaEntity;
import entities.ItemFacturaEntity;
import model.Factura;
import model.ItemFactura;
import model.Pedido;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FacturaDAO {

    public static void save(Factura factura, Pedido pedido){
        FacturaEntity facturaEntity = facturaToEntity(factura, pedido);
        HibernateUtils.saveTransaction(facturaEntity);
        factura.setId(facturaEntity.getId());
    }

    public static Factura getByPedidoId(Integer pedidoId){
        FacturaEntity result = (FacturaEntity) HibernateUtils.getOneResult("from FacturaEntity where pedidoId = " + pedidoId);
        return entityToFactura(result);
    }

    private static FacturaEntity facturaToEntity(Factura factura, Pedido pedido) {
        return new FacturaEntity(
                factura.getFechaCreacion(),
                factura.getTipo(),
                ConverterEntityUtils.clienteToEntity(factura.getCliente()),
                ConverterEntityUtils.pedidoToEntity(pedido),
                factura.getItems().stream().map(item -> new ItemFacturaEntity(
                        ConverterEntityUtils.proveedorToEntity(item.getProveedor()),
                        ConverterEntityUtils.articuloToEntity(item.getArticulo()),
                        item.getCantidad()
                )).collect(Collectors.toList())
        );
    }

    private static Factura entityToFactura(FacturaEntity entity){

        List<ItemFactura> items = entity.getItems().stream().map(item -> new ItemFactura(
                    item.getId(),
                    ConverterNegocioUtils.proveedorToNegocio(item.getProveedor()),
                    ConverterNegocioUtils.articuloToNegocio(item.getArticulo()),
                    item.getCantidad()
            )).collect(Collectors.toList());

        Factura factura = new Factura(
                entity.getId(),
                entity.getFechaCreacion(),
                entity.getTipo(),
                ConverterNegocioUtils.clienteToNegocio(entity.getCliente()),
                items
        );

        return factura;
    }
}
