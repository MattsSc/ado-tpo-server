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

    public static List<OrdenDePedido> obtenerOrdenesDePedido(){
        List<OrdenDePedidoEntity> result = HibernateUtils.getResultList("from OrdenDePedidoEntity");
        return result.stream().map(OrdenDePedidoDAO::ordenPedidoToNegocio).collect(Collectors.toList());
    }

    private static OrdenDePedidoEntity ordenPedidoToEntity(OrdenDePedido ordenDePedido) {
        return new OrdenDePedidoEntity(
                ConverterEntityUtils.articuloToEntity(ordenDePedido.getArticulo()),
                ordenDePedido.getCantidad(),
                ConverterEntityUtils.pedidoToEntity(PedidoDAO.getById(ordenDePedido.getIdPedido())),
                null
        );
    }

    private static OrdenDePedido ordenPedidoToNegocio(OrdenDePedidoEntity ordenDePedido) {
        return new OrdenDePedido(
                ordenDePedido.getId(),
                ConverterNegocioUtils.articuloToNegocio(ordenDePedido.getArticulo()),
                ordenDePedido.getCantidad(),
                ordenDePedido.getOrdenCompra() != null ? ordenDePedido.getOrdenCompra().getId() : null
        );
    }
}
