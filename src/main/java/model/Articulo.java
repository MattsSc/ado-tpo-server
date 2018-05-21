package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Articulo {
    private Integer codigo;
    private String descripcion;
    private String presentacion;
    private int tamanio;
    private int unidad;
    private float precio;
    private List<Lote> lotes;
    private List<Movimiento> movimientos;

    public Articulo(Integer codigo, String descripcion, String presentacion, int tamanio, int unidad, float precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.tamanio = tamanio;
        this.unidad = unidad;
        this.precio = precio;
        this.lotes = new ArrayList<>();
        this.movimientos = new ArrayList<>();
    }

    public Articulo(Integer codigo, String descripcion, String presentacion, int tamanio, int unidad, float precio, List<Lote> lotes, List<Movimiento> movimientos) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.tamanio = tamanio;
        this.unidad = unidad;
        this.precio = precio;
        this.lotes = lotes;
        this.movimientos = movimientos;
    }

    public boolean hayStock(int cantidad){
        return (lotes.stream().mapToInt(Lote::getStock).sum() > cantidad);
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

    public List<Lote> getLotes() {
        return lotes.stream().sorted(Comparator.comparing(Lote::getFechaVencimiento)).collect(Collectors.toList());
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
