package br.com.loja.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

	@Column(length = 30, nullable = true)
	private String nomeUsuario;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Column(length = 32, nullable = false)
	private String senha;

	@Column(nullable = false)
	private Character tipo;

	@Column(nullable = true)
	private Boolean ativo;

	@OneToOne
	@JoinColumn(nullable = false, unique = true)
	private Pessoa pessoa;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}

	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;
		if (tipo == 'A') {
			tipoFormatado = "Administrador";
		} else if (tipo == 'C') {
			tipoFormatado = "Cliente";
		}
		return tipoFormatado;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = null;
		if (ativo) {
			ativoFormatado = "Sim";
		} else {
			ativoFormatado = "Não";
		}

		return ativoFormatado;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
