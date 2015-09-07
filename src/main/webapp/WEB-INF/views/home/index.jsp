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
							<li class="list-group-item"><a href="#">${feed.titulo}
									(${feed.categoria})</a></li>
						</c:forEach>
					</ul>
				</nav>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="col-md-6">
		<h2>Últimas Notícias</h2>

		<c:forEach var="noticia" items="${noticias}">
			<ol class="list-group">
				<li class="list-group-item">
					<article>
						<header>
							<h3>
								<a href="${noticia.link}">${noticia.titulo}</a>
							</h3>
							<p>${noticia.dataDePublicacao}-${noticia.feed.titulo}</p>
						</header>
						<p>${noticia.descricao}</p>
					</article>
				</li>
			</ol>
		</c:forEach>
	</div>
</body>
</html>