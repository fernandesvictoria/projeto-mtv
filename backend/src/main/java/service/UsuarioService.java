package service;

import java.util.List;

import exception.CeramicaException;
import model.entity.Usuario;
import model.entity.dto.UsuarioDTO;
import model.repository.UsuarioRepository;

public class UsuarioService {
	private UsuarioRepository repository = new UsuarioRepository();

	public Usuario salvar(Usuario novoUsuario) throws CeramicaException {
		if (novoUsuario.getUsername() == null || novoUsuario.getUsername().isEmpty()) {
			throw new CeramicaException("Nome de usuário inválido.");
		}

		if (novoUsuario.getPassword() == null || novoUsuario.getPassword().isEmpty()) {
			throw new CeramicaException("Senha inválida.");
		}

		return repository.salvar(novoUsuario);
	}

	public boolean excluir(int id) throws CeramicaException {
		Usuario usuario = repository.consultarPorId(id);
		if (usuario == null) {
			throw new CeramicaException("Usuário não encontrado.");
		}

		return repository.excluir(id);
	}

	public boolean alterar(Usuario usuarioAlterado) throws CeramicaException {
		if (usuarioAlterado.getUsername() == null || usuarioAlterado.getUsername().isEmpty()) {
			throw new CeramicaException("Nome de usuário inválido.");
		}

		if (usuarioAlterado.getPassword() == null || usuarioAlterado.getPassword().isEmpty()) {
			throw new CeramicaException("Senha inválida.");
		}

		return repository.alterar(usuarioAlterado);
	}

	public Usuario consultarPorId(int id) throws CeramicaException {
		Usuario usuario = repository.consultarPorId(id);
		if (usuario == null) {
			throw new CeramicaException("Usuário não encontrado.");
		}

		return usuario;
	}

	public List<Usuario> consultarTodos() {
		return repository.consultarTodos();
	}

	public Usuario consultarPorLogin(Usuario usuario) throws CeramicaException {
		Usuario usuarioEncontrado = repository.consultarPorLogin(usuario);
		if (usuarioEncontrado == null) {
			throw new CeramicaException("Usuário não encontrado com o login: " + usuario.getUsername());
		}

		return usuarioEncontrado;
	}

	public Usuario consultarPorLoginESenha(UsuarioDTO usuarioDTO) throws CeramicaException {
		Usuario usuarioEncontrado = repository.consultarPorLoginSenha(usuarioDTO);
		if (usuarioEncontrado == null) {
			throw new CeramicaException("Usuário não encontrado com o login e senha fornecidos.");
		}

		return usuarioEncontrado;
	}

	public Usuario consultarPorIdSessao(String idSessao) throws CeramicaException {
		Usuario usuarioEncontrado = repository.consultarPorIdSessao(idSessao);
		if (usuarioEncontrado == null) {
			throw new CeramicaException("Usuário não encontrado com o ID de sessão: " + idSessao);
		}

		return usuarioEncontrado;
	}

	public boolean alterarIdSessao(Usuario usuario) throws CeramicaException {
		if (usuario.getIdSessao() == null || usuario.getIdSessao().isEmpty()) {
			throw new CeramicaException("Novo ID de sessão inválido.");
		}

		return repository.alterarIdSessao(usuario);
	}
}
