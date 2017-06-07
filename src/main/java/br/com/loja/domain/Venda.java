package br.com.loja.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Venda extends GenericDomain{

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(nullable = false, precision = 14, scale = 2)
	private BigDecimal precoTotal;
	
	@ManyToOne
	private Pessoa pessoa;
	
	@ManyToOne
	private FormaPgto formaPgto;

	public FormaPgto getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
