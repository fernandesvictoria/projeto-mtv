package controller;

import java.util.List;

import exception.CeramicaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Cliente;
import model.seletor.ClienteSeletor;
import service.ClienteService;

@Path("/cliente")
public class ClienteController {

	private ClienteService service = new ClienteService();

	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente salvar(Cliente novoCliente) throws CeramicaException {
		return service.salvar(novoCliente);
	}

	@DELETE
	@Path("excluir/{id}")
	public boolean excluir(@PathParam("id") int id) throws CeramicaException {
		return service.excluir(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/alterar")
	public boolean alterar(Cliente clienteAlterado) throws CeramicaException {
		return service.alterar(clienteAlterado);
	}

	@GET
	@Path("/todos")
	public List<Cliente> consultarTodasPessoas() {
		return service.consultarTodosClientes();
	}

	@GET
	@Path("consultar/{id}")
	public Cliente consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}

	@POST
	@Path("/filtrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> consultarComFiltro(ClienteSeletor seletor) {
		return service.consultarComFiltro(seletor);
	}
}