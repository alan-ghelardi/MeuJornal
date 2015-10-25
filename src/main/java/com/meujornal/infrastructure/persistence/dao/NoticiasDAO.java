package com.meujornal.infrastructure.persistence.dao;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.meujornal.infrastructure.persistence.common.CategoryAndQuantity;
import com.meujornal.infrastructure.persistence.common.NewsAndTheirCount;
import com.meujornal.models.noticias.Feed;
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

	public NewsAndTheirCount buscarTodasRelacionadasA(Feed feed,
			int comecandoEm, int quantas) {
		String query = "SELECT noticia FROM Noticia AS noticia WHERE noticia.feed = :feed ORDER BY noticia.dataDePublicacao DESC";

		List<Noticia> noticias = entityManager
				.createQuery(query, Noticia.class).setFirstResult(comecandoEm)
				.setParameter("feed", feed).setMaxResults(quantas)
				.getResultList();

		long contagem = contarNoticiasRelacionadasA(feed);

		return new NewsAndTheirCount(noticias, contagem);
	}

	private long contarNoticiasRelacionadasA(Feed feed) {
		String query = "SELECT COUNT(noticia) FROM Noticia AS noticia WHERE noticia.feed = :feed";

		return entityManager.createQuery(query, Long.class)
				.setParameter("feed", feed).getSingleResult();
	}

	public Map<CategoryAndQuantity, List<Noticia>> agruparUltimasNoticiasSegundoSuasCategorias(
			int quantasPorGrupo) {
		List<CategoryAndQuantity> categoriasESuasQuantidades = buscarAsCategoriasComMaisNoticias();
		return categoriasESuasQuantidades.stream().collect(
				toMap(identity(),
						categoryAndQuantity -> buscarPorCategoria(
								categoryAndQuantity.getCategory(),
								quantasPorGrupo)));
	}

	private List<CategoryAndQuantity> buscarAsCategoriasComMaisNoticias() {
		String query = "SELECT noticia.feed.categoria AS categoria, COUNT(noticia) AS total "
				+ "FROM Noticia AS noticia WHERE noticia.feed.categoria = categoria "
				+ "GROUP BY noticia.feed.categoria ORDER BY total DESC";

		@SuppressWarnings("unchecked")
		List<Object[]> results = entityManager.createQuery(query)
				.setMaxResults(10).getResultList();

		return results.stream().map(tupla -> {
			String categoria = (String) tupla[0];
			long quantidade = (long) tupla[1];
			return new CategoryAndQuantity(categoria, quantidade);
		}).collect(toList());
	}

	public List<Noticia> buscarPorCategoria(String categoria, int quantas) {
		String query = "SELECT noticia FROM Noticia noticia "
				+ "WHERE noticia.feed.categoria = :categoria "
				+ "ORDER BY noticia.dataDePublicacao DESC, noticia.titulo";

		return entityManager.createQuery(query, Noticia.class)
				.setParameter("categoria", categoria).setMaxResults(quantas)
				.getResultList();
	}

	public NewsAndTheirCount buscarNoticiasPorPalavraChaveECategoria(
			String palavraChave, String categoria, int quantas, int comecandoEm) {
		return null;
	}

	public NewsAndTheirCount buscarNoticiasPorPalavraChaveEFeed(
			String palavraChave, Long feed, int quantas, int comecandoEm) {
		return null;
	}

}
