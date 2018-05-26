package dao;

import dao.converters.ConverterEntityUtils;
import entities.OrdenDePedidoEntity;
import model.OrdenDePedido;
import utils.HibernateUtils;

public class OrdenDePedidoDAO {

    public static void save(OrdenDePedido ordenDePedido){
        HibernateUtils.saveTransaction(ordenPedidoToEntity(ordenDePedido));
    }

    private static OrdenDePedidoEntity ordenPedidoToEntity(OrdenDePedido ordenDePedido) {
        return new OrdenDePedidoEntity(
                ConverterEntityUtils.articuloToEntity(ordenDePedido.getArticulo()),
                ordenDePedido.getCantidad(),
                ConverterEntityUtils.pedidoToEntity(PedidoDAO.getById(ordenDePedido.getIdPedido())),
                null
        );
    }
}
