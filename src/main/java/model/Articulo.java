package model;
import java.util.ArrayList;
import java.util.List;

public class Articulo {

	
	private int idArticulo;
	private String cod;
	private String desc;
	private String presentacion;
	private float tamano;
	private int unidad;
	//private ArrayList<Lote> Lotes = new ArrayList<Lote>();
	private float precio;
	private int cantRepo;
	private List<MovimientoArticulo> Movimientos = new ArrayList<MovimientoArticulo>(); 	// NO AGREGAR AL DIAGRAMA DE CLASES
	


	public Articulo(int idArticulo, String cod, String desc, String presentacion, float tamano, int unidad,
			float precio, int cantRepo) {
		super();
		
		this.idArticulo = idArticulo;
		this.cod = cod;
		this.desc = desc;
		this.presentacion = presentacion;
		this.tamano = tamano;
		this.unidad = unidad;
		this.precio = precio;
		this.cantRepo = cantRepo;
	}

	public int getIdArticulo() {
		return idArticulo;
	}


	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}


	public void setCod(String cod) {
		this.cod = cod;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPresentacion() {
		return presentacion;
	}



	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}



	public float getTamano() {
		return tamano;
	}



	public void setTamano(float tamano) {
		this.tamano = tamano;
	}



	public int getUnidad() {
		return unidad;
	}



	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}




	/*public void setLotes(ArrayList<Lote> lotes) {
		Lotes = lotes;
	}
*/


	public void addMovimientos(MovimientoArticulo movimiento) {
		Movimientos.add(movimiento);
	}



	public int calcularStock() {
		int stock=0;
		for(MovimientoArticulo mov: this.Movimientos) {
			stock+=mov.getCantidad();
		}
		return stock;
	}

	/*
	public List<Lote> getLotes() {
		return this.Lotes;
	}

*/

	public List<MovimientoArticulo> getMovimientos() {

		return this.Movimientos;

	}

	/*
	
	public ArrayList<Lote> getVencidos() {
		ArrayList<Lote> vencidos = new ArrayList<Lote>();
		
		for(Lote lote : this.Lotes) {
			if(lote.getFechaVencimiento().after(new Date())) {
				vencidos.add(lote);
			}			
		}
		return vencidos;
	}
*/
	
	
	/*
	public void addLote(Lote lote) {
		Lotes.add(lote);
		
	}
	*/
	


	public String getDesc() {
		return desc;
	}

	
	


	public String getCod() {
		return cod;
	}


	
	
	

	public float getPrecio() {
		return precio;
	}



	public void setPrecio(float precio) {
		this.precio = precio;
	}



	
	/*
	public void generarMov(int cant,  tipoMov tipo,  Lote lote,  Ubicacion ubicacion, boolean positivo) {
		this.Movimientos.add(new MovimientoInventario(cant,tipo,lote,ubicacion, positivo));

		
	}


	
	


	public void generarMov(int idOC, tipoMov tipo,  int cant, Lote lote, int idPedido) {
		this.Movimientos.add(new MovimientoCompra(cant,tipo,lote, idPedido,idOC));

	}


	public void generarMov(int cant,  tipoMov tipo,  Lote lote,  String user1,  String user2) {
		
		Movimientos.add(new MovimientoDano(cant, tipo, lote, user1, user2));
		

	}


	public void generarMov(int cant, tipoMov tipo,  Lote lote,  int idFactura) {
		this.Movimientos.add(new MovimientoVenta(cant,tipo,lote,idFactura));

		
	}*/

/*


	public int getCantRepo() {
		return cantRepo;
	}
	
	
	public ArticuloEntity obtenerArtEntity() {
		
		
		return ArticuloDao.getinstancia().toEntity(this);
		
		
	}
	
	
	
	public Articulo obtenerArtById(int idArticulo) {
		
		return ArticuloDao.getinstancia().getArticuloByCodigo(idArticulo);
		
		
	}
	
	public Articulo obtenerArtByCod(String cod) {
		
		return ArticuloDao.getinstancia().getArticuloByCodigoArt(cod);
		
	}






	public void setCantRepo(int cantRepo) {
		this.cantRepo = cantRepo;
	}






	public void setMovimientos(List<MovimientoArticulo> movimientos) {
		Movimientos = movimientos;
	}
	
	
	
	public ArticuloDTO ToDTO() {
		return new ArticuloDTO(idArticulo,cod,desc,presentacion,tamano,unidad,precio,cantRepo);
	}
	
	*/

}