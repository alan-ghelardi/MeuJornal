package com.meujornal.infrastructure.vraptor;

import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.view.DefaultPathResolver;

@Specializes
class CustomPathResolver extends DefaultPathResolver {

	@Inject
	protected CustomPathResolver(FormatResolver resolver) {
		super(resolver);
	}

	@Override
	protected String getPrefix() {
		return "WEB-INF/views/";
	}

}
