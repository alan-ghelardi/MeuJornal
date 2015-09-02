package com.meujornal.models.noticias;

import java.time.LocalDateTime;

import com.google.common.base.Objects;

public class Noticia {

	private Long id;
	private String titulo;
	private String autor;
	private LocalDateTime dataDePublicacao;
	private String link;
	private String descricao;

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
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

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("titulo", titulo)
				.omitNullValues().add("autor", autor)
				.add("dataDePublicacao", dataDePublicacao).add("link", link)
				.add("descricao", descricao).toString();
	}

}
