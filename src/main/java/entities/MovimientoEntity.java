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
    @GeneratedValue
    private Integer id;

    private Date fecha;
    private int cantidad;
    private String tipo;

    @ManyToOne
    @JoinColumn(name="articuloId")
    private ArticuloEntity articulo;

    public MovimientoEntity() {
    }

    public MovimientoEntity(Date fecha, int cantidad, String tipo, ArticuloEntity articulo) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.articulo = articulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }
}
