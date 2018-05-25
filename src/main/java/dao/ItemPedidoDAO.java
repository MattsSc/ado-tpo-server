package dao;

import dao.converters.ConverterEntityUtils;
import dao.converters.ConverterNegocioUtils;
import entities.ItemPedidoEntity;
import model.Articulo;
import model.ItemPedido;
import utils.HibernateUtils;

public class ItemPedidoDAO {

    public  static ItemPedido getById(Integer id){
       return ConverterNegocioUtils.itemPedidoToNegocio(HibernateUtils.getById(ItemPedidoEntity.class, id));
    }

    public  static Articulo getArticulo(ItemPedido itemPedido){
        return ConverterNegocioUtils.articuloToNegocio(HibernateUtils.getById(ItemPedidoEntity.class, itemPedido.getId()).getArticulo());
    }
}
