package model;

import dao.OrdenDeCompraDAO;
import dao.OrdenDePedidoDAO;
import dao.PedidoDAO;
import dtos.OrdenDeCompraDTO;
import model.enums.TipoMovimiento;

import java.util.Date;
import java.util.List;

public class OrdenDeCompra {

    private Date fechaEmision;
    private Integer id;
    private Articulo articulo;
    private Integer cantidad;
    private boolean resuelto;
    private float precio;
    private Proveedor proovedor;

    public OrdenDeCompra(Articulo articulo, Proveedor proveedor) {
        this.articulo = articulo;
        this.cantidad = articulo.getCantReposicion();
        this.resuelto = Boolean.FALSE;
        this.proovedor = proveedor;
        this.fechaEmision = new Date();
    }

    public OrdenDeCompra(Date fechaEmision, Articulo articulo, Integer cantidad, boolean resuelto, Proveedor proveedor) {
        this.fechaEmision = fechaEmision;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.resuelto = resuelto;
        this.proovedor = proveedor;
    }

    public OrdenDeCompra(Integer id, Date fechaEmision, Articulo articulo, Integer cantidad, boolean resuelto, float precio, Proveedor proovedor) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.resuelto = resuelto;
        this.precio = precio;
        this.proovedor = proovedor;
    }

    //Logic
    public void update() {
        OrdenDeCompraDAO.update(this);
    }

    public void save(){
        OrdenDeCompraDAO.save(this);
    }

    public void resolver(float precioTotal){
        this.setResuelto(Boolean.TRUE);
        this.setPrecio(precioTotal);
        this.update();
    }

    public void cerrar(float precioTotal){
        this.resolver(precioTotal);
        generarMovimientoCompra();

        List<OrdenDePedido> ordenes = OrdenDePedidoDAO.obtenerOrdenesDePedidoParaOC(this.getId());
        ordenes.stream().forEach(ordenDePedido -> {
            ReservaArticulo reservaArticulo = new ReservaArticulo(
                    ordenDePedido.getCantidad(),
                    ordenDePedido.getIdPedido(),
                    Boolean.TRUE
            );
            reservaArticulo.save(ordenDePedido.getArticulo().getCodigo());
            ordenDePedido.delete();

            if(OrdenDePedidoDAO.obtenerOrdenesDePedidoParaPedido(ordenDePedido.getIdPedido()).isEmpty()){
                Pedido pedido = PedidoDAO.getById(ordenDePedido.getIdPedido());
                pedido.aprobarCambiandoEstado();
            }
        });
    }

    public OrdenDeCompraDTO toDto(){
        return new OrdenDeCompraDTO(
                this.getId(),
                this.getFechaEmision(),
                this.getArticulo().toDto(),
                this.getCantidad(),
                this.isResuelto(),
                this.getProovedor().toDto(),
                this.getPrecio()
        );
    }

    public void asignarOrdenesPedidoAbiertas(){
        List<OrdenDePedido> ordenDePedidos = OrdenDePedidoDAO.obtenerOrdenesDePedidoParaOC(this.getId());

        if(!ordenDePedidos.isEmpty()){
            this.asignarOrdenesDePedido(this.getCantidad());
        }else{
            Integer totalPedido = ordenDePedidos.stream().mapToInt(OrdenDePedido::getCantidad).sum();
            if(this.getCantidad() - totalPedido > 0){
                Integer restante = this.getCantidad() - totalPedido;
                this.asignarOrdenesDePedido(restante);
            }
        }
    }

    private void asignarOrdenesDePedido(int cantidad){
        List<OrdenDePedido> ordenes = OrdenDePedidoDAO.obtenerOrdenesDePedidoSinOc(this.getArticulo().getCodigo());
        if(!ordenes.isEmpty()){
            int indice = 0;
            while(cantidad > 0 && indice < ordenes.size()){
                OrdenDePedido ordenDePedido = ordenes.get(indice);
                cantidad = cantidad - ordenDePedido.getCantidad();
                if(cantidad >= 0){
                    ordenDePedido.asignarOC(this.getId());
                }
                indice++;
            }
        }
    }

    private void generarMovimientoCompra() {
        MovimientoBasico movimientoCompra = new MovimientoBasico(
                new Date(),
                this.getCantidad(),
                TipoMovimiento.COMPRA,
                "resuelto"
        );

        movimientoCompra.save(this.getArticulo());
    }

    //Getter & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public Proveedor getProovedor() {
        return proovedor;
    }

    public void setProovedor(Proveedor proovedor) {
        this.proovedor = proovedor;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}
