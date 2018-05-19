package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Pedido {



	private Integer id;
	private Cliente Cliente;
	private Date FechaSolicitudOrden;
	private Date FechaDespacho;
	private Date FechaEntrega;
	private String Estado;
	private String DireccionEntrega;
	private List <ItemPedido> items = new ArrayList<>();

	
	public Pedido(Integer id, model.Cliente cliente, Date fechaSolicitudOrden, Date fechaDespacho, Date fechaEntrega,
			String estado, String direccionEntrega, List<ItemPedido> items) {
		super();
		this.id = id;
		Cliente = cliente;
		FechaSolicitudOrden = fechaSolicitudOrden;
		FechaDespacho = fechaDespacho;
		FechaEntrega = fechaEntrega;
		Estado = estado;
		DireccionEntrega = direccionEntrega;
		this.items = items;
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
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return Cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	/**
	 * @return the fechaSolicitudOrden
	 */
	public Date getFechaSolicitudOrden() {
		return FechaSolicitudOrden;
	}

	/**
	 * @param fechaSolicitudOrden the fechaSolicitudOrden to set
	 */
	public void setFechaSolicitudOrden(Date fechaSolicitudOrden) {
		FechaSolicitudOrden = fechaSolicitudOrden;
	}

	/**
	 * @return the fechaDespacho
	 */
	public Date getFechaDespacho() {
		return FechaDespacho;
	}

	/**
	 * @param fechaDespacho the fechaDespacho to set
	 */
	public void setFechaDespacho(Date fechaDespacho) {
		FechaDespacho = fechaDespacho;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return FechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		FechaEntrega = fechaEntrega;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return Estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		Estado = estado;
	}

	/**
	 * @return the direccionEntrega
	 */
	public String getDireccionEntrega() {
		return DireccionEntrega;
	}

	/**
	 * @param direccionEntrega the direccionEntrega to set
	 */
	public void setDireccionEntrega(String direccionEntrega) {
		DireccionEntrega = direccionEntrega;
	}

	/**
	 * @return the items
	 */
	public List<ItemPedido> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}
}