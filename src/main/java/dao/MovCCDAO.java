package dao;

import dao.converters.ConverterEntityUtils;
import entities.ClienteEntity;
import entities.MovCCEntity;
import model.Cliente;
import model.MovimientoCC;
import utils.HibernateUtils;

public class MovCCDAO {

    public static void save(Cliente cliente, MovimientoCC movimientoCC){
        HibernateUtils.saveTransaction(toEntity(movimientoCC, cliente));
    }

    private static MovCCEntity toEntity(MovimientoCC movimientoCC, Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        return new MovCCEntity(
                movimientoCC.getFecha(),
                movimientoCC.getImporte(),
                movimientoCC.getTipo(),
                ConverterEntityUtils.clienteToEntity(cliente)
        );
    }
}
