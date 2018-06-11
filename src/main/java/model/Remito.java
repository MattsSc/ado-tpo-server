package model;

import dao.RemitoDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Remito {

    private Integer id;
    private Date fechaCreacion;
    private Cliente cliente;
    private List<ItemRemito> items;

    public Remito(Date fechaCreacion, Cliente cliente) {
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
    }

    public Remito(Date fechaCreacion, Cliente cliente, List<ItemRemito> items) {
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
        this.items = items;
    }

    public Remito(Integer id, Date fechaCreacion, Cliente cliente, List<ItemRemito> items) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
        this.items = items;
    }

    //Logic
    public void save(){
        RemitoDAO.save(this);
    }

    public void asignarItems(Map<ItemPedido, List<ItemAProcesar>> itemsAProcesar){
        List<ItemRemito> itemsRemito = new ArrayList<>();
        itemsAProcesar.forEach((item,aProcesar) ->{
            aProcesar.stream().forEach(itemAProcesar -> {
                ItemRemito itemRemito = new ItemRemito(
                        item.getArticulo(),
                        itemAProcesar.getCantidad()
                );
                itemsRemito.add(itemRemito);
            });
        });

        this.setItems(itemsRemito);
        this.save();
    }

    //Getter & Setter
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemRemito> getItems() {
        return items;
    }

    public void setItems(List<ItemRemito> items) {
        this.items = items;
    }
}
