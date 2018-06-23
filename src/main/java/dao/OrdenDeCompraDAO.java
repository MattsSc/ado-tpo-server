package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.OrdenDeCompraEntity;
import model.Articulo;
import model.OrdenDeCompra;
import model.Proveedor;
import utils.HibernateUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrdenDeCompraDAO {

    public static void save(OrdenDeCompra ordenDeCompra){
        OrdenDeCompraEntity oCEntity = ConverterEntityUtils.ordenDeCompraToEntity(ordenDeCompra);
        HibernateUtils.saveTransaction(oCEntity);
        ordenDeCompra.setId(oCEntity.getId());
    }

    public static OrdenDeCompra getById(Integer id){
        return ordenDeCompraToNegocio(HibernateUtils.getById(OrdenDeCompraEntity.class, id));
    }

    public static List<OrdenDeCompra> obtenerOrdenesAbiertas(){
        List<OrdenDeCompraEntity> result = HibernateUtils.getResultList("from OrdenDeCompraEntity where resuelto = 0");
       return result.stream().map(OrdenDeCompraDAO::ordenDeCompraToNegocio).collect(Collectors.toList());
    }

    public static void update(OrdenDeCompra or){
        HibernateUtils.updateTransaction(ConverterEntityUtils.ordenDeCompraToEntity(or));
    }

    public static OrdenDeCompra getUltimaOrdenDeCompra(Integer codigoArticulo){
        OrdenDeCompraEntity result = (OrdenDeCompraEntity) HibernateUtils.getOneResult("from OrdenDeCompraEntity where articuloId = " + codigoArticulo + " ORDER BY id DESC");

        if(result != null)
            return  ordenDeCompraToNegocio(result);
        else
            return null;
    }

    public static List<OrdenDeCompra> getUltimos3Proveedores(Integer codigoArticulo){
        List<OrdenDeCompraEntity> result = HibernateUtils.getResultList("from OrdenDeCompraEntity where articuloId = " + codigoArticulo + " ORDER BY id DESC");
        if(result.size() > 3)
            return result.stream().map(OrdenDeCompraDAO::ordenDeCompraToNegocio).collect(Collectors.toList()).subList(0,2);
        return result.stream().map(OrdenDeCompraDAO::ordenDeCompraToNegocio).collect(Collectors.toList());
    }

    private static OrdenDeCompra ordenDeCompraToNegocio(OrdenDeCompraEntity ordenDeCompra) {
        return new OrdenDeCompra(
                ordenDeCompra.getId(),
                ConverterNegocioUtils.articuloToNegocio(ordenDeCompra.getArticulo()),
                ordenDeCompra.getCantidad(),
                ordenDeCompra.isResuelto(),
                ordenDeCompra.getPrecio(),
                ConverterNegocioUtils.proveedorToNegocio(ordenDeCompra.getProovedor())
        );
    }



}
