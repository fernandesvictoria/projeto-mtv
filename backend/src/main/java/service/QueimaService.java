package service;

import java.util.List;
import exception.CeramicaException;
import model.entity.Queima;
import model.repository.QueimaRepository;

public class QueimaService {

	private QueimaRepository repository = new QueimaRepository();

	public Queima salvar(Queima novaQueima) throws CeramicaException {
		return repository.salvar(novaQueima);
	}

	public boolean excluir(int id) throws CeramicaException {
		return repository.excluir(id);
	}

	public boolean alterar(Queima queimaAlterada) throws CeramicaException {
		return repository.alterar(queimaAlterada);
	}

	public Queima consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Queima> consultarTodasQueimas() {
		return repository.consultarTodos();
	}
}
