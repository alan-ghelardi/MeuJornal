package com.meujornal.controllers;

import static java.lang.String.format;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

import com.meujornal.infrastructure.persistence.dao.FeedsDAO;
import com.meujornal.models.noticias.Feed;

/**
 * Controller responsável pelas URIs administrativas do sistema.
 * 
 */
@Controller
public class AdminController {

	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private FeedsDAO feedsDAO;

	@Get("/admin")
	public void index() {
		result.include("feeds", feedsDAO.buscarTodos());
	}

	@Get("/admin/novo-feed")
	public void formulario() {
		result.include("categorias",
				feedsDAO.buscarTodasAsCategoriasExistentes());
	}

	@Post("/admin/feeds")
	public void criarFeed(@Valid Feed feed) {
		validator.onErrorRedirectTo(this).formulario();
		feedsDAO.salvar(feed);
		result.include(
				"mensagem",
				format("O Feed RSS %s foi criado com sucesso!",
						feed.getTitulo())).redirectTo(this).index();
	}

	@Get("/admin/editar-feed/{id}")
	public void editarFeed(Long id) {
		Feed feed = feedsDAO.buscarPorId(id);

		if (feed == null) {
			result.notFound();
		} else {
			result.include("feed", feed)
					.include("categorias",
							feedsDAO.buscarTodasAsCategoriasExistentes())
					.of(this).formulario();
		}
	}

	@Post("/admin/feeds/{id}")
	public void editarFeed(Long id, @Valid Feed feed) {
		if (!feedsDAO.existeFeedComId(id)) {
			result.notFound();
		} else {
			validator.onErrorRedirectTo(this).formulario();
			feedsDAO.atualizar(feed);
			result.include(
					"mensagem",
					format("O Feed RSS %s foi atualizado com sucesso!",
							feed.getTitulo())).redirectTo(this).index();
		}
	}

	@Get("/admin/feeds/{id}")
	public void visualizar(Long id) {
		Feed feed = feedsDAO.buscarPorId(id);
		if (feed == null) {
			result.notFound();
		} else {
			result.include("feed", feed);
		}
	}

	@Delete("/admin/feeds/{id}")
	public void removerFeed(Long id) {
		Feed feed = feedsDAO.buscarPorId(id);

		if (feed == null) {
			result.notFound();
		} else {
			feedsDAO.remover(feed);
			result.include(
					"mensagem",
					format("O feed %s foi removido com sucesso!",
							feed.getTitulo())).redirectTo(this).index();
		}
	}

}
