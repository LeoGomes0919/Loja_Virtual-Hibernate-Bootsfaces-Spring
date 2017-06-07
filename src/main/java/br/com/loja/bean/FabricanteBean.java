package br.com.loja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.loja.dao.FabricanteDAO;
import br.com.loja.domain.Fabricante;

@SessionScoped
@ManagedBean(name = "fabricanteBean")
public class FabricanteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Fabricante fabricante = new Fabricante();
	private FabricanteDAO fabricanteDao = new FabricanteDAO();
	private List<Fabricante> fabricantes;

	public FabricanteDAO getFabricanteDao() {
		return fabricanteDao;
	}

	public void setFabricanteDao(FabricanteDAO fabricanteDao) {
		this.fabricanteDao = fabricanteDao;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	@PostConstruct
	public void listar() {
		try {
			fabricantes = fabricanteDao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar o fabricante");
			e.printStackTrace();
		}
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public void actionExcluir() {
		try {
			fabricanteDao.excluir(fabricante);
			
			fabricantes = fabricanteDao.listar();
			Messages.addGlobalInfo("Fabricante Deletado com Sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar apagar o fabricante");
			e.printStackTrace();
		}
	}

	public void actionSalvar() {
		try {
			fabricanteDao.merge(fabricante);
			actionInserir();

			fabricantes = fabricanteDao.listar();
			Messages.addGlobalInfo("Fabricante Salvo com Sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar o fabricante");
			e.printStackTrace();
		}
	}

	public void actionInserir() {
		fabricante = new Fabricante();
	}
}
