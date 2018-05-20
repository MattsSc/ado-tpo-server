package dao;

import entities.ArticuloEntity;
import entities.LoteEntity;
import entities.MovimientoBasicoEntity;
import model.*;
import model.enums.TipoMovimiento;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloDAO {

    public static void save(Articulo articulo){
        HibernateUtils.saveTransaction(toEntity(articulo));
    }

    public static Articulo getById(Integer id){
        return toNegocio(HibernateUtils.getById(ArticuloEntity.class, id));
    }


    private static ArticuloEntity toEntity(Articulo articulo){
        return new ArticuloEntity(
                articulo.getCodigo(),
                articulo.getDescripcion(),
                articulo.getPresentacion(),
                articulo.getTamanio(),
                articulo.getUnidad(),
                articulo.getPrecio());
    }

    private static Articulo toNegocio(ArticuloEntity entity){
        return new Articulo(
                entity.getCodigo(),
                entity.getDescripcion(),
                entity.getPresentacion(),
                entity.getTamanio(),
                entity.getUnidad(),
                entity.getPrecio(),
                entity.getLotes().isEmpty() ? new ArrayList<>(): lotesToNegocio(entity.getLotes()),
                entity.getMovimientos().isEmpty() ? new ArrayList<>(): movToNegocio(entity.getMovimientos())
           );
    }

    private static List<Lote> lotesToNegocio(List<LoteEntity> entities){
        return entities.stream().map(lote ->
            new Lote(
                    lote.getId(),
                    lote.getFechaVencimiento(),
                    lote.getCantidad(),
                    new Proovedor(
                            lote.getProovedor().getId(),
                            lote.getProovedor().getNombre(),
                            lote.getProovedor().getCuit())
            )).collect(Collectors.toList());

    }

    private static List<Movimiento> movToNegocio(List<MovimientoBasicoEntity> entities){
        return entities.stream().map(mov -> new MovimientoBasico(
                mov.getId(),
                mov.getFecha(),
                mov.getCantidad(),
                TipoMovimiento.valueOf(mov.getTipo()),
                mov.getEstado()
        )).collect(Collectors.toList());
    }
}
