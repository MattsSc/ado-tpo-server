package model;

import model.enums.TipoMovimiento;

import java.util.Date;

public class MovimientoPorEliminacion extends Movimiento{

    private String encargado;
    private String destino;
    private String autorizador;
    private String ubicacion;


    public MovimientoPorEliminacion(Date fecha, int cantidad, String encargado, String destino, String autorizador, String ubicacion) {
        super(fecha, cantidad, TipoMovimiento.ELIMINACION);
        this.encargado = encargado;
        this.destino = destino;
        this.autorizador = autorizador;
        this.ubicacion = ubicacion;
    }

    public MovimientoPorEliminacion(Integer id, Date fecha, int cantidad, String encargado, String destino, String autorizado, String ubicacion) {
        super(id, fecha, cantidad, TipoMovimiento.ELIMINACION);
        this.encargado = encargado;
        this.destino = destino;
        this.autorizador = autorizador;
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

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
