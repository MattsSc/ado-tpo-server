package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("3")
public class MovimientoPorInventarioEntity extends MovimientoEntity {
    private String descripcion;
    private String estado;

    public MovimientoPorInventarioEntity(String descripcion, String estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public MovimientoPorInventarioEntity(Date fecha, int cantidad, String tipo, ArticuloEntity articuloEntity, String descripcion, String estado) {
        super(fecha, cantidad, tipo, articuloEntity);
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
