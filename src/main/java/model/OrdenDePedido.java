package model;

import dao.OrdenDePedidoDAO;

public class OrdenDePedido {
    private Integer id;
    private Articulo articulo;
    private Integer cantidad;
    private Integer idPedido;
    private Integer idOrdenCompra;

    //Constructos
    public OrdenDePedido(Integer id, Articulo articulo, Integer cantidad, Integer idPedido) {
        this.id = id;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.idPedido = idPedido;
    }

    public OrdenDePedido(Articulo articulo, Integer cantidad, Integer idPedido) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.idPedido = idPedido;
    }


    //Logic
    public void save(){
        OrdenDePedidoDAO.save(this);
    }


    //Getter & setters
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

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }
}
