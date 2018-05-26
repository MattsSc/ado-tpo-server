package entities;

import model.Articulo;

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
    private Integer idPedido;
    private Integer idOrdenCompra;

    public OrdenDePedidoEntity() {}

    public OrdenDePedidoEntity(ArticuloEntity articulo, Integer cantidad, Integer idPedido, Integer idOrdenCompra) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.idPedido = idPedido;
        this.idOrdenCompra = idOrdenCompra;
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

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }
}
