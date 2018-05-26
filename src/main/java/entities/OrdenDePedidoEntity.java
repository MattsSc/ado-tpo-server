package entities;

import javax.persistence.*;

@Entity
@Table(name="OrdenDePedido")
public class OrdenDePedidoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="articuloId")
    private ArticuloEntity articulo;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name="pedidoId")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name="ordenDeCompraId")
    private OrdenDeCompraEntity ordenCompra;

    public OrdenDePedidoEntity() {}

    public OrdenDePedidoEntity(ArticuloEntity articulo, Integer cantidad, PedidoEntity pedido, OrdenDeCompraEntity ordenCompra) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.pedido = pedido;
        this.ordenCompra = ordenCompra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public OrdenDeCompraEntity getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenDeCompraEntity ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
}
