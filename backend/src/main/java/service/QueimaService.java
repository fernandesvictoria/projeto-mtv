package service;

import java.util.List;

import exception.CeramicaException;
import model.entity.Queima;
import model.repository.QueimaRepository;

public class QueimaService {
	private QueimaRepository repository = new QueimaRepository();

	public Queima salvar(Queima novaQueima) throws CeramicaException {
		validarQueima(novaQueima);
		return repository.salvar(novaQueima);
	}

	public boolean excluir(int id) throws CeramicaException {
		return repository.excluir(id);
	}

	public boolean alterar(Queima queimaAlterada) throws CeramicaException {
		validarQueima(queimaAlterada);
		return repository.alterar(queimaAlterada);
	}

	public List<Queima> consultarTodos() {
		return repository.consultarTodos();
	}

	public Queima consultarPorId(int id) throws CeramicaException {
		return repository.consultarPorId(id);
	}

	private void validarQueima(Queima queima) throws CeramicaException {
		if (queima.getDataQueima() == null) {
			throw new CeramicaException("Data da queima não pode ser nula.");
		}
		if (queima.getPeca() == null || queima.getPeca().getIdPeca() == 0) {
			throw new CeramicaException("Peça associada à queima não pode ser nula.");
		}
	}
}
