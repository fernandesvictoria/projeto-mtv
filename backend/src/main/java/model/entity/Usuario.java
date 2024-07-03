package model.entity;

public class Usuario {

	private int idUsuario;
	private String username;
	private String password;
	private String idSessao;

	public Usuario() {
		super();
	}

	public Usuario(int idUsuario, String username, String password, String idSessão) {
		super();
		this.idUsuario = idUsuario;
		this.username = username;
		this.password = password;
		this.idSessao = idSessão;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessão) {
		this.idSessao = idSessão;
	}

}
