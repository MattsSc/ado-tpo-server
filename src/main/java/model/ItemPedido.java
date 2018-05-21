package model;

import dtos.ItemPedidoDTO;

public class ItemPedido {

    private Integer id;
    private int cantidad;
    private Articulo articulo;


    public static ItemPedido dtoToModel(ItemPedidoDTO dto){
        return new ItemPedido(
                dto.getCantidad(),
                Articulo.dtoToModel(dto.getArticulo()));
    }

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

    public ItemPedido(Integer id, int cantidad, Articulo articulo) {
        this.id = id;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

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
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
