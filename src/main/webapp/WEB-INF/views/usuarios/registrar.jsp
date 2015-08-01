<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Criar Conta</title>
</head>
<body>
	<div class="col-md-12">
		<h2>Crie uma conta no MeuJornal.com</h2>
		<form role="form" class="form-horizontal" method="POST"
			action="${pageContext.request.contextPath}/registrar"
			novalidate="novalidate">
			<div class="form-group">
				<label for="name" class="control-label col-md-3">Seu nome</label>
				<div class="col-md-9">
					<input id="name" name="usuario.nome" class="form-control"
						type="text" required="required" />
				</div>
			</div>

			<div class="form-group">
				<label for="username" class="control-label col-md-3">Nome de
					usuário</label>
				<div class="col-md-9">
					<input id="username" name="usuario.nomeDeUsuario"
						class="form-control" type="text" aria-describedby="username-help"
						required="required" /> <span id="username-help"
						class="help-block">Apenas letras minúsculas (ao menos 3),
						dígitos e os caracteres hífen e/ou sublinhado. Não digite outros
						caracteres ou espaços em branco.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="password" class="control-label col-md-3">Escolha
					uma senha</label>
				<div class="col-md-9">
					<input id="password" name="usuario.senha" class="form-control"
						type="password" aria-describedby="password-help"
						required="required" /> <span id="password-help"
						class="help-block">Deve ter entre 8 e 16 caracteres e
						conter ao menos uma letra maiúscula, uma letra minúscula e um
						dígito.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="confirm-password" class="control-label col-md-3">Confirme
					sua senha</label>
				<div class="col-md-9">
					<input id="confirm-password" name="senha" class="form-control"
						type="password" required="required" />
				</div>
			</div>

			<div class="form-group">
				<label for="email" class="control-label col-md-3">E-mail</label>
				<div class="col-md-9">
					<input id="email" name="usuario.email" class="form-control"
						type="email" aria-describedby="email-help" required="required" />
					<span id="email-help" class="help-block">Informe um endereço
						de e-mail válido para que possamos contatá-lo(a) sempre que
						necessário.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="confirm-email" class="control-label col-md-3">Confirme
					seu e-mail</label>
				<div class="col-md-9">
					<input id="confirm-email" class="form-control" type="email"
						required="required" />
				</div>
			</div>

			<div class="form-group">
				<label for="security-question" class="control-label col-md-3">Escolha
					uma pergunta de segurança</label>
				<div class="col-md-9">
					<select id="security-question" class="form-control" name=""
						aria-describedby="security-question-help" required="required">
						<option value="">Selecione...</option>
						<option
							value="Qual era a profissão que você sonhava seguir quando criança?">Qual
							era a profissão que você sonhava seguir quando criança?</option>
						<option
							value="Qual era o primeiro nome do garoto ou da garota com quem você deu o primeiro beijo?">Qual
							era o primeiro nome do garoto ou da garota com quem você deu o
							primeiro beijo?</option>
						<option
							value="Qual era o primeiro nome do seu melhor amigo  (ou amiga) de infância?">Qual
							era o primeiro nome do seu melhor amigo (ou amiga) de infância?</option>
						<option
							value="Qual era a marca e a cor do seu primeiro carro? (e.g. ferrary/vermelho)">Qual
							era a marca e a cor do seu primeiro carro? (e.g.
							ferrary/vermelho)</option>
						<option value="Em que mês você se casou? (e.g. janeiro)">Em
							que mês você se casou? (e.g. janeiro)</option>
						<option
							value="Qual é o nome do bairro em que você exerceu seu primeiro emprego?">Qual
							é o nome do bairro em que você exerceu seu primeiro emprego?</option>
						<option
							value="Qual é o nome da rua em que você morou durante a faculdade?">Qual
							é o nome da rua em que você morou durante a faculdade?</option>
						<option
							value="Qual é o nome do bairro em que seus pais se conheceram?">Qual
							é o nome do bairro em que seus pais se conheceram?</option>
						<option
							value="Para qual cidade você fez sua primeira viagem de avião?">Para
							qual cidade você fez sua primeira viagem de avião?</option>
						<option
							value="Em que mês e ano nasceu seu irmão (ou irmã) mais velho? (e.g. janeiro/1990)">Em
							que mês e ano nasceu seu irmão (ou irmã) mais velho? (e.g.
							janeiro/1990)</option>
						<option
							value="Qual é o nome e o sobrenome do seu primo (ou prima) mais velho?">Qual
							é o nome e o sobrenome do seu primo (ou prima) mais velho?</option>
						<option
							value="Qual era o nome da escola em que você cursou o jardim de infância?">Qual
							era o nome da escola em que você cursou o jardim de infância?</option>
						<option value="Qual era o seu apelido de infância?">Qual
							era o seu apelido de infância?</option>
						<option value="Qual era o nome do seu primeiro bicho de pelúcia?">Qual
							era o nome do seu primeiro bicho de pelúcia?</option>
						<option
							value="Em que bairro você conheceu sua esposa, marido o companheiro (a)?">Em
							que bairro você conheceu sua esposa, marido o companheiro (a)?</option>
					</select> <span id="security-question-help" class="help-block">Certifique-se
						de escolher uma pergunta da qual apenas você saiba a resposta. Ela
						será utilizada mais tarde para ajudá-lo(a) a recuperar sua senha
						caso você a perca.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="answer" class="control-label col-md-3">Sua
					resposta</label>
				<div class="col-md-9">
					<input id="answer" name="" class="form-control" type="text"
						required="required" />
				</div>
			</div>

			<div class="btn-group">
				<button class="btn btn-primary">
					<i class="glyphicon glyphicon-save"></i> Registrar
				</button>
			</div>
		</form>
	</div>
</body>
</html>