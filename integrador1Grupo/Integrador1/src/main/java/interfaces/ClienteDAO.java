package interfaces;
import DTO.ClienteDTO;
import entidades.Cliente;

import java.util.List;

public interface ClienteDAO {
    void addCliente(Cliente cliente);
    List<Cliente> getAllClientes();
    List<ClienteDTO> getClientesPorFacturacion();
    void dropTable();
}
