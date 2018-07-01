package remoteObjects;

import controlador.ControladorCompra;
import dtos.OrdenDeCompraDTO;
import dtos.ProveedorDTO;
import dtos.ProveedorPrecioDTO;
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
    public List<ProveedorPrecioDTO> obtenerUltimos3Proveedores(Integer idProducto) throws RemoteException {
        return ControladorCompra.getInstance().obtenerUltimos3Proveedores(idProducto);
    }

    @Override
    public List<OrdenDeCompraDTO> obtenerTodasLasOrdenes() throws RemoteException {
        return ControladorCompra.getInstance().obtenerTodasLasOrdenes();
    }

    @Override
    public OrdenDeCompraDTO obtenerOc(Integer id) throws RemoteException {
        return ControladorCompra.getInstance().obtenerOc(id);
    }

    @Override
    public void asignarOrdenesDePedidoAOrdenesAbiertas() throws RemoteException {
        ControladorCompra.getInstance().asignarOrdenesDePedidoAOrdenesAbiertas();
    }

    @Override
    public void cerrarOrdenDeCompra(Integer ocId, float precioTotal, Date fechaVencimiento) throws RemoteException {
        ControladorCompra.getInstance().cerrarOrdenDeCompra(ocId, precioTotal, fechaVencimiento);
    }

    @Override
    public List<ProveedorDTO> obtenerProveedores() throws RemoteException {
        return ControladorCompra.getInstance().obtenerProveedores();
    }
}
