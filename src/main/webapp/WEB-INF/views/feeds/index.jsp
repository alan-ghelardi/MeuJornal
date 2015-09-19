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

		<h3>Últimas Notícias</h3>
		<ul class="list-group">
			<c:forEach var="noticia" items="${noticias}" varStatus="loop">
				<li class="list-group-item"><article>
						<header>
							<h3>${page * newsPerPage - (newsPerPage - 1) + loop.index}
								<a href="${noticia.link}" target="_blank">${noticia.titulo}</a>
							</h3>
							<p>${noticia.dataDePublicacao}-${noticia.feed.titulo}
								(${noticia.feed.categoria})</p>
						</header>
						<p>${noticia.descricao}</p>
					</article></li>
			</c:forEach>
		</ul>
		<nav>
			<ul class="pagination">
				<c:if test="${page != 1}">
					<li><a
						href="${pageContext.request.contextPath}/feeds/${feed.id}?page=${page - 1}">Anterior</a>
				</c:if>
				<c:forEach begin="${1}" end="${lastPage}" varStatus="loop">
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
				<c:if test="${page != lastPage}">
					<li><a
						href="${pageContext.request.contextPath}/feeds/${feed.id}?page=${page + 1}">Próxima</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</body>
</html>