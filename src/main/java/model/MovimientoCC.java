package model;

import dao.MovCCDAO;
import dtos.MovimientoCCDto;

import java.util.Date;

public class MovimientoCC {
    private Integer id;
    private Date fecha;
    private float importe;
    private String tipo;

    public MovimientoCC(Date fecha, float importe, String tipo) {
        this.fecha = fecha;
        this.importe = importe;
        this.tipo = tipo;
    }

    public MovimientoCCDto toDto(){
        return new MovimientoCCDto(
                this.getId(),
                this.getFecha(),
                this.getImporte(),
                this.getTipo()
        );
    }

    public void save(Cliente cliente){
        MovCCDAO.save(cliente,this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
