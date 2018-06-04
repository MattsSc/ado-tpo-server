package controlador;

import dao.ArticuloDAO;
import dao.OrdenDeCompraDAO;
import dao.OrdenDePedidoDAO;
import dtos.ArticuloDTO;
import interfaces.SistemaArticulo;
import model.Articulo;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorArticulo implements SistemaArticulo {

    private static ControladorArticulo INSTANCE = new ControladorArticulo();

    private ControladorArticulo(){}

    public static ControladorArticulo getInstance() {
        return INSTANCE;
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
}
