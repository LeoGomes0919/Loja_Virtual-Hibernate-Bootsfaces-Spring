package br.com.loja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class FormaPgto extends GenericDomain{

	@Column(nullable = false, length = 100)
	private String descricao;
	
	@Column(nullable = false)
	private int num_max_parc;
	
	@Column(nullable = false)
	private int num_padrao_parc;
	
	@Column(nullable = false)
	private int intervaloDias;
	
	@Column(nullable = false)
	private float percentualAcres;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNum_max_parc() {
		return num_max_parc;
	}

	public void setNum_max_parc(int num_max_parc) {
		this.num_max_parc = num_max_parc;
	}

	public int getNum_padrao_parc() {
		return num_padrao_parc;
	}

	public void setNum_padrao_parc(int num_padrao_parc) {
		this.num_padrao_parc = num_padrao_parc;
	}

	public int getIntervaloDias() {
		return intervaloDias;
	}

	public void setIntervaloDias(int intervaloDias) {
		this.intervaloDias = intervaloDias;
	}

	public float getPercentualAcres() {
		return percentualAcres;
	}

	public void setPercentualAcres(float percentualAcres) {
		this.percentualAcres = percentualAcres;
	}
}
