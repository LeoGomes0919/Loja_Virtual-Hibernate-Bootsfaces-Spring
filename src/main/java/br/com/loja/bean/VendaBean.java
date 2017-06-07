package br.com.loja.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.loja.dao.FormaPgtoDAO;
import br.com.loja.dao.ItemVendaDAO;
import br.com.loja.dao.PessoaDAO;
import br.com.loja.dao.ProdutoDAO;
import br.com.loja.dao.VendaDAO;
import br.com.loja.domain.FormaPgto;
import br.com.loja.domain.ItemVenda;
import br.com.loja.domain.Pessoa;
import br.com.loja.domain.Produto;
import br.com.loja.domain.Venda;

@ManagedBean
@SessionScoped
public class VendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<FormaPgto> pagamentos;
	private List<Venda> vendas;
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private Produto produto = new Produto();

	private Venda venda;
	private int totalCarrinho;
	private String logado;
	private Pessoa pessoa;

	@PostConstruct
	public void novo() {
		try {
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));

			produtos = produtoDAO.listar();

			itensVenda = new ArrayList<>();
			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os produtos");
			e.printStackTrace();
		}
	}

	public String itensDaVenda(){
		try {
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			itensVenda =  itemVendaDAO.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "itensVenda?faces-redirect=true";
	}
	
	public void salvar() {
		try {
			if (venda.getPrecoTotal().signum() == 0) {
				Messages.addGlobalInfo("Você precisa escolher um serviço para efetuar a compra");
			}
			
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, itensVenda);
			novo();
			Messages.addGlobalInfo("Compra efetuada com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Desculpe sua compra não pode ser efetuada");
			e.printStackTrace();
		}
	}

	// Adicinar itens ao carrinho
	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		if (itensVenda == null) {
			itensVenda = new ArrayList<>();
		}

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
			Messages.addGlobalInfo("Produto adicionado ao carrinho");
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		totalCarrinho = itensVenda.size();
		calcular();
	}

	// Remover item por completo independe da quantidade
	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
				achou = posicao;
			}

			if (achou > -1) {
				itensVenda.remove(achou);
			}
		}
		calcular();
	}

	// Calcular Valores ao serem adicionados
	public void calcular() {
		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
		}
	}

	// Acessar carrinho
	public String finalizar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			FormaPgtoDAO formaPgtoDAO = new FormaPgtoDAO();
			venda.setHorario(new Date());
			logado = SecurityContextHolder.getContext().getAuthentication().getName();
			pessoa = pessoaDAO.retornaUsuario(logado);
			pagamentos = formaPgtoDAO.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "/cliente/carrinho.xhtml?faces-redirect=true";
	}

	public String getLogado() {
		return logado;
	}

	public void setLogado(String logado) {
		this.logado = logado;
	}

	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public int getTotalCarrinho() {
		return totalCarrinho;
	}

	public void setTotalCarrinho(int totalCarrinho) {
		this.totalCarrinho = totalCarrinho;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public List<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<FormaPgto> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<FormaPgto> pagamentos) {
		this.pagamentos = pagamentos;
	}
}
