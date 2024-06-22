package service;

import java.util.ArrayList;

import exception.CeramicaException;
import model.entity.Peca;
import model.repository.PecaRepository;
import model.seletor.PecaSeletor;

public class PecaService {
	private PecaRepository repository = new PecaRepository();

	public Peca salvar(Peca novaPeca) throws CeramicaException {
		this.validarPeca(novaPeca);
		return repository.salvar(novaPeca);
	}

	public boolean excluir(int id) throws CeramicaException {
		if (repository.verificarSePossuiQueima(id)) {
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
	
	public ArrayList<Peca> consultarComFiltros(PecaSeletor seletor) {
		return repository.consultarComFiltros(seletor);
	}

	private void validarPeca(Peca peca) throws CeramicaException {
		
		if (peca.getCliente() == null) {
			throw new CeramicaException("Selecione um cliente!");
		}
		if (peca.getTipo() == null) {
			throw new CeramicaException("Selecione um tipo!");
		}
		if (peca.getTamanho() == null) {
			throw new CeramicaException("Selecione um tamanho!");
		}
		if (peca.getEstagio() == null) {
			throw new CeramicaException("Selecione um estágio!");
		}
	}
	
	public void atualizarValorTotal(int idPeca, double soma) {
		repository.atualizarValorTotal(idPeca, soma);
	}
	
	public void atualizarEstagioPeca(int idPeca, String estagio) {
		repository.atualizarEstagioPeca(idPeca, estagio);
	}

}
