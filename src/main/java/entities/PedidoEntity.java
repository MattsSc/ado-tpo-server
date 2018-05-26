package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "Pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "dni")
    private ClienteEntity cliente;

    private Date fechaSolicitudOrden;
    private Date fechaDespacho;
    private Date fechaEntrega;
    private String estado;
    private String direccionEntrega;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedidoId")
    private List<ItemPedidoEntity> items;

    public PedidoEntity() {
    }

    public PedidoEntity(ClienteEntity cliente, Date fechaSolicitudOrden, Date fechaDespacho, Date fechaEntrega, String estado, String direccionEntrega, List<ItemPedidoEntity> items) {
        this.cliente = cliente;
        this.fechaSolicitudOrden = fechaSolicitudOrden;
        this.fechaDespacho = fechaDespacho;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.direccionEntrega = direccionEntrega;
        this.items = items;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
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

    public List<ItemPedidoEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoEntity> items) {
        this.items = items;
    }
}
