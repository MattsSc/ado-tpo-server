package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CuentaCorriente")
public class MovCCEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idMovimiento;

    private Date fecha;
    private float importe;
    private String tipo;

    public MovCCEntity(){}

    public MovCCEntity(Date fecha, float importe, String tipo) {
        this.fecha = fecha;
        this.importe = importe;
        this.tipo = tipo;
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
}
