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

    public static List<Cliente> getClientes(){
        List<ClienteEntity> result = HibernateUtils.getResultList("from ClienteEntity");
        return result.stream().map(ConverterNegocioUtils::clienteToNegocio).collect(Collectors.toList());
    }

    public static Cliente getById(Integer dni){
        return ConverterNegocioUtils.clienteToNegocio(HibernateUtils.getById(ClienteEntity.class, dni));
    }

}
