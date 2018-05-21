package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("1")
public class MovimientoBasicoEntity extends MovimientoEntity {
    private String estado;

    public MovimientoBasicoEntity(){
        super();
    }

    public MovimientoBasicoEntity(String estado) {
        this.estado = estado;
    }

    public MovimientoBasicoEntity(Date fecha, int cantidad, String tipo, ArticuloEntity articuloEntity, String estado) {
        super(fecha, cantidad, tipo, articuloEntity);
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
