package model;

import dao.PedidoDAO;
import dtos.PedidoDTO;
import model.enums.EstadoPedido;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Pedido {
    private Integer id;
    private Cliente cliente;
    private Date fechaSolicitudOrden;
    private Date fechaDespacho;
    private Date fechaEntrega;
    private String estado;
    private String direccionEntrega;
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

    public Pedido(Integer id, Cliente cliente, Date fechaSolicitudOrden, Date fechaDespacho, Date fechaEntrega, String estado, String direccionEntrega, List<ItemPedido> items) {
        this.id = id;
        this.cliente = cliente;
        this.fechaSolicitudOrden = fechaSolicitudOrden;
        this.fechaDespacho = fechaDespacho;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.direccionEntrega = direccionEntrega;
        this.items = items;
    }

    //LOGIC
    public void save(){
        PedidoDAO.save(this);
    }

    public void update(){
        PedidoDAO.update(this);
    }

    public void aprobarPedido(){
        this.setEstado(EstadoPedido.DESPACHABLE.name());
        this.update();
    }

    public void rechazar(String estado){
        this.setEstado(estado);
        this.update();
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

    public List<ItemPedido> getItems() {
        if(items == null) this.items = PedidoDAO.getItemsPedidos(this);
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }
}
