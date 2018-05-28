package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.OrdenDePedidoEntity;
import model.OrdenDePedido;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrdenDePedidoDAO {

    public static void save(OrdenDePedido ordenDePedido){
        HibernateUtils.saveTransaction(ordenPedidoToEntity(ordenDePedido));
    }

    public static void update(OrdenDePedido ordenDePedido){
        HibernateUtils.updateTransaction(ordenPedidoToEntity(ordenDePedido));
    }

    public static List<OrdenDePedido> obtenerOrdenesDePedido(){
        List<OrdenDePedidoEntity> result = HibernateUtils.getResultList("from OrdenDePedidoEntity");
        return result.stream().map(OrdenDePedidoDAO::ordenPedidoToNegocio).collect(Collectors.toList());
    }

    public static List<OrdenDePedido> obtenerOrdenesDePedidoSinOc(Integer articuloId){
        List<OrdenDePedidoEntity> result = HibernateUtils.getResultList("from OrdenDePedidoEntity where articuloId = " + articuloId + " and ordenDeCompraId is null");
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
