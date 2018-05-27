package model;

public class Ubicacion {

    private Integer idUbicacion;
    private String clave;
    private String ocupado;
    private Lote lote;
    private Integer cantidad;

    public Ubicacion(String clave, String ocupado, Lote lote, Integer cantidad) {
        this.clave = clave;
        this.ocupado = ocupado;
        this.lote = lote;
        this.cantidad = cantidad;
    }

    public Ubicacion(Integer idUbicacion, String clave, String ocupado, Lote lote, Integer cantidad) {
        this.idUbicacion = idUbicacion;
        this.clave = clave;
        this.ocupado = ocupado;
        this.lote = lote;
        this.cantidad = cantidad;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getOcupado() {
        return ocupado;
    }

    public void setOcupado(String ocupado) {
        this.ocupado = ocupado;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
