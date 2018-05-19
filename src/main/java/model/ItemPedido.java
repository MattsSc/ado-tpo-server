package model;

public class ItemPedido {

	private Integer id;
	private int Cantidad;
	private Lote lote;

	public ItemPedido(Integer id, int cantidad, Lote lote) {
		super();
		this.id = id;
		Cantidad = cantidad;
		this.lote = lote;
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
	 * @return the cantidad
	 */
	public int getCantidad() {
		return Cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	/**
	 * @return the lote
	 */
	public Lote getLote() {
		return lote;
	}

	/**
	 * @param lote the lote to set
	 */
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
	
	
}