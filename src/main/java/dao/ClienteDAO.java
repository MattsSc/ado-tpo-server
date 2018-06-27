package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.ClienteEntity;
import model.Cliente;
import model.MovimientoCC;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDAO {

    public static void save(Cliente cliente) {
        HibernateUtils.saveTransaction(ConverterEntityUtils.clienteToEntity(cliente));
    }


    public static void update(Cliente cliente) {
        HibernateUtils.updateTransaction(ConverterEntityUtils.clienteToEntity(cliente));
    }

    public static List<Cliente> getClientes(){
        List<ClienteEntity> result = HibernateUtils.getResultList("from ClienteEntity");
        return result.stream().map(ConverterNegocioUtils::clienteToNegocio).collect(Collectors.toList());
    }

    public static Cliente getById(Integer dni){
        ClienteEntity cliente = HibernateUtils.getById(ClienteEntity.class, dni);
        return cliente != null ? ConverterNegocioUtils.clienteToNegocio(cliente): null;
    }

    public static List<MovimientoCC> getMovimientosDeCliente(Integer dni){
        return (HibernateUtils.getById(ClienteEntity.class, dni)).getMovimientosCC().stream().map(ConverterNegocioUtils::movimientoCCToNegocio).collect(Collectors.toList());
    }

}
