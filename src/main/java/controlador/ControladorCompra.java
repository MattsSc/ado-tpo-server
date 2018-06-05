package controlador;

import dao.ArticuloDAO;
import dao.OrdenDeCompraDAO;
import dao.ProovedorDAO;
import dtos.OrdenDeCompraDTO;
import dtos.ProveedorDTO;
import interfaces.SistemaCompra;
import model.OrdenDeCompra;
import model.Proveedor;
import model.manager.CompraManager;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
       ;
        return this.compraManager.crearOrdenDeCompra(ordenDeCompra);
    }

    @Override
    public List<ProveedorDTO> obtenerUltimos3Proveedores(Integer idProducto) throws RemoteException {
        return OrdenDeCompraDAO.getUltimos3Proveedores(idProducto).stream().map(Proveedor::toDto).collect(Collectors.toList());
    }

    @Override
    public void cerrarOrdenDeCompra(Integer ocId, Date fechaVencimiento) throws RemoteException {
        this.compraManager.cerrarOrdenDeCompra(ocId,fechaVencimiento);
    }
}