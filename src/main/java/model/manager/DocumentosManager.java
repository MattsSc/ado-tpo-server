package model.manager;

import dao.PedidoDAO;
import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DocumentosManager {

    public DocumentosManager(){}

    public void crearFactura(String tipoFactura, Integer idPedido, Map<ItemPedido, List<ItemAProcesar>> itemsAProcesar){
        Pedido pedido = PedidoDAO.getById(idPedido);
        List<ItemFactura> itemsFactura = new ArrayList<>();
        itemsAProcesar.forEach((item,aProcesar) ->{
            aProcesar.stream().forEach(itemAProcesar -> {
                ItemFactura itemFactura = new ItemFactura(
                        itemAProcesar.getProveedor(),
                        item.getArticulo(),
                        itemAProcesar.getCantidad()
                );
                itemsFactura.add(itemFactura);
            });
        });

        Factura factura = new Factura(
                new Date(),
                tipoFactura,
                pedido.getCliente(),
                itemsFactura
        );

        factura.save();
    }
}
