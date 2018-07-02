package controlador;

import dao.ArticuloDAO;
import dao.OrdenDePedidoDAO;
import dtos.ArticuloDTO;
import dtos.MovimientoPorAjusteDTO;
import dtos.MovimientoPorEliminacionDTO;
import dtos.UbicacionDTO;
import interfaces.SistemaArticulo;
import model.*;
import model.enums.TipoMovimiento;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorArticulo implements SistemaArticulo {

    private Deposito deposito;

    private static ControladorArticulo INSTANCE = new ControladorArticulo();

    private ControladorArticulo(){
        this.deposito = new Deposito();
    }

    public static ControladorArticulo getInstance() {
        return INSTANCE;
    }

    @Override
    public void crearArticulo(ArticuloDTO articuloDTO) throws RemoteException {
        Articulo articulo = new Articulo(
                articuloDTO.getCodigo(),
                articuloDTO.getDescripcion(),
                articuloDTO.getPresentacion(),
                articuloDTO.getTamanio(),
                articuloDTO.getUnidad(),
                articuloDTO.getPrecio(),
                articuloDTO.getCantReposicion()
        );
        articulo.save();
    }

    @Override
    public ArticuloDTO obtenerArticulo(Integer id) throws RemoteException {
        return ArticuloDAO.getById(id).toDto();
    }

    @Override
    public List<UbicacionDTO> obtenerUbicaciones(Integer id) throws RemoteException {
        return deposito.obtenerUbicacionesDeArticulo(id).stream().map(Ubicacion::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticuloDTO> obtenerArticulosFaltantes() throws RemoteException {
        return OrdenDePedidoDAO.getArticulosFaltantes().stream().map(Articulo::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticuloDTO> obtenerArticulos() throws RemoteException {
        return ArticuloDAO.getAll().stream().map(Articulo::toDto).collect(Collectors.toList());
    }

    @Override
    public void generarMovimientoPorRotura(Integer loteId,  MovimientoPorEliminacionDTO movimientoPorEliminacionDTO) throws RemoteException {
        MovimientoPorEliminacion movimientoPorEliminacion = new MovimientoPorEliminacion(
                movimientoPorEliminacionDTO.getFecha(),
                movimientoPorEliminacionDTO.getCantidad(),
                movimientoPorEliminacionDTO.getEncargado(),
                movimientoPorEliminacionDTO.getDestino(),
                movimientoPorEliminacionDTO.getAutorizador(),
                movimientoPorEliminacionDTO.getUbicacion()
        );

        deposito.generarMovimientoPorRotura(loteId,movimientoPorEliminacion);
    }

    @Override
    public void generarMovimientoPorAjustePositivo(Integer loteId, MovimientoPorAjusteDTO movimientoPorAjusteDTO) throws RemoteException {
        MovimientoBasico movimientoBasico = new MovimientoBasico(
                movimientoPorAjusteDTO.getFecha(),
                movimientoPorAjusteDTO.getCantidad(),
                TipoMovimiento.AJUSTE_POSITIVO,
                "resuelto"
        );

        deposito.generarMOvimientoPorAjustePositivo(loteId, movimientoBasico);
    }

    @Override
    public void generarMovimientoPorAjusteNegativo(Integer loteId, MovimientoPorAjusteDTO movimientoPorAjusteDTO) throws RemoteException {
        MovimientoBasico movimientoBasico = new MovimientoBasico(
                movimientoPorAjusteDTO.getFecha(),
                movimientoPorAjusteDTO.getCantidad(),
                TipoMovimiento.AJUSTE_NEGATIVO,
                "resuelto"
        );

        deposito.generarMOvimientoPorAjusteNegativo(loteId, movimientoBasico);
    }
}
