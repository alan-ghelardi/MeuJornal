package com.meujornal.infrastructure.vraptor;

import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.view.DefaultPathResolver;

/**
 * Sobrescreve as convenções do VRaptor para a resolução das views a partir do
 * diretório /WEB-INF/views.
 * 
 * @author Alan Ghelardi
 *
 */
@Specializes
class CustomPathResolver extends DefaultPathResolver {

	// Gera um novo resolvedor de caminhos para as views
	@Inject
	protected CustomPathResolver(FormatResolver resolver) {
		super(resolver);
	}

	// Retorna o prefixo que deve ser considerado na resolução de caminhos
	@Override
	protected String getPrefix() {
		return "/WEB-INF/views/";
	}

}
