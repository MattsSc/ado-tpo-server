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

    public static Ubicacion getUbicacionByClave(String clave){
        UbicacionEntity ubicacionEntity = (UbicacionEntity) HibernateUtils.getOneResult("from UbicacionEntity where  clave = '" + clave + "'");
        return  ubicacionToNegocio(ubicacionEntity);
    }

    public static List<Ubicacion> getUbicacionesVacias(){
        List<UbicacionEntity> ubicacionEntities = HibernateUtils.getResultList("from UbicacionEntity where ocupado = 0");
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
                entity.getLote() != null ? ConverterNegocioUtils.loteToNegocio(entity.getLote()) : null,
                entity.getCantidad()
        );
    }
}
