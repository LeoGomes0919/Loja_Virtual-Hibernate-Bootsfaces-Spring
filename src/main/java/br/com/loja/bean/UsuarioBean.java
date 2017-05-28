package br.com.loja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.loja.dao.PessoaDAO;
import br.com.loja.dao.UsuarioDAO;
import br.com.loja.domain.Usuario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean implements Serializable {

	private List<Usuario> usuarios;
	private Usuario usuario = new Usuario();

	@PostConstruct
	public void novo() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarios = usuarioDAO.listar("id");
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.listar();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
