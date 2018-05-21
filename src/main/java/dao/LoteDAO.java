package dao;

import dao.converters.ConverterEntityUtils;
import model.Articulo;
import model.Lote;
import utils.HibernateUtils;

public class LoteDAO {

    public  static void save(Lote lote, Articulo articulo){
        HibernateUtils.saveTransaction(ConverterEntityUtils.loteToEntity(lote,articulo));
    }
}
