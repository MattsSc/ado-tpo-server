package model;

import dao.ItemPedidoDAO;
import dtos.ItemPedidoDTO;

public class ItemPedido {

    private Integer id;
    private int cantidad;
    private Articulo articulo;


    public ItemPedidoDTO toDto(){
        return new ItemPedidoDTO(
                this.getId(),
                this.getCantidad(),
                this.getArticulo().toDto()
        );
    }

    public ItemPedido(int cantidad, Articulo articulo) {
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public ItemPedido(Integer id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    //LOGIC

    public boolean hayStock(){
        return this.getArticulo().hayStock(this.getCantidad());
    }

    //GETTER Y SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        if( articulo == null ) articulo = ItemPedidoDAO.getArticulo(this);
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
