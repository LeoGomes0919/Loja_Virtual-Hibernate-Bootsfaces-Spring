package br.com.loja.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.loja.dao.CategoriaDAO;
import br.com.loja.dao.FabricanteDAO;
import br.com.loja.dao.ProdutoDAO;
import br.com.loja.domain.Categoria;
import br.com.loja.domain.Fabricante;
import br.com.loja.domain.Produto;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "produtoBean")
public class ProdutoBean implements Serializable {

	private Produto produto = new Produto();
	private ProdutoDAO produtoDao = new ProdutoDAO();
	private List<Produto> produtos;
	private List<Categoria> categorias;
	private List<Fabricante> fabricantes;
	private Categoria categoria = new Categoria();
	private Fabricante fabricante = new Fabricante();

	public void actionSalvar() {
		try {
			if (produto.getCaminho() == null) {
				Messages.addGlobalError("Campo foto é obrigatório");
				return;
			}
			
			Produto produtoRetorno = produtoDao.merge(produto);
			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths
					.get("C:/Users/leogo/Dropbox/GTI-V/Progrmação com Framwork/Loja_Virtual/Loja_Virtual/src/main/webapp/resources/uploads/"
							+ produtoRetorno.getId() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			actionInserir();
			produtos = produtoDao.listar();

			Messages.addGlobalInfo("Produto Salvo com Sucesso!");
		} catch (RuntimeException | IOException e) {
			Messages.addGlobalError("Erro ao tentar salvar o produto");
			e.printStackTrace();
		}
	}

	public void actionEditar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			produto.setCaminho("C:/Users/leogo/Dropbox/GTI-V/Progrmação com Framwork/Loja_Virtual/Loja_Virtual/src/main/webapp/resources/uploads/"
					+ produto.getId() + ".png");

			FabricanteDAO fab = new FabricanteDAO();
			fabricantes = fab.listar("nome");

			CategoriaDAO cat = new CategoriaDAO();
			categorias = cat.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar carregar dados");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			produtos = produtoDao.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os produtos");
		}
	}

	public void actionInserir() {
		produto = new Produto();
	}

	public void actionExcluir() {
		try {
			produtoDao.excluir(produto);
			Path arquivo = Paths
					.get("C:/Users/leogo/Dropbox/GTI-V/Progrmação com Framwork/Loja_Virtual/Loja_Virtual/src/main/webapp/resources/uploads/"
							+ produto.getId() + ".png");
			Files.deleteIfExists(arquivo);
			actionInserir();
			produtos = produtoDao.listar();
			Messages.addGlobalInfo("Produto excluido com Sucesso!");
		} catch (RuntimeException | IOException e) {
			Messages.addGlobalError("Erro ao tentar excluir o produto");
			e.printStackTrace();
		}
	}

	public void upload(FileUploadEvent event) {
		try {
			UploadedFile arquivoUpload = event.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());
			Messages.addGlobalInfo("Envio da foto feito com sucesso");
		} catch (IOException e) {
			Messages.addGlobalError("Erro ao enviar foto");
			e.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoDAO getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDAO produtoDao) {
		this.produtoDao = produtoDao;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
}
