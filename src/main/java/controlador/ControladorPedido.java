package controlador;

import dao.PedidoDAO;
import dtos.*;
import interfaces.SistemaPedido;
import model.*;
import model.manager.DocumentosManager;
import model.manager.PedidoManager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControladorPedido implements SistemaPedido {

    private PedidoManager pedidoManager;
    private DocumentosManager documentosManager;

    private static ControladorPedido ourInstance = new ControladorPedido();

    public static ControladorPedido getInstance() {
        return ourInstance;
    }

    private ControladorPedido() {
        this.pedidoManager = new PedidoManager();
        this.documentosManager = new DocumentosManager();
    }

    @Override
    public Integer crearPedido(ClienteDTO cliente, String direccionEntrega, List<ItemPedidoDTO> items) throws RemoteException {
        return this.pedidoManager.crearPedido(
                dtoToCliente(cliente),
                direccionEntrega,
                items.stream().map(this::dtoToItemPedido).collect(Collectors.toList())
        );
    }

    @Override
    public PedidoDTO obtenerPedido(Integer id) throws RemoteException {
        return PedidoDAO.getById(id).toDto();
    }

    @Override
    public void aprobarPedido(Integer id) throws RemoteException {
        this.pedidoManager.aprobarPedido(id);
    }

    @Override
    public List<ItemAProcesarDTO> despacharPedido(Integer id, String tipoFactura) throws RemoteException {
        List<ItemAProcesarDTO> finalResult = new ArrayList<>();
        Map<ItemPedido, List<ItemAProcesar>> result = this.pedidoManager.despacharPedido(id);
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
        this.pedidoManager.rechazarPedido(id);
    }

    @Override
    public void completarPedido(Integer id, Date fechaEntrega) throws RemoteException {
        this.pedidoManager.completarPedido(id, fechaEntrega);
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
