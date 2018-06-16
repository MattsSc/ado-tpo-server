package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.ArticuloEntity;
import entities.LoteEntity;
import model.Articulo;
import model.Lote;
import model.Pedido;
import utils.HibernateUtils;

public class LoteDAO {

    public static void save(Lote lote, Articulo articulo){
        LoteEntity loteEntity = ConverterEntityUtils.loteToEntity(lote,articulo);
        HibernateUtils.saveTransaction(loteEntity);
        lote.setId(loteEntity.getId());
    }

    public static Articulo getArticuloByLote(Integer id){
        return ConverterNegocioUtils.articuloToNegocio(HibernateUtils.getById(LoteEntity.class, id).getArticulo());
    }

    public static Lote getById(Integer id){
        return ConverterNegocioUtils.loteToNegocio(HibernateUtils.getById(LoteEntity.class, id));
    }

    public static void delete(Lote lote){
        HibernateUtils.deleteTransaction(ConverterEntityUtils.loteToEntity(lote));
    }

    public static void update(Lote lote){
        HibernateUtils.updateTransaction(ConverterEntityUtils.loteToEntity(lote));
    }
}
