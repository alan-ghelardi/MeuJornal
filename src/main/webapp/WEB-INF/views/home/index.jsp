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
	
	<h2>Últimas Notícias</h2>
	<br>
	<div class="col-md-6">
		<form id="busca-form" role="form" class="form-horizontal"
			method="POST"
			action="${pageContext.request.contextPath}"
			novalidate="novalidate">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input name="busca.id" type="hidden"
				value="${busca.id}" />


			<div class="form-group">
				<label for="busca" class="control-label col-md-3">Busca</label>
				<div class="col-md-6">
					<input id="busca" name="home.busca" class="form-control" type="url"
						aria-describedby="busca-help"
						value="${home.busca}" />
				</div>
				<div class="btn-group">
					<button class="btn btn-primary" type="submit">
					<i class="glyphicon glyphicon-search"></i>
				</button>
				</div>
			</div>
			


			<div class="form-group">
				<label for="categories" class="control-label col-md-3">Categoria</label>

				<div class="col-md-6">
					<select id="categories" name="categories" class="form-control"
						aria-describedby="category-help" data-rule-required="true"
						data-msg-required="Informe uma categoria para filtrar as notícias.">
						<c:forEach var="categoria" items="${categorias}">
							<option value="${categoria}"
								${categoria == feed.categoria ? 'selected' : ''}>${categoria}</option>
						</c:forEach>
						<option selected="selected">Todas as categorias</option>
					</select>
				</div>
			</div>
		</form>


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
	</div>
</body>
</html>