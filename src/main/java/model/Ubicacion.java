package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ubicacion {


	private Integer idUbicacion;
	private String calle;
	private Integer bloque;
	private Integer estanteria;
	private Integer posicion;
	private String estado;
	private Lote lote;
	private Integer cantidad;

	public Ubicacion(Integer idUbicacion, String calle, Integer bloque, Integer estanteria, Integer posicion,
			String estado, Lote lote, Integer cantidad) {
		super();
		this.idUbicacion = idUbicacion;
		this.calle = calle;
		this.bloque = bloque;
		this.estanteria = estanteria;
		this.posicion = posicion;
		this.estado = estado;
		this.lote = lote;
		this.cantidad = cantidad;
	}

	public Integer getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Integer idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getBloque() {
		return bloque;
	}

	public void setBloque(Integer bloque) {
		this.bloque = bloque;
	}

	public Integer getEstanteria() {
		return estanteria;
	}

	public void setEstanteria(Integer estanteria) {
		this.estanteria = estanteria;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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