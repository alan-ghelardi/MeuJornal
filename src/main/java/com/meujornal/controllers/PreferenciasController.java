package com.meujornal.controllers;

import java.util.List;
import java.util.StringJoiner;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;

import com.meujornal.infrastructure.persistence.common.SearchResults;
import com.meujornal.infrastructure.persistence.dao.NoticiasDAO;
import com.meujornal.infrastructure.security.UserInContext;
import com.meujornal.models.usuarios.Usuario;

@Controller
public class PreferenciasController {

	private static final int NOTICIAS_POR_PAGINA = 10;

	@Inject
	private Result result;
	@Inject
	private UserInContext userInContext;
	@Inject
	private NoticiasDAO noticiasDAO;

	@Get("/preferencias")
	public void index(int page) {
		Usuario usuario = userInContext.getAuthenticated();
		List<String> palavrasDeInteresse = usuario.getPalavrasDeInteresse();
		page = page == 0 ? 1 : page;
		int comecandoEm = page * NOTICIAS_POR_PAGINA - NOTICIAS_POR_PAGINA;

		SearchResults searchResults = noticiasDAO.buscarTodasQueContemAs(
				palavrasDeInteresse, comecandoEm, NOTICIAS_POR_PAGINA);

		result.include("page", page).include("results", searchResults)
				.include("newsPerPage", NOTICIAS_POR_PAGINA)
				.include("keywords", toJSON(palavrasDeInteresse));
	}

	private String toJSON(List<String> palavrasDeInteresse) {
		StringJoiner joiner = new StringJoiner(",", "[", "]");
		palavrasDeInteresse.stream()
				.map((palavra) -> String.format("\"%s\"", palavra))
				.forEach(joiner::add);

		return joiner.toString();
	}

	@Post("/preferencias")
	public void salvar(List<String> keywords) {
		Usuario usuario = userInContext.getAuthenticated();
		usuario.setPalavrasDeInteresse(keywords);
		result.redirectTo(this).index(0);
	}

}
