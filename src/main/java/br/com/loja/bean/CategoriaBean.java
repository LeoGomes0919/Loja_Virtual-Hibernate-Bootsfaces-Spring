package br.com.loja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.loja.dao.CategoriaDAO;
import br.com.loja.domain.Categoria;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "categoriaBean")
public class CategoriaBean implements Serializable {

	private Categoria categoria = new Categoria();
	private CategoriaDAO categoriaDao = new CategoriaDAO();
	private List<Categoria> categorias;

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@PostConstruct
	public void listar() {
		try {
			categorias = categoriaDao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar as categorias");
			e.printStackTrace();
		}
	}

	public void actionSalvar() {
		try {
			categoriaDao.merge(categoria);
			actionInserir();
			
			categorias = categoriaDao.listar();
			Messages.addGlobalInfo("Categoria Salva com Sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar categoria");
			e.printStackTrace();
		}
	}

	public void actionInserir() {
		categoria = new Categoria();
	}

	public void actionExcluir() {
		try {
			categoriaDao.excluir(categoria);
			
			categorias = categoriaDao.listar();
			Messages.addGlobalInfo("Categoira Deletada com Sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir a categoria");
			e.printStackTrace();
		}
	}

	public void actionEditar(ActionEvent evento){
		try {
			categoria = (Categoria) evento.getComponent().getAttributes().get("categoriaSelecionado");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar carregar categoria");
			e.printStackTrace();
		}
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public CategoriaDAO getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDAO categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
}
