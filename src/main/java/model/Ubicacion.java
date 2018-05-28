package model;

import dao.UbicacionDAO;

public class Ubicacion {

    private Integer idUbicacion;
    private String clave;
    private Boolean ocupado;
    private Lote lote;
    private Integer cantidad;

    public Ubicacion(String clave, Boolean ocupado, Lote lote, Integer cantidad) {
        this.clave = clave;
        this.ocupado = ocupado;
        this.lote = lote;
        this.cantidad = cantidad;
    }

    public Ubicacion(Integer idUbicacion, String clave, Boolean ocupado, Lote lote, Integer cantidad) {
        this.idUbicacion = idUbicacion;
        this.clave = clave;
        this.ocupado = ocupado;
        this.lote = lote;
        this.cantidad = cantidad;
    }

    //Logic
    public void save(){
        System.out.println(this.getLote().getId());
        UbicacionDAO.save(this);
    }

    public void update(){
        UbicacionDAO.update(this);
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

    public Boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        if(ocupado.equals(Boolean.FALSE)){
            this.cantidad = 0;
            this.lote = null;
        }
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
