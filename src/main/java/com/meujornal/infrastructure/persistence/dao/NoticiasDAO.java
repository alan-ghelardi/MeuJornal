package com.meujornal.infrastructure.persistence.dao;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.meujornal.models.noticias.Noticia;

@RequestScoped
public class NoticiasDAO {

	private final EntityManager entityManager;

	/**
	 * @deprecated CDI eyes only.
	 */
	NoticiasDAO() {
		this(null);
	}

	@Inject
	public NoticiasDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void salvar(Noticia noticia) {
		entityManager.persist(noticia);
	}

	public Collection<Noticia> buscar(int quantas) {
		String query = "SELECT noticia FROM Noticia AS noticia ORDER BY noticia.dataDePublicacao";
		return entityManager.createQuery(query, Noticia.class)
				.setMaxResults(quantas).getResultList();
	}

}
