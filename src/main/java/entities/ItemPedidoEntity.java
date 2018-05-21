package entities;

import javax.persistence.*;

@Entity
@Table(name = "ItemPedido")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private int cantidad;

    @ManyToOne
    @JoinColumn(name="articuloId")
    private ArticuloEntity articulo;

    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private  PedidoEntity pedido;

    public ItemPedidoEntity() {
    }

    public ItemPedidoEntity(int cantidad, ArticuloEntity articulo) {
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public ItemPedidoEntity(int cantidad, ArticuloEntity articulo, PedidoEntity pedido) {
        this.cantidad = cantidad;
        this.articulo = articulo;
        this.pedido = pedido;
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

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
}
