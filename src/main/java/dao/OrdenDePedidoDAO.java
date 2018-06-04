package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.OrdenDeCompraEntity;
import entities.OrdenDePedidoEntity;
import model.Articulo;
import model.OrdenDePedido;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrdenDePedidoDAO {

    public static List<Articulo> getArticulosFaltantes(){
        List<OrdenDePedidoEntity> opList = HibernateUtils.getResultList("from OrdenDePedidoEntity");
        return opList.stream()
                .map(op -> op.getArticulo())
                .distinct()
                .map(ConverterNegocioUtils::articuloToNegocio)
                .collect(Collectors.toList());
    }

    public static void save(OrdenDePedido ordenDePedido){
        HibernateUtils.saveTransaction(ordenPedidoToEntity(ordenDePedido));
    }

    public static void update(OrdenDePedido ordenDePedido){
        HibernateUtils.updateTransaction(ordenPedidoToEntity(ordenDePedido));
    }

    public static void delete(OrdenDePedido ordenDePedido){
        HibernateUtils.deleteTransaction(ordenPedidoToEntity(ordenDePedido));
    }

    public static List<OrdenDePedido> obtenerOrdenesDePedido(){
        return obtenerPorQuery("from OrdenDePedidoEntity");
    }

    public static List<OrdenDePedido> obtenerOrdenesDePedidoSinOc(Integer articuloId){
        return obtenerPorQuery("from OrdenDePedidoEntity where articuloId = " + articuloId + " and ordenDeCompraId is null");
    }


    public static List<OrdenDePedido> obtenerOrdenesDePedidoParaPedido(Integer pedidoId){
        return obtenerPorQuery("from OrdenDePedidoEntity where pedidoId = " + pedidoId);
    }

    public static List<OrdenDePedido> obtenerOrdenesDePedidoParaOC(Integer ordenDeCompraId){
        return obtenerPorQuery("from OrdenDePedidoEntity where ordenDeCompraId = " + ordenDeCompraId);
    }

    private static List<OrdenDePedido> obtenerPorQuery(String query){
        List<OrdenDePedidoEntity> result = HibernateUtils.getResultList(query);
        return result.stream().map(OrdenDePedidoDAO::ordenPedidoToNegocio).collect(Collectors.toList());
    }


    private static OrdenDePedidoEntity ordenPedidoToEntity(OrdenDePedido ordenDePedido) {
        OrdenDePedidoEntity ordenDePedidoEntity =  new OrdenDePedidoEntity(
                ConverterEntityUtils.articuloToEntity(ordenDePedido.getArticulo()),
                ordenDePedido.getCantidad(),
                ConverterEntityUtils.pedidoToEntity(PedidoDAO.getById(ordenDePedido.getIdPedido())),
                ordenDePedido.getIdOrdenCompra() != null ? ConverterEntityUtils.ordenDeCompraToEntity(OrdenDeCompraDAO.getById(ordenDePedido.getIdOrdenCompra())) : null
        );

        if(ordenDePedido.getId() != null)
            ordenDePedidoEntity.setId(ordenDePedido.getId());

        return ordenDePedidoEntity;
    }

    private static OrdenDePedido ordenPedidoToNegocio(OrdenDePedidoEntity ordenDePedido) {
        return new OrdenDePedido(
                ordenDePedido.getId(),
                ConverterNegocioUtils.articuloToNegocio(ordenDePedido.getArticulo()),
                ordenDePedido.getCantidad(),
                ordenDePedido.getPedido().getId(),
                ordenDePedido.getOrdenCompra() != null ? ordenDePedido.getOrdenCompra().getId() : null
        );
    }
}
