package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Peca;
import model.entity.enums.EstagioPeca;
import model.entity.enums.Tamanho;

public class PecaRepository implements BaseRepository<Peca> {

	@Override
	public Peca salvar(Peca novaPeca) {
		String query = " INSERT INTO PECA " + " (ID_CLIENTE, ID_TIPO, TAMANHO, DESCRICAO, ESTAGIO, VALOR_TOTAL) "
				+ " VALUES(?, ?, ?, ?, ?, ?) ";

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			stmt.setInt(1, novaPeca.getCliente().getIdCliente());
			stmt.setInt(2, novaPeca.getTipo().getIdTipo());
			stmt.setString(3, novaPeca.getTamanho().toString());
			stmt.setString(4, novaPeca.getDescricao());
			stmt.setString(5, novaPeca.getEstagio().toString());
			stmt.setDouble(6, novaPeca.getValorTotal());

			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novaPeca.setIdPeca(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar nova peça.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return novaPeca;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM PECA WHERE ID_PECA = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir peça.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Peca pecaEditada) {
		boolean alterou = false;
		String query = " UPDATE peca " 
				+ " SET ID_CLIENTE=?, ID_TIPO=?, TAMANHO=?, DESCRICAO=?, ESTAGIO=?, VALOR_TOTAL=?"
				+ " WHERE ID_PECA=? ";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setInt(1, pecaEditada.getCliente().getIdCliente());
			stmt.setInt(2, pecaEditada.getTipo().getIdTipo());
			stmt.setString(3, pecaEditada.getTamanho().toString());
			stmt.setString(4, pecaEditada.getDescricao());
			stmt.setString(5, pecaEditada.getEstagio().toString());
			stmt.setDouble(6, pecaEditada.getValorTotal());

			stmt.setInt(7, pecaEditada.getIdPeca());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar peça.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	@Override
	public Peca consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Peca peca = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM PECA WHERE ID_PECA = " + id;

		try {
			resultado = stmt.executeQuery(query);
			ClienteRepository clienteRepository = new ClienteRepository();
			TipoRepository tipoRepository = new TipoRepository();
			if (resultado.next()) {
				peca = new Peca();
				peca.setIdPeca(Integer.parseInt(resultado.getString("ID_PECA")));
				peca.setCliente(clienteRepository.consultarPorId(resultado.getInt("ID_CLIENTE")));
				peca.setTipo(tipoRepository.consultarPorId(resultado.getInt("ID_TIPO")));
				peca.setTamanho(Tamanho.valueOf(resultado.getString("TAMANHO").toUpperCase()));
				peca.setDescricao(resultado.getString("DESCRICAO"));
				peca.setEstagio(EstagioPeca.valueOf(resultado.getString("ESTAGIO").toUpperCase()));
				peca.setValorTotal(resultado.getDouble("VALOR_TOTAL"));

			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar peça com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return peca;
	}

	@Override
	public ArrayList<Peca> consultarTodos() {
		ArrayList<Peca> pecas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = " SELECT * FROM PECA";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Peca peca = new Peca();
				peca.setIdPeca(resultado.getInt("ID_PECA"));

				ClienteRepository clienteRepository = new ClienteRepository();
				peca.setCliente(clienteRepository.consultarPorId(resultado.getInt("ID_CLIENTE")));
				
				TipoRepository tipoRepository = new TipoRepository();
				peca.setTipo(tipoRepository.consultarPorId(resultado.getInt("ID_TIPO")));
				
				peca.setTamanho(Tamanho.valueOf(resultado.getString("TAMANHO").toUpperCase()));
				peca.setDescricao(resultado.getString("DESCRICAO"));
				peca.setEstagio(EstagioPeca.valueOf(resultado.getString("ESTAGIO").toUpperCase()));
				peca.setValorTotal(resultado.getDouble("VALOR_TOTAL"));
				pecas.add(peca);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar todas as peças.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pecas;
	}
	
	public boolean verificarSePossuiQueima(int idPeca) {
	    Connection conn = Banco.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet resultado = null;
	    boolean temQueima = false;
 
	    try {
	        String query = "SELECT COUNT(*) FROM QUEIMA WHERE ID_PECA = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, idPeca);
	        resultado = stmt.executeQuery();
 
	        if (resultado.next()) {
	            int qtdeQueima = resultado.getInt(1);
	            if (qtdeQueima > 0) {
	            	temQueima = true;
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao verificar se há queima cadastrada na peça.");
	        e.printStackTrace();
	    } finally {
	        Banco.closeResultSet(resultado);
	        Banco.closeStatement(stmt);
	        Banco.closeConnection(conn);
	    }
 
	    return temQueima;
	}
	
}
