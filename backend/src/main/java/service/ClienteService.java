package service;
 
import java.util.ArrayList;
 
import exception.CeramicaException;
import model.entity.Cliente;
import model.repository.ClienteRepository;
 
public class ClienteService {
 
	private ClienteRepository repository = new ClienteRepository();
 
	public Cliente salvar(Cliente novoCliente) throws CeramicaException {
 
		return repository.salvar(novoCliente);
	}
 
	public boolean excluir(int id) throws CeramicaException {
 
		return repository.excluir(id);
	}
 
	public boolean alterar(Cliente clienteAlterado) throws CeramicaException {
 
		return repository.alterar(clienteAlterado);
	}
 
	public Cliente consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	public ArrayList<Cliente> consultarTodosClientes() {
		return repository.consultarTodos();
	}
 
	
}