package com.meujornal.infrastructure.persistence.dao;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.meujornal.models.noticias.Feed;

@RequestScoped
public class FeedsDAO {

	private final EntityManager entityManager;

	/**
	 * @deprecated CDI eyes only.
	 */
	FeedsDAO() {
		this(null);
	}

	@Inject
	public FeedsDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// Recebe um objeto Feed e o persiste
	public void salvar(Feed feed) {
		entityManager.persist(feed);
	}

	// Recebe um objeto Feed e o atualiza na fonte
	public void atualizar(Feed feed) {
		entityManager.merge(feed);
	}

	// Recebe um objeto Feed e o remove da fonte
	public void remover(Feed feed) {
		entityManager.remove(feed);
	}

	// Recebe um id e retorna verdadeiro caso exista um feed com tal id, e falso
	// c.c.
	public boolean existeFeedComId(Long id) {
		return buscarPorId(id) != null;
	}

	// Recebe um id e retorna um objeto para o feed relacionado
	public Feed buscarPorId(Long id) {
		return entityManager.find(Feed.class, id);
	}

	// Retorna uma coleção com todos os feeds
	public Collection<Feed> buscarTodos() {
		final String query = "SELECT feed FROM Feed AS feed ORDER BY feed.titulo";
		return entityManager.createQuery(query, Feed.class).getResultList();
	}

	// Retorna uma coleção com todas as categorias registradas no sistema
	public Collection<String> buscarTodasAsCategoriasExistentes() {
		final String query = "SELECT DISTINCT feed.categoria FROM Feed AS feed ORDER BY feed.categoria";
		return entityManager.createQuery(query, String.class).getResultList();
	}

}
