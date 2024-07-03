package service;

import java.util.UUID;

import exception.CeramicaException;
import model.entity.Usuario;
import model.entity.dto.UsuarioDTO;
import model.repository.UsuarioRepository;

public class LoginService {

	private UsuarioRepository repository = new UsuarioRepository();

	public Usuario autenticar(UsuarioDTO usuarioDTO) throws CeramicaException {
		if (usuarioDTO == null || (usuarioDTO.getLogin() == null || usuarioDTO.getLogin().trim().isEmpty())) {
			throw new CeramicaException("Usuário não informado");
		}
		if (usuarioDTO.getSenha() == null || usuarioDTO.getSenha().trim().isEmpty()) {
			throw new CeramicaException("Senha não informada");
		}
		Usuario usuarioAutenticado = repository.consultarPorLoginSenha(usuarioDTO);
		if (usuarioAutenticado == null) {
			throw new CeramicaException("Login ou senha inválidos, tente novamente");
		}

		String idSessao = UUID.randomUUID().toString();
		usuarioAutenticado.setIdSessao(idSessao);
		repository.alterarIdSessao(usuarioAutenticado);
		return usuarioAutenticado;
	}

	public boolean chaveValida(String idSessao) {
		Usuario usuario = this.repository.consultarPorIdSessao(idSessao);

		return usuario != null && usuario.getIdSessao() != null && usuario.getIdSessao().equals(idSessao);
	}
}