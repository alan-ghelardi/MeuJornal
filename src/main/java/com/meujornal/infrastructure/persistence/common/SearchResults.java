package com.meujornal.infrastructure.persistence.common;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.meujornal.models.noticias.Noticia;

public final class SearchResults {

	public static final SearchResults NONE = new SearchResults(
			ImmutableList.of(), 0l);
	private List<Noticia> news;
	private long count;

	public SearchResults(List<Noticia> news, long count) {
		this.news = news;
		this.count = count;
	}

	public List<Noticia> getNews() {
		return news;
	}

	public long getCount() {
		return count;
	}

}
