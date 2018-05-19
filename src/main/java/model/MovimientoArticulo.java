package modelo;

import enums.tipoMov;

public abstract class MovimientoArticulo{

	protected static int Id=0;
	protected int Cantidad;
	protected Lote Lote;
	protected tipoMov Tipo;
	
	public MovimientoArticulo(int cantidad,tipoMov tipo, Lote lote) {
		super();
		Cantidad = cantidad;
		Tipo = tipo;
		Lote = lote;
		this.Id=getId();

	}


	

public abstract void save();
	
	
	public int movId() {
		
		this.Id = this.Id + 1;
		return this.Id;
	}

	


	public static int getId() {
		return Id;
	}




	public Lote getLote() {
		return Lote;
	}




	public int getCantidad() {
		return Math.abs(Cantidad);
	}




	public String getTipo() {
		
		return this.Tipo.toString();
		
	}



	
	
	

}