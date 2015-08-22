package com.meujornal.infrastructure.persistence.dao;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.meujornal.models.noticias.Feed;

@RequestScoped
public class FeedsDAO {

	@Inject
	private EntityManager entityManager;

	public void salvar(Feed feed) {
		entityManager.persist(feed);
	}

	public void atualizar(Feed feed) {
		entityManager.merge(feed);
	}

	public void remover(Feed feed) {
		entityManager.remove(feed);
	}

	public boolean existeFeedComId(Long id) {
		return buscarPorId(id) != null;
	}

	public Feed buscarPorId(Long id) {
		return entityManager.find(Feed.class, id);
	}

	public Collection<Feed> buscarTodos() {
		final String query = "SELECT feed FROM Feed AS feed ORDER BY feed.titulo";
		return entityManager.createQuery(query, Feed.class).getResultList();
	}

	public Collection<String> buscarTodasAsCategoriasExistentes() {
		final String query = "SELECT feed.categoria FROM Feed AS feed ORDER BY feed.categoria";
		return entityManager.createQuery(query, String.class).getResultList();
	}

}
