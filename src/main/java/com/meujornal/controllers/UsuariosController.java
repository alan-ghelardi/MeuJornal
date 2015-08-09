package com.meujornal.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import com.meujornal.infrastructure.persistence.UsuariosDAO;
import com.meujornal.models.usuarios.Usuario;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class UsuariosController {

	@Inject
	private Result result;
	@Inject
	private Validator validator;
	@Inject
	private UsuariosDAO usuariosDAO;

	@Get("/entrar")
	public void entrar() {
	}

	@Get("/registrar")
	public void registrar() {

	}

	@Post("/registrar")
	public void registrar(@Valid Usuario usuario) {
		validator.onErrorRedirectTo(this).registrar();
		usuariosDAO.salvar(usuario);
		result.include("usuario", usuario);
		result.redirectTo(HomeController.class).index();
	}

}
