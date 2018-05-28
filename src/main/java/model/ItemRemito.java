package model;

public class ItemRemito {
    private Integer id;
    private Articulo articulo;
    private Integer cantidad;

    public ItemRemito(Articulo articulo, Integer cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public ItemRemito(Integer id, Articulo articulo, Integer cantidad) {
        this.id = id;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

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
}
