package br.com.loja.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.loja.dao.CidadeDAO;
import br.com.loja.dao.EstadoDAO;
import br.com.loja.dao.PessoaDAO;
import br.com.loja.domain.Cidade;
import br.com.loja.domain.Estado;
import br.com.loja.domain.Pessoa;

@ManagedBean(name = "pesssoaBean")
@SessionScoped
public class PessoaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Objetos
	private Pessoa pessoa;
	private Estado estado;

	private List<Pessoa> pessoas;
	private List<Cidade> cidades;
	private List<Estado> estados;

	PessoaDAO pessoaDAO = new PessoaDAO();

	
	@PostConstruct
	public void listar(){
		try {
			pessoa = new Pessoa();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao buscar a lista de pessoas");
			e.printStackTrace();
		}
	}
	
	public String novo() {
		try {
			pessoa = new Pessoa();
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			
			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}
		return "form_pessoa.xhtml?faces-redirect=true";
	}
	
	public void actionExcluir(){
		try {
			pessoaDAO.excluir(pessoa);
			Messages.addGlobalInfo("Pessoa Deletada com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao Deletar Pessoa");
		}
	}

	public String fazerlogin() {
		pessoaDAO.listar();
		return "/publico/login";
	}

	public String actionSalvar() {
		try {
			pessoaDAO.merge(pessoa);
			pessoaDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao efetuar seu cadastro");
			e.printStackTrace();
		}

		return "confirmacao?faces-redirect=true";
	}

	public void popularCidades() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getId());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
