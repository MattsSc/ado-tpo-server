package dao;

import dao.converters.ConverterEntityUtils;
import entities.FacturaEntity;
import entities.ItemFacturaEntity;
import model.Factura;
import utils.HibernateUtils;

import java.util.stream.Collectors;

public class FacturaDAO {

    public static void save(Factura factura){
        FacturaEntity facturaEntity = facturaToEntity(factura);
        HibernateUtils.saveTransaction(facturaEntity);
        factura.setId(facturaEntity.getId());
    }

    private static FacturaEntity facturaToEntity(Factura factura) {
        return new FacturaEntity(
                factura.getFechaCreacion(),
                factura.getTipo(),
                ConverterEntityUtils.clienteToEntity(factura.getCliente()),
                factura.getItems().stream().map(item -> new ItemFacturaEntity(
                        ConverterEntityUtils.proveedorToEntity(item.getProveedor()),
                        ConverterEntityUtils.articuloToEntity(item.getArticulo()),
                        item.getCantidad()
                )).collect(Collectors.toList())
        );
    }
}
