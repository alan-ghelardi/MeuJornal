package com.meujornal.infrastructure.persistence.common;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.meujornal.models.noticias.Noticia;

public final class SearchResults {

	public static final SearchResults NONE = new SearchResults(
			ImmutableList.of(), 0l);

	private final List<Noticia> news;
	private final long totalOfResultsFound;
	private final int lastPage;

	public SearchResults(List<Noticia> news, long totalOfResultsFound) {
		int totalPerPage = news.size() == 0 ? 1 : news.size();

		this.news = news;
		this.totalOfResultsFound = totalOfResultsFound;
		this.lastPage = (int) Math.ceil((double) totalOfResultsFound
				/ totalPerPage);
	}

	public List<Noticia> getNews() {
		return news;
	}

	public long getTotalOfResultsFound() {
		return totalOfResultsFound;
	}

	public int getLastPage() {
		return lastPage;
	}

}
