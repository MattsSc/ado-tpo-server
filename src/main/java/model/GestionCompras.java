package model;

import java.util.ArrayList;
import java.util.List;

public class GestionCompras {


	private List<OrdenCompra> ordenesCompra = new ArrayList<OrdenCompra>();
	
	public GestionCompras(List<OrdenCompra> ordenesCompra) {
		super();
		this.ordenesCompra = ordenesCompra;
	}

	public List<OrdenCompra> getOrdenesCompra() {
		return ordenesCompra;
	}

	public void setOrdenesCompra(List<OrdenCompra> ordenesCompra) {
		this.ordenesCompra = ordenesCompra;
	}
	
	
}
