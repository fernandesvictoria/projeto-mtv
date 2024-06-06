package model.seletor;


public class PecaSeletor {
	private String cliente;
	private String estagio;
	private String tipo;
	
	public PecaSeletor() {
		super();
	}

	public PecaSeletor(String cliente, String estagio, String tipo) {
		super();
		this.cliente = cliente;
		this.estagio = estagio;
		this.tipo = tipo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEstagio() {
		return estagio;
	}

	public void setEstagio(String estagio) {
		this.estagio = estagio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
}
