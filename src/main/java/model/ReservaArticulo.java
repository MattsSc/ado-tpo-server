package model;

public class ReservaArticulo {

    private Integer id;
    private int cantidad;
    private Integer idPedido;
    private boolean esCompleta;

    public ReservaArticulo(Integer id, int cantidad, Integer idPedido,boolean esCompleta) {
        this.id = id;
        this.cantidad = cantidad;
        this.idPedido = idPedido;
        this.esCompleta = esCompleta;
    }

    public ReservaArticulo(int cantidad, Integer idPedido, boolean esCompleta) {
        this.cantidad = cantidad;
        this.idPedido = idPedido;
        this.esCompleta = esCompleta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
}