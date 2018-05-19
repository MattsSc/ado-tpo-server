package model;

import model.enums.TipoMovimiento;

import java.util.Date;

public class MovimientoPorDanio extends Movimiento{

    private String encargado;
    private String destino;
    private String auotorizador;


    public MovimientoPorDanio(Date fecha, int cantidad, String encargado, String destino, String auotorizador) {
        super(fecha, cantidad, TipoMovimiento.ROTURA);
        this.encargado = encargado;
        this.destino = destino;
        this.auotorizador = auotorizador;
    }

    public MovimientoPorDanio(String id, Date fecha, int cantidad, String encargado, String destino, String auotorizador) {
        super(id, fecha, cantidad, TipoMovimiento.ROTURA);
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
