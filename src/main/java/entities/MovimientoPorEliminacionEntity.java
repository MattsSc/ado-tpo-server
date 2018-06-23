package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("1")
public class MovimientoPorEliminacionEntity extends MovimientoEntity{

    private String encargado;
    private String destino;
    private String auotorizador;
    private String ubicacion;

    public MovimientoPorEliminacionEntity(){
        super();
    }

    public MovimientoPorEliminacionEntity(String encargado, String destino, String auotorizador, String ubicacion) {
        this.encargado = encargado;
        this.destino = destino;
        this.auotorizador = auotorizador;
        this.ubicacion = ubicacion;
    }

    public MovimientoPorEliminacionEntity(Date fecha, int cantidad, String tipo, ArticuloEntity articuloEntity, String encargado, String destino, String auotorizador, String ubicacion) {
        super(fecha, cantidad, tipo, articuloEntity);
        this.encargado = encargado;
        this.destino = destino;
        this.auotorizador = auotorizador;
        this.ubicacion = ubicacion;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
