package model;

import dao.OrdenDeCompraDAO;

public class OrdenDeCompra {

    private Integer id;
    private Articulo articulo;
    private Integer cantidad;
    private boolean resuelto;
    private Proveedor proovedor;

    public OrdenDeCompra(Articulo articulo, Integer cantidad, boolean resuelto) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.resuelto = resuelto;
    }

    public OrdenDeCompra(Integer id, Articulo articulo, Integer cantidad, boolean resuelto, Proveedor proovedor) {
        this.id = id;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.resuelto = resuelto;
        this.proovedor = proovedor;
    }

    //Logic
    public void update() {
        OrdenDeCompraDAO.update(this);
    }

    public void save(){
        OrdenDeCompraDAO.save(this);
    }

    //Getter & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public Proveedor getProovedor() {
        return proovedor;
    }

    public void setProovedor(Proveedor proovedor) {
        this.proovedor = proovedor;
    }
}
