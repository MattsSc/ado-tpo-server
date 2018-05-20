package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CuentaCorriente")
public class MovCCEntity {
    @Id
    @GeneratedValue
    private int idMovimiento;

    private Date fecha;
    private float importe;
    private String tipo;

    @ManyToOne
    @JoinColumn(name="idCliente")
    private ClienteEntity cliente;

    public MovCCEntity(){}

    public MovCCEntity(Date fecha, float importe, String tipo, ClienteEntity cliente) {
        this.fecha = fecha;
        this.importe = importe;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
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
}
