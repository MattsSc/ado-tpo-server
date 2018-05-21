package dao;

import entities.ProveedorEntity;
import model.Proovedor;
import utils.HibernateUtils;

public class ProovedorDAO {

    public static void save(Proovedor proovedor){
        HibernateUtils.saveTransaction(toEntity(proovedor));
    }


    private static ProveedorEntity toEntity(Proovedor proovedor){
        return new ProveedorEntity(proovedor.getNombre(), proovedor.getCuit());
    }
}
