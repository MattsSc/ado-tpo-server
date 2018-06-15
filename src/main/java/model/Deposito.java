package model;

import dao.LoteDAO;
import dao.UbicacionDAO;
import dtos.MovimientoPorEliminacionDTO;

import java.util.Date;
import java.util.List;

public class Deposito {

    public void generarMovimientoPorRotura(Integer loteId, MovimientoPorEliminacionDTO mov){
        MovimientoPorEliminacion movimientoPorEliminacion = new MovimientoPorEliminacion(
                mov.getFecha(),
                mov.getCantidad(),
                mov.getEncargado(),
                mov.getDestino(),
                mov.getAutorizador()
        );

        Articulo articulo = LoteDAO.getArticuloByLote(loteId);
        Lote lote = LoteDAO.getById(loteId);

        lote.informarRotura(mov.getCantidad());
        movimientoPorEliminacion.save(articulo);
    }

    public void crearYGuardarLote(Articulo articulo, Date fechaVenc, Proveedor proveedor){
        Lote lote = new Lote(fechaVenc, articulo.getCantReposicion(), proveedor);
        lote.save(articulo);
        this.asignarLoteAUbicacion(articulo, lote);
    }

    private void asignarLoteAUbicacion(Articulo articulo, Lote lote){
        List<Ubicacion> ubicaciones = UbicacionDAO.getUbicacionesVacias();
        int cantidad = lote.getStock();
        int indice = 0;
        while(cantidad > 0){
            Ubicacion ubicacion = ubicaciones.get(indice);
            int cantidadPosiblePorUbicacion = ubicacion.cantidadAGuardar(articulo.getPresentacion());
            if(cantidad - cantidadPosiblePorUbicacion >= 0){
                ubicacion.guardar(lote, cantidadPosiblePorUbicacion);
                cantidad = cantidad - cantidadPosiblePorUbicacion;
            }else{
                ubicacion.guardar(lote, cantidad);
                cantidad = 0;
            }
            indice++;
        }

    }
}
