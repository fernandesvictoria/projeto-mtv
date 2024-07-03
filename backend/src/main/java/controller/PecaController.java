package controller;

import java.util.List;

import exception.CeramicaException;
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
import model.entity.Peca;
import model.seletor.PecaSeletor;
import service.PecaService;

@Path("/restrito/peca")
public class PecaController {
	
	private PecaService service = new PecaService();

	@Context
	private HttpServletRequest request;
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Peca salvar(Peca novaPeca) throws CeramicaException {
		return service.salvar(novaPeca);
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
	public boolean alterar(Peca pecaAlterada) throws CeramicaException {
		return service.alterar(pecaAlterada);
	}

	@GET
	@Path("/todos")
	public List<Peca> consultarTodasPecas() {
		return service.consultarTodasPecas();
	}

	@GET
	@Path("/consultar/{id}")
	public Peca consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}

	@POST
	@Path("/filtrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Peca> consultarComFiltros(PecaSeletor seletor) {
		return service.consultarComFiltros(seletor);
	}

}
