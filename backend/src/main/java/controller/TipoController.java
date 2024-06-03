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
import model.entity.Tipo;
import service.TipoService;

@Path("/tipo")
public class TipoController {
	private TipoService service = new TipoService();
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tipo salvar(Tipo novoTipo) throws CeramicaException {
		return service.salvar(novoTipo);
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
	public boolean alterar(Tipo tipoAlterado) throws CeramicaException {
		return service.alterar(tipoAlterado);
	}

	@GET
	@Path("/todos")
	public List<Tipo> consultarTodasPecas() {
		return service.consultarTodasPecas();
	}

	@GET
	@Path("/consultar/{id}")
	public Tipo consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}
}
