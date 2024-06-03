package model.entity;

import java.util.Date;

import model.entity.enums.TipoQueima;

public class Queima {

	private int idQueima;
	private Date dataQueima;
	private TipoQueima tipoQueima;
	private int temperaturaAlcancada;
	private Peca peca;
	private String forno;
	private double precoQueima;
	private boolean pago;

	public Queima() {
		super();
	}

	public Queima(int idQueima, Date dataQueima, TipoQueima tipoQueima, int temperaturaAlcancada, Peca peca,
			String forno, double precoQueima, boolean pago) {
		super();
		this.idQueima = idQueima;
		this.dataQueima = dataQueima;
		this.tipoQueima = tipoQueima;
		this.temperaturaAlcancada = temperaturaAlcancada;
		this.peca = peca;
		this.forno = forno;
		this.precoQueima = precoQueima;
		this.pago = pago;
	}

	public int getIdQueima() {
		return idQueima;
	}

	public void setIdQueima(int idQueima) {
		this.idQueima = idQueima;
	}

	public Date getDataQueima() {
		return dataQueima;
	}

	public void setDataQueima(Date dataQueima) {
		this.dataQueima = dataQueima;
	}

	public TipoQueima getTipoQueima() {
		return tipoQueima;
	}

	public void setTipoQueima(TipoQueima tipoQueima) {
		this.tipoQueima = tipoQueima;
	}

	public int getTemperaturaAlcancada() {
		return temperaturaAlcancada;
	}

	public void setTemperaturaAlcancada(int temperaturaAlcancada) {
		this.temperaturaAlcancada = temperaturaAlcancada;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public String getForno() {
		return forno;
	}

	public void setForno(String forno) {
		this.forno = forno;
	}

	public double getPrecoQueima() {
		return precoQueima;
	}

	public void setPrecoQueima(double precoQueima) {
		this.precoQueima = precoQueima;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	
}
