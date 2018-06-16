package model;

import dao.OrdenDeCompraDAO;

public class OrdenDeCompra {

    private Integer id;
    private Articulo articulo;
    private Integer cantidad;
    private boolean resuelto;
    private float precio;
    private Proveedor proovedor;

    public OrdenDeCompra(Articulo articulo, Integer cantidad, Proveedor proveedor) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.resuelto = Boolean.FALSE;
        this.proovedor = proveedor;
    }

    public OrdenDeCompra(Articulo articulo, Integer cantidad, boolean resuelto, Proveedor proveedor) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.resuelto = resuelto;
        this.proovedor = proveedor;
    }

    public OrdenDeCompra(Integer id, Articulo articulo, Integer cantidad, boolean resuelto, float precio, Proveedor proovedor) {
        this.id = id;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.resuelto = resuelto;
        this.precio = precio;
        this.proovedor = proovedor;
    }

    //Logic
    public void update() {
        OrdenDeCompraDAO.update(this);
    }

    public void save(){
        OrdenDeCompraDAO.save(this);
    }

    public void resolver(float precioTotal){
        this.setResuelto(Boolean.TRUE);
        this.setPrecio(precioTotal);
        this.update();
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
