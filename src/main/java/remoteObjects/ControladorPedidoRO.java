package remoteObjects;

import controlador.ControladorPedido;
import dtos.ItemAProcesarDTO;
import dtos.OrdenDePedidoDTO;
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
    public Integer crearPedido(PedidoDTO pedidoDTO) throws RemoteException {
        return ControladorPedido.getInstance().crearPedido(pedidoDTO);
    }

    @Override
    public PedidoDTO obtenerPedido(Integer id) throws RemoteException {
        return ControladorPedido.getInstance().obtenerPedido(id);
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorCliente(Integer idCliente) throws RemoteException {
        return ControladorPedido.getInstance().obtenerPedidosPorCliente(idCliente);
    }

    @Override
    public void aprobarPedido(Integer id, String aclaracion) throws RemoteException {
        ControladorPedido.getInstance().aprobarPedido(id,aclaracion);
    }

    @Override
    public List<ItemAProcesarDTO> despacharPedido(Integer id, String tipoFactura) throws RemoteException {
        return ControladorPedido.getInstance().despacharPedido(id,tipoFactura);
    }

    @Override
    public void rechazarPedido(Integer id, String aclaracion) throws RemoteException {
        ControladorPedido.getInstance().rechazarPedido(id, aclaracion);
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

    @Override
    public List<OrdenDePedidoDTO> listarOrdenesDePedido() throws RemoteException {
        return ControladorPedido.getInstance().listarOrdenesDePedido();
    }
}
