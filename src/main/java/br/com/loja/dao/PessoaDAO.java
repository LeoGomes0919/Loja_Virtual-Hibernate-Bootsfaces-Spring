package br.com.loja.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.loja.domain.Pessoa;
import br.com.loja.util.HibernateUtil;

public class PessoaDAO extends GenericDAO<Pessoa>{


	public Pessoa retornaUsuario(String logado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("from Pessoa where nomeusuario = :logado");
			query.setParameter("logado", logado);
			Pessoa login = (Pessoa) query.uniqueResult();
			sessao.close();
			return login;
		} catch (RuntimeException ex) {
			throw ex;
		} 
	}
}
