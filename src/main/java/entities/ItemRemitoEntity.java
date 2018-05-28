package entities;

import javax.persistence.*;

@Entity
@Table(name = "ItemRemito")
public class ItemRemitoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="articuloId")
    private ArticuloEntity articulo;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name="remitoId")
    private RemitoEntity remito;

    public ItemRemitoEntity(ArticuloEntity articulo, Integer cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public ItemRemitoEntity(ArticuloEntity articulo, Integer cantidad, RemitoEntity remito) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.remito = remito;
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

    public RemitoEntity getRemito() {
        return remito;
    }

    public void setRemito(RemitoEntity remito) {
        this.remito = remito;
    }
}
