package model;
import java.util.*;

public class ItemFactura {

	private int IdItem;
	private Articulo Articulo;
	private int Cantidad;
	
	public ItemFactura(Articulo articulo, int cantidad) {
		super();
		
		Articulo = articulo;
		Cantidad = cantidad;
	}

	public int getIdItem() {
		return IdItem;
	}

	public void setIdItem(int idItem) {
		IdItem = idItem;
	}

	public Articulo getArticulo() {
		return Articulo;
	}

	public void setArticulo(Articulo articulo) {
		Articulo = articulo;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	

}