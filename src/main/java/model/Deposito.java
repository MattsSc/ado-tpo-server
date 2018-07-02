package model;

import dao.ArticuloDAO;
import dao.LoteDAO;
import dao.UbicacionDAO;

import java.util.*;

public class Deposito {

    public  List<Ubicacion> obtenerUbicacionesDeArticulo(Integer id){
        Articulo articulo = ArticuloDAO.getById(id);
        List<Ubicacion> ubicaciones = new ArrayList<>();
       for(Lote lote: articulo.getLotes()){
           ubicaciones.addAll(UbicacionDAO.getUbicacionesDeLote(lote.getId()));
       }
       return ubicaciones;
    }

    public void despacharPedido(Pedido pedido){
        Map<ItemPedido, List<ItemAProcesar>> result = new HashMap<>();
        for(ItemPedido item : pedido.getItems()){
            result.put(item,this.llenarPedido(item));
        }

    }

    public void generarMovimientoPorRotura(Integer loteId, MovimientoPorEliminacion mov){

        UbicacionDAO.getUbicacionByClave(mov.getUbicacion()).removerCantidad(mov.getCantidad());

        Articulo articulo = LoteDAO.getArticuloByLote(loteId);
        Lote lote = LoteDAO.getById(loteId);

        lote.informarRotura(mov.getCantidad());
        mov.save(articulo);
    }

    public void generarMOvimientoPorAjustePositivo(Integer loteId, MovimientoBasico mov){
        Articulo articulo = LoteDAO.getArticuloByLote(loteId);
        Lote lote = LoteDAO.getById(loteId);

        lote.informarAjustePositivo(mov.getCantidad());
        mov.save(articulo);
    }

    public void generarMOvimientoPorAjusteNegativo(Integer loteId, MovimientoBasico mov){
        Articulo articulo = LoteDAO.getArticuloByLote(loteId);
        Lote lote = LoteDAO.getById(loteId);

        lote.informarRotura(mov.getCantidad());
        mov.save(articulo);
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

    private List<ItemAProcesar> llenarPedido(ItemPedido item) {
        Integer cantidadTotal = item.getCantidad();
        int i = 0;
        List<ItemAProcesar> itemsAProcesar = new ArrayList<>();
        List<Lote> lotesARevisar = item.getArticulo().getLotes();

        while(cantidadTotal > 0){
            Lote lote = lotesARevisar.get(i);
            if(cantidadTotal - lote.getStock() >= 0){
                ItemAProcesar itemAProcesar = new ItemAProcesar(lote.getStock(),lote.getProveedor());
                for(Ubicacion ubicacion : UbicacionDAO.getUbicacionesDeLote(lote.getId())){
                    vaciarUbicacion(ubicacion);
                    itemAProcesar.addUbicacion(ubicacion.getClave());
                }
                itemsAProcesar.add(itemAProcesar);
                cantidadTotal = cantidadTotal - lote.getStock();
                lote.delete();
            }else{
                List<Ubicacion> ub = UbicacionDAO.getUbicacionesDeLote(lote.getId());
                int j = 0;
                int cantidadAReducir = lote.getStock() - cantidadTotal;
                ItemAProcesar itemAProcesar = new ItemAProcesar(cantidadTotal, lote.getProveedor());
                while(cantidadTotal > 0) {
                    Ubicacion ubicacion = ub.get(j);
                    if(cantidadTotal - ubicacion.getCantidad() >= 0){
                        cantidadTotal = cantidadTotal - ubicacion.getCantidad();
                        vaciarUbicacion(ubicacion);
                    }else{
                        ubicacion.setCantidad(ubicacion.getCantidad() - cantidadTotal);
                        ubicacion.update();
                        cantidadTotal = 0;
                    }
                    itemAProcesar.addUbicacion(ubicacion.getClave());
                    j++;
                }
                lote.setStock(cantidadAReducir);
                lote.update();
                itemsAProcesar.add(itemAProcesar);
                cantidadTotal = 0;
            }
            i++;
        }
        return itemsAProcesar;
    }

    private void vaciarUbicacion(Ubicacion ubicacion) {
        ubicacion.setOcupado(false);
        ubicacion.update();
    }

}
