package model;

import dao.ArticuloDAO;
import dtos.ArticuloDTO;
import model.enums.TipoMovimiento;

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
    private int cantReposicion;
    private List<Lote> lotes;
    private List<Movimiento> movimientos;

    //Constructor
    public Articulo(Integer codigo, String descripcion, String presentacion, int tamanio, int unidad, float precio, int cantReposicion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.tamanio = tamanio;
        this.unidad = unidad;
        this.precio = precio;
        this.cantReposicion = cantReposicion;
        this.lotes = null;
        this.movimientos = null;
    }

    public Articulo(Integer codigo, String descripcion, String presentacion, int tamanio, int unidad, float precio, int cantReposicion, List<Lote> lotes, List<Movimiento> movimientos) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.tamanio = tamanio;
        this.unidad = unidad;
        this.precio = precio;
        this.cantReposicion = cantReposicion;
        this.lotes = lotes;
        this.movimientos = movimientos;
    }

    //Logic
    public void save(){
        ArticuloDAO.save(this);
    }

    public int stockRestante(){
        return getMovimientos().stream().mapToInt(mov -> esMovimientoNegativo(mov) ? -mov.getCantidad(): mov.getCantidad()).sum();
    }

    private boolean esMovimientoNegativo(Movimiento mov){
        return (mov.getTipo().equals(TipoMovimiento.ELIMINACION) || mov.getTipo().equals(TipoMovimiento.AJUSTE_NEGATIVO) || mov.getTipo().equals(TipoMovimiento.VENTA));
    }


    //Getter & Setter
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

    public List<Lote> getLotes() {
        if(lotes == null){
            lotes = ArticuloDAO.getLotes(this)
                    .stream()
                    .sorted(Comparator.comparing(Lote::getFechaVencimiento))
                    .collect(Collectors.toList());
        }
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public List<Movimiento> getMovimientos() {
        if(movimientos == null)
            movimientos = ArticuloDAO.getMovimientos(this);
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    //Transformer
    public ArticuloDTO toDto() {
        return new ArticuloDTO(
                this.getCodigo(),
                this.getDescripcion(),
                this.getPresentacion(),
                this.getTamanio(),
                this.getUnidad(),
                this.getPrecio(),
                this.getCantReposicion()
        );
    }

}
