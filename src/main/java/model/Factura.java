package model;

import dao.FacturaDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public Factura(Date fechaCreacion, String tipo, Cliente cliente) {
        this.fechaCreacion = fechaCreacion;
        this.tipo = tipo;
        this.cliente = cliente;
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

    public void asignarItems(Map<ItemPedido, List<ItemAProcesar>> itemsAProcesar){
        List<ItemFactura> itemsFactura = new ArrayList<>();

        itemsAProcesar.forEach((item,aProcesar) ->{
            aProcesar.stream().forEach(itemAProcesar -> {
                ItemFactura itemFactura = new ItemFactura(
                        itemAProcesar.getProveedor(),
                        item.getArticulo(),
                        itemAProcesar.getCantidad()
                );
                itemsFactura.add(itemFactura);
            });
        });

        this.setItems(itemsFactura);
        this.save();
    }

    //Getter Y Setter
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
