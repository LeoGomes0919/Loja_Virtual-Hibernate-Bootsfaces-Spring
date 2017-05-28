package br.com.loja.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.loja.dao.PessoaDAO;
import br.com.loja.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class BuscarPessoaBean implements Serializable {

	private Pessoa pessoa;
	
	@PostConstruct
	public void novo(){
		pessoa = new Pessoa();
	}

	public void buscar() {
		try {
			PessoaDAO pessoaDao = new PessoaDAO();
			Pessoa resultado = pessoaDao.buscar(pessoa.getId());

			if (resultado == null) {
				Messages.addGlobalWarn("N�o existe pessoa com esse CPF cadastrado!!!Fa�a seu Cadastro Primeiro!");
			} else {
				pessoa = resultado;
				Messages.addGlobalInfo("Cadastro encontrado com exit�!");
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao buscar pessoa");
			e.printStackTrace();
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
