package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.OrdenDeCompraEntity;
import model.OrdenDeCompra;
import utils.HibernateUtils;

import java.util.Optional;

public class OrdenDeCompraDAO {

    public static void save(OrdenDeCompra ordenDeCompra){
        OrdenDeCompraEntity oCEntity = ordenDeCompraToEntity(ordenDeCompra);
        HibernateUtils.saveTransaction(oCEntity);
        ordenDeCompra.setId(oCEntity.getId());
    }

    public static OrdenDeCompra getById(Integer id){
        return ordenDeCompraToNegocio(HibernateUtils.getById(OrdenDeCompraEntity.class, id));
    }

    public static void update(OrdenDeCompra or){
        HibernateUtils.updateTransaction(ordenDeCompraToEntity(or));
    }

    public static OrdenDeCompra getUltimaOrdenDeCompra(Integer codigoArticulo){
        Optional<OrdenDeCompraEntity> result =HibernateUtils.getOneResult("from OrdenDeCompraEntity where articuloId = " + codigoArticulo + " ORDER BY id DESC");

        return result.map(OrdenDeCompraDAO::ordenDeCompraToNegocio).orElse(null);
    }

    private static OrdenDeCompraEntity ordenDeCompraToEntity(OrdenDeCompra ordenDeCompra) {
        return new OrdenDeCompraEntity(
                ConverterEntityUtils.articuloToEntity(ordenDeCompra.getArticulo()),
                ordenDeCompra.getCantidad(),
                ordenDeCompra.isResuelto(),
                ordenDeCompra.getProovedor() != null ? ConverterEntityUtils.proveedorToEntity(ordenDeCompra.getProovedor()) : null
        );
    }

    private static OrdenDeCompra ordenDeCompraToNegocio(OrdenDeCompraEntity ordenDeCompra) {
        return new OrdenDeCompra(
                ordenDeCompra.getId(),
                ConverterNegocioUtils.articuloToNegocio(ordenDeCompra.getArticulo()),
                ordenDeCompra.getCantidad(),
                ordenDeCompra.isResuelto(),
                ConverterNegocioUtils.proveedorToNegocio(ordenDeCompra.getProovedor())
        );
    }

}
