package model;

import dao.UbicacionDAO;
import dtos.UbicacionDTO;
import model.enums.TipoProducto;

public class Ubicacion {

    private Integer idUbicacion;
    private String clave;
    private Boolean ocupado;
    private Lote lote;
    private Integer cantidad;

    public Ubicacion(String clave) {
        this.clave = clave;
        this.ocupado = Boolean.FALSE;
    }

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
        UbicacionDAO.save(this);
    }

    public void guardar(Lote lote, int cantidad) {
        this.setLote(lote);
        this.setCantidad(cantidad);
        this.setOcupado(Boolean.TRUE);
        this.update();
    }

    public Integer cantidadAGuardar(String tipoProducto){
        if(tipoProducto.equals(TipoProducto.BOTELLA.name()))
            return 600;
        if(tipoProducto.equals(TipoProducto.BOLSA.name()))
            return 400;
        if(tipoProducto.equals(TipoProducto.CAJA.name()))
            return 300;
        return 1000;
    }

    public void update(){
        UbicacionDAO.update(this);
    }

    public void vaciar(){
        this.cantidad = 0;
        this.lote = null;
        this.setOcupado(Boolean.FALSE);
        this.update();
    }

    public void removerCantidad(Integer cantidad){
        if(cantidad >= this.getCantidad())
            this.vaciar();
        else{
            this.setCantidad(this.getCantidad() - cantidad);
            this.update();
        }
    }

    //Getter & Setter
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

    public UbicacionDTO toDto() {
        return new UbicacionDTO(
                this.getIdUbicacion(),
                this.getClave(),
                this.isOcupado(),
                this.getLote().toDto(),
                this.getCantidad()
        );
    }
}
