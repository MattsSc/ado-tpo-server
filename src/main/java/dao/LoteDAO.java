package dao;

import entities.ArticuloEntity;
import entities.LoteEntity;
import entities.ProveedorEntity;
import model.Articulo;
import model.Lote;
import utils.HibernateUtils;

import java.util.ArrayList;

public class LoteDAO {

    public  static void save(Lote lote, Articulo articulo){
        HibernateUtils.saveTransaction(toEntity(lote,articulo));
    }

    private static LoteEntity toEntity(Lote lote, Articulo articulo){
        return new LoteEntity(
                lote.getFechaVencimiento(),
                lote.getStock(),
                new ProveedorEntity(
                        lote.getProovedor().getNombre(),
                        lote.getProovedor().getCuit()
                ),
                new ArticuloEntity(
                        articulo.getCodigo(),
                        articulo.getDescripcion(),
                        articulo.getPresentacion(),
                        articulo.getTamanio(),
                        articulo.getUnidad(),
                        articulo.getPrecio(),
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
    }
}
