package model.manager;

import dao.PedidoDAO;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class PedidoManager {
    private List<Pedido> pedidos;
    private ReservaManager reservaManager;

    public PedidoManager(){
        this.pedidos = new ArrayList<>();
        this.reservaManager = new ReservaManager();
    }

    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
    }

    public void aprobarPedido(Integer codigoPedido){
        Pedido pedido = PedidoDAO.getById(codigoPedido);
        pedido.getItems().stream().forEach(item ->{
            Articulo articulo = item.getArticulo();
            if(articulo.stockRestante() >= item.getCantidad()){
                generarReservaDeStock(pedido, item, articulo, true);
            }else{
                OrdenDePedido ordenDePedido = new OrdenDePedido(item.getArticulo(),item.getCantidad(),pedido.getId());
                ordenDePedido.save();
            }
        });
    }

    private void generarReservaDeStock(Pedido pedido, ItemPedido item, Articulo articulo, boolean esCompleta) {
        ReservaArticulo reservaArticulo = new ReservaArticulo(item.getCantidad(), pedido.getId(), esCompleta);
        reservaManager.agregarReserva(articulo.getCodigo(), reservaArticulo);
    }
}
