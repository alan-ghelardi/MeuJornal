package com.meujornal.services;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.meujornal.models.noticias.Feed;
import com.meujornal.models.noticias.Noticia;
import com.rometools.rome.io.SyndFeedInput;

public final class RSSParser {

	private final ExecutorService threadPool;
	private final SyndFeedInput feedInput;

	public RSSParser(ExecutorService threadPool) {
		this.threadPool = threadPool;
		this.feedInput = new SyndFeedInput();
	}

	public Collection<Noticia> parse(Collection<Feed> feeds) {
		List<Noticia> noticias = new ArrayList<>();
		List<Task> tasks = feeds.stream()
				.map(feed -> new Task(feedInput, feed)).collect(toList());
		try {
			threadPool.invokeAll(tasks).forEach(
					futureTask -> alwaitTerminationThemStoreNews(futureTask,
							noticias));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return noticias;
	}

	private void alwaitTerminationThemStoreNews(
			Future<Collection<Noticia>> futureTask, Collection<Noticia> noticias) {
		try {
			noticias.addAll(futureTask.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
