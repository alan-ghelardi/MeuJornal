/**
 * Configura o processo de validação client-side da página de criação de novos
 * usuários.
 */
(function($) {

	$(function() {

		$("#registrar-usuarios-form").validate({

			submitHandler : function(form) {
				form.submit();
			},

			rules : {
				"usuario.nome" : {
					required : true,
					maxlength : 60
				},
				"usuario.nomeDeUsuario" : {
					required : true,
					maxlength : 30,
					pattern : /^(?=.*?[a-z]{3,})[a-z0-9\-_]+$/
				},
				"usuario.senha" : {
					required : true,
					pattern : /^(?=.{8,16}$)(?=.*?[A-Z])(?=.*?[a-z])(?=.*?\d).*/
				},
				"confirmacaoDaSenha" : {
					required : true,
					equalTo : "#password"
				},
				"usuario.email" : {
					required : true,
					email : true
				},
				"confirmacaoDoEmail" : {
					required : true,
					equalTo : "#email"
				},
				"usuario.perguntaDeSeguranca" : {
					required : true
				},
				"usuario.respostaDaPerguntaDeSeguranca" : {
					required : true
				}
			},

			messages : {
				"usuario.nome" : {
					required : "Por favor, informe seu nome.",
					maxlength : "Valor longo demais. O nome deve ter no máximo {0} caracteres."
				},
				"usuario.nomeDeUsuario" : {
					required : "Por favor, escolha seu nome de usuário.",
					maxlength : "Valor longo demais. O nome de usuário deve ter no máximo {0} caracteres.",
					pattern : "Nome de usuário inválido."
				},
				"usuario.senha" : {
					required : "Por favor, escolha uma senha",
					pattern : "Senha inválida."
				},
				"confirmacaoDaSenha" : {
					required : "Por favor, confirme sua senha.",
					equalTo : "As senhas digitadas não correspondem."
				},
				"usuario.email" : {
					required : "Por favor, informe seu e-mail",
					email : "Endereço de e-mail inválido."
				},
				"confirmacaoDoEmail" : {
					required : "Por favor, digite seu e-mail novamente",
					email : "Por favor, forneça um endereço de e-mail válido.",
					equalTo : "Os endereços de e-mail não correspondem."
				},
				"usuario.perguntaDeSeguranca" : {
					required : "Por favor, selecione uma pergunta de segurança."
				},
				"usuario.respostaDaPerguntaDeSeguranca" : {
					required : "Por favor, forneça a resposta para sua pergunta de segurança."
				}
			}
		});

	});

}(jQuery));
