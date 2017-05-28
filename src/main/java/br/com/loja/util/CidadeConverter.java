package br.com.loja.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.loja.dao.CidadeDAO;
import br.com.loja.domain.Cidade;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {

	@Inject
	private CidadeDAO cidadeDAO = new CidadeDAO();

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		Cidade retorno = null;

		if (valor != null) {
			Long id = new Long(valor);
			retorno = (Cidade) this.cidadeDAO.buscarPorEstado(new Long(id));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) {
		if (valor != null) {
			Long id = ((Cidade) valor).getId();
			String retorno = (id == null ? null : id.toString());

			return retorno;
		}
		return "";
	}
}
