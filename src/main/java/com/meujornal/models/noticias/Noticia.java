package com.meujornal.models.noticias;

import static org.hibernate.annotations.OnDeleteAction.CASCADE;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import com.google.common.base.Objects;
import com.meujornal.infrastructure.persistence.converters.LocalDateTimeConverter;

@Entity
public class Noticia {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String titulo;
	@NotNull
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataDePublicacao;
	@NotBlank
	@URL
	private String link;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	@ManyToOne(optional = false)
	@OnDelete(action = CASCADE)
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
