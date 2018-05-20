package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("2")
public class MovimientoPorDanioEntity extends MovimientoEntity{

    private String encargado;
    private String destino;
    private String auotorizador;

    public MovimientoPorDanioEntity(String encargado, String destino, String auotorizador) {
        this.encargado = encargado;
        this.destino = destino;
        this.auotorizador = auotorizador;
    }

    public MovimientoPorDanioEntity(Date fecha, int cantidad, String tipo, ArticuloEntity articuloEntity, String encargado, String destino, String auotorizador) {
        super(fecha, cantidad, tipo, articuloEntity);
        this.encargado = encargado;
        this.destino = destino;
        this.auotorizador = auotorizador;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAuotorizador() {
        return auotorizador;
    }

    public void setAuotorizador(String auotorizador) {
        this.auotorizador = auotorizador;
    }
}
