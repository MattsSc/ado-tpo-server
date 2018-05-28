package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.UbicacionEntity;
import model.Ubicacion;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UbicacionDAO {

    public static void save(Ubicacion ubicacion){
        UbicacionEntity ub = ubicacionToEntity(ubicacion);
        HibernateUtils.saveTransaction(ub);
        ubicacion.setIdUbicacion(ub.getIdUbicacion());
    }

    public static void update(Ubicacion ubicacion){
        HibernateUtils.updateTransaction(ubicacionToEntity(ubicacion));
    }

    public static List<Ubicacion> getUbicacionesDeLote(Integer idLote){
        List<UbicacionEntity> ubicacionEntities = HibernateUtils.getResultList("from UbicacionEntity where loteId = " + idLote);
        return  ubicacionEntities.stream().map(UbicacionDAO::ubicacionToNegocio).collect(Collectors.toList());
    }

    private static UbicacionEntity ubicacionToEntity(Ubicacion ubicacion) {
        UbicacionEntity ubicacionEntity = new UbicacionEntity(
                ubicacion.getClave(),
                ubicacion.isOcupado(),
                ubicacion.getLote() != null ? ConverterEntityUtils.loteToEntity(ubicacion.getLote()) : null,
                ubicacion.getCantidad()
        );

        if(ubicacion.getIdUbicacion() != null)
            ubicacionEntity.setIdUbicacion(ubicacion.getIdUbicacion());
        return ubicacionEntity;
    }

    private static Ubicacion ubicacionToNegocio(UbicacionEntity entity){
        return new Ubicacion(
                entity.getIdUbicacion(),
                entity.getClave(),
                entity.getOcupado(),
                ConverterNegocioUtils.loteToNegocio(entity.getLote()),
                entity.getCantidad()
        );
    }
}
