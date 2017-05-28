package br.com.loja.domain;

import java.math.BigDecimal;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class ItemVenda extends GenericDomain {

	@Column(nullable = false)
	private Short quantidade;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal precoParcial;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Venda Venda;

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoParcial() {
		return precoParcial;
	}

	public void setPrecoParcial(BigDecimal precoParcial) {
		this.precoParcial = precoParcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return Venda;
	}

	public void setVenda(Venda venda) {
		Venda = venda;
	}
}
