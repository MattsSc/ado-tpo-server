package controlador;

import dao.ClienteDAO;
import dao.PedidoDAO;
import dtos.ClienteDTO;
import dtos.ItemPedidoDTO;
import dtos.PedidoDTO;
import interfaces.SistemaPedido;
import model.Articulo;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ControladorPedido implements SistemaPedido {
    private static ControladorPedido ourInstance = new ControladorPedido();

    public static ControladorPedido getInstance() {
        return ourInstance;
    }

    private ControladorPedido() {
    }

    @Override
    public void crearPedido(ClienteDTO cliente, String direccionEntrega, List<ItemPedidoDTO> items) throws RemoteException {

    }

    @Override
    public PedidoDTO obtenerPedido(Integer id) throws RemoteException {
        return PedidoDAO.getById(id).toDto();
    }

    @Override
    public void aprobarPedido(Integer id) throws RemoteException {

    }

    @Override
    public void rechazarPedido(Integer id) throws RemoteException {

    }

    @Override
    public void actualizarPedido(PedidoDTO pedidoDTO) throws RemoteException {

    }

    @Override
    public List<PedidoDTO> listarPedidos() throws RemoteException {
        return null;
    }

    @Override
    public List<PedidoDTO> listarPedidos(String estado) throws RemoteException {
        return null;
    }
}
