package service;

import java.util.List;

import exception.CeramicaException;
import model.entity.Peca;
import model.entity.Queima;
import model.repository.QueimaRepository;
import model.seletor.QueimaSeletor;

public class QueimaService {
	private QueimaRepository repository = new QueimaRepository();

	public Queima salvar(Queima novaQueima) throws CeramicaException {
		if (novaQueima.getPeca() == null || novaQueima.getPeca().getIdPeca() <= 0) {
			throw new CeramicaException("Peça inválida para a queima.");
		}

		// RN52: Adicionar valor da queima ao valor total da peça
		
		novaQueima = repository.salvar(novaQueima);
		double soma = repository.calcularValorPeca(novaQueima.getPeca().getIdPeca());
		
		PecaService pecaService = new PecaService();
		pecaService.atualizarValorTotal(novaQueima.getPeca().getIdPeca(), soma);

		return novaQueima;
	}

	public boolean excluir(int id) throws CeramicaException {
		Queima queima = repository.consultarPorId(id);
		if (queima == null) {
			throw new CeramicaException("Queima não encontrada.");
		}

		// RN52: Remover valor da queima do valor total da peça
		Peca peca = queima.getPeca();
		peca.setValorTotal(peca.getValorTotal() - queima.getPrecoQueima());

		return repository.excluir(id);
	}

	public boolean alterar(Queima queimaAlterada) throws CeramicaException {
		if (queimaAlterada.getPeca() == null || queimaAlterada.getPeca().getIdPeca() <= 0) {
			throw new CeramicaException("Peça inválida para a queima.");
		}

		return repository.alterar(queimaAlterada);
	}

	public Queima consultarPorId(int id) throws CeramicaException {
		return repository.consultarPorId(id);
	}

	public List<Queima> consultarTodos() {
		return repository.consultarTodos();
	}

	public List<Queima> consultarComFiltros(QueimaSeletor seletor) {
		return repository.consultarComFiltros(seletor);
	}
}
