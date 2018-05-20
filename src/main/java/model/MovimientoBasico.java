package model;

import model.enums.TipoMovimiento;

import java.util.Date;

public class MovimientoBasico extends Movimiento{

    private String estado;

    public MovimientoBasico(Date fecha, int cantidad, TipoMovimiento tipo, String estado) {
        super(fecha, cantidad, tipo);
        this.estado = estado;
    }

    public MovimientoBasico(Integer id, Date fecha, int cantidad, TipoMovimiento tipo, String estado) {
        super(id, fecha, cantidad, tipo);
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
