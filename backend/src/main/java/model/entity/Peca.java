package model.entity;

import java.util.List;

import model.entity.enums.EstagioPeca;
import model.entity.enums.Tamanho;

public class Peca {
	private int idPeca;
	private Cliente cliente;
	private Tamanho tamanho;
	private Tipo tipo;
	private String descricao;
	private EstagioPeca estagio;
	private double valorTotal;

	public Peca() {
		super();
	}

	public Peca(int idPeca, Cliente cliente, Tamanho tamanho, Tipo tipo, String descricao, EstagioPeca estagio,
			double valorTotal) {
		super();
		this.idPeca = idPeca;
		this.cliente = cliente;
		this.tamanho = tamanho;
		this.tipo = tipo;
		this.descricao = descricao;
		this.estagio = estagio;
		this.valorTotal = valorTotal;
	}

	public int getIdPeca() {
		return idPeca;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EstagioPeca getEstagio() {
		return estagio;
	}

	public void setEstagio(EstagioPeca estagio) {
		this.estagio = estagio;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void calcularPrecoTotal(List<Queima> queimas) {
		double precoTotal = 0.0;
		for (Queima queima : queimas) {
			precoTotal += queima.getPrecoQueima();
		}
		this.setValorTotal(precoTotal);
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setIdPeca(int idPeca) {
		this.idPeca = idPeca;
	}
}
