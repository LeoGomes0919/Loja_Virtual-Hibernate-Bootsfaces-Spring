package br.com.loja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.loja.dao.FormaPgtoDAO;
import br.com.loja.domain.FormaPgto;

@ManagedBean
@SessionScoped
public class FormaPgtoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FormaPgto formaPgto = new FormaPgto();
	private FormaPgtoDAO formaPgtoDAO = new FormaPgtoDAO();
	private List<FormaPgto> pagamentos;

	@PostConstruct
	public void listar() {
		try {
			pagamentos = formaPgtoDAO.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void actionEditar(ActionEvent evento) {
		try {
			formaPgto = (FormaPgto) evento.getComponent().getAttributes().get("pagamentoSelecionado");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar carregar dados");
			e.printStackTrace();
		}
	}

	public void actionSalvar() {
		try {
			formaPgtoDAO.merge(formaPgto);
			formaPgto = new FormaPgto();
			pagamentos = formaPgtoDAO.listar();
			Messages.addGlobalInfo("Salvo com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Algo deu errado ao salvar essa forma de pagamento");
			e.printStackTrace();
		}
	}

	public void actionExcluir() {
		try {
			formaPgtoDAO.excluir(formaPgto);
			formaPgto = new FormaPgto();
			pagamentos = formaPgtoDAO.listar();
			Messages.addGlobalInfo("Excluido com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir");
			e.printStackTrace();
		}
	}

	public FormaPgto getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}

	public List<FormaPgto> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<FormaPgto> pagamentos) {
		this.pagamentos = pagamentos;
	}
}
