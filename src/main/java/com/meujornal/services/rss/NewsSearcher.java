package com.meujornal.services.rss;

import static java.util.stream.Collectors.toList;

import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import com.meujornal.models.noticias.Feed;
import com.meujornal.models.noticias.Noticia;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

/**
 * Instâncias desta classe são threads responsáveis por consumir as últimas
 * notícias publicadas por um determinado Feed RSS.
 * 
 * @author Alan Ghelardi
 *
 */
final class NewsSearcher implements Callable<List<Noticia>> {

	private final SyndFeedInput input;
	private final Feed feed;

	NewsSearcher(SyndFeedInput input, Feed feed) {
		this.input = input;
		this.feed = feed;
	}

	@Override
	public List<Noticia> call() throws Exception {
		List<SyndEntry> entries = getAllSyndEntries();
		List<SyndEntry> latestEntries = findLatestEntriesFrom(entries);
		List<Noticia> latestNews = latestEntries.stream()
				.map(this::convertToNews).collect(toList());

		if (!latestNews.isEmpty()) {
			feed.setNoticiaMaisRecente(latestNews.get(0));
		}

		return latestNews;
	}

	private List<SyndEntry> getAllSyndEntries() throws Exception {
		URL url = new URL(feed.getLink());
		URLConnection connection = url.openConnection();
		List<SyndEntry> entries = input.build(new XmlReader(connection))
				.getEntries();
		return entries;
	}

	private List<SyndEntry> findLatestEntriesFrom(List<SyndEntry> allEntries) {
		int latestConsumedEntry = findIndexOfLatestConsumedEntry(allEntries);

		if (latestConsumedEntry == -1) {
			return allEntries;
		}

		return allEntries.subList(0, latestConsumedEntry);
	}

	private int findIndexOfLatestConsumedEntry(List<SyndEntry> entries) {
		int index = -1;
		Noticia latest = feed.getNoticiaMaisRecente();

		for (int i = 0; latest != null && i < entries.size(); i++) {
			if (entries.get(i).getTitle().equals(latest.getTitulo())) {
				index = i;
				break;
			}
		}

		return index;
	}

	private Noticia convertToNews(SyndEntry entry) {
		Noticia noticia = new Noticia();

		noticia.setTitulo(entry.getTitle());
		noticia.setLink(entry.getLink());
		LocalDateTime dataDePublicacao = extrairDataDePublicacao(entry
				.getPublishedDate());
		noticia.setDataDePublicacao(dataDePublicacao);
		noticia.setDescricao(entry.getDescription().getValue());
		noticia.setFeed(feed);

		return noticia;
	}

	private LocalDateTime extrairDataDePublicacao(Date publishedDate) {
		if (publishedDate != null) {
			LocalDateTime dataDePublicacao = LocalDateTime.ofInstant(
					publishedDate.toInstant(), ZoneId.systemDefault());
			return dataDePublicacao;
		}

		return LocalDateTime.now();
	}

}
