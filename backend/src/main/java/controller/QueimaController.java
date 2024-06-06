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
import model.entity.Queima;
import service.QueimaService;

@Path("/queima")
public class QueimaController {
	private QueimaService service = new QueimaService();

	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Queima salvar(Queima novaQueima) throws CeramicaException {
		return service.salvar(novaQueima);
	}

	@DELETE
	@Path("/excluir/{id}")
	public boolean excluir(@PathParam("id") int id) throws CeramicaException {
		return service.excluir(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/alterar")
	public boolean alterar(Queima queimaAlterada) throws CeramicaException {
		return service.alterar(queimaAlterada);
	}

	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Queima> consultarTodasQueimas() {
		return service.consultarTodos();
	}

	@GET
	@Path("/consultar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Queima consultarPorId(@PathParam("id") int id) throws CeramicaException {
		return service.consultarPorId(id);
	}
}
