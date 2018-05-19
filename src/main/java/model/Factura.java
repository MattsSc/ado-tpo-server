package model;
import java.util.*;

public class Factura {

	
	private Integer nroFactura;
	private Date Fecha;
	private Cliente Cliente;
	private List<ItemFactura> itemsFactura = new ArrayList<ItemFactura>();
	


	public Factura(Date fecha, Cliente cliente,List<ItemFactura> itemsFactura ) {
		Fecha = fecha;
		Cliente = cliente;
	}


	public Factura(List<ItemFactura> itemFactura) {
		this.nroFactura = (Integer) null;
		this.Fecha = new Date();
		this.itemsFactura = itemFactura;
	}


	public void addItemFactura(ItemFactura itemFactura) {
		itemsFactura.add(itemFactura);
		
	}

	public List<ItemFactura> getItemsFactura() {
		return itemsFactura;
	}

	public Integer getnroFactura() {
		return nroFactura;
	}

	public void setnroFactura(int nroFactura) {
		nroFactura = nroFactura;
	}


	public Date getFecha() {
		return Fecha;
	}


	public void setFecha(Date fecha) {
		Fecha = fecha;
	}


	public Cliente getCliente() {
		return Cliente;
	}


	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}


}