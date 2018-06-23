package model;

import dao.LoteDAO;

import java.util.Date;

public class Lote {
    private Integer id;
    private Date fechaVencimiento;
    private int stock;
    private Proveedor proveedor;

    public Lote(Date fechaVencimiento, int stock, Proveedor proveedor) {
        this.fechaVencimiento = fechaVencimiento;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    public Lote(Integer id, Date fechaVencimiento, int stock, Proveedor proveedor) {
        this.id = id;
        this.fechaVencimiento = fechaVencimiento;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    //Logic
    public void informarRotura(Integer cantidad) {
        this.setStock(this.getStock() - cantidad);
        this.update();
    }

    public void informarAjustePositivo(Integer cantidad) {
        this.setStock(this.getStock() + cantidad);
        this.update();
    }

    public void save(Articulo articulo){
        LoteDAO.save(this, articulo);
    }

    public void update(){
        LoteDAO.update(this);
    }

    public void delete(){
        LoteDAO.delete(this);
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
