package model;

import dao.FacturaDAO;

import java.util.Date;
import java.util.List;

public class Factura {

    private Integer id;
    private Date fechaCreacion;
    private String tipo;
    private Cliente cliente;
    private List<ItemFactura> items;

    public Factura(Date fechaCreacion, String tipo, Cliente cliente, List<ItemFactura> items) {
        this.fechaCreacion = fechaCreacion;
        this.tipo = tipo;
        this.cliente = cliente;
        this.items = items;
    }

    public Factura(Integer id, Date fechaCreacion, String tipo, Cliente cliente, List<ItemFactura> items) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.tipo = tipo;
        this.cliente = cliente;
        this.items = items;
    }

    //Logic
    public void save(){
        FacturaDAO.save(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }
}
