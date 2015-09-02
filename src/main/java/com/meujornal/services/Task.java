package com.meujornal.services;

import static java.util.stream.Collectors.toList;

import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Callable;

import com.meujornal.models.noticias.Feed;
import com.meujornal.models.noticias.Noticia;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

class Task implements Callable<Collection<Noticia>> {

	private final SyndFeedInput input;
	private final Feed feed;

	Task(SyndFeedInput input, Feed feed) {
		this.input = input;
		this.feed = feed;
	}

	@Override
	public Collection<Noticia> call() throws Exception {
		URL url = new URL(feed.getLink());
		URLConnection connection = url.openConnection();
		SyndFeed syndFeed = input.build(new XmlReader(connection));
		return syndFeed.getEntries().stream().map(this::mapFromEntry)
				.collect(toList());
	}

	private Noticia mapFromEntry(SyndEntry entry) {
		Noticia noticia = new Noticia();

		noticia.setTitulo(entry.getTitle());
		noticia.setAutor(entry.getAuthor());
		noticia.setLink(entry.getLink());
		LocalDateTime dataDePublicacao = mapPublishedDate(entry
				.getPublishedDate());
		noticia.setDataDePublicacao(dataDePublicacao);
		noticia.setDescricao(entry.getDescription().getValue());

		return noticia;
	}

	private LocalDateTime mapPublishedDate(Date publishedDate) {
		if (publishedDate != null) {
			LocalDateTime dataDePublicacao = LocalDateTime.ofInstant(
					publishedDate.toInstant(), ZoneId.systemDefault());
			return dataDePublicacao;
		}

		return null;
	}
}