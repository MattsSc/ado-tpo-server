package controlador;

import dao.PedidoDAO;
import dtos.*;
import interfaces.SistemaPedido;
import model.*;
import model.enums.EstadoPedido;
import model.manager.DocumentosManager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControladorPedido implements SistemaPedido {

    private DocumentosManager documentosManager;

    private static ControladorPedido ourInstance = new ControladorPedido();

    public static ControladorPedido getInstance() {
        return ourInstance;
    }

    private ControladorPedido() {
        this.documentosManager = new DocumentosManager();
    }

    @Override
    public Integer crearPedido(ClienteDTO cliente, String direccionEntrega, List<ItemPedidoDTO> items) throws RemoteException {
        Pedido pedido = new Pedido(
                dtoToCliente(cliente),
                EstadoPedido.RECIBIDO.name(),
                direccionEntrega,
                items.stream().map(this::dtoToItemPedido).collect(Collectors.toList())
        );
        pedido.save();
        return pedido.getId();
    }

    @Override
    public PedidoDTO obtenerPedido(Integer id) throws RemoteException {
        return PedidoDAO.getById(id).toDto();
    }

    @Override
    public void aprobarPedido(Integer id) throws RemoteException {
        PedidoDAO.getById(id).aprobar();
    }

    @Override
    public List<ItemAProcesarDTO> despacharPedido(Integer id, String tipoFactura) throws RemoteException {
        List<ItemAProcesarDTO> finalResult = new ArrayList<>();

        Map<ItemPedido, List<ItemAProcesar>> result = PedidoDAO.getById(id).despachar();


        this.documentosManager.crearFactura(tipoFactura, id, result);
        this.documentosManager.crearRemito(id, result);

        result.forEach((itemPedido,itemsAProcesar) ->{
            itemsAProcesar.forEach(it ->{
                finalResult.add(
                        new ItemAProcesarDTO(
                                itemPedido.getArticulo().getDescripcion(),
                                it.getUbicaciones(),
                                it.getCantidad()
                        )
                );
            });
        });

        return finalResult;
    }

    @Override
    public void rechazarPedido(Integer id) throws RemoteException {
        PedidoDAO.getById(id).rechazar();
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
