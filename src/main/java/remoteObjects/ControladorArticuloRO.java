package remoteObjects;

import controlador.ControladorArticulo;
import dtos.ArticuloDTO;
import interfaces.SistemaArticulo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ControladorArticuloRO extends UnicastRemoteObject implements SistemaArticulo {


    public ControladorArticuloRO() throws RemoteException {
        super();
    }

    @Override
    public void crearArticulo(ArticuloDTO articuloDTO) throws RemoteException {
        ControladorArticulo.getInstance().crearArticulo(articuloDTO);
    }

    @Override
    public ArticuloDTO obtenerArticulo(Integer id) throws RemoteException {
        return ControladorArticulo.getInstance().obtenerArticulo(id);
    }

    @Override
    public List<ArticuloDTO> obtenerArticulosFaltantes() throws RemoteException {
        return ControladorArticulo.getInstance().obtenerArticulosFaltantes();
    }

    @Override
    public List<ArticuloDTO> obtenerArticulos() throws RemoteException {
        return ControladorArticulo.getInstance().obtenerArticulos();
    }
}
