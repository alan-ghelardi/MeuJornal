<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Meu Jornal | <sitemesh:write property="title" /></title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"
	rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
	window.baseUrl = "${pageContext.request.contextPath}";
</script>
<sitemesh:write property="head" />
</head>
<body>
	<header role="banner">
		<div class="jumbotron">
			<h1>MeuJornal.com</h1>
			<p>Acompanhe as últimas notícias do dia, pesquise por assuntos de
				interesse, compartilhe informações, receba novidades e muito mais!</p>
		</div>
	</header>
	<sec:authorize access="isAuthenticated()">
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar-links"
						aria-expanded="false">
						<span class="sr-only">Alternar navegação</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>

				<div class="collapse navbar-collapse" id="navbar-links">
					<ul class="nav navbar-nav">
						<li><a href="${linkTo[HomeController].index}"><i
								class="glyphicon glyphicon-home"></i> Home</a></li>
						<li><a href="${linkTo[SobreController].index}">Sobre</a></li>
						<sec:authorize access="hasRole('Administrator')">
							<li><a href="${linkTo[AdminController].index}">Administração</a></li>
						</sec:authorize>
					</ul>

					<form class="navbar-form navbar-left" method="POST"
						action="${pageContext.request.contextPath}/sair">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="form-group">
							<p class="form-control-static">
								Você acessou como
								<c:out value="${pageContext.request.remoteUser}"></c:out>
							</p>
						</div>

						<button type="submit" class="btn btn-sm">
							<i class="glyphicon glyphicon-log-out"></i> Sair
						</button>
					</form>
				</div>
			</div>
		</nav>
	</sec:authorize>

	<div class="container">
		<main id="main" role="main">
		<div class="row">
			<sitemesh:write property="body" />
		</div>
		</main>
		<footer role="contentinfo">
			<p>&#169; 2015 MeuJornal.com. Todos os direitos reservados.</p>
		</footer>
	</div>
</body>
</html>