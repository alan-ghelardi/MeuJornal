package com.meujornal.infrastructure.shared;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.jpa.EntityManagerFactoryCreator;

import com.meujornal.infrastructure.persistence.settings.DatabaseSettings;

@Specializes
public class CustomEntityManagerFactoryCreator extends
		EntityManagerFactoryCreator {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomEntityManagerFactoryCreator.class);

	// Fábrica do gerenciador de entidades
	@Override
	@ApplicationScoped
	@Produces
	public EntityManagerFactory getEntityManagerFactory() {
		DatabaseSettings settings = new DatabaseSettings();

		Map<String, String> overrides = new HashMap<>();
		overrides
				.put("javax.persistence.jdbc.driver", settings.getDriverName());
		overrides.put("javax.persistence.jdbc.url", settings.getJdbcUrl());
		overrides.put("javax.persistence.jdbc.user", settings.getUsername());
		overrides
				.put("javax.persistence.jdbc.password", settings.getPassword());
		overrides.put("hibernate.dialect", settings.getHibernateDialect());

		logger.debug(
				"Creating EntityManagerFactory with following persistence settings {}",
				overrides);

		return Persistence.createEntityManagerFactory("default", overrides);
	}

	// Descarta a fábrica
	@Override
	public void destroy(@Disposes EntityManagerFactory factory) {
		super.destroy(factory);
	}

}
