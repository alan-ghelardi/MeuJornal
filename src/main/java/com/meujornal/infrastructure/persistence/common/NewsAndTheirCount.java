package com.meujornal.infrastructure.persistence.common;

import java.util.List;

import com.meujornal.models.noticias.Noticia;

public final class NewsAndTheirCount {

	private List<Noticia> news;
	private long count;

	public NewsAndTheirCount(List<Noticia> news, long count) {
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
