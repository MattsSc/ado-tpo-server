package dao;

import entities.ArticuloEntity;
import entities.MovimientoBasicoEntity;
import entities.MovimientoPorDanioEntity;
import entities.MovimientoPorInventarioEntity;
import model.*;
import utils.HibernateUtils;

import java.util.ArrayList;

public class MovimientoDAO {

    public static void save(Movimiento movimiento, Articulo articulo){
        switch (movimiento.getTipo()){
            case VENTA:
                HibernateUtils.saveTransaction(toMovBasicoEntity(movimiento,articulo));
                break;
            case ROTURA:
                HibernateUtils.saveTransaction(toMovPorDanioEntity(movimiento,articulo));
                break;
            default:
                HibernateUtils.saveTransaction(toMovPorInventarioEntity(movimiento,articulo));
                break;
        }
    }

    private static MovimientoBasicoEntity toMovBasicoEntity(Movimiento movimiento, Articulo articulo){
        MovimientoBasico movimientoBasico = (MovimientoBasico) movimiento;
        return new MovimientoBasicoEntity(
                movimientoBasico.getFecha(),
                movimientoBasico.getCantidad(),
                movimientoBasico.getTipo().toString(),
                new ArticuloEntity(
                        articulo.getCodigo(),
                        articulo.getDescripcion(),
                        articulo.getPresentacion(),
                        articulo.getTamanio(),
                        articulo.getUnidad(),
                        articulo.getPrecio(),
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                movimientoBasico.getEstado());
    }

    private static MovimientoPorDanioEntity toMovPorDanioEntity(Movimiento movimiento, Articulo articulo){
        MovimientoPorDanio movimientoPorDanio = (MovimientoPorDanio) movimiento;
        return new MovimientoPorDanioEntity(
                movimientoPorDanio.getFecha(),
                movimientoPorDanio.getCantidad(),
                movimientoPorDanio.getTipo().toString(),
                new ArticuloEntity(
                        articulo.getCodigo(),
                        articulo.getDescripcion(),
                        articulo.getPresentacion(),
                        articulo.getTamanio(),
                        articulo.getUnidad(),
                        articulo.getPrecio(),
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                movimientoPorDanio.getEncargado(),
                movimientoPorDanio.getDestino(),
                movimientoPorDanio.getAuotorizador());
    }

    private static MovimientoPorInventarioEntity toMovPorInventarioEntity(Movimiento movimiento, Articulo articulo){
        MovimientoPorInventario movimientoPorInventario = (MovimientoPorInventario) movimiento;
        return new MovimientoPorInventarioEntity(
                movimientoPorInventario.getFecha(),
                movimientoPorInventario.getCantidad(),
                movimientoPorInventario.getTipo().toString(),
                new ArticuloEntity(
                        articulo.getCodigo(),
                        articulo.getDescripcion(),
                        articulo.getPresentacion(),
                        articulo.getTamanio(),
                        articulo.getUnidad(),
                        articulo.getPrecio(),
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                movimientoPorInventario.getDescripcion(),
                movimientoPorInventario.getEstado());
    }
}
