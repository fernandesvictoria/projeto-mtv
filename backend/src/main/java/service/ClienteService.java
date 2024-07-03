package service;

import java.util.ArrayList;

import exception.CeramicaException;
import model.entity.Cliente;
import model.repository.ClienteRepository;
import model.seletor.ClienteSeletor;


public class ClienteService {

	private ClienteRepository repository = new ClienteRepository();

	public Cliente salvar(Cliente novoCliente) throws CeramicaException {
		this.validarCliente(novoCliente);
		validarCpf(novoCliente);
		return repository.salvar(novoCliente);
	}

	private void validarCpf(Cliente novoCliente) throws CeramicaException {
	    if (repository.cpfExiste(novoCliente.getCpf())) {
	        throw new CeramicaException("CPF " + novoCliente.getCpf() + " já cadastrado ");
	    }
	}

	public boolean excluir(int id) throws CeramicaException {
		if (repository.verificarSePossuiPeca(id)) {
			throw new CeramicaException("Não é possivel excluir, cliente já possui peças cadastradas! ");
		}
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

	public ArrayList<Cliente> consultarComFiltro(ClienteSeletor seletor) {
		return repository.consultarComFiltro(seletor);
	}

	private void validarCliente(Cliente cliente) throws CeramicaException {
		if (cliente == null) {
			throw new CeramicaException("Cliente não pode ser nulo.");
		}
		if (cliente.getNome() == null) {
			throw new CeramicaException("Nome cliente não pode ser nulo.");
		}
		if (cliente.getCpf() == null) {
			throw new CeramicaException("CPF não pode ser nulo.");
		}
		if (cliente.getTelefone() == null) {
			throw new CeramicaException("Telefone não pode ser nulo.");
		}

	}
}