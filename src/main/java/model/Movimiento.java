package model;

import model.enums.TipoMovimiento;

import java.util.Date;

public class Movimiento {
    private String id;
    private Date fecha;
    private int cantidad;
    private TipoMovimiento tipo;

    public Movimiento(Date fecha, int cantidad, TipoMovimiento tipo) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public Movimiento(String id, Date fecha, int cantidad, TipoMovimiento tipo) {
        this.id = id;
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

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }
}
