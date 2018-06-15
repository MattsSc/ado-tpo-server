package model;

import dao.OrdenDeCompraDAO;
import dao.PedidoDAO;
import dao.ReservaArticuloDAO;
import dao.UbicacionDAO;
import dtos.PedidoDTO;
import model.enums.EstadoPedido;
import model.enums.TipoMovimiento;

import java.util.*;
import java.util.stream.Collectors;

public class Pedido {
    private Integer id;
    private Cliente cliente;
    private Date fechaSolicitudOrden;
    private Date fechaDespacho;
    private Date fechaEntrega;
    private String estado;
    private String direccionEntrega;
    private String aclaracion;
    private List<ItemPedido> items;

    //CONVERTER
    public PedidoDTO toDto(){
        return new PedidoDTO(
                this.getId(),
                this.getCliente().toDto(),
                this.getFechaSolicitudOrden(),
                this.getFechaDespacho(),
                this.getFechaEntrega(),
                this.getEstado(),
                this.getDireccionEntrega(),
                this.getAclaracion(),
                this.getItems().stream().map(ItemPedido::toDto).collect(Collectors.toList())
        );
    }

    //CONSTRUCTORS
    public Pedido(Cliente cliente, String estado, String direccionEntrega, List<ItemPedido> items) {
        this.cliente = cliente;
        this.fechaSolicitudOrden = new Date();
        this.estado = estado;
        this.direccionEntrega = direccionEntrega;
        this.items = items;
    }

    public Pedido(Integer id, Date fechaSolicitudOrden, Date fechaDespacho, Date fechaEntrega, String estado, String direccionEntrega) {
        this.id = id;
        this.fechaSolicitudOrden = fechaSolicitudOrden;
        this.fechaDespacho = fechaDespacho;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.direccionEntrega = direccionEntrega;
    }

    public Pedido(Integer id, Cliente cliente, Date fechaSolicitudOrden, Date fechaDespacho, Date fechaEntrega, String estado, String direccionEntrega, String aclaracion, List<ItemPedido> items) {
        this.id = id;
        this.cliente = cliente;
        this.fechaSolicitudOrden = fechaSolicitudOrden;
        this.fechaDespacho = fechaDespacho;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.direccionEntrega = direccionEntrega;
        this.aclaracion = aclaracion;
        this.items = items;
    }

    //LOGIC
    public void save(){
        PedidoDAO.save(this);
    }

    public void update(){
        PedidoDAO.update(this);
    }

    public void rechazar(String aclaracion){
        this.setEstado(EstadoPedido.RECHAZADO.name());
        this.setAclaracion(aclaracion);
        this.update();
    }

    public void aprobar(String aclaracion){
        Boolean estaCompleto = Boolean.TRUE;

        for(ItemPedido item: this.getItems()){
            Articulo articulo = item.getArticulo();

            Integer totalStock = articulo.stockRestante() - getCantidadReservadaDeArticulo(articulo);
            OrdenDeCompra ultimaOC = OrdenDeCompraDAO.getUltimaOrdenDeCompra(articulo.getCodigo());

            if(totalStock > 0 && ( ultimaOC == null || ultimaOC.isResuelto() ) ){
                if(totalStock >= item.getCantidad()){
                    generarReservaDeStock(this, item.getCantidad(), articulo, true);
                }else{
                    generarReservaDeStock(this, totalStock, articulo, false);
                    generarOrdenDePedido(this, item.getArticulo(), (item.getCantidad() - totalStock));
                    estaCompleto = Boolean.FALSE;
                }
            }else{
                generarOrdenDePedido(this, item.getArticulo(), item.getCantidad());
                estaCompleto = Boolean.FALSE;
            }
        }

        this.setAclaracion(aclaracion);

        if(estaCompleto)
            this.aprobarCambiandoEstado();
        else
            this.faltaStockCambiandoEstado();
    }

    public void aprobarCambiandoEstado(){
        this.setEstado(EstadoPedido.DESPACHABLE.name());
        this.update();
    }

    public void faltaStockCambiandoEstado(){
        this.setEstado(EstadoPedido.FALTA_STOCK.name());
        this.update();
    }

    public void completar(Date fechaEntrega){
        this.setFechaEntrega(fechaEntrega);
        this.setEstado(EstadoPedido.COMPLETO.name());
        this.update();
    }

