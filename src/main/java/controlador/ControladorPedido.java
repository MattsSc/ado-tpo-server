package controlador;

import dao.OrdenDePedidoDAO;
import dao.PedidoDAO;
import dtos.*;
import interfaces.SistemaPedido;
import model.*;
import model.enums.EstadoPedido;
import exceptions.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControladorPedido implements SistemaPedido {

    private static ControladorPedido ourInstance = new ControladorPedido();

    public static ControladorPedido getInstance() {
        return ourInstance;
    }

    private ControladorPedido() {}

    @Override
    public Integer crearPedido(PedidoDTO pedidoDTO) throws RemoteException {
        Pedido pedido = new Pedido(
                dtoToCliente(pedidoDTO.getCliente()),
                EstadoPedido.RECIBIDO.name(),
                pedidoDTO.getDireccionEntrega(),
                pedidoDTO.getItems().stream().map(this::dtoToItemPedido).collect(Collectors.toList())
        );
        pedido.save();
        return pedido.getId();
    }

    @Override
    public PedidoDTO obtenerPedido(Integer id) throws PedidoNotFoundException {
        Pedido pedido = PedidoDAO.getById(id);
        if (pedido == null){
            throw new PedidoNotFoundException (id);
        }
        return PedidoDAO.getById(id).toDto();
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorCliente(Integer idCliente) throws RemoteException {
        return PedidoDAO.getByClienteId(idCliente).stream().map(Pedido::toDto).collect(Collectors.toList());
    }

    @Override
    public void aprobarPedido(Integer id, String aclaracion) throws RemoteException {
        PedidoDAO.getById(id).aprobar(aclaracion);
    }

    @Override
    public List<ItemAProcesarDTO> despacharPedido(Integer id, String tipoFactura) throws RemoteException {
        List<ItemAProcesarDTO> finalResult = new ArrayList<>();

        Pedido pedido = PedidoDAO.getById(id);
        Map<ItemPedido, List<ItemAProcesar>> result =pedido.despachar(tipoFactura);

        //Mapea a DTO
        result.forEach((itemPedido,itemsAProcesar) ->{
            itemsAProcesar.forEach(it -> finalResult.add(it.toDto(itemPedido)));
        });

        return finalResult;
    }

    @Override
    public void rechazarPedido(Integer id, String aclaracion) throws RemoteException {
        PedidoDAO.getById(id).rechazar(aclaracion);
    }

    @Override
    public void completarPedido(Integer id, Date fechaEntrega) throws RemoteException {
        PedidoDAO.getById(id).completar(fechaEntrega);
    }

    @Override
    public List<PedidoDTO> listarPedidos() throws RemoteException {
        return PedidoDAO.getAll().stream().map(Pedido::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PedidoDTO> listarPedidos(String estado) throws RemoteException {
        return PedidoDAO.getAllByEstado(estado).stream().map(Pedido::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrdenDePedidoDTO> listarOrdenesDePedido() throws RemoteException {
        return OrdenDePedidoDAO.obtenerOrdenesDePedido().stream().map(OrdenDePedido::toDto).collect(Collectors.toList());
    }

    //Private Methods

    private Cliente dtoToCliente(ClienteDTO cliente){
        return new Cliente(
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDomicilio(),
                cliente.getCuit(),
                cliente.getRazonSocial(),
                cliente.getLimiteCredito(),
                cliente.getMontoDisponible()
        );
    }

    private ItemPedido dtoToItemPedido(ItemPedidoDTO item){
        return new ItemPedido(
                item.getCantidad(),
                dtoToArticulo(item.getArticulo())
        );
    }

    private Articulo dtoToArticulo(ArticuloDTO articulo){
        return new Articulo(
                articulo.getCodigo(),
                articulo.getDescripcion(),
                articulo.getPresentacion(),
                articulo.getTamanio(),
                articulo.getUnidad(),
                articulo.getPrecio(),
                articulo.getCantReposicion()
        );
    }


}
