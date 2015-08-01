<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<li><a href="#">Sobre</a></li>
				</ul>
			</div>
		</div>
	</nav>
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