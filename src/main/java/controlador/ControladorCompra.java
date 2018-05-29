package controlador;

import dao.ArticuloDAO;
import dao.ProovedorDAO;
import dtos.OrdenDeCompraDTO;
import interfaces.SistemaCompra;
import model.OrdenDeCompra;
import model.manager.CompraManager;

import java.rmi.RemoteException;
import java.util.Date;

public class ControladorCompra implements SistemaCompra {
    private static ControladorCompra ourInstance = new ControladorCompra();
    private CompraManager compraManager;

    public static ControladorCompra getInstance() {
        return ourInstance;
    }

    private ControladorCompra() {
        this.compraManager = new CompraManager();
    }

    @Override
    public Integer crearOrdenDeCompra(OrdenDeCompraDTO ordenDeCompraDTO) throws RemoteException {
        OrdenDeCompra ordenDeCompra = new OrdenDeCompra(
                ArticuloDAO.getById(ordenDeCompraDTO.getArticulo().getCodigo()),
                ordenDeCompraDTO.getCantidad(),
                ProovedorDAO.getById(ordenDeCompraDTO.getProovedor().getId())
        );
        this.compraManager.crearOrdenDeCompra(ordenDeCompra);
        return null;
    }

    @Override
    public void cerrarOrdenDeCompra(Integer ocId, Date fechaVencimiento) throws RemoteException {
        this.compraManager.cerrarOrdenDeCompra(ocId,fechaVencimiento);
    }
}
