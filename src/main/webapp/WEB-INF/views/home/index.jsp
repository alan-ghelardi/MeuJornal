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
		<h2>Últimas Notícias</h2>

		<c:forEach var="noticia" items="${noticias}" varStatus="loop">
			<ul class="list-group">
				<li class="list-group-item">
					<article>
						<header>
							<h3>${loop.index + 1}.
								<a href="${noticia.link}" target="_blank">${noticia.titulo}</a>
							</h3>
							<p>${noticia.dataDePublicacao}-${noticia.feed.titulo}
								(${noticia.feed.categoria})</p>
						</header>
						<p>${noticia.descricao}</p>
					</article>
				</li>
			</ul>
		</c:forEach>
	</div>
</body>
</html>