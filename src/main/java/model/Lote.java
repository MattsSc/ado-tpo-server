package model;

import java.util.Date;

public class Lote {
    private Integer id;
    private Date fechaVencimiento;
    private int stock;
    private Proovedor proovedor;

    public Lote(Date fechaVencimiento, int stock, Proovedor proovedor) {
        this.fechaVencimiento = fechaVencimiento;
        this.stock = stock;
        this.proovedor = proovedor;
    }

    public Lote(Integer id, Date fechaVencimiento, int stock, Proovedor proovedor) {
        this.id = id;
        this.fechaVencimiento = fechaVencimiento;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Proovedor getProovedor() {
        return proovedor;
    }

    public void setProovedor(Proovedor proovedor) {
        this.proovedor = proovedor;
    }
}
