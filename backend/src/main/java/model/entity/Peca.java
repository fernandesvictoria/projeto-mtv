package model.entity;


public class Peca {
	private int idPeca;
	private Cliente cliente;
	private String tamanho;
	private String tipoPeca;
	private String estagio;
	private double valorTotal;
		
	public Peca() {
		super();
	}

	public Peca(int idPeca, Cliente cliente, String tamanho, String tipoPeca, String estagio, double valorTotal) {
		super();
		this.idPeca = idPeca;
		this.cliente = cliente;
		this.tamanho = tamanho;
		this.tipoPeca = tipoPeca;
		this.estagio = estagio;
		this.valorTotal = valorTotal;
	}

	public int getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(int idPeca) {
		this.idPeca = idPeca;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getTipoPeca() {
		return tipoPeca;
	}

	public void setTipoPeca(String tipoPeca) {
		this.tipoPeca = tipoPeca;
	}

	public String getEstagio() {
		return estagio;
	}

	public void setEstagio(String estagio) {
		this.estagio = estagio;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
