package com.meujornal.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import static br.com.caelum.vraptor.view.Results.*;

import com.meujornal.infrastructure.persistence.dao.UsuariosDAO;
import com.meujornal.models.usuarios.Usuario;

@Controller
public class UsuariosController {

	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private UsuariosDAO usuariosDAO;

	// Redireciona à página de entrada
	@Get("/entrar")
	public void entrar() {
	}

	// Redireciona à página de registro
	@Get("/registrar")
	public void registrar() {

	}

	// Redireciona à página de registro e registra um novo usuário
	@Post("/registrar")
	public void registrar(@Valid Usuario usuario) {
		validator.onErrorRedirectTo(this).registrar();
		usuariosDAO.salvar(usuario);
		result.include("usuario", usuario);
		result.redirectTo(HomeController.class).index();
	}

	// Checa se o usuário já existe
	@Get("/registrar/checar-usuario")
	@Consumes("application/json")
	public void checarSeOUsuarioExiste(String q) {
		boolean valido = !usuariosDAO
				.jaExisteUmUsuarioComNomeDeUsuarioOuEmail(q);
		result.use(json()).withoutRoot().from(valido).serialize();
	}

}
