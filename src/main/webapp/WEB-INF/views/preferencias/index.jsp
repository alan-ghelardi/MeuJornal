<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Preferências</title>
<script src="${pageContext.request.contextPath}/js/knockout.js"></script>
<script
	src="${pageContext.request.contextPath}/js/PreferencesViewModel.js"></script>
<script>
	$(function() {
		var viewModel = new PreferencesViewModel(JSON.parse('${keywords}'));

		ko.applyBindings(viewModel);
	});
</script>
</head>
<body>
	<div class="col-md-12">
		<h2>Minhas Preferências</h2>

		<button class="btn btn-primary"
			data-bind="click: togglePreferences, attr: {'aria-expanded': isExpanded}">
			<i class="glyphicon glyphicon-plus-sign"
				data-bind="visible: !isVisible()"></i> <i
				class="glyphicon glyphicon-minus-sign"
				data-bind="visible: isVisible"></i>
		</button>

		<form id="preferences-form" role="form" class="form-horizontal"
			method="POST"
			action="${pageContext.request.contextPath}/preferencias"
			novalidate="novalidate" data-bind="visible: isVisible">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<!-- ko foreach: keywords -->
			<input type="hidden"
				data-bind="attr: {name: 'keywords[' + $index() + ']', value: $data}" />
			<!-- /ko -->
			<div class="form-group">
				<div class="col-md-12">
					<ul class="list-group" data-bind="foreach: keywords">
						<li class="list-group-item"><span data-bind="text: $data"></span>

							<button type="button" class="btn btn-xs"
								data-bind="click: $parent.removeKeyword.bind($parent)">
								<i class="glyphicon glyphicon-remove"> Remover</i>
							</button></li>
					</ul>
				</div>
			</div>

			<div class="form-group">
				<label for="keyword" class="control-label col-md-3">Palavra-chave</label>
				<div class="col-md-9">
					<div class="input-group">
						<input id="keyword" name="keyword" class="form-control"
							type="text" data-bind="value: newKeyword, valueUpdate: 'keydown'" />
						<span class="input-group-btn">
							<button type="button" class="btn btn-xs"
								data-bind="click: addKeyword, enable: !!newKeyword()">
								<i class="glyphicon glyphicon-plus"></i>
							</button>
						</span>
					</div>
				</div>
			</div>
			<div class="btn-group">
				<button type="submit" class="btn btn-primary"
					data-bind="enable: canSubmitChanges">
					<i class="glyphicon glyphicon-save"></i> Salvar
				</button>
			</div>
		</form>

		<c:choose>
			<c:when test="${results.totalOfResultsFound == 0}">
				<p class="text-info">Você ainda não possui notícias relacionadas
					à palavras de interesse.</p>
			</c:when>
			<c:otherwise>
				<h3>Notícias (${results.totalOfResultsFound})</h3>
				<ul class="list-group">
					<c:forEach var="noticia" items="${results.news}" varStatus="loop">
						<li class="list-group-item"><article>
								<header>
									<h4>${page * newsPerPage - (newsPerPage - 1) + loop.index}.<a
											href="${noticia.link}" target="_blank">${noticia.titulo}</a>
									</h4>
									<p>${noticia.feed.titulo}-${noticia.dataDePublicacao}</p>
								</header>
								<p>${noticia.descricao}</p>
							</article></li>
					</c:forEach>
				</ul>

				<c:if test="${results.lastPage > 1}">
					<nav>
						<ul class="pagination">
							<c:if test="${page != 1}">
								<li><a
									href="${pageContext.request.contextPath}/preferencias?page=${page - 1}">Anterior</a>
							</c:if>
							<c:forEach begin="${1}" end="${results.lastPage}"
								varStatus="loop">
								<c:choose>
									<c:when test="${loop.index == page}">
										<li class="active">${loop.index}</li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath}/preferencias?page=${loop.index}">${loop.index}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${page != results.lastPage}">
								<li><a
									href="${pageContext.request.contextPath}/preferencias?page=${page + 1}">Próxima</a></li>
							</c:if>
						</ul>
					</nav>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>