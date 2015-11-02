package com.meujornal.controllers;

import static br.com.caelum.vraptor.view.Results.status;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;

import com.meujornal.infrastructure.persistence.common.SearchResults;
import com.meujornal.infrastructure.persistence.dao.FeedsDAO;
import com.meujornal.infrastructure.persistence.dao.NoticiasDAO;
import com.meujornal.models.noticias.Feed;

@Controller
public class FeedsController {

	private static final int NOTICIAS_POR_PAGINA = 10;

	@Inject
	private Result result;
	@Inject
	private FeedsDAO feedsDAO;
	@Inject
	private NoticiasDAO noticiasDAO;

	@Get("/feeds/{id}")
	public void index(Long id, int page) {
		Feed feed = feedsDAO.buscarPorId(id);

		if (feed == null) {
			result.use(status()).notFound();
		} else {
			page = page == 0 ? 1 : page;
			int comecandoEm = page * NOTICIAS_POR_PAGINA - NOTICIAS_POR_PAGINA;

			SearchResults searchResults = noticiasDAO.buscarTodasRelacionadasA(
					feed, comecandoEm, NOTICIAS_POR_PAGINA);

			result.include("feed", feed).include("page", page)
					.include("results", searchResults)
					.include("newsPerPage", NOTICIAS_POR_PAGINA);
		}
	}

	@Get("/feeds/{id}/pesquisa")
	@IncludeParameters
	public void pesquisarNoFeed(String palavraChave, Long id, int page) {
		Feed feed = feedsDAO.buscarPorId(id);

		if (feed == null) {
			result.notFound();
		} else {
			int comecandoEm = page * NOTICIAS_POR_PAGINA - NOTICIAS_POR_PAGINA;

			SearchResults searchResults = noticiasDAO
					.buscarNoticiasPorPalavraChaveEFeed(palavraChave, id,
							comecandoEm, NOTICIAS_POR_PAGINA);

			result.include("feed", feed).include("results", searchResults)
					.include("newsPerPage", NOTICIAS_POR_PAGINA)
					.include("isSearching", true);

			result.of(this).index(id, page);
		}
	}

}
