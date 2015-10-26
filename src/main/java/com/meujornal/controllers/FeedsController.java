package com.meujornal.controllers;

import static br.com.caelum.vraptor.view.Results.status;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

import com.meujornal.infrastructure.persistence.common.SearchResults;
import com.meujornal.infrastructure.persistence.dao.FeedsDAO;
import com.meujornal.infrastructure.persistence.dao.NoticiasDAO;
import com.meujornal.models.noticias.Feed;

@Controller
public class FeedsController {

	private static final int NOTICIAS_POR_PAGINA = 15;

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
			int comecandoEm = page * NOTICIAS_POR_PAGINA
					- (NOTICIAS_POR_PAGINA - 1);

			SearchResults searchResults = noticiasDAO
					.buscarTodasRelacionadasA(feed, comecandoEm,
							NOTICIAS_POR_PAGINA);
			long lastPage = determinarUltimaPaginaComBaseEm(searchResults
					.getCount());

			result.include("feed", feed)
					.include("noticias", searchResults.getNews())
					.include("page", page).include("lastPage", lastPage)
					.include("newsPerPage", NOTICIAS_POR_PAGINA);
		}
	}

	private int determinarUltimaPaginaComBaseEm(long totalDeNoticias) {
		return (int) Math.ceil((double) totalDeNoticias / NOTICIAS_POR_PAGINA);
	}

}
