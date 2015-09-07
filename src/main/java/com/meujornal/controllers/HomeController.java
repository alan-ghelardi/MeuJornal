package com.meujornal.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

import com.meujornal.infrastructure.persistence.dao.FeedsDAO;
import com.meujornal.infrastructure.persistence.dao.NoticiasDAO;

@Controller
public class HomeController {

	@Inject
	private Result result;
	@Inject
	private FeedsDAO feedsDAO;
	@Inject
	private NoticiasDAO NoticiasDAO;

	@Get("/")
	public void index() {
		result.include("feeds", feedsDAO.buscarTodos()).include("noticias",
				NoticiasDAO.buscar(15));
	}

}
