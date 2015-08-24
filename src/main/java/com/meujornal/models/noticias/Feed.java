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

	// Retorna o id do feed
	public Long getId() {
		return id;
	}

	// Retorna o título do feed
	public String getTitulo() {
		return titulo;
	}

	// Atribui um título ao feed
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	// Retorna um link para um feed
	public String getLink() {
		return link;
	}

	// Atribui um link para um feed
	public void setLink(String uRL) {
		link = uRL;
	}

	// Retorna a categoria relacionada ao feed
	public String getCategoria() {
		return categoria;
	}

	// Relaciona uma categoria ao feed
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	// Retorna a descrição do feed
	public String getDescricao() {
		return descricao;
	}

	// Atribui uma descrição ao feed
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
