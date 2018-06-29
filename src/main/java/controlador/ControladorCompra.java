package controlador;

import dao.ArticuloDAO;
import dao.OrdenDeCompraDAO;
import dao.ProovedorDAO;
import dtos.OrdenDeCompraDTO;
import dtos.ProveedorDTO;
import dtos.ProveedorPrecioDTO;
import interfaces.SistemaCompra;
import model.Deposito;
import model.OrdenDeCompra;
import model.Proveedor;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorCompra implements SistemaCompra {
    private static ControladorCompra ourInstance = new ControladorCompra();
    private Deposito deposito;

    public static ControladorCompra getInstance() {
        return ourInstance;
    }

    private ControladorCompra() {
        this.deposito = new Deposito();
    }

    @Override
    public Integer crearOrdenDeCompra(OrdenDeCompraDTO ordenDeCompraDTO) throws RemoteException {
        OrdenDeCompra ordenDeCompra = new OrdenDeCompra(
                ArticuloDAO.getById(ordenDeCompraDTO.getArticulo().getCodigo()),
                ProovedorDAO.getById(ordenDeCompraDTO.getProovedor().getId())
        );
        ordenDeCompra.save();
        ordenDeCompra.asignarOrdenesPedidoAbiertas();
        return ordenDeCompra.getId();
    }

    @Override
    public List<OrdenDeCompraDTO> obtenerTodasLasOrdenes() throws RemoteException {
        return OrdenDeCompraDAO.getAll().stream().map(OrdenDeCompra::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProveedorPrecioDTO> obtenerUltimos3Proveedores(Integer idProducto) throws RemoteException {
        return OrdenDeCompraDAO.getUltimos3Proveedores(idProducto).stream().map(oc -> new ProveedorPrecioDTO(
                oc.getProovedor().toDto(),
                oc.getPrecio()
        )).collect(Collectors.toList());
    }

    @Override
    public void asignarOrdenesDePedidoAOrdenesAbiertas() throws RemoteException {
            List<OrdenDeCompra> ordenesAbierts = OrdenDeCompraDAO.obtenerOrdenesAbiertas();
            ordenesAbierts.forEach(OrdenDeCompra::asignarOrdenesPedidoAbiertas);
    }

    @Override
    public List<ProveedorDTO> obtenerProveedores() throws RemoteException {
        return ProovedorDAO.getAll().stream().map(Proveedor::toDto).collect(Collectors.toList());
    }

    @Override
    public void cerrarOrdenDeCompra(Integer ocId, float precioTotal, Date fechaVencimiento) throws RemoteException {
        OrdenDeCompra ordenDeCompra = OrdenDeCompraDAO.getById(ocId);
        ordenDeCompra.cerrar(precioTotal);
        deposito.crearYGuardarLote( ordenDeCompra.getArticulo(), fechaVencimiento, ordenDeCompra.getProovedor());
    }
}
