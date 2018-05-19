package model;
import java.util.*;

public class ItemRemito {

	private int Item;
	private Articulo Articulo;
	private int Cantidad;
	
	public ItemRemito(Articulo articulo, int cantidad) {
		super();
		//
		Articulo = articulo;
		Cantidad = cantidad;
	}

	public int getID() {
		return Item;
	}

	public void setItem(int item) {
		Item = item;
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