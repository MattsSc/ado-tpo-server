package dao;

import dao.converters.ConverterEntityUtils;
import entities.ReservaArticuloEntity;
import model.ReservaArticulo;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ReservaArticuloDAO {

    public static void save(ReservaArticulo reservaArticulo, Integer codigoArticulo){
        ReservaArticuloEntity res = reservaArticuloToEntity(reservaArticulo, codigoArticulo);
        HibernateUtils.saveTransaction(res);
    }

    public static List<ReservaArticulo> getByArticuloId(Integer articuloId){
        List<ReservaArticuloEntity> result = HibernateUtils.getResultList("from ReservaArticuloEntity where articuloId = " + articuloId);
        return result.stream().map(ReservaArticuloDAO::reservaArticuloToNegocio).collect(Collectors.toList());
    }

    public static List<ReservaArticulo> getByPedidoId(Integer pedidoId){
        List<ReservaArticuloEntity> result = HibernateUtils.getResultList("from ReservaArticuloEntity where pedidoId = " + pedidoId);
        return result.stream().map(ReservaArticuloDAO::reservaArticuloToNegocio).collect(Collectors.toList());
    }

    public static void delete(ReservaArticulo reservaArticulo){
        HibernateUtils.deleteTransaction(reservaArticuloToEntity(reservaArticulo));
    }


    private static ReservaArticuloEntity reservaArticuloToEntity(ReservaArticulo reservaArticulo) {
        ReservaArticuloEntity reservaArticuloEntity = new ReservaArticuloEntity(
                reservaArticulo.getCantidad(),
                null,
                ConverterEntityUtils.pedidoToEntity(PedidoDAO.getById(reservaArticulo.getIdPedido())),
                reservaArticulo.isEsCompleta()
        );
        if(reservaArticulo.getId() != null)
            reservaArticuloEntity.setId(reservaArticulo.getId());

        return  reservaArticuloEntity;
    }

    private static ReservaArticuloEntity reservaArticuloToEntity(ReservaArticulo reservaArticulo, Integer codigoArticulo) {
        return  new ReservaArticuloEntity(
                reservaArticulo.getCantidad(),
                ConverterEntityUtils.articuloToEntity(ArticuloDAO.getById(codigoArticulo)),
                ConverterEntityUtils.pedidoToEntity(PedidoDAO.getById(reservaArticulo.getIdPedido())),
                reservaArticulo.isEsCompleta()
        );
    }

    private static ReservaArticulo reservaArticuloToNegocio(ReservaArticuloEntity res){
        return new ReservaArticulo(
                res.getId(),
                res.getCantidad(),
                res.getPedido().getId(),
                res.isEsCompleta()
        );
    }
}
