package entities;

import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("case when tipo in ('COMPRA','VENTA') then 1 when 'ROTURA' then 2 else 3 end")
@Table(name="Movimiento")
public class MovimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private Date fecha;
    private int cantidad;
    private String tipo;

    public MovimientoEntity() {
    }

    public MovimientoEntity(Date fecha, int cantidad, String tipo) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
