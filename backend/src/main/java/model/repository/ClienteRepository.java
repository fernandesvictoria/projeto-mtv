package model.repository;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
import model.entity.Cliente;
 
 
 
public class ClienteRepository {
 
	public Cliente salvar(Cliente cliente) {
 
		String query = "INSERT INTO cliente (nome, cpf, telefone) VALUES (?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3, cliente.getTelefone());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				cliente.setIdCliente(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método cadastrarCliente!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return cliente;
	}
 
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM cliente WHERE id_cliente = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir cliente");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}
 
	public boolean alterar(Cliente novoCliente) {
 
		boolean alterou = false;
 
		String query = " UPDATE cliente " + " SET   nome=?, cpf=?, telefone=? " + " WHERE id_cliente= ?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
 
			pstmt.setString(1, novoCliente.getNome());
			pstmt.setString(2, novoCliente.getCpf());
			pstmt.setString(3, novoCliente.getTelefone());
			pstmt.setInt(4, novoCliente.getIdCliente());
 
			alterou = pstmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar cliente!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}
	
	
	public Cliente consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
 
		ResultSet resultado = null;
		Cliente cliente = new Cliente();
		String query = " SELECT * FROM cliente WHERE id_cliente = " + id;
 
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				cliente.setIdCliente(id);
				cliente.setNome(resultado.getString("NOME"));
				cliente.setCpf(resultado.getString("cpf"));
				cliente.setTelefone(resultado.getString("telefone"));
 
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar Cliemte com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return cliente;
	}
 
	
	
	public ArrayList<Cliente> consultarTodos() {
 
		ArrayList<Cliente> clientes = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
 
		ResultSet resultado = null;
		String query = " SELECT * FROM cliente";
 
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Cliente cliente = new Cliente();
 
				cliente.setIdCliente(resultado.getInt("id_cliente"));
				cliente.setNome(resultado.getString("NOME"));
				cliente.setCpf(resultado.getString("cpf"));
				cliente.setTelefone(resultado.getString("telefone"));
				
				clientes.add(cliente);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todos os clientes!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return clientes;
	}
}