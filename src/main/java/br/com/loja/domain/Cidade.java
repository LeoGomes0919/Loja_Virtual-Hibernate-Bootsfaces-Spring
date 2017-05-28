package br.com.loja.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Cidade extends GenericDomain{

	@Column(length = 50, nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Estado estado;
	
	@OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
