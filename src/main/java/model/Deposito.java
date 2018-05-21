package model;
import java.util.ArrayList;

public class Deposito {
	

	private ArrayList<Ubicacion> Ubicaciones = new ArrayList<Ubicacion>();  // NO AGREGAR AL DIAGRAMA DE CLASES
	
	public Deposito(ArrayList<Ubicacion> ubicaciones) {
		super();
		Ubicaciones = ubicaciones;
	}

	/**
	 * @return the ubicaciones
	 */
	public ArrayList<Ubicacion> getUbicaciones() {
		return Ubicaciones;
	}

	/**
	 * @param ubicaciones the ubicaciones to set
	 */
	public void setUbicaciones(ArrayList<Ubicacion> ubicaciones) {
		Ubicaciones = ubicaciones;
	}

	
}