package com.meujornal.models.noticias;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
public class Feed {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	@Size(max = 60)
	private String titulo;
	@URL
	private String link;
	@NotBlank
	@Size(max = 60)
	private String categoria;
	@NotBlank
	@Size(max = 1000)
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

	public String getLink() {
		return link;
	}

	public void setLink(String uRL) {
		link = uRL;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
