package service;

import java.util.ArrayList;

import exception.CeramicaException;
import model.entity.Queima;
import model.repository.QueimaRepository;

public class QueimaService {

	private QueimaRepository repository = new QueimaRepository();

	public Queima salvar(Queima queima) throws CeramicaException {
		validarQueima(queima);
		return repository.salvar(queima);
	}

	public boolean excluir(int id) throws CeramicaException {
		if (id <= 0) {
			throw new CeramicaException("ID inválido para exclusão.");
		}
		return repository.excluir(id);
	}

	public boolean alterar(Queima queima) throws CeramicaException {
		validarQueima(queima);
		return repository.alterar(queima);
	}

	public Queima consultarPorId(int id) throws CeramicaException {
		if (id <= 0) {
			throw new CeramicaException("ID inválido para consulta.");
		}
		return repository.consultarPorId(id);
	}

	public ArrayList<Queima> consultarTodos() {
		return repository.consultarTodos();
	}

	private void validarQueima(Queima queima) throws CeramicaException {
		if (queima == null) {
			throw new CeramicaException("Queima não pode ser nula.");
		}
		if (queima.getDataQueima() == null) {
			throw new CeramicaException("Data da queima não pode ser nula.");
		}
		if (queima.getTipoQueima() == null || queima.getTipoQueima().isEmpty()) {
			throw new CeramicaException("Tipo de queima não pode ser vazio.");
		}
		if (queima.getTemperaturaAlcancada() <= 0) {
			throw new CeramicaException("Temperatura alcançada deve ser positiva.");
		}
		if (queima.getPeca() == null) {
			throw new CeramicaException("Peça associada à queima não pode ser nula.");
		}
		if (queima.getForno() == null || queima.getForno().isEmpty()) {
			throw new CeramicaException("Forno não pode ser vazio.");
		}
		if (queima.getPrecoQueima() < 0) {
			throw new CeramicaException("Preço da queima não pode ser negativo.");
		}
	}
}
