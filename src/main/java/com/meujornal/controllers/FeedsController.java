package com.meujornal.controllers;

import java.util.Collection;

import javax.inject.Inject;

import com.meujornal.infrastructure.persistence.dao.FeedsDAO;
import com.meujornal.infrastructure.persistence.dao.NoticiasDAO;
import com.meujornal.models.noticias.Feed;
import com.meujornal.models.noticias.Noticia;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

@Controller
public class FeedsController {

	@Inject
	private Result result;
	@Inject
	private FeedsDAO feedsDAO;
	@Inject
	private NoticiasDAO noticiasDAO;

	@Get("/feeds/{id}")
	public void index(Long id) {
		Feed feed = feedsDAO.buscarPorId(id);
		Collection<Noticia> noticias = noticiasDAO.buscarTodasRelacionadasA(feed, 15);

		result.include("feed", feed)
		.include("noticias", noticias);
		
	}

}
