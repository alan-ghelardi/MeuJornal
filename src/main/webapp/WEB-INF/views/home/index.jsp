<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<body>
	<div class="col-md-12">
		<h2>Entre no MeuJornal.com</h2>

		<c:if test="${usuario != null}">
			<div class="alert alert-success">
				<p>${usuario.nome}, seja bem-vindo(a)ao Meu Jornal! Informe suas
					credenciais abaixo para acessar sua conta.</p>
			</div>
		</c:if>

		<form role="form" class="form-horizontal" method="POST">
			<div class="form-group">
				<label for="username" class="control-label col-md-2">Nome de
					usuário ou e-mail</label>
				<div class="col-md-10">
					<input id="username" name="" class="form-control" type="text"
						value="${usuario.nomeDeUsuario }" required />
				</div>
			</div>

			<div class="form-group">
				<label for="password" class="control-label col-md-2">Senha</label>
				<div class="col-md-10">
					<input id="password" name="" class="form-control" type="password"
						required />
				</div>
			</div>

			<div class="btn-group">
				<button class="btn btn-primary">
					<i class="glyphicon glyphicon-log-in"></i> Entrar
				</button>
			</div>
		</form>

		<p>
			Ainda não tem uma conta? <a
				href="${linkTo[UsuariosController].registrar}">Registre-se</a> agora
			mesmo. É simples, rápido e gratuito.
		</p>
	</div>
</body>
</html>