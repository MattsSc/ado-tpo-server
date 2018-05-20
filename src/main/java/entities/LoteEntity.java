package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Lote")
public class LoteEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private Date fechaVencimiento;
    private int cantidad;

    @ManyToOne
    @JoinColumn(name="proveedorId")
    private ProveedorEntity proovedor;

    @ManyToOne
    @JoinColumn(name="articuloId")
    private ArticuloEntity articulo;

    public LoteEntity() {
    }

    public LoteEntity(Date fechaVencimiento, int cantidad, ProveedorEntity proovedor, ArticuloEntity articulo) {
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.proovedor = proovedor;
        this.articulo = articulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ProveedorEntity getProovedor() {
        return proovedor;
    }

    public void setProovedor(ProveedorEntity proovedor) {
        this.proovedor = proovedor;
    }

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }
}
