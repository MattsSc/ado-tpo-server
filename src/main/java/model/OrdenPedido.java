package model;

public class OrdenPedido {

	private Integer id;
	private Articulo articulo;
	private Integer cantidad;
	private Pedido pedido;
	private String estado;
	
	public OrdenPedido(Integer id, Articulo articulo, Integer cantidad, Pedido pedido, String estado) {
		super();
		this.id = id;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
