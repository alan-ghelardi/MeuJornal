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
public class RSSConsumer {

	private static final Logger logger = LoggerFactory
			.getLogger(RSSConsumer.class);

	private final ExecutorService threadPool;
	private final SyndFeedInput feedInput;

	/**
	 * @deprecated CDI eyes only
	 */
	public RSSConsumer() {
		this(null);
	}

	@Inject
	public RSSConsumer(@CustomThreadPool ExecutorService threadPool) {
		this.threadPool = threadPool;
		this.feedInput = new SyndFeedInput();
	}

	public List<Noticia> getLatestPublishedNewsFrom(Collection<Feed> feeds) {
		List<Noticia> latestNews = new ArrayList<>();
		List<NewsSearcher> tasks = feeds.stream()
				.map(feed -> new NewsSearcher(feedInput, feed))
				.collect(toList());

		try {
			threadPool.invokeAll(tasks).forEach(
					future -> awaitUntilFinishingThenSaveTheNews(future,
							latestNews));
		} catch (InterruptedException e) {
			logger.error(
					"The execution was interrupted abruptly. Details bellow:",
					e);
		}

		return latestNews;
	}

	private void awaitUntilFinishingThenSaveTheNews(
			Future<List<Noticia>> future, List<Noticia> news) {
		try {
			news.addAll(future.get());
		} catch (InterruptedException | ExecutionException e) {
			logger.error(
					"An error occurred during the attempt to consume news. See the stack trace bellow for details:",
					e);
		}
	}

}
