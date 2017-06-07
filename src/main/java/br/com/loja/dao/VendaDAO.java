package br.com.loja.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.loja.domain.ItemVenda;
import br.com.loja.domain.Venda;
import br.com.loja.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda>{

	public void salvar(Venda venda, List<ItemVenda> itensVenda){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			
			sessao.save(venda);
			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);
				
				sessao.save(itemVenda);
			}
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}
}
