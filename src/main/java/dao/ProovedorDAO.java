package dao;

import entities.ProveedorEntity;
import model.Proveedor;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ProovedorDAO {

    public static void save(Proveedor proveedor){
        HibernateUtils.saveTransaction(toEntity(proveedor));
    }

    public static List<Proveedor> getAll(){
        return HibernateUtils.getResultList("from ProveedorEntity").stream().map(p -> toNegocio((ProveedorEntity)p)).collect(Collectors.toList());
    }


    private static ProveedorEntity toEntity(Proveedor proveedor){
        return new ProveedorEntity(proveedor.getNombre(), proveedor.getCuit());
    }

    private static Proveedor toNegocio(ProveedorEntity proveedor){
        return new Proveedor(proveedor.getId(), proveedor.getNombre(), proveedor.getCuit());
    }
}
