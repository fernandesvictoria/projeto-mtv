package service;

import java.util.ArrayList;

import exception.CeramicaException;
import model.entity.Peca;
import model.repository.PecaRepository;

public class PecaService {
	private PecaRepository repository = new PecaRepository();
	 
	public Peca salvar(Peca novaPeca) throws CeramicaException {
		
		return repository.salvar(novaPeca);
	}
 
	public boolean excluir(int id) throws CeramicaException {
		if(repository.verificarSePossuiQueima(id)) {
			throw new CeramicaException("Peça não pode ser excluída, porque possui queima cadastrada.");
		}
		
		return repository.excluir(id);
	}
 
	public boolean alterar(Peca pecaAlterada) throws CeramicaException {
 
		return repository.alterar(pecaAlterada);
	}
 
	public Peca consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	public ArrayList<Peca> consultarTodasPecas() {
		return repository.consultarTodos();
	}
	
	
}
