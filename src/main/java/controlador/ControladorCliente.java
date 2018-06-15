package controlador;

import dao.ClienteDAO;
import dao.MovCCDAO;
import dtos.ClienteDTO;
import dtos.MovimientoCCDTO;
import interfaces.SistemaCliente;
import model.Cliente;
import model.MovimientoCC;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorCliente implements SistemaCliente{

    private static ControladorCliente INSTANCE = new ControladorCliente();

    private ControladorCliente(){}

    public static ControladorCliente getInstance() {
        return INSTANCE;
    }

    public void crearCliente(ClienteDTO cliente) {
        Cliente cli = new Cliente(
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDomicilio(),
                cliente.getCuit(),
                cliente.getRazonSocial(),
                cliente.getLimiteCredito(),
                cliente.getMontoDisponible()
        );
        ClienteDAO.save(cli);
    }

    public void modificarCliente(Integer dni, String nombre, String apellido, String domicilio, String cuit, String razonSocial, float limiteCredito, float montoDisponible) {

    }

    public void borrarCliente(Integer dni) {

    }

    public void agregarMovimiento(Integer dni, MovimientoCCDTO movimientoCCDTO) {
        MovCCDAO.save(ClienteDAO.getById(dni), dtoToModel(movimientoCCDTO));
    }

    public List<ClienteDTO> listarClientes() {
        return ClienteDAO.getClientes().stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public ClienteDTO obtenerCliente(Integer dni) {
        return modelToDto(ClienteDAO.getById(dni));
    }

    @Override
    public List<MovimientoCCDTO> obtenerMovDeCliente(Integer dni) throws RemoteException {
        return ClienteDAO.getMovimientosDeCliente(dni).stream().map(MovimientoCC::toDto).collect(Collectors.toList());
    }

    @Override
    public void registrarPago(Integer dni, float cantidad) throws RemoteException {
        Cliente cliente = ClienteDAO.getById(dni);
        cliente.pagar(cantidad);
    }

    /***************** PRIVATE METHODS **********************/

    private ClienteDTO modelToDto(Cliente cliente){
       ClienteDTO clienteDTO = cliente.toDto();
        return clienteDTO;
    }

    private MovimientoCC dtoToModel(MovimientoCCDTO dto){
        return new MovimientoCC(dto.getFecha(), dto.getImporte(), dto.getTipo());
    }
}
