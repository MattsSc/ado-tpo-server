package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Factura")
public class FacturaEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private Date fechaCreacion;
    private String tipo;

    @ManyToOne
    @JoinColumn(name="clienteId")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name="pedidoId")
    private PedidoEntity pedido;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "facturaId")
    private List<ItemFacturaEntity> items;

    public FacturaEntity() { }

    public FacturaEntity(Date fechaCreacion, String tipo, ClienteEntity cliente, PedidoEntity pedido, List<ItemFacturaEntity> items) {
        this.fechaCreacion = fechaCreacion;
        this.tipo = tipo;
        this.cliente = cliente;
        this.pedido = pedido;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<ItemFacturaEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemFacturaEntity> items) {
        this.items = items;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
}
