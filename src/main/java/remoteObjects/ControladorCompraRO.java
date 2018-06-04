package remoteObjects;

import controlador.ControladorCompra;
import dtos.OrdenDeCompraDTO;
import dtos.ProveedorDTO;
import interfaces.SistemaCompra;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

public class ControladorCompraRO extends UnicastRemoteObject implements SistemaCompra {

    public ControladorCompraRO() throws RemoteException {
        super();
    }

    @Override
    public Integer crearOrdenDeCompra(OrdenDeCompraDTO ordenDeCompraDTO) throws RemoteException {
        return ControladorCompra.getInstance().crearOrdenDeCompra(ordenDeCompraDTO);
    }

    @Override
    public List<ProveedorDTO> obtenerUltimos3Proveedores(Integer idProducto) throws RemoteException {
        return ControladorCompra.getInstance().obtenerUltimos3Proveedores(idProducto);
    }

    @Override
    public void cerrarOrdenDeCompra(Integer ocId, Date fechaVencimiento) throws RemoteException {
        ControladorCompra.getInstance().cerrarOrdenDeCompra(ocId,fechaVencimiento);
    }
}
