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
		<ul class="list-group">
			<c:forEach var="noticia" items="${noticias}">
				<li class="list-group-item"><article>
						<header>
							<h3>
								<a href="${noticia.link}" target="_blank">${noticia.titulo}</a>
							</h3>
							<p>${noticia.dataDePublicacao}-${noticia.feed.titulo}
								(${noticia.feed.categoria})</p>
						</header>
						<p>${noticia.descricao}</p>
					</article></li>
			</c:forEach>
		</ul>

	</div>
</body>
</html>