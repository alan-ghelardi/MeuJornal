<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<body>
	<div class="col-md-6">
		<h2>Navegue por nossas fontes de informação</h2>
		<c:choose>
			<c:when test="${feeds.size() == 0}">
				<p class="text-info">Não há feeds registrados no momento.</p>
			</c:when>
			<c:otherwise>
				<nav>
					<ul class="list-group">
						<c:forEach var="feed" items="${feeds}">
							<li class="list-group-item"><a
								href="${pageContext.request.contextPath}/feeds/${feed.id}">${feed.titulo}
									(${feed.categoria})</a></li>
						</c:forEach>
					</ul>
				</nav>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="col-md-6">
		<form id="busca-form" role="form" class="form-horizontal" method="GET"
			action="${pageContext.request.contextPath}/pesquisa">
			<input type="hidden" name="page" value="1" />

			<div class="form-group">
				<label for="busca" class="control-label col-md-3">Busca</label>
				<div class="col-md-6">
					<input id="busca" name="palavraChave" class="form-control"
						type="search" value="${palavraChave}" required />
				</div>
			</div>

			<div class="form-group">
				<label for="categories" class="control-label col-md-3">Categoria</label>

				<div class="col-md-6">
					<select id="categories" name="categoria" class="form-control">
						<option value="">Todas as categorias</option>

						<c:forEach var="categoriaExistente" items="${categorias}">
							<option value="${categoriaExistente}"
								${categoriaExistente == categoria ? 'selected' : ''}>${categoriaExistente}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="btn-group centered">
				<button class="btn btn-primary" type="submit">
					<i class="glyphicon glyphicon-search"></i> Buscar
				</button>
			</div>
		</form>

		<c:choose>
			<c:when test="${resultados != null}">
				<h3>Resultados da Pesquisa</h3>

				<c:if test="${resultados.totalOfResultsFound == 0}">
					<p class="text-info">
						Sua pesquisa por <strong>${palavraChave}</strong>
						<c:if test="${not empty categoria}">
						 na categoria <strong>${categoria }</strong>
						</c:if>
						não encontrou nenhum resultado. Sugestão:
					</p>
					<ul>
						<li>Verifique a ortografia do termo digitado.</li>
						<li>Digite uma única palavra-chave. E.g. <strong>educação</strong>.
						</li>
						<li>Informe uma palavra-chave mais genérica.</li>
						<li>Experimente efetuar a pesquisa em todas as categorias.</li>
					</ul>
				</c:if>

				<c:if test="${resultados.totalOfResultsFound > 0}">
					<p class="text-success">
						Encontradas ${resultados.totalOfResultsFound} notícias com a
						palavra-chave <strong>${palavraChave}</strong>
						<c:if test="${not empty categoria}">
						 na categoria <strong>${categoria }</strong>
						</c:if>

						<c:forEach var="noticia" items="${resultados.news}">
							<article>
								<header>
									<h4>${page * newsPerPage - (newsPerPage - 1)}.<a
											href="${noticia.link}" target="_blank">${noticia.titulo}</a>
									</h4>
									<p>${noticia.dataDePublicacao}-${noticia.feed.titulo}
										(${noticia.feed.categoria})</p>
								</header>
								<p>${noticia.descricao}</p>
							</article>
						</c:forEach>

						<c:if test="${resultados.lastPage > 1}">
							<nav>
								<ul class="pagination">
									<c:if test="${page != 1}">
										<li><a
											href="${pageContext.request.contextPath}/pesquisa?palavraChave=${palavraChave}&categoria=${categoria}&page=${page - 1}">Anterior</a>
									</c:if>
									<c:forEach begin="${1}" end="${resultados.lastPage}"
										varStatus="loop">
										<c:choose>
											<c:when test="${loop.index == page}">
												<li class="active">${loop.index}</li>
											</c:when>
											<c:otherwise>
												<li><a
													href="${pageContext.request.contextPath}/pesquisa?palavraChave=${palavraChave}&categoria=${categoria}&page=${loop.index}">${loop.index}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${page != resultados.lastPage}">
										<li><a
											href="${pageContext.request.contextPath}/pesquisa?palavraChave=${palavraChave}&categoria=${categoria}&page=${page + 1}">Próxima</a></li>
									</c:if>
								</ul>
							</nav>
						</c:if>
				</c:if>
			</c:when>

			<c:otherwise>
				<h2>Últimas Notícias</h2>

				<c:forEach var="group" items="${groups.entrySet()}">
					<h3>
						<a href="#">${group.key.category} (${group.key.quantity})</a>
					</h3>

					<ul class="list-group">
						<c:forEach var="noticia" items="${group.value}">
							<li class="list-group-item">
								<article>
									<header>
										<h4>
											<a href="${noticia.link}" target="_blank">${noticia.titulo}</a>
										</h4>
										<p>${noticia.dataDePublicacao}-${noticia.feed.titulo}
											(${noticia.feed.categoria})</p>
									</header>
									<p>${noticia.descricao}</p>
								</article>
							</li>
						</c:forEach>
					</ul>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>