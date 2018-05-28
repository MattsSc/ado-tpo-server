package entities;

import model.ItemRemito;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Remito")
public class RemitoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name="clienteId")
    private ClienteEntity cliente;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "remitoId")
    private List<ItemRemitoEntity> items;

    public RemitoEntity(Date fechaCreacion, ClienteEntity cliente, List<ItemRemitoEntity> items) {
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
        this.items = items;
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

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<ItemRemitoEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemRemitoEntity> items) {
        this.items = items;
    }
}
