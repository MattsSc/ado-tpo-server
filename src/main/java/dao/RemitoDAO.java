package dao;

import dao.converters.ConverterEntityUtils;
import entities.ItemRemitoEntity;
import entities.RemitoEntity;
import model.Remito;
import utils.HibernateUtils;

import java.util.stream.Collectors;

public class RemitoDAO {

    public static void save(Remito remito){
        RemitoEntity remitoEntity = remitoToEntity(remito);
        HibernateUtils.saveTransaction(remitoEntity);
        remito.setId(remitoEntity.getId());
    }

    private static RemitoEntity remitoToEntity(Remito remito) {
        return new RemitoEntity(
                remito.getFechaCreacion(),
                ConverterEntityUtils.clienteToEntity(remito.getCliente()),
                remito.getItems().stream().map(item -> new ItemRemitoEntity(
                        ConverterEntityUtils.articuloToEntity(item.getArticulo()),
                        item.getCantidad()
                )).collect(Collectors.toList())
        );
    }
}
