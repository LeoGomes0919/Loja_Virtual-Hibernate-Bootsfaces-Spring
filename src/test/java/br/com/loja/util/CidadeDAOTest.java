package br.com.loja.util;

import java.util.List;

import org.junit.Test;

import br.com.loja.dao.CidadeDAO;
import br.com.loja.domain.Cidade;

public class CidadeDAOTest {

	@Test
	public void buscarCidades(){
		Long id = 27L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.buscarPorEstado(id);
		
		for (Cidade cidade : resultado) {
			System.out.println("Nome Cidade: " + cidade.getNome());
			
			System.out.println("Id Estado: " + cidade.getEstado().getId());
			System.out.println("Nome Estado: " + cidade.getEstado().getNome());
			System.out.println();
		}
	}
}
