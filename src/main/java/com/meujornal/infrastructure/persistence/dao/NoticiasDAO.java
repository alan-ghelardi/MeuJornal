package com.meujornal.infrastructure.persistence.dao;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import com.meujornal.infrastructure.persistence.common.CategoryAndQuantity;
import com.meujornal.infrastructure.persistence.common.SearchResults;
import com.meujornal.models.noticias.Feed;
import com.meujornal.models.noticias.Noticia;

@RequestScoped
public class NoticiasDAO {

	private final EntityManager entityManager;
	private final CriteriaBuilder cb;

	/**
	 * @deprecated CDI eyes only.
	 */
	NoticiasDAO() {
		this.entityManager = null;
		this.cb = null;
	}

	@Inject
	public NoticiasDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.cb = entityManager.getCriteriaBuilder();
	}

	public void salvar(Noticia noticia) {
		entityManager.persist(noticia);
	}

	public SearchResults buscarTodasRelacionadasA(Feed feed, int comecandoEm,
			int quantas) {
		String query = "SELECT noticia FROM Noticia AS noticia WHERE noticia.feed = :feed ORDER BY noticia.dataDePublicacao DESC";

		List<Noticia> noticias = entityManager
				.createQuery(query, Noticia.class).setFirstResult(comecandoEm)
				.setParameter("feed", feed).setMaxResults(quantas)
				.getResultList();

		long contagem = contarNoticiasRelacionadasA(feed);

		return new SearchResults(noticias, contagem);
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

	public SearchResults buscarNoticiasPorPalavraChaveECategoria(
			String palavraChave, String categoria, int comecandoEm, int quantas) {
		return efetuarPesquisa(palavraChave, comecandoEm, quantas,
				(noticia) -> {
					if (!isNullOrEmpty(categoria))
						return cb.equal(noticia.get("feed").get("categoria"),
								categoria);
					return null;
				});
	}

	private SearchResults efetuarPesquisa(String palavraChave, int comecandoEm,
			int quantas, Function<Root<Noticia>, Expression<Boolean>> filtro) {
		if (isNullOrEmpty(palavraChave)) {
			return SearchResults.NONE;
		}

		palavraChave = "%" + palavraChave.toLowerCase() + "%";
		CriteriaQuery<Noticia> query = cb.createQuery(Noticia.class);
		Root<Noticia> noticia = rootFor(query);

		Expression<Boolean> predicado = construirPredicadoPara(noticia,
				palavraChave, filtro);

		List<Noticia> noticias = entityManager
				.createQuery(query.where(predicado))
				.setFirstResult(comecandoEm).setMaxResults(quantas)
				.getResultList();

		Long total = contagemTotalDeResultadosPara(predicado);

		return new SearchResults(noticias, total);
	}

	private Expression<Boolean> construirPredicadoPara(Root<Noticia> noticia,
			String palavraChave,
			Function<Root<Noticia>, Expression<Boolean>> filtro) {
		Expression<Boolean> predicado = cb.or(
				cb.like(cb.lower(noticia.get("titulo")), palavraChave),
				cb.like(cb.lower(noticia.get("descricao")), palavraChave));

		Expression<Boolean> resultadoDoFiltro = filtro.apply(noticia);

		if (resultadoDoFiltro != null) {
			predicado = cb.and(predicado, filtro.apply(noticia));
		}
		return predicado;
	}

	private <T> Root<Noticia> rootFor(CriteriaQuery<T> query) {
		Root<Noticia> noticia = query.from(Noticia.class);
		noticia.alias("n");
		return noticia;
	}

	private Long contagemTotalDeResultadosPara(Expression<Boolean> predicate) {
		CriteriaQuery<Long> countingQuery = cb.createQuery(Long.class);
		Root<Noticia> not = rootFor(countingQuery);
		countingQuery.select(cb.count(not)).where(predicate);
		return entityManager.createQuery(countingQuery).getSingleResult();
	}

	public SearchResults buscarNoticiasPorPalavraChaveEFeed(
			String palavraChave, Long idDoFeed, int comecandoEm, int quantas) {
		return efetuarPesquisa(
				palavraChave,
				comecandoEm,
				quantas,
				(noticia) -> {
					if (idDoFeed != null)
						return cb
								.equal(noticia.get("feed").get("id"), idDoFeed);
					return null;
				});
	}

	public SearchResults buscarNoticiasRelacionadasAsSeguintesPalavras(
			List<String> palavrasDeInteresse, int comecandoEm, int quantas) {
		if (palavrasDeInteresse.size() == 0) {
			return SearchResults.NONE;
		}

		CriteriaQuery<Noticia> query = cb.createQuery(Noticia.class);
		Root<Noticia> noticia = rootFor(query);

		Expression<Boolean> predicado = construirPredicadoComBaseEm(
				palavrasDeInteresse, noticia);

		List<Noticia> noticias = entityManager
				.createQuery(query.where(predicado))
				.setFirstResult(comecandoEm).setMaxResults(quantas)
				.getResultList();

		Long total = contagemTotalDeResultadosPara(predicado);

		return new SearchResults(noticias, total);

	}

	private Expression<Boolean> construirPredicadoComBaseEm(
			List<String> palavrasDeInteresse, Root<Noticia> noticia) {
		Expression<Boolean> predicadoFinal = null;

		for (String palavra : palavrasDeInteresse) {
			palavra = ("%" + palavra + "%").toLowerCase();
			Expression<Boolean> predicadoAtual = cb.or(
					cb.like(cb.lower(noticia.get("titulo")), palavra),
					cb.like(cb.lower(noticia.get("descricao")), palavra));

			if (predicadoFinal != null) {
				predicadoAtual = cb.or(predicadoAtual, predicadoFinal);
			}

			predicadoFinal = predicadoAtual;
		}

		return predicadoFinal;
	}

}
