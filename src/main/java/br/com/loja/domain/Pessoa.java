package br.com.loja.domain;

import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Pessoa extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String nome;
	@Column(unique = true, nullable = false)
	private Long cpf;
	@Column(nullable = false, unique = true)
	private String rg;
	@Column(length = 40, nullable = false)
	private String rua;
	@Column(nullable = false)
	private Short numero;
	@Column(length = 50, nullable = false)
	private String bairro;
	@Column(length = 10, nullable = false)
	private String cep;
	@Column(length = 15, nullable = false)
	private String telefone;
	@Column(length = 40, nullable = false)
	private String email;
	@Column(length = 15, nullable = false)
	private String celular;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNasc;

	//Dados de login
	@Column(length = 30, nullable = true)
	private String nomeUsuario;
	
	@Column(length = 32, nullable = false)
	private String senha;

	@Column(nullable = false)
	private Character tipo;

	@Column(nullable = true)
	private Boolean ativo;
	
	
	@ManyToOne
	@JoinColumn(nullable = false, name="cidade_id")
	private Cidade cidade;
	
	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}
