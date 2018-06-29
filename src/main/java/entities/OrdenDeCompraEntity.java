package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="OrdenDeCompra")
public class OrdenDeCompraEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private Date fechaEmision;

    @ManyToOne
    @JoinColumn(name="articuloId")
    private ArticuloEntity articulo;

    private Integer cantidad;
    private boolean resuelto;
    private float precio;

    @ManyToOne
    @JoinColumn(name="proveedorId")
    private ProveedorEntity proovedor;

    public OrdenDeCompraEntity(){}

    public OrdenDeCompraEntity(Date fechaEmision, ArticuloEntity articulo, Integer cantidad, boolean resuelto, float precio, ProveedorEntity proovedor) {
        this.fechaEmision = fechaEmision;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.resuelto = resuelto;
        this.precio = precio;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public ProveedorEntity getProovedor() {
        return proovedor;
    }

    public void setProovedor(ProveedorEntity proovedor) {
        this.proovedor = proovedor;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}
