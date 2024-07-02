package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import model.entity.Usuario;
import model.repository.Banco;

public class AuthService {

	public Usuario autenticar(String username, String password) {
		Usuario usuario = null;

		try (Connection conn = Banco.getConnection()) {
			String sql = "SELECT * FROM USUARIO WHERE USERNAME = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String storedPasswordHash = rs.getString("PASSWORD");

				if (BCrypt.checkpw(password, storedPasswordHash)) {
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
					usuario.setUsername(rs.getString("USERNAME"));
					usuario.setPassword(storedPasswordHash);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}

	public void criarUsuario(String username, String password) {
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

		try (Connection conn = Banco.getConnection()) {
			String sql = "INSERT INTO USUARIO (USERNAME, PASSWORD) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, hashedPassword);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
