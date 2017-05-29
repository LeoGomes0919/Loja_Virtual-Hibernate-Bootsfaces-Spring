package br.com.loja.domain;

import java.math.BigDecimal;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 100, nullable = false)
	private String descircao;

	@Column(nullable = false)
	private Short quantidade;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal preco;

	@Transient
	private String caminho;
	
	@ManyToOne
	@JoinColumn()
	private Fabricante fabricante;

	@ManyToOne
	@JoinColumn()
	private Categoria categoria;

	
	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescircao() {
		return descircao;
	}

	public void setDescircao(String descircao) {
		this.descircao = descircao;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
}
