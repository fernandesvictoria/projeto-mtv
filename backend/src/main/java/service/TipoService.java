package service;

import java.util.ArrayList;

import exception.CeramicaException;
import model.entity.Tipo;
import model.repository.TipoRepository;

public class TipoService {
	private TipoRepository repository = new TipoRepository();

	public Tipo salvar(Tipo novoTipo) throws CeramicaException {
		return repository.salvar(novoTipo);
	}

	public boolean excluir(int id) throws CeramicaException {
		return repository.excluir(id);
	}

	public boolean alterar(Tipo pecaAlterada) throws CeramicaException {
		return repository.alterar(pecaAlterada);
	}

	public Tipo consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public ArrayList<Tipo> consultarTodasPecas() {
		return repository.consultarTodos();
	}

}
