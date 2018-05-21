package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.PedidoEntity;
import model.Pedido;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoDAO {

    public static void save(Pedido pedido){
        HibernateUtils.saveTransaction(ConverterEntityUtils.pedidoToEntity(pedido));
    }

    public static void updateStatus(Integer id, String estado){
        PedidoEntity pedidoEntity = HibernateUtils.getById(PedidoEntity.class, id);
        pedidoEntity.setEstado(estado);
        HibernateUtils.updateTransaction(pedidoEntity);
    }

    public static List<Pedido> getAll(){
        return HibernateUtils.getResultList("from PedidoEntity").stream()
                .map(pe -> ConverterNegocioUtils.pedidoToNegocio((PedidoEntity)pe))
                .collect(Collectors.toList());
    }

    public static Pedido getById(Integer id){
        return ConverterNegocioUtils.pedidoToNegocio(HibernateUtils.getById(PedidoEntity.class, id));
    }

}
