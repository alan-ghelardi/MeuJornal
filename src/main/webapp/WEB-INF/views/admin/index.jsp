<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Administração</title>
</head>
<body>
	<div class="col-md-12">
		<strong>Você está aqui</strong>
		<ol class="breadcrumb">
			<li class="active">Administração</li>
		</ol>

		<h2>Administração</h2>

		<p>Esta é a página de administração do MeuJornal.com. A partir
			desta página, você, administrador, poderá criar, editar, visualizar
			ou remover os Feeds RSS que serão utilizados para o consumo de
			notícias.</p>

		<c:if test="${mensagem != null}">
			<div class="alert alert-success">
				<p>${mensagem}</p>
			</div>
		</c:if>

		<div class="btn-group">
			<a role="button" class="btn btn-sm btn-link"
				href="${pageContext.request.contextPath}/admin/novo-feed"><i
				class="glyphicon glyphicon-leaf"></i> Novo Feed</a>
		</div>

		<c:choose>
			<c:when test="${feeds.size() == 0}">
				<p class="text-info">Ainda não existem feeds registrados no
					MeuJornal.com.</p>
			</c:when>

			<c:otherwise>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Título</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="feed" items="${feeds}">
							<tr>
								<td><a
									href="${linkTo[AdminController].visualizar(feed.id)}">${feed.titulo}
										(${feed.categoria})</a></td>
								<td><a class="btn btn-sm btn-link" role="button"
									href="${linkTo[AdminController].editarFeed(feed.id)}"><i
										class="glyphicon glyphicon-edit"></i> Editar</a></td>
								<td>
									<form action="${linkTo[AdminController].removerFeed(feed.id)}"
										method="POST">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input type="hidden"
											name="_method" value="DELETE" />
										<button class="btn btn-sm btn-danger">
											<i class="glyphicon glyphicon-remove"></i> Remover
										</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>