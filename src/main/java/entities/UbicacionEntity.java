package entities;

import javax.persistence.*;

@Entity
@Table(name="Ubicacion")
public class UbicacionEntity {

    @Id
    @GeneratedValue
    private Integer idUbicacion;

    private String clave;
    private String ocupado;

    @ManyToOne
    @JoinColumn(name="loteId")
    private LoteEntity lote;

    private Integer cantidad;

    public UbicacionEntity(String clave, String ocupado, LoteEntity lote, Integer cantidad) {
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

    public LoteEntity getLote() {
        return lote;
    }

    public void setLote(LoteEntity lote) {
        this.lote = lote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
