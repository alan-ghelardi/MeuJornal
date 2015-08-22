<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${feed.titulo}</title>
</head>
<body>
	<div class="col-md-12">
		<strong>Você está aqui</strong>
		<ol class="breadcrumb">
			<li><a href="${linkTo[AdminController].index}">Administração</a></li>
			<li class="active">${feed.titulo}</li>
		</ol>

		<h2>${feed.titulo}</h2>

		<dl class="dl-horizontal">
			<dt>Título</dt>
			<dd>${feed.titulo}</dd>
			<dt>Link</dt>
			<dd>
				<a href="${feed.link}">${feed.link}</a>
			</dd>
			<dt>Categoria</dt>
			<dd>${feed.categoria}</dd>
			<dt>Descrição</dt>
			<dd>${feed.descricao}</dd>
		</dl>
	</div>
</body>
</html>