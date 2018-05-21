package dao;

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
                new ClienteEntity(
                        cliente.getDni(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getDomicilio(),
                        cliente.getCuit(),
                        cliente.getRazonSocial(),
                        cliente.getLimiteCredito(),
                        cliente.getMontoDisponible()
                )
        );
    }
}
