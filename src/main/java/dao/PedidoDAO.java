package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.PedidoEntity;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoDAO {

    public static void save(Pedido pedido){
        PedidoEntity pedidoEntity = ConverterEntityUtils.pedidoToEntity(pedido);
        HibernateUtils.saveTransaction(pedidoEntity);
        pedido.setId(pedidoEntity.getId());
        pedido.setItems(PedidoDAO.getItemsPedidos(pedido));
    }

    public static void update(Pedido pedido){
        HibernateUtils.updateTransaction(ConverterEntityUtils.pedidoToEntity(pedido));
    }

    public static List<Pedido> getAll(){
        return HibernateUtils.getResultList("from PedidoEntity").stream()
                .map(pe -> ConverterNegocioUtils.pedidoToNegocio((PedidoEntity)pe))
                .collect(Collectors.toList());
    }

    public static List<Pedido> getAllByEstado(String estado){
        return HibernateUtils.getResultList("from PedidoEntity P where P.estado =  '" + estado + "'").stream()
                .map(pe -> ConverterNegocioUtils.pedidoToNegocio((PedidoEntity)pe))
                .collect(Collectors.toList());
    }

    public static Pedido getById(Integer id){
        return ConverterNegocioUtils.pedidoToNegocio(HibernateUtils.getById(PedidoEntity.class, id));
    }

    public static List<ItemPedido> getItemsPedidos(Pedido pedido){
        return HibernateUtils.getById(PedidoEntity.class, pedido.getId()).getItems().stream().map(ConverterNegocioUtils::itemPedidoToNegocio).collect(Collectors.toList());
    }

    public static Cliente getCliente(Pedido pedido){
        return ConverterNegocioUtils.clienteToNegocio(HibernateUtils.getById(PedidoEntity.class, pedido.getId()).getCliente());
    }
}
