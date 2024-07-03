package controller;

import exception.CeramicaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Usuario;
import model.entity.dto.UsuarioDTO;
import service.LoginService;

@Path("/auth")
public class LoginController {

	private LoginService authService = new LoginService();

	@POST
	@Path("/autenticar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario autenticar(UsuarioDTO loginRequest) throws CeramicaException {
		return authService.autenticar(loginRequest);
	}

}
