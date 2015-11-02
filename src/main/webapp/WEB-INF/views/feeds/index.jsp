<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${feed.titulo}(${feed.categoria})</title>
</head>
<body>
	<div class="col-md-12">
		<h2>${feed.titulo}(${feed.categoria})</h2>
		<p>${feed.descricao}</p>

		<form id="busca-feeds-form" role="form" class="form-horizontal"
			method="GET"
			action="${pageContext.request.contextPath}/feeds/${feed.id}/pesquisa">
			<input type="hidden" name="page" value="1" />
			<div class="form-group">
				<label for="busca" class="control-label col-md-3">Busca</label>
				<div class="col-md-6">
					<input id="busca" name="palavraChave" class="form-control"
						type="search" value="${palavraChave}" required />
				</div>
				<div class="btn-group">
					<button class="btn btn-primary" type="submit">
						<i class="glyphicon glyphicon-search"></i> Buscar
					</button>
				</div>
			</div>
		</form>

		<c:choose>
			<c:when test="${isSearching}">
				<h3>Resultados da Pesquisa</h3>

				<c:if test="${results.totalOfResultsFound > 0}">
					<p class="text-success">
						Exibindo resultados para <strong>${palavraChave}</strong>.
					</p>
				</c:if>

				<c:if test="${results.totalOfResultsFound == 0}">
					<p class="text-info">
						Sua pesquisa por <strong>${palavraChave}</strong> não encontrou
						nenhum resultado. Sugestão:
					</p>
					<ul>
						<li>Verifique a ortografia do termo digitado.</li>
						<li>Digite uma única palavra-chave. E.g. <strong>educação</strong>.
						</li>
						<li>Informe uma palavra-chave mais genérica.</li>
					</ul>
				</c:if>
			</c:when>
			<c:otherwise>
				<h3>Últimas Notícias</h3>
			</c:otherwise>
		</c:choose>

		<c:if test="${results.totalOfResultsFound > 0}">
			<ul class="list-group">
				<c:forEach var="noticia" items="${results.news}" varStatus="loop">
					<li class="list-group-item"><article>
							<header>
								<h4>${page * newsPerPage - (newsPerPage - 1) + loop.index}.<a
										href="${noticia.link}" target="_blank">${noticia.titulo}</a>
								</h4>
								<p>${noticia.dataDePublicacao}</p>
							</header>
							<p>${noticia.descricao}</p>
						</article></li>
				</c:forEach>
			</ul>

			<c:if test="${results.lastPage > 1}">
				<c:choose>
					<c:when test="${!isSearching}">
						<nav>
							<ul class="pagination">
								<c:if test="${page != 1}">
									<li><a
										href="${pageContext.request.contextPath}/feeds/${feed.id}?page=${page - 1}">Anterior</a>
								</c:if>
								<c:forEach begin="${1}" end="${results.lastPage}"
									varStatus="loop">
									<c:choose>
										<c:when test="${loop.index == page}">
											<li class="active">${loop.index}</li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath}/feeds/${feed.id}?page=${loop.index}">${loop.index}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${page != results.lastPage}">
									<li><a
										href="${pageContext.request.contextPath}/feeds/${feed.id}?page=${page + 1}">Próxima</a></li>
								</c:if>
							</ul>
						</nav>
					</c:when>
					<c:otherwise>
						<nav>
							<ul class="pagination">
								<c:if test="${page != 1}">
									<li><a
										href="${pageContext.request.contextPath}/feeds/${feed.id}/pesquisa?palavraChave=${palavraChave}&page=${page - 1}">Anterior</a>
								</c:if>
								<c:forEach begin="${1}" end="${results.lastPage}"
									varStatus="loop">
									<c:choose>
										<c:when test="${loop.index == page}">
											<li class="active">${loop.index}</li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath}/feeds/${feed.id}/pesquisa?palavraChave=${palavraChave}&page=${loop.index}">${loop.index}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${page != results.lastPage}">
									<li><a
										href="${pageContext.request.contextPath}/feeds/${feed.id}/pesquisa?palavraChave=${palavraChave}&page=${page + 1}">Próxima</a></li>
								</c:if>
							</ul>
						</nav>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:if>
	</div>
</body>
</html>