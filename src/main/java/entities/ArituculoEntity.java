package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Articulo")
public class ArituculoEntity {
    @Id
    private Integer codigo;

    private String descripcion;
    private String presentacion;
    private int tamanio;
    private int unidad;
    private float precio;

    @OneToMany
    @JoinColumn(name="id")
    private List<LoteEntity> lotes;

    @OneToMany
    @JoinColumn(name="id")
    private List<MovimientoEntity> movimientos;

    public ArituculoEntity(Integer codigo, String descripcion, String presentacion, int tamanio, int unidad, float precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.tamanio = tamanio;
        this.unidad = unidad;
        this.precio = precio;
    }

    public ArituculoEntity(Integer codigo, String descripcion, String presentacion, int tamanio, int unidad, float precio, List<LoteEntity> lotes, List<MovimientoEntity> movimientos) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.tamanio = tamanio;
        this.unidad = unidad;
        this.precio = precio;
        this.lotes = lotes;
        this.movimientos = movimientos;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<LoteEntity> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteEntity> lotes) {
        this.lotes = lotes;
    }

    public List<MovimientoEntity> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoEntity> movimientos) {
        this.movimientos = movimientos;
    }
}
