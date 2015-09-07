package com.meujornal.services.rss;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meujornal.infrastructure.shared.CustomThreadPool;
import com.meujornal.models.noticias.Feed;
import com.meujornal.models.noticias.Noticia;
import com.rometools.rome.io.SyndFeedInput;

/**
 * Serviço responsável por consumir as últimas notícias publicadas por uma lista
 * de Feeds RSS.
 *
 */
@ApplicationScoped
public class ConsumidorDeRSS {

	private static final Logger logger = LoggerFactory
			.getLogger(ConsumidorDeRSS.class);

	private final ExecutorService threadPool;
	private final SyndFeedInput feedInput;

	/**
	 * @deprecated CDI eyes only
	 */
	public ConsumidorDeRSS() {
		this(null);
	}

	@Inject
	public ConsumidorDeRSS(@CustomThreadPool ExecutorService threadPool) {
		this.threadPool = threadPool;
		this.feedInput = new SyndFeedInput();
	}

	public Collection<Noticia> consumirNoticiasPublicadasPor(
			Collection<Feed> feeds) {
		List<Noticia> noticias = new ArrayList<>();
		List<ConsumidorDeNoticias> tarefas = feeds.stream()
				.map(feed -> new ConsumidorDeNoticias(feedInput, feed))
				.collect(toList());

		try {
			threadPool
					.invokeAll(tarefas)
					.forEach(
							tarefaFutura -> aguardeAteTerminarEntaoArmazeneAsNoticiasConsumidas(
									tarefaFutura, noticias));
		} catch (InterruptedException e) {
			logger.error(
					"The execution was interrupted abruptly. Details bellow:",
					e);
		}

		return noticias;
	}

	private void aguardeAteTerminarEntaoArmazeneAsNoticiasConsumidas(
			Future<Collection<Noticia>> tarefaFutura,
			Collection<Noticia> noticias) {
		try {
			noticias.addAll(tarefaFutura.get());
		} catch (InterruptedException | ExecutionException e) {
			logger.error(
					"An error occurred during the attempt to consume news. See the stack trace bellow for details:",
					e);
		}
	}

}
