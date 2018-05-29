package dao;

import dao.converters.ConverterEntityUtils;
import entities.ClienteEntity;
import entities.MovCCEntity;
import model.Cliente;
import model.MovimientoCC;
import utils.HibernateUtils;

public class MovCCDAO {

    public static void save(Cliente cliente, MovimientoCC movimientoCC){
        MovCCEntity movCCEntity = toEntity(movimientoCC, cliente);
        HibernateUtils.saveTransaction(movCCEntity);
        movimientoCC.setId(movCCEntity.getIdMovimiento());
    }

    private static MovCCEntity toEntity(MovimientoCC movimientoCC, Cliente cliente) {
        return new MovCCEntity(
                movimientoCC.getFecha(),
                movimientoCC.getImporte(),
                movimientoCC.getTipo(),
                ConverterEntityUtils.clienteToEntity(cliente)
        );
    }
}
