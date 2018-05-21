package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.ArticuloEntity;
import model.*;
import utils.HibernateUtils;

public class ArticuloDAO {

    public static void save(Articulo articulo){
        HibernateUtils.saveTransaction(ConverterEntityUtils.articuloToEntity(articulo));
    }

    public static Articulo getById(Integer id){
        return ConverterNegocioUtils.articuloToNegocio(HibernateUtils.getById(ArticuloEntity.class, id));
    }
}
