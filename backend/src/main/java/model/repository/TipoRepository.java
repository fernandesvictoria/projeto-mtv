package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.entity.Tipo;

public class TipoRepository implements BaseRepository<Tipo> {

	@Override
	public Tipo salvar(Tipo novaEntidade) {
		String query = "INSERT INTO tipo (NOME) VALUES (?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaEntidade.getNome());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				novaEntidade.setIdTipo(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método salvar tipo!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novaEntidade;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM tipo WHERE id_tipo= " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir tipo");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Tipo entidade) {
		boolean alterou = false;

		String query = " UPDATE tipo " + " SET   nome = ?  " + " WHERE id_tipo= ?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {

			pstmt.setString(1, entidade.getNome());
			pstmt.setInt(2, entidade.getIdTipo());
			
			alterou = pstmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar tipo!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}
	
	public boolean verificarSePossuiPeca(int idTipo) {
	    Connection conn = Banco.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet resultado = null;
	    boolean temPeca = false;

	    try {
	        String query = "SELECT COUNT(*) FROM peca WHERE id_tipo = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, idTipo);
	        resultado = stmt.executeQuery();

	        if (resultado.next()) {
	            int quantidadePeca = resultado.getInt(1);
	            if (quantidadePeca > 0) {
	                temPeca = true;
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao verificar se a Peca possui uma tipo válido.");
	        e.printStackTrace();
	    } finally {
	        Banco.closeResultSet(resultado);
	        Banco.closeStatement(stmt);
	        Banco.closeConnection(conn);
	    }

	    return temPeca;
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
		ArrayList<Tipo> tipos = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = " SELECT * FROM tipo";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Tipo tipo = new Tipo();

				tipo.setIdTipo(resultado.getInt("id_tipo"));
				tipo.setNome(resultado.getString("NOME"));
				

				tipos.add(tipo);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todos os tipos!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return tipos;
	}
	
	public boolean existeTipo(String nome) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultado = null;
        boolean existe = false;

        try {
            String query = "SELECT COUNT(*) FROM tipo WHERE nome = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nome);
            resultado = pstmt.executeQuery();

            if (resultado.next() && resultado.getInt(1) > 0) {
                existe = true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao verificar se o tipo com nome já existe!");
            erro.printStackTrace();
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closeStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return existe;
    }
}
