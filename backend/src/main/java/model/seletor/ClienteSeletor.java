package model.seletor;

public class ClienteSeletor {
	private String nomeCliente;
	private String cpf;
	
	public ClienteSeletor() {
		super();
	}

	public ClienteSeletor(String nomeCliente, String cpf) {
		super();
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
