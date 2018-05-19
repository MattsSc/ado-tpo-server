package model;

import model.enums.TipoMovimiento;

import java.util.Date;

public class MovimientoPorInventario  extends  Movimiento{
    private String descripcion;
    private String estado;

    public MovimientoPorInventario(Date fecha, int cantidad, TipoMovimiento tipo, String descripcion, String estado) {
        super(fecha, cantidad, tipo);
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public MovimientoPorInventario(String id, Date fecha, int cantidad, TipoMovimiento tipo, String descripcion, String estado) {
        super(id, fecha, cantidad, tipo);
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
