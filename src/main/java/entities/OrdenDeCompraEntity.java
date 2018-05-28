package entities;

import javax.persistence.*;

@Entity
@Table(name="OrdenDeCompra")
public class OrdenDeCompraEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="articuloId")
    private ArticuloEntity articulo;

    private Integer cantidad;
    private boolean resuelto;

    @ManyToOne
    @JoinColumn(name="proveedorId")
    private ProveedorEntity proovedor;

    public OrdenDeCompraEntity(){}

    public OrdenDeCompraEntity(ArticuloEntity articulo, Integer cantidad, boolean resuelto, ProveedorEntity proovedor) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.resuelto = resuelto;
        this.proovedor = proovedor;
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

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public ProveedorEntity getProovedor() {
        return proovedor;
    }

    public void setProovedor(ProveedorEntity proovedor) {
        this.proovedor = proovedor;
    }
}
