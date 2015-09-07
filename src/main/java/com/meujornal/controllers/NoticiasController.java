package com.meujornal.controllers;

import static br.com.caelum.vraptor.view.Results.status;

import java.util.Collection;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.quartzjob.CronTask;

import com.meujornal.infrastructure.persistence.dao.FeedsDAO;
import com.meujornal.models.noticias.Feed;
import com.meujornal.models.noticias.Noticia;
import com.meujornal.services.rss.ConsumidorDeRSS;

/**
 * Controller responsável por buscar e persistir novas notícias.
 */
@Controller
public class NoticiasController implements CronTask {

	@Inject
	private Result result;
	@Inject
	private ConsumidorDeRSS consumidorDeRSS;
	@Inject
	private FeedsDAO feedsDAO;

	@Override
	@Post("/jobs/find-news")
	public void execute() {
		Collection<Feed> feeds = feedsDAO.buscarTodos();
		Collection<Noticia> noticias = consumidorDeRSS
				.consumirNoticiasPublicadasPor(feeds);

		noticias.forEach(System.out::println);

		result.use(status()).ok();
	}

	@Override
	public String frequency() {
		return "0 0/5 * * * ?";
	}

}
