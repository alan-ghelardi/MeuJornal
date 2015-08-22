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

		<ul class="list-group">
			<li class="list-group-item">
				<article>
					<header>
						<h3>Lorem ipsum</h3>
						<p>Publicado em 09/07/2015 no Jornal XYZ</p>
					</header>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
						do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
						enim ad minim veniam, quis nostrud exercitation ullamco laboris
						nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
						reprehenderit in voluptate velit...</p>
					<p>
						<a href="#">Ler completo</a>
					</p>
				</article>
			</li>

			<li class="list-group-item">
				<article>
					<header>
						<h3>Lorem ipsum</h3>
						<p>Publicado em 09/07/2015 no Jornal XYZ</p>
					</header>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
						do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
						enim ad minim veniam, quis nostrud exercitation ullamco laboris
						nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
						reprehenderit in voluptate velit...</p>
					<p>
						<a href="#">Ler completo</a>
					</p>
				</article>
			</li>

			<li class="list-group-item">
				<article>
					<header>
						<h3>Lorem ipsum</h3>
						<p>Publicado em 09/07/2015 no Jornal XYZ</p>
					</header>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
						do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
						enim ad minim veniam, quis nostrud exercitation ullamco laboris
						nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
						reprehenderit in voluptate velit...</p>
					<p>
						<a href="#">Ler completo</a>
					</p>
				</article>
			</li>

			<li class="list-group-item">
				<article>
					<header>
						<h3>Lorem ipsum</h3>
						<p>Publicado em 09/07/2015 no Jornal XYZ</p>
					</header>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
						do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
						enim ad minim veniam, quis nostrud exercitation ullamco laboris
						nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
						reprehenderit in voluptate velit...</p>
					<p>
						<a href="#">Ler completo</a>
					</p>
				</article>
			</li>

			<li class="list-group-item">
				<article>
					<header>
						<h3>Lorem ipsum</h3>
						<p>Publicado em 09/07/2015 no Jornal XYZ</p>
					</header>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
						do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
						enim ad minim veniam, quis nostrud exercitation ullamco laboris
						nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
						reprehenderit in voluptate velit...</p>
					<p>
						<a href="#">Ler completo</a>
					</p>
				</article>
			</li>

			<li class="list-group-item">
				<article>
					<header>
						<h3>Lorem ipsum</h3>
						<p>Publicado em 09/07/2015 no Jornal XYZ</p>
					</header>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
						do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
						enim ad minim veniam, quis nostrud exercitation ullamco laboris
						nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
						reprehenderit in voluptate velit...</p>
					<p>
						<a href="#">Ler completo</a>
					</p>
				</article>
			</li>
		</ul>
	</div>
</body>
</html>