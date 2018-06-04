package entities;

import javax.persistence.*;

@Entity
@Table(name="ReservaArticulo")
public class ReservaArticuloEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private int cantidad;

    @ManyToOne
    @JoinColumn(name="articuloId")
    private ArticuloEntity articulo;

    @ManyToOne
    @JoinColumn(name="pedidoId")
    private PedidoEntity pedido;

    private boolean esCompleta;

    public ReservaArticuloEntity(){}

    public ReservaArticuloEntity(int cantidad, ArticuloEntity articulo, PedidoEntity pedido, boolean esCompleta) {
        this.cantidad = cantidad;
        this.articulo = articulo;
        this.pedido = pedido;
        this.esCompleta = esCompleta;
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

    public boolean isEsCompleta() {
        return esCompleta;
    }

    public void setEsCompleta(boolean esCompleta) {
        this.esCompleta = esCompleta;
    }
}