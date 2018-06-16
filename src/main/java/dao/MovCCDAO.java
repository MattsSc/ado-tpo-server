package dao;

import dao.converters.ConverterEntityUtils;
import entities.ClienteEntity;
import entities.MovCCEntity;
import model.Cliente;
import model.MovimientoCC;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MovCCDAO {

    public static void save(Cliente cliente, MovimientoCC movimientoCC){
        MovCCEntity movCCEntity = toEntity(movimientoCC, cliente);
        HibernateUtils.saveTransaction(movCCEntity);
        movimientoCC.setId(movCCEntity.getIdMovimiento());
    }

    public static List<MovimientoCC> getMovsByIdCliente(Integer idCliente){
        List<MovCCEntity> movCCEntities = HibernateUtils.getResultList("from MovCCEntity where idCliente = " + idCliente);
        return movCCEntities.stream().map(MovCCDAO::toNegocio).collect(Collectors.toList());
    }

    private static MovCCEntity toEntity(MovimientoCC movimientoCC, Cliente cliente) {
        return new MovCCEntity(
                movimientoCC.getFecha(),
                movimientoCC.getImporte(),
                movimientoCC.getTipo(),
                ConverterEntityUtils.clienteToEntity(cliente)
        );
    }

    private static MovimientoCC toNegocio(MovCCEntity entity) {
        return new MovimientoCC(
                entity.getIdMovimiento(),
                entity.getFecha(),
                entity.getImporte(),
                entity.getTipo()
        );
    }

}
