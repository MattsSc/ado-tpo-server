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
