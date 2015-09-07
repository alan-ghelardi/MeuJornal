package com.meujornal.controllers;

import static br.com.caelum.vraptor.view.Results.status;

import java.util.Collection;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.quartzjob.CronTask;

import com.meujornal.infrastructure.persistence.dao.FeedsDAO;
import com.meujornal.infrastructure.persistence.dao.NoticiasDAO;
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
	@Inject
	private NoticiasDAO noticiasDAO;

	/**
	 * A URI abaixo é invocada automaticamente pelo VRaptor Quartz-Job. A cada
	 * invocação as últimas notícias publicadas por cada Feed-RSS registrado no
	 * sistema são recuperadas e salvas no banco de dados do MeuJornal.com.
	 */
	@Override
	@Post("/jobs/find-news")
	public void execute() {
		Collection<Feed> feeds = feedsDAO.buscarTodos();
		Collection<Noticia> noticias = consumidorDeRSS
				.consumirNoticiasPublicadasPor(feeds);
		noticias.forEach(noticiasDAO::salvar);

		result.use(status()).ok();
	}

	/**
	 * Define a periodicidade em que as notícias serão procuradas. Atualmente, a
	 * busca é disparada a cada 45 minutos. Note que a configuração segue o
	 * padrão do Crom.
	 */
	@Override
	public String frequency() {
		return "0 0/45 * * * ?";
	}

}
