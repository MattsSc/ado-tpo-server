package model;

import dtos.ItemAProcesarDTO;

import java.util.ArrayList;
import java.util.List;

//NO AGREGAR AL DIAGRAMA DE CLASES
public class ItemAProcesar {
    private Integer cantidad;
    private Proveedor proveedor;
    private List<String> ubicaciones;

    public ItemAProcesar(Integer cantidad, Proveedor proveedor) {
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.ubicaciones = new ArrayList<>();
    }

    public void addUbicacion(String ubicacion){
        this.ubicaciones.add(ubicacion);
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public List<String> getUbicaciones() {
        return ubicaciones;
    }

    public ItemAProcesarDTO toDto(ItemPedido itemPedido){
        return new ItemAProcesarDTO(
                itemPedido.getArticulo().getDescripcion(),
                this.getUbicaciones(),
                this.getCantidad()
        );
    }
}
