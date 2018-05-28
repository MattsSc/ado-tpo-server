package model;

public class ItemFactura {
    private Integer id;
    private Proveedor proveedor;
    private Articulo articulo;
    private Integer cantidad;

    public ItemFactura(Proveedor proveedor, Articulo articulo, Integer cantidad) {
        this.proveedor = proveedor;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public ItemFactura(Integer id, Proveedor proveedor, Articulo articulo, Integer cantidad) {
        this.id = id;
        this.proveedor = proveedor;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
}
