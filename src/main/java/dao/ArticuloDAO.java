package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import dtos.ArticuloDTO;
import entities.ArticuloEntity;
import model.*;
import utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ArticuloDAO {

    public static void save(Articulo articulo){
        HibernateUtils.saveTransaction(ConverterEntityUtils.articuloToEntity(articulo));
    }

    public static Articulo getById(Integer id){
        return ConverterNegocioUtils.articuloToNegocio(HibernateUtils.getById(ArticuloEntity.class, id));
    }


    public static List<Articulo> getAll(){
        List<ArticuloEntity> articuloDTOS = HibernateUtils.getResultList("from ArticuloEntity");
        return articuloDTOS.stream().map(ConverterNegocioUtils::articuloToNegocio).collect(Collectors.toList());
    }

    public static List<Lote> getLotes(Articulo articulo){
        return  HibernateUtils.getById(ArticuloEntity.class, articulo.getCodigo()).getLotes().stream()
                .map(ConverterNegocioUtils::loteToNegocio)
                .collect(Collectors.toList());
    }

    public static List<Movimiento> getMovimientos(Articulo articulo){
        return  HibernateUtils.getById(ArticuloEntity.class, articulo.getCodigo()).getMovimientos().stream()
                .map(ConverterNegocioUtils::movToNegocio)
                .collect(Collectors.toList());
    }

}
