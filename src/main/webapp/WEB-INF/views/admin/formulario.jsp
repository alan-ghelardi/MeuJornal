<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title><c:if test="${feed.id != null}">Editar ${feed.titulo}</c:if>
	<c:if test="${feed.id == null}">Criar novo feed de notícias</c:if></title>
<script
	src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/additional-methods.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/global-validation-settings.js"></script>
<script>
	$(function() {
		$('#feeds-form').validate({
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>
</head>
<body>
	<div class="col-md-12">
		<strong>Você está aqui</strong>
		<ol class="breadcrumb">
			<li><a href="${linkTo[AdminController].index}">Administração</a></li>
			<li class="active"><c:if test="${feed.id != null}">Editar ${feed.titulo}</c:if>
				<c:if test="${feed.id == null}">Criar novo feed de notícias</c:if></li>
		</ol>

		<h2>
			<c:if test="${feed.id != null}">Editar ${feed.titulo}</c:if>
			<c:if test="${feed.id == null}">Criar novo feed de notícias</c:if>
		</h2>

		<form id="feeds-form" role="form" class="form-horizontal"
			method="POST"
			action="${pageContext.request.contextPath}/admin/feeds${feed.id == null ? '' : '/'}${feed.id}"
			novalidate="novalidate">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input name="feed.id" type="hidden"
				value="${feed.id}" />

			<div class="form-group">
				<label for="title" class="control-label col-md-3">Título</label>
				<div class="col-md-9">
					<input id="title" name="feed.titulo" class="form-control"
						type="text" data-rule-required="true" data-rule-maxlength="60"
						data-msg-required="Por favor, informe o título do Feed RSS"
						data-msg-maxlength="Valor longo demais. Por favor, informe até {0} caracteres."
						value="${feed.titulo}" />
				</div>
			</div>

			<div class="form-group">
				<label for="link" class="control-label col-md-3">Link</label>
				<div class="col-md-9">
					<input id="link" name="feed.link" class="form-control" type="url"
						aria-describedby="link-help" data-rule-required="true"
						data-rule-url="true"
						data-msg-required="Por favor, informe o link para o Feed RSS."
						data-msg-url="Link inválido. Por favor, informe uma URL válida."
						value="${feed.link}" /> <span id="link-help" class="help-block">A
						URL em que o feed RSS pode ser encontrado.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="category" class="control-label col-md-3">Categoria</label>

				<div class="col-md-9">
					<select id="category" name="feed.categoria" class="form-control"
						aria-describedby="category-help" data-rule-required="true"
						data-msg-required="Por favor, informe uma categoria para o Feed RSS.">
						<option value="">Selecione...</option>
						<option value="Cultura">Cultura</option>
						<option value="Educação">Educação</option>
						<option value="Entretenimento">Entretenimento</option>
						<option value="Esportes">Esportes</option>
						<option value="Política">Política</option>
						<option value="Variedades">Variedades</option>
					</select> <span id="category-help" class="help-block">Escolha uma das
						categorias para o portal de notícias.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="description" class="control-label col-md-3">Descrição</label>
				<div class="col-md-9">
					<textarea id="description" name="feed.descricao"
						data-rule-required="true" data-rule-maxlength="1000"
						data-msg-required="Por favor, informe uma descrição para o Feed RSS."
						data-msg-maxlength="Valor longo demais. Por favor, informe no máximo {0} caracteres."
						rows="4" cols="50">${feed.descricao}</textarea>
				</div>
			</div>

			<div class="btn-group">
				<button class="btn btn-primary" type="submit">
					<i class="glyphicon glyphicon-save"></i> Salvar
				</button>
			</div>
		</form>
	</div>
</body>
</html>