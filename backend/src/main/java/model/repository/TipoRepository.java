package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Tipo;

public class TipoRepository implements BaseRepository<Tipo>{

	@Override
	public Tipo salvar(Tipo novaEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Tipo entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tipo consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Tipo tipo = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM TIPO WHERE ID_TIPO = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				tipo = new Tipo();
				tipo.setIdTipo(resultado.getInt("ID_TIPO"));
				tipo.setNome(resultado.getString("NOME"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar tipo com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return tipo;
	}

	@Override
	public ArrayList<Tipo> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
