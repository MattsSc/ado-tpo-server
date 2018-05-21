package model;

public class OrdenCompra {

	private Integer id;
	private Articulo articulo;
	private Integer cantidad;
	private OrdenPedido ordenPedido;
	private String estado;
	private Proovedor proovedor;
	private Float cortoOrdenDeCompra;
	
	public OrdenCompra(Integer id, Articulo articulo, Integer cantidad, OrdenPedido ordenDePedido, String estado,
			Proovedor proovedor, Float cortoOrdenDeCompra) {
		super();
		this.id = id;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.ordenPedido = ordenDePedido;
		this.estado = estado;
		this.proovedor = proovedor;
		this.cortoOrdenDeCompra = cortoOrdenDeCompra;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}

	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the ordenDePedido
	 */
	public OrdenPedido getOrdenPedido() {
		return ordenPedido;
	}

	/**
	 * @param ordenDePedido the ordenDePedido to set
	 */
	public void setOrdenDePedido(OrdenPedido ordenDePedido) {
		this.ordenPedido = ordenDePedido;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the proovedor
	 */
	public Proovedor getProovedor() {
		return proovedor;
	}

	/**
	 * @param proovedor the proovedor to set
	 */
	public void setProovedor(Proovedor proovedor) {
		this.proovedor = proovedor;
	}

	/**
	 * @return the cortoOrdenDeCompra
	 */
	public Float getCortoOrdenDeCompra() {
		return cortoOrdenDeCompra;
	}

	/**
	 * @param cortoOrdenDeCompra the cortoOrdenDeCompra to set
	 */
	public void setCortoOrdenDeCompra(Float cortoOrdenDeCompra) {
		this.cortoOrdenDeCompra = cortoOrdenDeCompra;
	}
	
	

}