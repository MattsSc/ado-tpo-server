package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Cliente")
public class LoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Date fechaVencimiento;
    private int cantidad;

    @ManyToOne
    @JoinColumn(name="id")
    private ProveedorEntity proovedor;

    public LoteEntity(Date fechaVencimiento, int cantidad, ProveedorEntity proovedor) {
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.proovedor = proovedor;
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
}
