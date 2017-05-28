package br.com.loja.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.loja.domain.Cidade;
import br.com.loja.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long estadoId) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.id", estadoId));
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally{
			sessao.close();
		}
	}
}
