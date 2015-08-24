package com.meujornal.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

import com.meujornal.infrastructure.persistence.dao.FeedsDAO;

@Controller
public class HomeController {

	@Inject
	private Result result;
	@Inject
	private FeedsDAO feedsDAO;

	// Indexa todos os feeds
	@Get("/")
	public void index() {
		result.include("feeds", feedsDAO.buscarTodos());
	}

}
