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
        Pedido pedido = new Pedido(
                ClienteDAO.getById(cliente.getDni()),
                "EN_ESPERA",
                direccionEntrega,
                items.stream().map(ItemPedido::dtoToModel).collect(Collectors.toList())
        );

        PedidoDAO.save(pedido);
    }

    @Override
    public PedidoDTO obtenerPedido(Integer id) throws RemoteException {
        return PedidoDAO.getById(id).toDto();
    }

    @Override
    public void aprobarPedido(Integer id) throws RemoteException {
        Pedido pedido = PedidoDAO.getById(id);
        Optional<ItemPedido> optional = pedido.getItems().stream().filter(it -> !(it.getArticulo().hayStock(it.getCantidad()))).findFirst();
        if(optional.isPresent()){
            //Falta stock de optional
            PedidoDAO.updateStatus(pedido.getId(),"FALTA_STOCK");
        }else{
            //no falta stock
            PedidoDAO.updateStatus(pedido.getId(),"APROBADO");
        }
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
