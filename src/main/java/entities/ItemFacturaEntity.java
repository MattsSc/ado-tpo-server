package entities;

import javax.persistence.*;

@Entity
@Table(name="ItemFactura")
public class ItemFacturaEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="proveedorId")
    private ProveedorEntity proveedor;

    @ManyToOne
    @JoinColumn(name="articuloId")
    private ArticuloEntity articulo;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name="facturaId")
    private FacturaEntity factura;


    public ItemFacturaEntity(){}

    public ItemFacturaEntity(ProveedorEntity proveedor, ArticuloEntity articulo, Integer cantidad) {
        this.proveedor = proveedor;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public ItemFacturaEntity(ProveedorEntity proveedor, ArticuloEntity articulo, Integer cantidad, FacturaEntity factura) {
        this.proveedor = proveedor;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.factura = factura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }
}
