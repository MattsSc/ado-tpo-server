package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.OrdenDeCompraEntity;
import model.OrdenDeCompra;
import utils.HibernateUtils;

import java.util.Optional;

public class OrdenDeCompraDAO {

    public static void save(OrdenDeCompra ordenDeCompra){
        HibernateUtils.saveTransaction(ordenDeCompraToEntity(ordenDeCompra));
    }

    public static OrdenDeCompra getUltimaOrdenDeCompra(Integer codigoArticulo){
        Optional<OrdenDeCompraEntity> result =HibernateUtils.getOneResult("from OrdenDeCompraEntity where articuloId = " + codigoArticulo + " ORDER BY id DESC\n" +
                "LIMIT 1");

        return result.map(OrdenDeCompraDAO::ordenDeCompraToNegocio).orElse(null);
    }

    private static OrdenDeCompraEntity ordenDeCompraToEntity(OrdenDeCompra ordenDeCompra) {
        return new OrdenDeCompraEntity(
                ConverterEntityUtils.articuloToEntity(ordenDeCompra.getArticulo()),
                ordenDeCompra.getCantidad(),
                ordenDeCompra.isResuelto(),
                null
        );
    }

    private static OrdenDeCompra ordenDeCompraToNegocio(OrdenDeCompraEntity ordenDeCompra) {
        return new OrdenDeCompra(
                ordenDeCompra.getId(),
                ConverterNegocioUtils.articuloToNegocio(ordenDeCompra.getArticulo()),
                ordenDeCompra.getCantidad(),
                ordenDeCompra.isResuelto(),
                null
        );
    }

}