    public Map<ItemPedido, List<ItemAProcesar>> despachar(String tipoFactura){
        this.setFechaDespacho(new Date());
        this.setEstado(EstadoPedido.DESPACHO.name());
        Map<ItemPedido, List<ItemAProcesar>> result = new HashMap<>();
        for(ItemPedido item : this.getItems()){
            result.put(item,this.llenarPedido(item));
        }
        this.update();

        //Descontar monto al cliente
        float total = 0;
        for(ItemPedido item : this.getItems()){
            total = total + (item.getCantidad() * item.getArticulo().getPrecio());
        }
        this.getCliente().descontarMontoDisponible(total);

        //Borrar Reserva
        List<ReservaArticulo> reservas = ReservaArticuloDAO.getByPedidoId(this.getId());
        reservas.forEach(ReservaArticulo::delete);

        //Crear Remito
        Remito remito = new Remito(new Date(),this.getCliente());
        remito.asignarItems(result);

        //Crear Factura
        Factura factura = new Factura(new Date(), tipoFactura, this.getCliente());
        factura.asignarItems(this,result);

        return result;
    }

    //PRIVATE

    private void generarReservaDeStock(Pedido pedido, Integer cantidad, Articulo articulo, boolean esCompleta) {
        ReservaArticulo reservaArticulo = new ReservaArticulo(cantidad, pedido.getId(), esCompleta);
        reservaArticulo.save(articulo.getCodigo());
    }

    private void generarOrdenDePedido(Pedido pedido, Articulo articulo, Integer cantidad) {
        OrdenDePedido ordenDePedido = new OrdenDePedido(articulo,cantidad,pedido.getId());
        ordenDePedido.save();
    }

    private Integer getCantidadReservadaDeArticulo(Articulo articulo) {
        List<ReservaArticulo> result = ReservaArticuloDAO.getByArticuloId(articulo.getCodigo());
        return result.stream().mapToInt(ReservaArticulo::getCantidad).sum();
    }

    private List<ItemAProcesar> llenarPedido(ItemPedido item) {
        Integer cantidadTotal = item.getCantidad();
        int i = 0;
        List<ItemAProcesar> itemsAProcesar = new ArrayList<>();
        List<Lote> lotesARevisar = item.getArticulo().getLotes();

        while(cantidadTotal > 0){
            Lote lote = lotesARevisar.get(i);
            if(cantidadTotal - lote.getStock() >= 0){
                ItemAProcesar itemAProcesar = new ItemAProcesar(lote.getStock(),lote.getProveedor());
                for(Ubicacion ubicacion : UbicacionDAO.getUbicacionesDeLote(lote.getId())){
                    vaciarUbicacion(ubicacion);
                    itemAProcesar.addUbicacion(ubicacion.getClave());
                }
                itemsAProcesar.add(itemAProcesar);
                cantidadTotal = cantidadTotal - lote.getStock();
                lote.delete();
            }else{
                List<Ubicacion> ub = UbicacionDAO.getUbicacionesDeLote(lote.getId());
                int j = 0;
                int cantidadAReducir = lote.getStock() - cantidadTotal;
                ItemAProcesar itemAProcesar = new ItemAProcesar(cantidadTotal, lote.getProveedor());
                while(cantidadTotal > 0) {
                    Ubicacion ubicacion = ub.get(j);
                    if(cantidadTotal - ubicacion.getCantidad() >= 0){
                        cantidadTotal = cantidadTotal - ubicacion.getCantidad();
                        vaciarUbicacion(ubicacion);
                    }else{
                        ubicacion.setCantidad(ubicacion.getCantidad() - cantidadTotal);
                        ubicacion.update();
                        cantidadTotal = 0;
                    }
                    itemAProcesar.addUbicacion(ubicacion.getClave());
                    j++;
                }
                lote.setStock(cantidadAReducir);
                lote.update();
                itemsAProcesar.add(itemAProcesar);
                cantidadTotal = 0;
            }
            i++;
        }
        generarMovimientoDeVenta(item);
        return itemsAProcesar;
    }

    private void generarMovimientoDeVenta(ItemPedido item) {
        MovimientoBasico movimientoBasico = new MovimientoBasico(
                new Date(),
                item.getCantidad(),
                TipoMovimiento.VENTA,
                "Resuelto"
        );
        movimientoBasico.save(item.getArticulo());
    }

    private void vaciarUbicacion(Ubicacion ubicacion) {
        ubicacion.setOcupado(false);
        ubicacion.update();
    }


    //GETTERS Y SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        if(cliente == null) cliente = PedidoDAO.getCliente(this);
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaSolicitudOrden() {
        return fechaSolicitudOrden;
    }

    public void setFechaSolicitudOrden(Date fechaSolicitudOrden) {
        this.fechaSolicitudOrden = fechaSolicitudOrden;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getAclaracion() {
        return aclaracion;
    }

    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }

    public List<ItemPedido> getItems() {
        if(items == null) this.items = PedidoDAO.getItemsPedidos(this);
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }
}
