package controller;

import java.util.List;

import exception.CeramicaException;
import filter.AuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import model.entity.Cliente;
import model.entity.Usuario;
import model.seletor.ClienteSeletor;
import service.ClienteService;
import service.UsuarioService;

@Path("/restrito/cliente")
public class ClienteController {

	private ClienteService service = new ClienteService();
	private UsuarioService usuario = new UsuarioService();

	@Context
	private HttpServletRequest request;

	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente salvar(Cliente novoCliente) throws CeramicaException {
		String idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		Usuario usuarioAutenticado = this.usuario.consultarPorIdSessao(idSessaoNoHeader);
		if (usuarioAutenticado == null) {
			throw new CeramicaException("Usuário não encontrado");
		}
		return service.salvar(novoCliente);
	}

	@DELETE
	@Path("excluir/{id}")
	public boolean excluir(@PathParam("id") int id) throws CeramicaException {
		String idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		Usuario usuarioAutenticado = this.usuario.consultarPorIdSessao(idSessaoNoHeader);
		if (usuarioAutenticado == null) {
			throw new CeramicaException("Usuário não encontrado");
		}
		return service.excluir(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/alterar")
	public boolean alterar(Cliente clienteAlterado) throws CeramicaException {
		String idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		Usuario usuarioAutenticado = this.usuario.consultarPorIdSessao(idSessaoNoHeader);
		if (usuarioAutenticado == null) {
			throw new CeramicaException("Usuário não encontrado");
		}
		return service.alterar(clienteAlterado);
	}

	@GET
	@Path("/todos")
	public List<Cliente> consultarTodasPessoas() throws CeramicaException {
		String idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		Usuario usuarioAutenticado = this.usuario.consultarPorIdSessao(idSessaoNoHeader);
		if (usuarioAutenticado == null) {
			throw new CeramicaException("Usuário não encontrado");
		}
		return service.consultarTodosClientes();
	}

	@GET
	@Path("consultar/{id}")
	public Cliente consultarPorId(@PathParam("id") int id) throws CeramicaException {
		String idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		Usuario usuarioAutenticado = this.usuario.consultarPorIdSessao(idSessaoNoHeader);
		if (usuarioAutenticado == null) {
			throw new CeramicaException("Usuário não encontrado");
		}
		return service.consultarPorId(id);
	}

	@POST
	@Path("/filtrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> consultarComFiltro(ClienteSeletor seletor) throws CeramicaException {
		String idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		Usuario usuarioAutenticado = this.usuario.consultarPorIdSessao(idSessaoNoHeader);
		if (usuarioAutenticado == null) {
			throw new CeramicaException("Usuário não encontrado");
		}
		return service.consultarComFiltro(seletor);
	}
}