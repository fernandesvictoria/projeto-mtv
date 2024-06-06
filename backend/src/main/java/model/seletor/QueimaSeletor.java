package model.seletor;

import java.util.Date;

import model.entity.Peca;

public class QueimaSeletor {

	private Integer idQueima;
	private Date dataQueima;
	private String tipoQueima;
	private Peca peca;
	private String forno;
	private Boolean pago;

	public Integer getIdQueima() {
		return idQueima;
	}

	public void setIdQueima(Integer idQueima) {
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

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	public boolean hasFilters() {
		return (this.idQueima != null) || (this.dataQueima != null)
				|| (this.tipoQueima != null && !this.tipoQueima.isEmpty())
				|| (this.peca != null && this.peca.getIdPeca() > 0) || (this.forno != null && !this.forno.isEmpty())
				|| (this.pago != null);
	}

	// Método para construir a query com filtros
	public String buildQuery() {
		StringBuilder query = new StringBuilder("SELECT * FROM QUEIMA WHERE 1=1");

		if (this.idQueima != null) {
			query.append(" AND ID_QUEIMA = ").append(this.idQueima);
		}
		if (this.dataQueima != null) {
			query.append(" AND DATA_QUEIMA = '").append(new java.sql.Date(this.dataQueima.getTime())).append("'");
		}
		if (this.tipoQueima != null && !this.tipoQueima.isEmpty()) {
			query.append(" AND TIPO_QUEIMA LIKE '%").append(this.tipoQueima).append("%'");
		}
		if (this.peca != null && this.peca.getIdPeca() > 0) {
			query.append(" AND ID_PECA = ").append(this.peca.getIdPeca());
		}
		if (this.forno != null && !this.forno.isEmpty()) {
			query.append(" AND FORNO LIKE '%").append(this.forno).append("%'");
		}
		if (this.pago != null) {
			query.append(" AND PAGO = ").append(this.pago ? 1 : 0);
		}

		return query.toString();
	}
}
