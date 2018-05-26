package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Articulo")
public class ArticuloEntity {
    @Id
    private Integer codigo;

    private String descripcion;
    private String presentacion;
    private int tamanio;
    private int unidad;
    private int cantReposicion;
    private float precio;

    @OneToMany
    @JoinColumn(name="articuloId")
    private List<LoteEntity> lotes;

    @OneToMany
    @JoinColumn(name="articuloId")
    private List<MovimientoBasicoEntity> movimientos;

    public ArticuloEntity(){
    }

    public ArticuloEntity(Integer codigo, String descripcion, String presentacion, int tamanio, int unidad, int cantReposicion, float precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.tamanio = tamanio;
        this.unidad = unidad;
        this.cantReposicion = cantReposicion;
        this.precio = precio;
    }

    public ArticuloEntity(Integer codigo, String descripcion, String presentacion, int tamanio, int unidad, int cantReposicion, float precio, List<LoteEntity> lotes, List<MovimientoBasicoEntity> movimientos) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.tamanio = tamanio;
        this.unidad = unidad;
        this.cantReposicion = cantReposicion;
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

    public int getCantReposicion() {
        return cantReposicion;
    }

    public void setCantReposicion(int cantReposicion) {
        this.cantReposicion = cantReposicion;
    }

    public List<LoteEntity> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteEntity> lotes) {
        this.lotes = lotes;
    }

    public List<MovimientoBasicoEntity> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoBasicoEntity> movimientos) {
        this.movimientos = movimientos;
    }
}
