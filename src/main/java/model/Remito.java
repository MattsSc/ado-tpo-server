package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Remito {

	
	private Integer IDRemito;
	private Date Fecha;
	private Cliente Cliente;
	private List<ItemRemito> itemsRemito = new ArrayList<ItemRemito>();
	

	public Remito(Date fecha, Cliente cliente, List<ItemRemito> itemsRemito) {
		super();
		
		Fecha = fecha;
		Cliente = cliente;
	}
	
	
	public Remito(List<ItemRemito> itemsRemito) {
		this.IDRemito = null;
		this.Fecha = new Date();
		this.itemsRemito = itemsRemito;
	}



	public void addItemRemito(ItemRemito itemRemito) {
		
		// TODO implement here
		
		itemsRemito.add(itemRemito);
		
		
	}

	public List<ItemRemito> getItemsRemito() {
	
		return itemsRemito;
	}



	public Integer getIDRemito() {
		return IDRemito;
	}



	public Date getFecha() {
		return Fecha;
	}

	public Cliente getCliente() {
		return Cliente;
	}


}