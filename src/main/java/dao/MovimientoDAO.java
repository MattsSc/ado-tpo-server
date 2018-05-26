package dao;

import dao.converters.ConverterEntityUtils;
import entities.MovimientoBasicoEntity;
import entities.MovimientoPorEliminacionEntity;
import model.*;
import utils.HibernateUtils;


public class MovimientoDAO {

    public static void save(Movimiento movimiento, Articulo articulo){
        switch (movimiento.getTipo()){
            case ELIMINACION:
                HibernateUtils.saveTransaction(toMovPorEliminacionEntity(movimiento,articulo));
                break;
            default:
                HibernateUtils.saveTransaction(toMovBasicoEntity(movimiento,articulo));
                break;
        }
    }

    private static MovimientoBasicoEntity toMovBasicoEntity(Movimiento movimiento, Articulo articulo){
        MovimientoBasico movimientoBasico = (MovimientoBasico) movimiento;
        return new MovimientoBasicoEntity(
                movimientoBasico.getFecha(),
                movimientoBasico.getCantidad(),
                movimientoBasico.getTipo().toString(),
                ConverterEntityUtils.articuloToEntity(articulo),
                movimientoBasico.getEstado());
    }

    private static MovimientoPorEliminacionEntity toMovPorEliminacionEntity(Movimiento movimiento, Articulo articulo){
        MovimientoPorEliminacion movimientoPorEliminacion = (MovimientoPorEliminacion) movimiento;
        return new MovimientoPorEliminacionEntity(
                movimientoPorEliminacion.getFecha(),
                movimientoPorEliminacion.getCantidad(),
                movimientoPorEliminacion.getTipo().toString(),
                ConverterEntityUtils.articuloToEntity(articulo),
                movimientoPorEliminacion.getEncargado(),
                movimientoPorEliminacion.getDestino(),
                movimientoPorEliminacion.getAutorizador());
    }
}
