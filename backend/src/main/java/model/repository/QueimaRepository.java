package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Queima;
import model.entity.enums.TipoQueima;

public class QueimaRepository implements BaseRepository<Queima> {

	@Override
	public Queima salvar(Queima novaQueima) {
		String query = "INSERT INTO QUEIMA (DATA_QUEIMA, TIPO_QUEIMA, TEMPERATURA_ALCANCADA, ID_PECA, FORNO, PRECO_QUEIMA) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			stmt.setDate(1, new java.sql.Date(novaQueima.getDataQueima().getTime()));
			stmt.setString(2, novaQueima.getTipoQueima().toString());
			stmt.setInt(3, novaQueima.getTemperaturaAlcancada());
			stmt.setInt(4, novaQueima.getPeca().getIdPeca());
			stmt.setString(5, novaQueima.getForno());
			stmt.setDouble(6, novaQueima.getPrecoQueima());

			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novaQueima.setIdQueima(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar nova queima.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return novaQueima;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM QUEIMA WHERE ID_QUEIMA = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir queima.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Queima queimaEditada) {
		boolean alterou = false;
		String query = "UPDATE QUEIMA SET DATA_QUEIMA=?, TIPO_QUEIMA=?, TEMPERATURA_ALCANCADA=?, ID_PECA=?, FORNO=?, PRECO_QUEIMA=? WHERE ID_QUEIMA=?";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setDate(1, new java.sql.Date(queimaEditada.getDataQueima().getTime()));
			stmt.setString(2, queimaEditada.getTipoQueima().toString());
			stmt.setInt(3, queimaEditada.getTemperaturaAlcancada());
			stmt.setInt(4, queimaEditada.getPeca().getIdPeca());
			stmt.setString(5, queimaEditada.getForno());
			stmt.setDouble(6, queimaEditada.getPrecoQueima());
			stmt.setInt(7, queimaEditada.getIdQueima());

			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar queima.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	@Override
	public Queima consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Queima queima = null;
		ResultSet resultado = null;
		String query = "SELECT * FROM QUEIMA WHERE ID_QUEIMA = " + id;

		try {
			resultado = stmt.executeQuery(query);
			PecaRepository pecaRepository = new PecaRepository();
			if (resultado.next()) {
				queima = new Queima();
				queima.setIdQueima(resultado.getInt("ID_QUEIMA"));
				queima.setDataQueima(resultado.getDate("DATA_QUEIMA"));
				queima.setTipoQueima(TipoQueima.valueOf(resultado.getString("TIPO_QUEIMA").toUpperCase()));
				queima.setTemperaturaAlcancada(resultado.getInt("TEMPERATURA_ALCANCADA"));
				queima.setPeca(pecaRepository.consultarPorId(resultado.getInt("ID_PECA")));
				queima.setForno(resultado.getString("FORNO"));
				queima.setPrecoQueima(resultado.getDouble("PRECO_QUEIMA"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar queima com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return queima;
	}

	@Override
	public ArrayList<Queima> consultarTodos() {
		ArrayList<Queima> queimas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = "SELECT * FROM QUEIMA";

		try {
			resultado = stmt.executeQuery(query);
			PecaRepository pecaRepository = new PecaRepository();
			while (resultado.next()) {
				Queima queima = new Queima();
				queima.setIdQueima(resultado.getInt("ID_QUEIMA"));
				queima.setDataQueima(resultado.getDate("DATA_QUEIMA"));
				queima.setTipoQueima(TipoQueima.valueOf(resultado.getString("TIPO_QUEIMA").toUpperCase()));
				queima.setTemperaturaAlcancada(resultado.getInt("TEMPERATURA_ALCANCADA"));
				queima.setPeca(pecaRepository.consultarPorId(resultado.getInt("ID_PECA")));
				queima.setForno(resultado.getString("FORNO"));
				queima.setPrecoQueima(resultado.getDouble("PRECO_QUEIMA"));

				queimas.add(queima);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar todas as queimas.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return queimas;
	}
}
