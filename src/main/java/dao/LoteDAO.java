package dao;

import dao.converters.ConverterEntityUtils;
import entities.LoteEntity;
import model.Articulo;
import model.Lote;
import utils.HibernateUtils;

public class LoteDAO {

    public static void save(Lote lote, Articulo articulo){
        LoteEntity loteEntity = ConverterEntityUtils.loteToEntity(lote,articulo);
        HibernateUtils.saveTransaction(loteEntity);
        lote.setId(loteEntity.getId());
    }
}
