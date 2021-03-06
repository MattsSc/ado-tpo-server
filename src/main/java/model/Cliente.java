package model;

import dtos.ClienteDTO;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private Integer dni;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String cuit;
    private String razonSocial;
    private float limiteCredito;
    private float montoDisponible;
    private List<MovimientoCC> movimientosCC;

    public Cliente(int dni, String nombre, String apellido, String domicilio, String cuit, String razonSocial, float limiteCredito, float montoDisponible) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.limiteCredito = limiteCredito;
        this.montoDisponible = montoDisponible;
        this.movimientosCC = new ArrayList<MovimientoCC>();
    }

    public Cliente(Integer dni, String nombre, String apellido, String domicilio, String cuit, String razonSocial, float limiteCredito, float montoDisponible, List<MovimientoCC> movimientosCC) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.limiteCredito = limiteCredito;
        this.montoDisponible = montoDisponible;
        this.movimientosCC = movimientosCC;
    }

    public ClienteDTO toDto(){
        return new ClienteDTO(
                this.getDni(),
                this.getNombre(),
                this.getApellido(),
                this.getDomicilio(),
                this.getCuit(),
                this.getRazonSocial(),
                this.getLimiteCredito(),
                this.getMontoDisponible()
        );
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public float getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoDisponible(float montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    public List<MovimientoCC> getMovimientosCC() {
        return movimientosCC;
    }

    public void setMovimientosCC(List<MovimientoCC> movimientosCC) {
        this.movimientosCC = movimientosCC;
    }
}
