package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Usuario;
import model.entity.dto.UsuarioDTO;
import utils.StringUtils;

public class UsuarioRepository implements BaseRepository<Usuario> {

	@Override
	public Usuario salvar(Usuario novoUsuario) {
		String query = "INSERT INTO USUARIO (USERNAME, PASSWORD, ID_SESSAO) VALUES (?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			stmt.setString(1, novoUsuario.getUsername());
			stmt.setString(2, StringUtils.cifrar(novoUsuario.getPassword()));
			stmt.setString(3, novoUsuario.getIdSessao());

			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novoUsuario.setIdUsuario(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar novo usuário.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return novoUsuario;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM USUARIO WHERE ID_USUARIO = " + id;

		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir usuário.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Usuario usuarioAlterado) {
		boolean alterou = false;
		String query = "UPDATE USUARIO SET USERNAME=?, PASSWORD=?, ID_SESSAO=? WHERE ID_USUARIO=?";

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, query);

		try {
			stmt.setString(1, usuarioAlterado.getUsername());
			stmt.setString(2, StringUtils.cifrar(usuarioAlterado.getPassword()));
			stmt.setString(3, usuarioAlterado.getIdSessao());
			stmt.setInt(4, usuarioAlterado.getIdUsuario());

			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar usuário.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	@Override
	public Usuario consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Usuario usuario = null;
		ResultSet resultado = null;
		String query = "SELECT * FROM USUARIO WHERE ID_USUARIO = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(resultado.getInt("ID_USUARIO"));
				usuario.setUsername(resultado.getString("USERNAME"));
				usuario.setPassword(resultado.getString("PASSWORD"));
				usuario.setIdSessao(resultado.getString("ID_SESSAO"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar usuário com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}

	@Override
	public ArrayList<Usuario> consultarTodos() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = "SELECT * FROM USUARIO";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(resultado.getInt("ID_USUARIO"));
				usuario.setUsername(resultado.getString("USERNAME"));
				usuario.setPassword(resultado.getString("PASSWORD"));
				usuario.setIdSessao(resultado.getString("ID_SESSAO"));

				usuarios.add(usuario);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar todos os usuários.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuarios;
	}

	public Usuario consultarPorLogin(Usuario usuario) {
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = null;
		Usuario usuarioEncontrado = null;
		ResultSet resultado = null;
		String query = "SELECT * FROM USUARIO WHERE USERNAME = ?";

		try {
			stmt = Banco.getPreparedStatement(conn, query);
			stmt.setString(1, usuario.getUsername());
			resultado = stmt.executeQuery();

			if (resultado.next()) {
				usuarioEncontrado = new Usuario();
				usuarioEncontrado.setIdUsuario(resultado.getInt("ID_USUARIO"));
				usuarioEncontrado.setUsername(resultado.getString("USERNAME"));
				usuarioEncontrado.setPassword(resultado.getString("PASSWORD"));
				usuarioEncontrado.setIdSessao(resultado.getString("ID_SESSAO"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar usuário por login.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuarioEncontrado;
	}

	public Usuario consultarPorLoginSenha(UsuarioDTO usuarioDTO) {
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = null;
		Usuario usuarioEncontrado = null;
		ResultSet resultado = null;
		String query = "SELECT * FROM USUARIO WHERE USERNAME = ? AND PASSWORD = ?";

		try {
			stmt = Banco.getPreparedStatement(conn, query);
			stmt.setString(1, usuarioDTO.getLogin());
			stmt.setString(2, usuarioDTO.getSenha());
			resultado = stmt.executeQuery();

			if (resultado.next()) {
				usuarioEncontrado = new Usuario();
				usuarioEncontrado.setIdUsuario(resultado.getInt("ID_USUARIO"));
				usuarioEncontrado.setUsername(resultado.getString("USERNAME"));
				usuarioEncontrado.setPassword(resultado.getString("PASSWORD"));
				usuarioEncontrado.setIdSessao(resultado.getString("ID_SESSAO"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar usuário por login e senha.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuarioEncontrado;
	}

	public Usuario consultarPorIdSessao(String idSessao) {
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = null;
		Usuario usuarioEncontrado = null;
		ResultSet resultado = null;
		String query = "SELECT * FROM USUARIO WHERE ID_SESSAO = ?";

		try {
			stmt = Banco.getPreparedStatement(conn, query);
			stmt.setString(1, idSessao);
			resultado = stmt.executeQuery();

			if (resultado.next()) {
				usuarioEncontrado = new Usuario();
				usuarioEncontrado.setIdUsuario(resultado.getInt("ID_USUARIO"));
				usuarioEncontrado.setUsername(resultado.getString("USERNAME"));
				usuarioEncontrado.setPassword(resultado.getString("PASSWORD"));
				usuarioEncontrado.setIdSessao(resultado.getString("ID_SESSAO"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar usuário por ID de sessão.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuarioEncontrado;
	}

	public boolean alterarIdSessao(Usuario usuario) {
		boolean alterou = false;
		String query = "UPDATE USUARIO SET ID_SESSAO=? WHERE ID_USUARIO=?";

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, query);

		try {
			stmt.setString(1, usuario.getIdSessao());
			stmt.setInt(2, usuario.getIdUsuario());

			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar ID de sessão do usuário.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}
}
