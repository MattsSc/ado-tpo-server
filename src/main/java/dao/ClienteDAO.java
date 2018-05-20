package dao;

import entities.ClienteEntity;
import model.Cliente;
import model.MovimientoCC;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDAO {

    public static void save(Cliente cliente) {
        HibernateUtils.saveTransaction(toEntity(cliente));
    }

    public static List<Cliente> getClientes(){
        List<ClienteEntity> result = HibernateUtils.getResultList("from ClienteEntity");
        return result.stream().map(ClienteDAO::toNegocio).collect(Collectors.toList());
    }

    public static Cliente getById(Integer dni){
        return toNegocio(HibernateUtils.getById(ClienteEntity.class, dni));
    }



    //Converters

    private static ClienteEntity toEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDomicilio(),
                cliente.getCuit(),
                cliente.getRazonSocial(),
                cliente.getLimiteCredito(),
                cliente.getMontoDisponible()
        );
    }

    private static Cliente toNegocio(ClienteEntity clienteE) {
        if(!clienteE.getMovimientosCC().isEmpty()){
            return new Cliente(clienteE.getDni(),
                    clienteE.getNombre(),
                    clienteE.getApellido(),
                    clienteE.getDomicilio(),
                    clienteE.getCuit(),
                    clienteE.getRazonSocial(),
                    clienteE.getLimiteCredito(),
                    clienteE.getMontoDisponible(),
                    clienteE.getMovimientosCC().stream().map(movCC -> new MovimientoCC(movCC.getFecha(),movCC.getImporte(),movCC.getTipo())).collect(Collectors.toList()));
        }
        return new Cliente(clienteE.getDni(),
                clienteE.getNombre(),
                clienteE.getApellido(),
                clienteE.getDomicilio(),
                clienteE.getCuit(),
                clienteE.getRazonSocial(),
                clienteE.getLimiteCredito(),
                clienteE.getMontoDisponible());
    }


}
