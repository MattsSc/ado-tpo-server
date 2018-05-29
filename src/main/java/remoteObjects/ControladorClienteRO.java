package remoteObjects;

import controlador.ControladorCliente;
import dtos.ClienteDTO;
import dtos.MovimientoCCDto;
import interfaces.SistemaCliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ControladorClienteRO extends UnicastRemoteObject implements SistemaCliente {

    public ControladorClienteRO() throws RemoteException {
        super();
    }

    @Override
    public void crearCliente(ClienteDTO cliente) throws RemoteException{
        ControladorCliente.getInstance().crearCliente(cliente);
    }

    @Override
    public void modificarCliente(Integer dni, String nombre, String apellido, String domicilio, String cuit, String razonSocial, float limiteCredito, float montoDisponible)  throws RemoteException{

    }

    @Override
    public void borrarCliente(Integer dni)  throws RemoteException{

    }

    @Override
    public void agregarMovimiento(Integer dni, MovimientoCCDto movimientoCCDto)  throws RemoteException{
        ControladorCliente.getInstance().agregarMovimiento(dni,movimientoCCDto);
    }

    @Override
    public List<ClienteDTO> listarClientes()  throws RemoteException{
        return ControladorCliente.getInstance().listarClientes();
    }

    @Override
    public ClienteDTO obtenerCliente(Integer dni)  throws RemoteException{
        return ControladorCliente.getInstance().obtenerCliente(dni);
    }
}
