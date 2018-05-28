package remoteObjects;

import controlador.ControladorPedido;
import dtos.ClienteDTO;
import dtos.ItemPedidoDTO;
import dtos.PedidoDTO;
import interfaces.SistemaPedido;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

public class ControladorPedidoRO extends UnicastRemoteObject implements SistemaPedido {

    public ControladorPedidoRO() throws RemoteException {
        super();
    }

    @Override
    public void crearPedido(ClienteDTO cliente, String direccionEntrega, List<ItemPedidoDTO> items) throws RemoteException {
        ControladorPedido.getInstance().crearPedido(cliente,direccionEntrega,items);
    }

    @Override
    public PedidoDTO obtenerPedido(Integer id) throws RemoteException {
        return ControladorPedido.getInstance().obtenerPedido(id);
    }

    @Override
    public void aprobarPedido(Integer id) throws RemoteException {
        ControladorPedido.getInstance().aprobarPedido(id);
    }

    @Override
    public void despacharPedido(Integer id, String tipoFactura) throws RemoteException {
        ControladorPedido.getInstance().despacharPedido(id,tipoFactura);
    }

    @Override
    public void rechazarPedido(Integer id) throws RemoteException {
        ControladorPedido.getInstance().rechazarPedido(id);
    }

    @Override
    public void completarPedido(Integer id, Date fechaEntrega) throws RemoteException {
        ControladorPedido.getInstance().completarPedido(id, fechaEntrega);
    }

    @Override
    public List<PedidoDTO> listarPedidos() throws RemoteException {
        return ControladorPedido.getInstance().listarPedidos();
    }

    @Override
    public List<PedidoDTO> listarPedidos(String estado) throws RemoteException {
        return ControladorPedido.getInstance().listarPedidos(estado);
    }
}