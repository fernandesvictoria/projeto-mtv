package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Queima;

public class QueimaRepository implements BaseRepository<Queima> {

	public Queima salvar(Queima queima) {
		// desenvolver lógica
		return queima;
	}

	public boolean excluir(int id) {
		// desenvolver lógica
		return true;
	}

	public boolean alterar(Queima queima) {
		// desenvolver lógica
		return true;
	}

	public Queima consultarPorId(int id) {
		String query = "SELECT * FROM QUEIMA WHERE ID_QUEIMA = ?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		ResultSet resultado = null;
		Queima queima = null;
		try {
			pstmt.setInt(1, id);
			resultado = pstmt.executeQuery();
			if (resultado.next()) {
				// desenvolver lógica
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarPorId!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return queima;
	}

	public ArrayList<Queima> consultarTodos() {
		String query = "SELECT * FROM QUEIMA";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		ResultSet resultado = null;
		ArrayList<Queima> queimas = new ArrayList<>();
		try {
			resultado = pstmt.executeQuery();
			while (resultado.next()) {
				Queima queima = new Queima();
				// desenvolver lógica
				queimas.add(queima);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarTodos!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return queimas;
	}
}
