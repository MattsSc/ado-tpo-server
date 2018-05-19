package model;

import java.util.Date;

public class Lote {
    private Integer id;
    private Date fechaVencimiento;
    private int cantidad;
    private Proovedor proovedor;

    public Lote(Date fechaVencimiento, int cantidad, Proovedor proovedor) {
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.proovedor = proovedor;
    }

    public Lote(Integer id, Date fechaVencimiento, int cantidad, Proovedor proovedor) {
        this.id = id;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.proovedor = proovedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Proovedor getProovedor() {
        return proovedor;
    }

    public void setProovedor(Proovedor proovedor) {
        this.proovedor = proovedor;
    }
}
