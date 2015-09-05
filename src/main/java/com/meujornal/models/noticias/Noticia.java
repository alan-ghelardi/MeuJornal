package com.meujornal.models.noticias;

import java.time.LocalDateTime;

import com.google.common.base.Objects;

public class Noticia {

	private Long id;
	private String titulo;
	private LocalDateTime dataDePublicacao;
	private String link;
	private String descricao;
	private Feed feed;

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getDataDePublicacao() {
		return dataDePublicacao;
	}

	public void setDataDePublicacao(LocalDateTime dataDePublicacao) {
		this.dataDePublicacao = dataDePublicacao;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				.add("titulo", titulo)
				.add("dataDePublicacao", dataDePublicacao).add("link", link)
				.add("descricao", descricao).toString();
	}

}
