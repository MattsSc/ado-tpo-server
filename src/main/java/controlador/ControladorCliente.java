package controlador;

import dao.ClienteDAO;
import dao.MovCCDAO;
import dtos.ClienteDTO;
import dtos.MovimientoCCDto;
import interfaces.SistemaCliente;
import model.Cliente;
import model.MovimientoCC;

import java.util.List;
import java.util.stream.Collectors;

public class ControladorCliente implements SistemaCliente{

    private static ControladorCliente INSTANCE = new ControladorCliente();

    private ControladorCliente(){};

    public static ControladorCliente getInstance() {
        return INSTANCE;
    }

    public void crearCliente(Integer dni, String nombre, String apellido, String domicilio, String cuit, String razonSocial, float limiteCredito, float montoDisponible) {
        ClienteDAO.save(new Cliente(dni,nombre,apellido,domicilio,cuit,razonSocial,limiteCredito,montoDisponible));
    }

    public void modificarCliente(Integer dni, String nombre, String apellido, String domicilio, String cuit, String razonSocial, float limiteCredito, float montoDisponible) {

    }

    public void borrarCliente(Integer dni) {

    }

    public void agregarMovimiento(Integer dni, MovimientoCCDto movimientoCCDto) {
        MovCCDAO.save(ClienteDAO.getCliente(dni), dtoToModel(movimientoCCDto));
    }

    public List<ClienteDTO> listarClientes() {
        return ClienteDAO.getClientes().stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public ClienteDTO obtenerCliente(Integer dni) {
        return modelToDto(ClienteDAO.getCliente(dni));
    }

    /***************** PRIVATE METHODS **********************/

    private ClienteDTO modelToDto(Cliente cliente){
        List<MovimientoCCDto> movCCDto = cliente.getMovimientosCC().stream().map(MovimientoCC::toDto).collect(Collectors.toList());
        ClienteDTO clienteDTO = cliente.toDto();
        clienteDTO.setMovimientosCC(movCCDto);

        return clienteDTO;
    }

    private MovimientoCC dtoToModel(MovimientoCCDto dto){
        return new MovimientoCC(dto.getFecha(), dto.getImporte(), dto.getTipo());
    }
}
