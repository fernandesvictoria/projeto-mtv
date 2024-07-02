package controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entity.Usuario;
import service.AuthService;

@Path("/auth")
public class AuthController {

	private AuthService authService = new AuthService();

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Usuario loginRequest) {
		Usuario usuario = authService.autenticar(loginRequest.getUsername(), loginRequest.getPassword());

		if (usuario != null) {
			return Response.ok(usuario).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas").build();
		}
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(Usuario usuario) {
		authService.criarUsuario(usuario.getUsername(), usuario.getPassword());
		return Response.status(Response.Status.CREATED).build();
	}
}
