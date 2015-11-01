package com.meujornal.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;

import com.meujornal.infrastructure.persistence.common.SearchResults;
import com.meujornal.infrastructure.persistence.dao.FeedsDAO;
import com.meujornal.infrastructure.persistence.dao.NoticiasDAO;

@Controller
public class HomeController {

	@Inject
	private Result result;
	@Inject
	private FeedsDAO feedsDAO;
	@Inject
	private NoticiasDAO NoticiasDAO;

	@Get("/")
	public void index() {
		result.include("feeds", feedsDAO.buscarTodos())
				.include(
						"groups",
						NoticiasDAO
								.agruparUltimasNoticiasSegundoSuasCategorias(10))
				.include("categorias",
						feedsDAO.buscarTodasAsCategoriasExistentes());
	}

	@Get("/pesquisa")
	@IncludeParameters
	public void pesquisar(String palavraChave, String categoria, int page) {
		int noticiasPorPagina = 10;
		int comecandoEm = page * noticiasPorPagina - noticiasPorPagina;

		SearchResults resultados = NoticiasDAO
				.buscarNoticiasPorPalavraChaveECategoria(palavraChave,
						categoria, comecandoEm, noticiasPorPagina);

		result.include("resultados", resultados)
				.redirectTo(HomeController.class).index();
	}

}
