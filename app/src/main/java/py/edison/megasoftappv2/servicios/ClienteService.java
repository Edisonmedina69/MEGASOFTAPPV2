package py.edison.megasoftappv2.servicios;

import py.edison.megasoftappv2.entidades.Client;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private List<Client> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    public void registrarCliente(Client cliente) {
        clientes.add(cliente);
    }

    public Client buscarClientePorId(String id) {
        for (Client cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    public List<Client> listarTodosLosClientes() {
        return clientes;
    }

    public void actualizarCliente(Client clienteActualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(clienteActualizado.getId())) {
                clientes.set(i, clienteActualizado);
                break;
            }
        }
    }
}