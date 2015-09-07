package com.meujornal.infrastructure.shared;

import static br.com.caelum.vraptor.quartzjob.http.DefaultHttpRequestExecutor.VRAPTOR_QUARTZ_KEY;

import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.environment.Property;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.quartzjob.QuartzJobSecurityInterceptor;

@Intercepts
@Specializes
public class CustomQuartzJobSecurityInterceptor extends
		QuartzJobSecurityInterceptor {

	@Inject
	@Property(defaultValue = "unsecured", value = VRAPTOR_QUARTZ_KEY)
	private String securityKey;

	@Override
	@AroundCall
	public void verifySecurity(SimpleInterceptorStack stack) {
		System.out.println("Hello " + securityKey);
		stack.next();
	}

}
