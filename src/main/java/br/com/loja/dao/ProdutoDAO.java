package br.com.loja.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.loja.domain.Produto;
import br.com.loja.util.HibernateUtil;

public class ProdutoDAO extends GenericDAO<Produto> {

	@SuppressWarnings("unchecked")
	public List<Produto> buscarPorCategoria(Long categoriaId) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Produto.class);
			consulta.add(Restrictions.eq("categoria.id", categoriaId));
			consulta.addOrder(Order.asc("nome"));
			List<Produto> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
}
