package remoteObjects;

import controlador.ControladorCompra;
import dtos.OrdenDeCompraDTO;
import interfaces.SistemaCompra;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ControladorCompraRO extends UnicastRemoteObject implements SistemaCompra {

    public ControladorCompraRO() throws RemoteException {
        super();
    }

    @Override
    public Integer crearOrdenDeCompra(OrdenDeCompraDTO ordenDeCompraDTO) throws RemoteException {
        return ControladorCompra.getInstance().crearOrdenDeCompra(ordenDeCompraDTO);
    }

    @Override
    public void cerrarOrdenDeCompra(Integer ocId, Date fechaVencimiento) throws RemoteException {
        ControladorCompra.getInstance().cerrarOrdenDeCompra(ocId,fechaVencimiento);
    }
}
