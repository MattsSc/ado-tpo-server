package controlador;

import dao.ArticuloDAO;
import dao.LoteDAO;
import dao.OrdenDeCompraDAO;
import dao.OrdenDePedidoDAO;
import dtos.ArticuloDTO;
import dtos.MovimientoPorEliminacionDTO;
import interfaces.SistemaArticulo;
import model.Articulo;
import model.Deposito;

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
    public List<ArticuloDTO> obtenerArticulosFaltantes() throws RemoteException {
        return OrdenDePedidoDAO.getArticulosFaltantes().stream().map(Articulo::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticuloDTO> obtenerArticulos() throws RemoteException {
        return ArticuloDAO.getAll().stream().map(Articulo::toDto).collect(Collectors.toList());
    }

    @Override
    public void generarMovimientoPorRotura(Integer loteId,  MovimientoPorEliminacionDTO movimientoPorEliminacionDTO) throws RemoteException {
        deposito.generarMovimientoPorRotura(loteId,movimientoPorEliminacionDTO);
    }
}
