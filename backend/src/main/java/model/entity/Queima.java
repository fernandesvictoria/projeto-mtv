package model.entity;

import java.util.Date;

public class Queima {
	private int idQueima;
	private Date dataQueima;
	private String tipoQueima;
	private int temperaturaAlcancada;
	private int idPeca;
	private String forno;
	private double precoQueima;

	public Queima() {
		super();
	}

	public Queima(int idQueima, Date dataQueima, String tipoQueima, int temperaturaAlcancada, int idPeca, String forno,
			double precoQueima) {
		super();
		this.idQueima = idQueima;
		this.dataQueima = dataQueima;
		this.tipoQueima = tipoQueima;
		this.temperaturaAlcancada = temperaturaAlcancada;
		this.idPeca = idPeca;
		this.forno = forno;
		this.precoQueima = precoQueima;
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

	public String getTipoQueima() {
		return tipoQueima;
	}

	public void setTipoQueima(String tipoQueima) {
		this.tipoQueima = tipoQueima;
	}

	public int getTemperaturaAlcancada() {
		return temperaturaAlcancada;
	}

	public void setTemperaturaAlcancada(int temperaturaAlcancada) {
		this.temperaturaAlcancada = temperaturaAlcancada;
	}

	public int getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(int idPeca) {
		this.idPeca = idPeca;
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
}
