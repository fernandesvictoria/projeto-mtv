package model.seletor;

import model.entity.Cliente;
import model.entity.Tipo;
import model.entity.enums.EstagioPeca;

public class PecaSeletor {
	private Cliente cliente;
	private EstagioPeca estagio;
	private Tipo tipo;
	
	public PecaSeletor() {
		super();
	}

	public PecaSeletor(Cliente cliente, EstagioPeca estagio, Tipo tipo) {
		super();
		this.cliente = cliente;
		this.estagio = estagio;
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EstagioPeca getEstagio() {
		return estagio;
	}

	public void setEstagio(EstagioPeca estagio) {
		this.estagio = estagio;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
