package br.com.loja.bean;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class RedirecionaPaginaPadrao implements AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest reuqest, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (roles.contains("ROLE_A")) {
			response.sendRedirect("/Loja_Virtual/admin/principal.xhtml");
		} else if (roles.contains("ROLE_C")) {
			response.sendRedirect("/Loja_Virtual/publico/index.xhtml");
		}
	}
}
